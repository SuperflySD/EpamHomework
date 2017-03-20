package task3utf8reader;

import java.io.*;

public class JavaUTF8Reader {

    public String readUTF8(String fileName) {
        StringBuilder str = new StringBuilder();
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileName), "UTF-8"))) {
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

    public void writeUTF16(String fileName, String string) {
        try (PrintWriter printWriter = new PrintWriter(fileName, "UTF-16")) {
            printWriter.write(string);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
