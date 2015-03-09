package grader.model;

import javafx.util.Callback;

public class BasicModel
{
    public static Callback<String, Boolean> mCallback;

    public static void setCallback(Callback<String, Boolean> callback)
    {
        mCallback = callback;
    }



}
