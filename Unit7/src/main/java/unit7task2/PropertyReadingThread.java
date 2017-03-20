package unit7task2;

public class PropertyReadingThread extends Thread {
    private PropertiesReader propertiesReader;
    private String key;

    public PropertyReadingThread(PropertiesReader propertiesReader, String key) {
        this.propertiesReader = propertiesReader;
        this.key = key;
    }

    @Override
    public void run() {
        synchronized (propertiesReader) {
            try {
                propertiesReader.plusReadersSameTime();
                propertiesReader.getValue(key);
                propertiesReader.minusReadersSameTime();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}