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
        root = new Node();
    }

    public void addTo(Category node, Category toAdd)
    {
        if(node == null)
        {
            root.addNode(new Node(toAdd));
            return;
        }
        findNode(node).addNode(new Node(toAdd));
    }

    public void addTo(Category node, Assignment toAdd)
    {
        if(node == null)
        {
            root.addAssignment(toAdd);
            return;
        }
        findNode(node).addAssignment(toAdd);
    }

    private Node findNode(Category node)
    {
        ArrayList<Node> nodes = root.getNodes();
        Node n = null;
        for(int i = 0; i < root.getNodes().size() && n == null; i++)
        {
            n = findNodeHelper(node, nodes.get(i));
        }
        return n;
    }

    private Node findNodeHelper(Category node, Node n)
    {
        ArrayList<Node> nodes = n.getNodes();
        Node ni = null;
        if(n.is(node))
            return n;
        if(!nodes.isEmpty())
        {
            for (int i = 0; i < n.getNodes().size() && ni == null; i++)
            {
                ni = findNodeHelper(node, nodes.get(i));
            }
            return ni;
        }

        return ni;
    }

    private double gradeCheckNode(Node node, HashMap<Assignment, RawScore> map)
    {
        ArrayList<Node> nodes = node.getNodes();
        for (int i = 0; i < nodes.size(); i++)
        {
            Category c = nodes.get(i).getCategory();
            node.total += c.weight.getValue();
        }
        ArrayList<Assignment> unweightAssignments = new ArrayList<Assignment>();
        for(Assignment a : node.getAssignments())
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
            equilDistribution = !node.getCategory().uncategorizedByRawScore;
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
        if(node.getCategory() == null)
        {
            return node.grade;
        }
        return node.grade * node.getCategory().weight.getValue();
        
    }

    //assignment iterator
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
        }
        catch(RawScoreFormatException e) {}
        catch(PercentageFormatException e) {}
        catch(InvalidNameException e) {}
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


    class Node
    {
        private Category category;
        private ArrayList<Node> nodes;
        private ArrayList<Assignment> assignments;
        private double total;
        private double grade;

        public Node()
        {
            nodes = new ArrayList<Node>();
            total = 0.0;
            grade = 0.0;
        }

        public Node(Category category)
        {
            this.category = category;
            nodes = new ArrayList<Node>();
            init();
        }

        private void init()
        {

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

        private ArrayList<Node> getNodes()
        {
            return nodes;
        }

        private ArrayList<Assignment> getAssignments()
        {
            return assignments;
        }

        private Category getCategory()
        {
            return category;
        }
    }
}
