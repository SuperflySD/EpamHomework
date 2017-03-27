package task2mapusing;

import java.io.*;
import java.util.Map;
import java.util.stream.Collectors;

public class PropertiesReader {
    private Map<String, String> map;

    public PropertiesReader(String filePath)  {
        try {
            this.directoryCheck(filePath);
            map = this.parseFile(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getValue(String key) throws Exception {
        if (key == null)
            throw new NullPointerException("Key can't be null");
        if (map == null)
            throw new IllegalArgumentException("PropertiesReader's instance was not created, pass correct parameters to the constructor ");
        if (!map.containsKey(key))
            throw new KeyNotFoundException("Searching key is not in existence");
        return map.get(key);
    }

    public String addDuplicateKeyToMap (String duplicateKey , String value){
       return map.put(duplicateKey,value);
    }

    private Map<String, String> parseFile(String filePath) throws IOException {
        try (BufferedReader bf = new BufferedReader(new FileReader(filePath))) {
            return bf.lines().map(x -> x.split(":")).collect(Collectors.toMap(x -> x[0], x -> x[1]));
        }
    }

    private boolean directoryCheck(String filePath) throws FileNotFoundException {
        if (filePath == null)
            throw new NullPointerException("directoryPath can't be null");
        if (!new File(filePath).exists())
            throw new FileNotFoundException("File can't be found in the pointed place \"" + filePath + "\"");
        return true;
    }



}

class KeyNotFoundException extends Exception {
    KeyNotFoundException(String message) {
        super(message);
    }
}