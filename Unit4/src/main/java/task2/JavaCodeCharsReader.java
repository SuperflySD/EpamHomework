package task2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaCodeCharsReader {

    public String readJavaCode(String fileName) {
        StringBuilder str = new StringBuilder();

        try (FileReader fileReader = new FileReader(fileName)) {
            BufferedReader bf = new BufferedReader(fileReader);
            while (true) {
                String s = bf.readLine();
                if (s == null)
                    break;
                str.append(s+"\n");
            }
            bf.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(str);
    }

    public void writeJavaCode(String fileName, String string) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(string);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Map<String, Integer> detectJavaKeyWords(String inputStr) {
        Map<String, Integer> map = new HashMap<>();
        String[] keyWords = {"byte", "short", "int", "long", "float", "double", "char", "boolean", "do", "while", "for", "break", "continue",
                "else", "switch", "case", "default", "break", "private", "public", "protected", "final", "static", "abstract",
                "synchronized", "volatile", "strictfp", "false", "true", "null", "return", "void", "package", "import", "try",
                "catch", "finally", "throw", "throws", "new", "extends", "implements", "class", "instanceof", "this", "super"};

        for (int i = 0; i < keyWords.length; i++) {
            Pattern pattern = Pattern.compile(keyWords[i]);
            Matcher matcher = pattern.matcher(inputStr);
            int counter = 0;
            while (matcher.find())
                map.put(keyWords[i], ++counter);
        }
        return map;
    }


}
