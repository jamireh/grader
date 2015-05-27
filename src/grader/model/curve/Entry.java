package grader.model.curve;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by mallika on 5/8/15.
 */
public class Entry {

    private final SimpleStringProperty letter;
    public final SimpleStringProperty percent;
    public final SimpleStringProperty star;

    public Entry(String let, String per, String st)
    {
        letter = new SimpleStringProperty(let);
        percent = new SimpleStringProperty(per);
        star = new SimpleStringProperty(st);
    }

    public String getLetter()
    {
        return letter.get();
    }

    public String getPercent()
    {
        return percent.get();
    }

    public String getStar()
    {
        return star.get();
    }

    public boolean equals(Entry e)
    {
        return e.getLetter().equals(letter) && e.getPercent().equals(percent) && e.getStar().equals(star);
    }
}
