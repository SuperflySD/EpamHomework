package task1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaCodeBytesReader {

    public String readJavaCode(String fileName) {
        StringBuilder str = new StringBuilder();
        byte[] arr;
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            arr = new byte[fileInputStream.available()];
            fileInputStream.read(arr);

            for (int i = 0; i < arr.length; i++) {
                str.append((char) arr[i]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(str);
    }

    public void writeJavaCode(String fileName, String string) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte[] b = string.getBytes(StandardCharsets.UTF_8);
            fileOutputStream.write(b);
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
