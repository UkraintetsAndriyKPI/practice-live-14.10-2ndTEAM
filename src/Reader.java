import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Reader{

    public static void calculate(String filePath){
        ArrayList<String> words = readFile(filePath);
        for (int i = 0; i < words.size(); i++) {
            if (!Main.wordCount.containsKey(words.get(i))){
                Main.wordCount.put(words.get(i), 1);
                continue;
            }
            Main.wordCount.put(words.get(i), Main.wordCount.get(words.get(i)) + 1);
        }
//        System.out.println(Main.wordCount.toString());
    }

    private static ArrayList<String> readFile(String filePath){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<String> words = new ArrayList<String>();
        while(scanner.hasNext()){
            String line = scanner.nextLine();
            String[] wordsInLine = line.split(" ");
//            System.out.println(wordsInLine.toString());
            words.addAll(Arrays.asList(wordsInLine));
        }
        return words;
    }

}
