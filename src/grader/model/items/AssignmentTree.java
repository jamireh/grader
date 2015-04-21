package grader.model.items;

import grader.model.gradebook.Percentage;
import grader.model.gradebook.RawScore;

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
        if(nodes.isEmpty())
        {
            return n.is(node) ? n : null;
        }
        else
        {
            for (int i = 0; i < root.getNodes().size() && ni == null; i++)
            {
                ni = findNodeHelper(node, nodes.get(i));
            }
            return ni;
        }
    }

    /**
     * per student
     * @param scores
     * @return
     */
    public Percentage calculatePercentage(HashMap<Assignment, RawScore> scores)
    {
        return null;
    }


    class Node
    {
        private Category category;
        private ArrayList<Node> nodes;
        private ArrayList<Assignment> assignments;

        public Node()
        {
            nodes = new ArrayList<Node>();
        }

        public Node(Category category)
        {
            this.category = category;
            nodes = new ArrayList<Node>();
        }

        public Node(Category category, ArrayList<Assignment> assignments)
        {
            this.category = category;
            this.assignments = assignments;
            nodes = new ArrayList<Node>();
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

        private ArrayList<Node> getAssignments()
        {
            return nodes;
        }
    }
}
