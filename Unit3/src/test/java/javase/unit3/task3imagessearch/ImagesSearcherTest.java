package javase.unit3.task3imagessearch;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ImagesSearcherTest {
    ImagesSearcher searcher = new ImagesSearcher();

    @Test
    public void findImagesTest() throws Exception {
       List<String> list = searcher.findAllSentencesWithImages("src\\main\\resources\\Article.html");
       for (String str: list)
          assertTrue(str.contains("Рис")||str.contains("рис"));

}

    @Test
    public void areImagesConsequent1() throws Exception {
        List<String> list = searcher.findAllSentencesWithImages("src\\main\\resources\\Article.html");
        assertFalse(searcher.areImagesConsequent(list));

    }
}
