import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static ConcurrentHashMap<String, Integer> wordCount = new ConcurrentHashMap<>();
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        Callable<Void> counter1 = () -> {
            Reader.calculate("text.txt");
            return null;
        };
        Callable<Void> counter2 = () -> {
            Reader.calculate("text1.txt");
            return null;
        };
        Callable<Void> counter3 = () -> {
            Reader.calculate("text2.txt");
            return null;
        };

        ExecutorService es = Executors.newFixedThreadPool(3);

        List<Future<Void>> futures = new ArrayList<>();

        futures.add(es.submit(counter1));
        futures.add(es.submit(counter2));
        futures.add(es.submit(counter3));
//        es.submit(counter1);
//        es.submit(counter2);
//        es.submit(counter3);

        for(Future<Void> future: futures){
            while (!future.isDone()){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        es.shutdown();
        System.out.println(wordCount.toString());
    }
}