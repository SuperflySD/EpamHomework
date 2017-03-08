package javase.unit3.task3imagessearch;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ImagesSearcher {

    public List<String> findAllSentencesWithImages(String fileName) {
        List<String> groupsList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            Pattern patternGeneral = Pattern.compile("[А-Я].*?((?<!([Рр]ис)|\\d)[.!?])", Pattern.UNICODE_CHARACTER_CLASS);
            Pattern patternSpecial = Pattern.compile(".+[Рр]ис.+");
            Matcher matcher;
            while (scanner.hasNextLine()) {
                String str= scanner.nextLine();
                matcher = patternGeneral.matcher(str);
                if (matcher.find()){
                    String stringGeneral = str.substring(matcher.start(), matcher.end());
                    matcher = patternSpecial.matcher(stringGeneral);
                    if (matcher.find())
                        groupsList.add(stringGeneral.substring(matcher.start(), matcher.end()));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return groupsList;
    }

    public boolean areImagesConsequent(List<String> groupsList) {
        Pattern pattern = Pattern.compile("((?<=[Рр]ис. )\\d\\d?)|((?<=[Рр]исун[а-я][а-я] )\\d\\d?)");
        Matcher matcher;
        List<Integer> intList = new ArrayList<>();
        for (int i=0;i<groupsList.size(); i++) {
            matcher = pattern.matcher(groupsList.get(i));
            if (matcher.find())
                intList.add(Integer.parseInt(groupsList.get(i).substring(matcher.start(), matcher.end())));
        }
        for (int i=1; i<intList.size(); i++)
            if(intList.get(i)<intList.get(i-1))
                return false;

        return true;

    }
}
