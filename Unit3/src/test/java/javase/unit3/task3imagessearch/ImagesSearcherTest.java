package javase.unit3.task3imagessearch;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class ImagesSearcherTest {
    ImagesSearcher searcher = new ImagesSearcher();

    @Test
    public void findImagesTest() throws Exception {
       List<String> list = searcher.findAllSentencesWithImages("src\\main\\resources\\Article.html");
       for (String str: list)
          System.out.println(str);
       System.out.println(list.size());

}

    @Test
    public void areImagesConsequent1() throws Exception {
        List<String> list = searcher.findAllSentencesWithImages("src\\main\\resources\\Article.html");
        assertFalse(searcher.areImagesConsequent(list));

    }
}
