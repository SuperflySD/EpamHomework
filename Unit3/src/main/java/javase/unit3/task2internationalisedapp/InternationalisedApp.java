package javase.unit3.task2internationalisedapp;

import java.util.*;

public class InternationalisedApp {
    private Locale locale;

    public InternationalisedApp(Locale locale) {
        if (locale==null)
            return;
        Locale.setDefault(locale);
    }
    public void questionsList()  {
        ResourceBundle bundle = ResourceBundle.getBundle("QuestionsAndAnswers");
        for (String entry: bundle.keySet())
            if (entry.startsWith("q"))
                System.out.println(bundle.getString(entry));
    }

    public String getAnswer(int numberOfQuestion) {
        ResourceBundle bundle = ResourceBundle.getBundle("QuestionsAndAnswers");
        if (numberOfQuestion<1||numberOfQuestion > bundle.keySet().size())
            return "";
        else
            System.out.println(bundle.getString(numberOfQuestion+""));
        return bundle.getString(numberOfQuestion+"");
    }
}


