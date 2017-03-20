package unit7task1;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class DirectoryManager {

    public List<String> getDirectoryContent(String directoryPath) throws DirectoryNotFoundException {
        directoryCheck(directoryPath);
        File path = new File(directoryPath);
        File[] files = path.listFiles();
        return Arrays.stream(files).sorted((x, y) -> {
            if (x.isDirectory() && y.isFile())
                return -1;
            if (x.isFile() && y.isDirectory())
                return 1;
            else return x.compareTo(y);
        }).map(x -> {
            if (x.isFile())
                return x.getName();
            if (x.isDirectory())
                return x.getName() + "(subDirectory)";
            else return "";
        }).collect(Collectors.toList());
    }

    public boolean createFile(String directoryPath, String fileName) throws IOException {
        directoryCheck(directoryPath);
        String filePath = directoryPath + "\\" + fileName + ".txt";
        try {
            Files.createFile(Paths.get(filePath));
        } catch (FileAlreadyExistsException e) {
            System.out.println("File already exists: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean deleteFile(String directoryPath, String fileName) throws IOException {
        directoryCheck(directoryPath);
        String filePath = directoryPath + "\\" + fileName + ".txt";
        try {
            Files.delete(Paths.get(filePath));
        } catch (NoSuchFileException e) {
            System.out.println("There is no such a file in the directory: " + e.getMessage());
            return false;
        }
        return true;
    }

    public String readFile(String directoryPath, String fileName) throws DirectoryNotFoundException {
        directoryCheck(directoryPath);
        String filePath = directoryPath + "\\" + fileName + ".txt";
        StringBuilder str = new StringBuilder();
        try (FileReader fileReader = new FileReader(filePath)) {
            BufferedReader bf = new BufferedReader(fileReader);
            while (true) {
                String s = bf.readLine();
                if (s == null)
                    break;
                str.append(s + "\n");
            }
            bf.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(str);
    }

    public boolean writeToFile(String directoryPath, String fileName, String stringToWrite, boolean destroyPreviousData) throws DirectoryNotFoundException {
        directoryCheck(directoryPath);
        String filePath = directoryPath + "\\" + fileName + ".txt";
        String previousData = "";
        if (!destroyPreviousData)
            previousData = readFile(directoryPath, fileName);
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(previousData + stringToWrite);
        } catch (IOException e) {
            System.out.println("Something went wrong during writing to the file...  " + e.getMessage());
            return false;
        }
        return true;
    }

    private boolean directoryCheck(String directoryPath) throws DirectoryNotFoundException {
        if (directoryPath == null)
            throw new NullPointerException("directoryPath can't be null");
        File path = new File(directoryPath);
        if (!path.exists())
            throw new DirectoryNotFoundException("Pointed directory doesn't exist or unavailable for some other reason");
        return true;
    }
}


class DirectoryNotFoundException extends IOException {
    DirectoryNotFoundException(String message) {
        super(message);
    }
}
