package grader.model.curve;

import grader.model.gradebook.WorkSpace;
import grader.model.gradebook.gradescheme.GradeRange;
import grader.model.gradebook.gradescheme.GradeScheme;
import grader.model.gradebook.scores.RawScore;
import grader.model.items.Assignment;
import grader.model.items.Percentage;
import grader.model.people.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * The PieChart class takes all of its required data and functionality from the AbstractGraph class.
 * 
 * Derived from the requirements documentation regarding visuals.
 */
public class PieChart implements Observer
{

    private int aPlus = 0;
    private int aS = 0;
    private int aMinus = 0;
    private int bPlus = 0;
    private int bS = 0;
    private int bMinus = 0;
    private int cPlus = 0;
    private int cS = 0;
    private int cMinus = 0;
    private int dPlus = 0;
    private int dS = 0;
    private int dMinus = 0;
    private int fS = 0;


    private List<Student> students;




   /**
    * Updates the Pie Chart.
    */
   public void update(Observable obj, Object args)
   {

       if (WorkSpace.instance.getGradeScheme() != null)
       {

           aPlus = 0;
           aS = 0;
           aMinus = 0;
           bPlus = 0;
           bS = 0;
           bMinus = 0;
           cPlus = 0;
           cS = 0;
           cMinus = 0;
           dPlus = 0;
           dS = 0;
           dMinus = 0;
           fS = 0;

           students = WorkSpace.instance.getStudents();

           for (Student s : students)
           {
               HashMap<Assignment, RawScore> map = WorkSpace.instance.getScores().getScoresMap(s);
               Percentage percent = WorkSpace.instance.getAssignmentTree().calculatePercentage(map);
               addToGrade(percent);
           }
       }
   }



    /**
     * Returns percentage of A+s.
     * @return percentage of A+s
     */
    public double getNumAPlus()
    {
        return (aPlus/((double)students.size())) * 100;
    }

    /**
     * Returns percentage of As.
     * @return percentage of As
     */
    public double getNumA()
    {
        return (aS/((double)students.size())) * 100;
    }

    /**
     * Returns percentage of A-s.
     * @return percentage of A-s
     */
    public double getNumAMinus()
    {
        return (aMinus/((double)students.size())) * 100;
    }

    /**
     * Returns percentage of B+s.
     * @return percentage of B+s
     */
    public double getNumBPlus()
    {
        return (bPlus/((double)students.size())) * 100;
    }

    /**
     * Returns percentage of Bs.
     * @return percentage of Bs
     */
    public double getNumB()
    {
        return (bS/((double)students.size())) * 100;
    }

    /**
     * Returns percentage of B-s.
     * @return percentage of B-s
     */
    public double getNumBMinus()
    {
        return (bMinus/((double)students.size())) * 100;
    }

    /**
     * Returns percentage of C+s.
     * @return percentage of C+s
     */
    public double getNumCPlus()
    {
        return (cPlus/((double)students.size())) * 100;
    }

    /**
     * Returns percentage of Cs.
     * @return percentage of Cs
     */
    public double getNumC()
    {
        return (cS/((double)students.size())) * 100;
    }

    /**
     * Returns percentage of C-s.
     * @return percentage of C-s
     */
    public double getNumCMinus()
    {
        return (cMinus/((double)students.size())) * 100;
    }

    /**
     * Returns percentage of D+s.
     * @return percentage of D+s
     */
    public double getNumDPlus()
    {
        return (dPlus/((double)students.size())) * 100;
    }

    /**
     * Returns percentage of Ds.
     * @return percentage of Ds
     */
    public double getNumD()
    {
        return (dS/((double)students.size())) * 100;
    }

    /**
     * Returns percentage of D-s.
     * @return percentage of D-s
     */
    public double getNumDMinus()
    {
        return (dMinus/((double)students.size())) * 100;
    }

    /**
     * Returns percentage of Fs.
     * @return percentage of Fs
     */
    public double getNumF()
    {
        return (fS/((double)students.size())) * 100;
    }

    /**
     * Adds to grade variables.
     * @param percent of grade
     */
    public void addToGrade(Percentage percent)
    {

        GradeScheme current = WorkSpace.instance.getGradeScheme();
        GradeRange range = current.getGradeRange(percent);

        switch (range.getLetterGrade())
        {
            case A_PLUS:
                aPlus++;
                break;
            case A:
                aS++;
                break;
            case A_MINUS:
                aMinus++;
                break;
            case B_PLUS:
                bPlus++;
                break;
            case B:
                bS++;
                break;
            case B_MINUS:
                bMinus++;
                break;
            case C_PLUS:
                cPlus++;
                break;
            case C:
                cS++;
                break;
            case C_MINUS:
                cMinus++;
                break;
            case D_PLUS:
                dPlus++;
                break;
            case D:
                dS++;
                break;
            case D_MINUS:
                dMinus++;
                break;
            case F:
                fS++;
                break;
            default:
                break;
        }

    }
}
