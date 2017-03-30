package task1directorymanager;

import org.junit.Test;
import task1directorymanager.DirectoryManager;
import task1directorymanager.DirectoryNotFoundException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class DirectoryManagerTest {
    private DirectoryManager manager = new DirectoryManager();
    private final String DIRECTORYPATH = "src\\main\\resources";
    private final String FILENAME = "newFile";

        @Test
    public void getDirectoryContent() throws DirectoryNotFoundException {
        assertTrue(manager.getDirectoryContent(DIRECTORYPATH).contains("1.txt"));
    }

    @Test
    public void createFile() throws Exception {
        if (manager.getDirectoryContent(DIRECTORYPATH).contains(FILENAME+".txt"))
            manager.deleteFile(DIRECTORYPATH, FILENAME);
        assertTrue(manager.createFile(DIRECTORYPATH, FILENAME));
        assertTrue(manager.getDirectoryContent(DIRECTORYPATH).contains(FILENAME+".txt"));
    }
    @Test
    public void deleteFile() throws Exception {
        if (!manager.getDirectoryContent(DIRECTORYPATH).contains(FILENAME+".txt"))
            manager.createFile(DIRECTORYPATH, FILENAME);
        assertTrue(manager.deleteFile(DIRECTORYPATH, FILENAME));
        assertFalse(manager.getDirectoryContent(DIRECTORYPATH).contains(FILENAME+".txt"));

    }
    @Test(expected = DirectoryNotFoundException.class)
    public void checkForDirectoryNotFoundException() throws Exception {
        manager.getDirectoryContent(DIRECTORYPATH+1);
    }

    @Test
    public void readFile() throws Exception {
        System.out.println(manager.readFile(DIRECTORYPATH,FILENAME));

    }

    @Test
    public void writeToFile() throws Exception {
        manager.writeToFile(DIRECTORYPATH, FILENAME, "ssd Something1", true );

    }


}
