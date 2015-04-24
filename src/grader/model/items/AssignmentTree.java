package grader.model.items;

import grader.model.errors.PercentageFormatException;
import grader.model.errors.RawScoreFormatException;
import grader.model.gradebook.Percentage;
import grader.model.gradebook.RawScore;
import grader.model.people.Name;
import grader.model.people.Student;

import javax.naming.InvalidNameException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Beta implementation of AssignmentTree. Expect bugs.
 *
 * @author Jon Amireh
 */
public class AssignmentTree
{
    private Node root;


    public AssignmentTree()
    {
        root = new Node(null);
    }

    public void addTo(Category parentCategory, Category childCategory)
    {
        if(parentCategory == null)
        {
            root.addNode(new Node(null, childCategory));
            return;
        }
        Node parentNode = findNode(parentCategory);
        parentNode.addNode(new Node(parentNode, childCategory));
    }

    public void addTo(Category parentNode, Assignment childAssignment)
    {
        if(parentNode == null)
        {
            root.addAssignment(childAssignment);
            return;
        }
        findNode(parentNode).addAssignment(childAssignment);
    }

    private Node findNode(Category node)
    {
        ArrayList<Node> nodes = root.nodes;
        Node n = null;
        for(int i = 0; i < root.nodes.size() && n == null; i++)
        {
            n = findNodeHelper(node, nodes.get(i));
        }
        return n;
    }

    private Node findNodeHelper(Category node, Node n)
    {
        ArrayList<Node> nodes = n.nodes;
        Node ni = null;
        if(n.is(node))
            return n;
        if(!nodes.isEmpty())
        {
            for (int i = 0; i < n.nodes.size() && ni == null; i++)
            {
                ni = findNodeHelper(node, nodes.get(i));
            }
            return ni;
        }

        return ni;
    }

    private double gradeCheckNode(Node node, HashMap<Assignment, RawScore> map)
    {
        ArrayList<Node> nodes = node.nodes;
        for (int i = 0; i < nodes.size(); i++)
        {
            Category c = nodes.get(i).category;
            node.total += c.weight.getValue();
        }
        ArrayList<Assignment> unweightAssignments = new ArrayList<Assignment>();
        for(Assignment a : node.assignments)
        {
            if(a.hasWeight)
            {
                node.total += a.weight.getValue();
                node.grade += a.weight.getValue() * (map.get(a).getScore()/a.rawPoints);
            }
            else
            {
                unweightAssignments.add(a);
            }
        }

        double indWeight = (1.0 - node.total)/unweightAssignments.size();
        boolean equilDistribution;
        try
        {
            equilDistribution = !node.category.uncategorizedByRawScore;
        }
        catch(NullPointerException e)
        {
            equilDistribution = false;
        }
        if(equilDistribution)
        {
            for(Assignment a : unweightAssignments)
            {
                node.grade += indWeight*(map.get(a).getScore()/a.rawPoints);
            }
        }
        else
        {
            double smallestRawScore = Double.MAX_VALUE;
            for(Assignment a : unweightAssignments)
            {
                if(a.rawPoints < smallestRawScore)
                {
                    smallestRawScore = a.rawPoints;
                }
            }
            double ref = 1.0 - node.total;
            for(Assignment a : unweightAssignments)
            {
                double relWeight = ref * (a.rawPoints/smallestRawScore);
                node.grade += relWeight * (map.get(a).getScore()/a.rawPoints);
            }
        }
        for(Node n : nodes)
        {
            n.grade += gradeCheckNode(n, map);
            node.grade += n.grade;
        }
        if(node.category == null)
        {
            return node.grade;
        }
        return node.grade * node.category.weight.getValue();
        
    }

    /**
     * per student
     * @param scores
     * @return
     */
    public Percentage calculatePercentage(HashMap<Assignment, RawScore> scores)
    {
        try
        {
            return new Percentage(gradeCheckNode(root, scores));
        }
        catch(PercentageFormatException e)
        {
            return new Percentage();
        }
    }

    public AssignmentIterator getAssignmentIterator()
    {
        return new AssignmentIterator();
    }

    //level iterator
    //return list of categories

