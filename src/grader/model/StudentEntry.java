package grader.model;

import javafx.beans.property.SimpleStringProperty;

public class StudentEntry {
    public final SimpleStringProperty name;
    public final SimpleStringProperty group;
    public final SimpleStringProperty projects1;
    public final SimpleStringProperty projects2;
    public final SimpleStringProperty quizzes1;
    public final SimpleStringProperty quizzes2;
    public final SimpleStringProperty midterm1;
    public final SimpleStringProperty midterm2;
    public final SimpleStringProperty finalExam;
    public final SimpleStringProperty participation;
    public final SimpleStringProperty total;
    public final SimpleStringProperty letterGrade;

    public StudentEntry(String name, String group, int Project1, int Project2,
                        int Quiz1, int Quiz2, int Midterm1, int Midterm2, int FinalExam,
                        int Participation, int total, String letterGrade) {
        this.name = new SimpleStringProperty(name);
        this.group = new SimpleStringProperty(group);
        this.projects1 = new SimpleStringProperty(""+Project1);
        this.projects2 = new SimpleStringProperty(""+Project2);
        this.quizzes1 = new SimpleStringProperty(""+Quiz1);
        this.quizzes2 = new SimpleStringProperty(""+Quiz2);
        this.midterm1 = new SimpleStringProperty(""+Midterm1);
        this.midterm2 = new SimpleStringProperty(""+Midterm2);
        this.finalExam = new SimpleStringProperty(""+FinalExam);
        this.participation = new SimpleStringProperty(""+Participation);
        this.total = new SimpleStringProperty(""+total);
        this.letterGrade = new SimpleStringProperty(""+letterGrade);
    }

    public String getName() {
        return name.get();
    }
    public void setName(String fName) {
        name.set(fName);
    }
    public String getGroup() {
        return group.get();
    }
    public String getProjects1() {
        return projects1.get();
    }
    public String getProjects2() {
        return projects2.get();
    }
    public String getQuizzes1() {
        return quizzes1.get();
    }
    public String getQuizzes2() {
        return quizzes2.get();
    }
    public String getMidterm1() {
        return midterm1.get();
    }
    public String getMidterm2() {
        return midterm2.get();
    }
    public String getFinalExam() {
        return finalExam.get();
    }
    public String getParticipation() {
        return participation.get();
    }
    public String getTotal() {
        return total.get();
    }
    public String getLetterGrade() {
        return letterGrade.get();
    }


}