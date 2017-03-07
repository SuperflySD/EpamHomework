package javase.unit3.task2internationalisedapp;


import java.util.Locale;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LocaleAppTest {

    @Test
    public void questionsList() throws Exception {
        InternationalisedApp app = new InternationalisedApp(new Locale("ru"));
        app.questionsList();
    }

    @Test
    public void getAnswer() throws Exception {
        InternationalisedApp app = new InternationalisedApp(new Locale("ru"));
        assertEquals(app.getAnswer(3), "3. Потому что мне нужно сделать второе задание;");

        app = new InternationalisedApp(new Locale("en"));
        assertEquals(app.getAnswer(3), "3. Because I want to have my task number two done;");

        assertEquals(app.getAnswer(2), "2. You should open File/Settings/Editor/File Encodings and set default encoding on UTF-8;");
    }

}