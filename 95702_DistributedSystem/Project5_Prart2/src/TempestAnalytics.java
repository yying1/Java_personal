//Frank Yue Ying | yying2@

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import scala.Tuple2;
import java.util.Arrays;
import java.util.Scanner;

public class TempestAnalytics {
    public static SparkConf sparkConf;

    // Task 0, split txt file by newline and count the number of lines
    private static void task0_linecount(String fileName){
//        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("JD Word Counter");

        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<String> inputFile = sparkContext.textFile(fileName);

        JavaRDD<String> wordsFromFile = inputFile.flatMap(content -> Arrays.asList(content.split("\n")));

        System.out.println("Number of lines: "+wordsFromFile.count());

    }

    //Task 1, apply filter and split method to perform word count
    private static void task1_wordcount(String fileName) {

//        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("JD Word Counter");

        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<String> inputFile = sparkContext.textFile(fileName);
        //referenced this from Project 5 document
        JavaRDD<String> wordsFromFile = inputFile.flatMap(content -> Arrays.asList(content.split("[^a-zA-Z]+")));

        Function<String, Boolean> filter = k -> ( !k.isEmpty());
        wordsFromFile = wordsFromFile.filter(filter);

        System.out.println("Number of words: "+wordsFromFile.count());
    }

    //Task 2, same with task 1 but added .distinct() to count distinct words only
    private static void task2_distinctwordcount(String fileName) {

//        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("JD Word Counter");

        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<String> inputFile = sparkContext.textFile(fileName);
        //referenced this from Project 5 document
        JavaRDD<String> wordsFromFile = inputFile.flatMap(content -> Arrays.asList(content.split("[^a-zA-Z]+")));

        Function<String, Boolean> filter = k -> ( !k.isEmpty());
        wordsFromFile = wordsFromFile.filter(filter);

        System.out.println("Number of distinct words: "+wordsFromFile.distinct().count());
    }

    //Task 3, split then count
    private static void task3_symbolcount(String fileName) {

//        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("JD Word Counter");

        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<String> inputFile = sparkContext.textFile(fileName);

        JavaRDD<String> wordsFromFile = inputFile.flatMap(content -> Arrays.asList(content.split("")));

        System.out.println("Number of symbols: "+wordsFromFile.count());
    }

    //Task 4, split then distinct count
    private static void task4_distinctsymbolcount(String fileName) {

//        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("JD Word Counter");

        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<String> inputFile = sparkContext.textFile(fileName);

        JavaRDD<String> wordsFromFile = inputFile.flatMap(content -> Arrays.asList(content.split("")));

        System.out.println("Number of distinct symbols: "+wordsFromFile.distinct().count());
    }

    //Task 5, First break everything into symbols, then remove empty items from wordsFromFile before count letters
    private static void task5_distinctlettercount(String fileName) {

//        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("JD Word Counter");

        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<String> inputFile = sparkContext.textFile(fileName);

        JavaRDD<String> wordsFromFile = inputFile.flatMap(content -> Arrays.asList(content.split("")));

        Function<String, Boolean> filter = k -> ( !k.isEmpty());
        wordsFromFile = wordsFromFile.filter(filter);

        Function<String, Boolean> filter1 = k -> (Character.isLetter(k.charAt(0)));
        wordsFromFile = wordsFromFile.filter(filter1);

        System.out.println("Number of distinct letters: "+wordsFromFile.distinct().count());
//        for(String line:wordsFromFile.collect()){
//            System.out.println("* "+line);
//        }
    }

    //Task 6, create custom filter to keep lines containing the word
    private static void task6_searchword(String fileName, String word) {

//        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("JD Word Counter");

        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<String> inputFile = sparkContext.textFile(fileName);

        JavaRDD<String> wordsFromFile = inputFile.flatMap(content -> Arrays.asList(content.split("\n")));

        //Create custom filter to include lines containing word only
        Function<String, Boolean> filter = k -> ( k.contains(word));
        wordsFromFile = wordsFromFile.filter(filter);

//        System.out.println("Number of distinct letters: "+wordsFromFile.distinct().count());
        //print each line in wordsFromFile
        for(String line:wordsFromFile.collect()){
            System.out.println(line);
        }
    }

    //This is an example taken from Lab 9
    private static void wordCount(String fileName) {

        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("JD Word Counter");

        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<String> inputFile = sparkContext.textFile(fileName);

        JavaRDD<String> wordsFromFile = inputFile.flatMap(content -> Arrays.asList(content.split(" ")));

        JavaPairRDD countData = wordsFromFile.mapToPair(t -> new Tuple2(t, 1)).reduceByKey((x, y) -> (int) x + (int) y);

        countData.saveAsTextFile("CountData");
    }

    public static void main(String[] args) {
        TempestAnalytics TA = new TempestAnalytics();
        TA.sparkConf = new SparkConf().setMaster("local").setAppName("JD Word Counter");

        if (args.length == 0) {
            System.out.println("No files provided.");
            System.exit(0);
        }

        // Please un-comment each task to execute them
        TA.task0_linecount(args[0]);
        TA.task1_wordcount(args[0]);
        TA.task2_distinctwordcount(args[0]);
        TA.task3_symbolcount(args[0]);
        TA.task4_distinctsymbolcount(args[0]);
        TA.task5_distinctlettercount(args[0]);

        System.out.println("Please enter your search word:");
        Scanner readInput = new Scanner(System.in);
        String word = readInput.nextLine();
        TA.task6_searchword(args[0],word);
    }
}
