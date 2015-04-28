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
    private ArrayList<Category> categories;

    /**
     * Constructs a new Assignment tree with an empty list of Categories.
     */
    public AssignmentTree()
    {
        root = new Node(null);
        categories = new ArrayList<Category>();
    }

    @Override
    protected AssignmentTree clone()
    {
        AssignmentTree at = new AssignmentTree();
        at.root = cloneHelper(root, null);
        return at;
    }

    private Node cloneHelper(Node currentNode, Node parent)
    {
        Node toReturn = new Node(parent);
        toReturn.assignments.addAll(currentNode.assignments);
        for(Node n : currentNode.nodes)
        {
            toReturn.nodes.add(cloneHelper(n, toReturn));
        }
        return toReturn;
    }


    /**
     * Adds the given child (sub)Category to the given parent Category.
     * @param parent the parent Category
     * @param child the child Category
     */
    public void addTo(Category parent, Category child)
    {
        categories.add(child);
        // add a top level Node
        if (parent == null)
            root.addNode(new Node(null, child));
        // otherwise find the parent node and add it
        else
        {
            Node parentNode = findNode(parent);
            parentNode.addNode(new Node(parentNode, child));
        }
    }

    /**
     * Adds the given Assignment to the given parent Category.
     * @param parent the parent Category
     * @param assignment the child Assignment
     */
    public void addTo(Category parent, Assignment assignment)
    {
        // add a top level Assignment
        if (parent == null)
            root.addAssignment(assignment);
        // otherwise add the Assignment
        else
            findNode(parent).addAssignment(assignment);
    }

    /**
     * Finds the Node for the given Category.
     * @param category the Category whose Node to find
     * @return the Node if found, otherwise null
     */
    private Node findNode(Category category)
    {
        ArrayList<Node> nodes = root.nodes;
        Node n = null;
        for(int i = 0; i < root.nodes.size() && n == null; i++)
        {
            n = findNodeHelper(category, nodes.get(i));
        }
        return n;
    }

    private Node findNodeHelper(Category category, Node n)
    {
        ArrayList<Node> nodes = n.nodes;
        Node ni = null;
        if(n.is(category))
            return n;
        if(!nodes.isEmpty())
        {
            for (int i = 0; i < n.nodes.size() && ni == null; i++)
            {
                ni = findNodeHelper(category, nodes.get(i));
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
     * Calculates the percentage for the current Student for the Assignment.
     * @param scores the map of assignment scores for the current Student
     * @return the Percentage representation of the Student's score
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

    /**
     * Builds and returns an AssignmentIterator for this tree.
     * @return an AssignmentIterator
     */
    public AssignmentIterator getAssignmentIterator()
    {
        return new AssignmentIterator(clone());
    }

    /**
     * Gets the list of Categories in this tree.
     * @return the list of Categories
     */
    public ArrayList<Category> getCategories()
    {
        return categories;
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
            Student foo = new Student(new Name("Foo", "", "Bar"));
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

            for(Category c : at.getCategories())
            {
                System.out.println(c.name);
            }
        }
        catch(RawScoreFormatException e) {}
        catch(PercentageFormatException e) {}
        catch(InvalidNameException e) {}
    }

    /**
     * Iterator class for the AssignmentTree.
     */
    public class AssignmentIterator implements Iterator<Assignment>
    {
        private Assignment nextAssignment;
        private Node currentNode;
        private AssignmentTree at;

        /**
         * Constructs a new AssignmentIterator.
         */
        public AssignmentIterator(AssignmentTree at)
        {
            this.at = at;
            currentNode = at.root;
            nextAssignment = findNextAssignment();
        }

        /**
         * Gets the next Assignment on this iteration.
         * @return the next Assignment
         */
        private Assignment findNextAssignment()
        {
            // check if the iterator has reached the end of the tree
            if(currentNode == null)
            {
                return null;
            }
            // check if the current node has a next assignment
            Assignment assignment = currentNode.getNextAssignment();
            if(assignment != null)
            {
                return assignment;
            }
            // check if the next child node has an assignment
            Node node = currentNode.getNextNode();
            if(node != null)
            {
                currentNode = node;
                return findNextAssignment();
            }
            // otherwise get the next node from the parent and try again
            else
            {
                // check if there are no more assignments in the next node
                if(currentNode.nodes.size() == currentNode.nextNodeIndex)
                {
                    currentNode = currentNode.parent;
                    return findNextAssignment();
                }
                // otherwise get the next subcategory of the next node
                else
                {
                    currentNode = currentNode.getNextNode();
                    return findNextAssignment();
                }
            }
        }

        /**
         * Check if the iterator has a next Assignment
         * @return whether the iterator has a next Assignment or not
         */
        @Override
        public boolean hasNext()
        {
            return nextAssignment != null;
        }

        /**
         * Gets the next Assignment from this iterator
         * @return the next Assignment
         */
        @Override
        public Assignment next()
        {
            Assignment temp = nextAssignment;
            nextAssignment = findNextAssignment();
            return temp;
        }
    }

    /**
     * Represents a node in the AssignmentTree.
     */
    class Node
    {
        private Category category;
        private ArrayList<Node> nodes;
        private ArrayList<Assignment> assignments;
        private Node parent;
        private int nextAssignIndex = 0;
        private int nextNodeIndex = 0;

        private double total;
        private double grade;

        /**
         * Constructs a new Node with the given Node as parent.
         * @param parent the parent of this Node
         */
        public Node(Node parent)
        {
            this.nodes = new ArrayList<Node>();
            this.total = 0.0;
            this.grade = 0.0;
            this.parent = parent;
            this.nextAssignIndex = 0;
            this.nextNodeIndex = 0;
            this.assignments = new ArrayList<Assignment>();
        }

        /**
         * Constructs a new Node for the given Category with
         * the given Node as a parent.
         * @param parent the parent of this Node
         * @param category the Category this Node represents
         */
        public Node(Node parent, Category category)
        {
            this(parent);
            this.category = category;
        }

        /**
         * Adds the given Assignment to this Node's category.
         * @param assignment the Assignment to add
         */
        public void addAssignment(Assignment assignment)
        {
            assignments.add(assignment);
        }

        /**
         * Adds the given Node as a child (subcategory) to this node.
         * @param node the Node to add
         */
        public void addNode(Node node)
        {
            nodes.add(node);
        }

        /**
         * Checks if this Node represents the given Category.
         * @param category the Category to check for equivalence
         * @return true if this Node represents the given Category
         *  <br /> false otherwise
         */
        public boolean is(Category category)
        {
            return this.category == category;
        }

        /**
         * Returns the next Assignment under the iterator and increments it.
         * @return the next Assignment
         */
        public Assignment getNextAssignment()
        {
            // return null if either there are no Assignments
            // or the iterator has reached the end of the list
            if(assignments.size() == 0 ||
               assignments.size() == nextAssignIndex)
            {
                return null;
            }

            return assignments.get(nextAssignIndex++);
        }

        /**
         * Returns the next Node under the iterator and increments it.
         * @return the next Node
         */
        public Node getNextNode()
        {
            // return null if either there are no Nodes
            // or the iterator has reached the end of the list
            if(nodes.size() == 0 || nodes.size() == nextNodeIndex)
            {
                return null;
            }

            return nodes.get(nextNodeIndex++);
        }
    }
}
