package grader.model.curve;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

    public StringProperty letterProperty() { return letter;}
    public StringProperty percentProperty() { return percent;}
    public StringProperty starProperty() { return star;}

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