    public static void main(String[] args)
    {
        AssignmentTree at = new AssignmentTree();
        try
        {
            Assignment finalExam = new Assignment("final", LocalDate.now(), "100", "0.3");
            at.addTo(null, finalExam);
            Category tests = new Category("tests", "0.7", false);
            at.addTo(null, tests);
            Category quizzes = new Category("quizzes", "0.2", false);
            Category midterms = new Category("midterms", "0.7", false);
            at.addTo(tests, quizzes);
            at.addTo(tests, midterms);

            Assignment m1 = new Assignment("m1", LocalDate.now(), "50", "");
            Assignment m2 = new Assignment("m2", LocalDate.now(), "50", "");
            Assignment q1 = new Assignment("q1", LocalDate.now(), "10", "");
            Assignment a1 = new Assignment("a1", LocalDate.now(), "10", "");
            at.addTo(quizzes, q1);
            at.addTo(midterms, m1);
            at.addTo(midterms, m2);
            at.addTo(tests, a1);

            HashMap<Assignment, RawScore> map = new HashMap<Assignment, RawScore>();
            Student foo = new Student(new Name("Foo", "", "Bar", ""));
            map.put(finalExam, new RawScore(foo, finalExam, 100.0));
            map.put(m1, new RawScore(foo, m1, 50.0));
            map.put(m2, new RawScore(foo, m2, 50.0));
            map.put(q1, new RawScore(foo, q1, 0.0));
            map.put(a1, new RawScore(foo, a1, 10.0));

            System.out.println(at.calculatePercentage(map).getValue());

            Iterator<Assignment> iter = at.getAssignmentIterator();
            while(iter.hasNext())
            {
                Assignment a = iter.next();
                System.out.println(a.name);
            }
        }
        catch(RawScoreFormatException e) {}
        catch(PercentageFormatException e) {}
        catch(InvalidNameException e) {}
    }

    class AssignmentIterator implements Iterator<Assignment>
    {
        private Assignment nextAssignment;
        private Node currentNode = root;

        public AssignmentIterator()
        {
            nextAssignment = findNextAssignment();
        }

        private Assignment findNextAssignment()
        {
            if(currentNode == null)
            {
                return null;
            }
            Assignment assignment = currentNode.getNextAssignment();
            if(assignment != null)
            {
                return assignment;
            }
            Node node = currentNode.getNextNode();
            if(node != null)
            {
                currentNode = node;
                return findNextAssignment();
            }
            else
            {
                if(currentNode.nodes.size() == currentNode.nextNodeIndex)
                {
                    currentNode = currentNode.parent;
                    return findNextAssignment();
                }
                else
                {
                    currentNode = currentNode.getNextNode();
                    return findNextAssignment();
                }
            }
        }

        @Override
        public boolean hasNext()
        {
            return nextAssignment != null;
        }

        @Override
        public Assignment next()
        {
            Assignment temp = nextAssignment;
            nextAssignment = findNextAssignment();
            return temp;
        }
    }

    class Node
    {
        private Category category;
        private ArrayList<Node> nodes;
        private ArrayList<Assignment> assignments;
        private double total;
        private double grade;
        private Node parent;
        private int nextAssignIndex = 0;
        private int nextNodeIndex = 0;


        public Node(Node parent)
        {
            this.nodes = new ArrayList<Node>();
            this.total = 0.0;
            this.grade = 0.0;
            this.parent = parent;
            this.nextAssignIndex = 0;
            this.nextNodeIndex = 0;
        }

        public Node(Node parent, Category category)
        {
            this(parent);
            this.category = category;
        }

        public void addAssignment(Assignment assignment)
        {
            if(assignments == null)
            {
                assignments = new ArrayList<Assignment>();
            }
            assignments.add(assignment);
        }

        public void addNode(Node node)
        {
            nodes.add(node);
        }

        public boolean is(Category category)
        {
            return this.category == category;
        }

        public Assignment getNextAssignment()
        {
            if(assignments.size() == 0 || nextAssignIndex == assignments.size())
            {
                return null;
            }
            return assignments.get(nextAssignIndex++);
        }

        public Node getNextNode()
        {
            if(nodes.size() == 0 || nextNodeIndex == nodes.size())
            {
                return null;
            }
            return nodes.get(nextNodeIndex++);
        }
    }
}
