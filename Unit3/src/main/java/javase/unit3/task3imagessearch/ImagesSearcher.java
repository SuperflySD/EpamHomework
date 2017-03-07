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
            Pattern pattern = Pattern.compile("(\\.|>)\\s?.+(\\([Рр]ис. [1-9][0-9]?\\)|[рР]исун[а-я]{2,3}\\s[1-9][0-9]?).*\\.", Pattern.UNICODE_CHARACTER_CLASS);
            Matcher matcher;

            while (scanner.hasNextLine()) {
                String str= scanner.nextLine();
                matcher = pattern.matcher(str);
                if (matcher.find())
                    groupsList.add(str.substring(matcher.start()+1, matcher.end()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return groupsList;
    }

    public boolean areImagesConsequent(List<String> groupsList) {
        Pattern pattern = Pattern.compile("(\\d\\d?)");
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
