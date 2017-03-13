package task2;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PropertiesReader {
    private Map<String, String> map;

    public PropertiesReader(String directoryPath, String fileName) throws IOException {
            String filePath = directoryPath + "\\" + fileName + ".properties";
            this.directoryCheck(directoryPath, filePath);
            map = this.parseFile(filePath);

    }

    public String getValue(String key) throws Exception {
        if (key == null)
            throw new NullPointerException("Key can't be null");
        if (map == null)
            throw new Exception("Created PropertiesReader's instance is inconsistent, so you can't call getValue method." +
                    " Pass correct parameters to the constructor for getting usable instance.");
        if (!map.containsKey(key))
            throw new KeyNotFoundException("Searching key is not in existence");
        return map.get(key);
    }

    private Map<String, String> parseFile(String filePath) throws IOException {
        try (BufferedReader bf = new BufferedReader(new FileReader(filePath))) {
            Map<String, String> map = new HashMap<>();
            return bf.lines().map(x -> x.trim().split(":")).collect(Collectors.toMap(x -> x[0], x -> x[1]));
        }
    }

    private boolean directoryCheck(String directoryPath, String filePath) throws DirectoryNotFoundException, FileNotFoundException {
        if (directoryPath == null)
            throw new NullPointerException("directoryPath can't be null");
        if (!new File(directoryPath).exists())
            throw new DirectoryNotFoundException("Pointed directory " + directoryPath + " doesn't exist or unavailable for some other reason");
        if (!new File(filePath).exists())
            throw new FileNotFoundException("File can't be found in the pointed place \"" + directoryPath + "\"");
        return true;
    }
}

class DirectoryNotFoundException extends IOException {
    DirectoryNotFoundException(String message) {
        super(message);
    }
}

class KeyNotFoundException extends Exception {
    KeyNotFoundException(String message) {
        super(message);
    }
}