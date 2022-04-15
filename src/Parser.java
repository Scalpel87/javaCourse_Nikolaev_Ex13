import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private String text = "";
    private List<String> bigWords = new ArrayList<String>();

    public Parser(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        if (reader.ready()) text = reader.readLine();
        while(reader.ready()){
            text += "\n" + reader.readLine();
        }
        reader.close();
        if (text.length() == 0) {
            System.out.println("Файл " + file.getName() + " пуст. Для его обработки наполните информацией.");
            System.exit(0);
        }
        Pattern pattern = Pattern.compile("[а-яА-Яa-zA-Z]+");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String temp = text.substring(matcher.start(), matcher.end());
            if (temp.length() > 6) bigWords.add(temp.toLowerCase(Locale.ROOT));
        }
        if (bigWords.size() > 1 && bigWords.size()%2 == 0) {//Проверка обрабатывался ли файл ранее
            boolean flag = true;
            for (int i = 0; i < bigWords.size()/2; i++) {
                if (!bigWords.get(i).toLowerCase(Locale.ROOT).equals(bigWords.get(i + bigWords.size()/2))) flag = false;
            }
            if (flag) {
                System.out.println("Файл уже обрабатывался ранее.");
                System.exit(0);
            }
        }
        text = text.replaceAll("\\p{Punct}", "");
        text = text.toLowerCase(Locale.ROOT);
    }

    public String getText() {
        return text;
    }

    public List<String> getBigWords() {
        return bigWords;
    }
}