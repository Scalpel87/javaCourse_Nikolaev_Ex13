import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("file.txt");
        if (file.exists()) {
            Parser parser = new Parser(file);
            if (parser.getBigWords().size() > 0){
                String stringForWrite = "";
                for (int i = 0; i < parser.getBigWords().size(); i++) {
                    stringForWrite += parser.getBigWords().get(i) + " ";
                }
                stringForWrite = stringForWrite.substring(0, stringForWrite.length() - 1);//убираем последний пробел
                File newFile = new File("newFile.txt");
                if (!newFile.exists()) newFile.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
                writer.write(parser.getText() + "\n" + stringForWrite);
                writer.close();
            }
        } else {
            file.createNewFile();
            System.out.println("Создан файл " + file.getName() + ". Для его обработки наполните информацией.");
        }
    }
}