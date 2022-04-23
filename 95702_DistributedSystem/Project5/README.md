# 95-702 Distributed Systems For Information Systems Management
# Distributed Computation
## Project 5 Spring 2022

Assigned: Friday, April 8, 2022

Due: Friday, April 22, 11:59 PM

**Principles**

Big data infrastructures support distributed computation through services
such as MapReduce and Spark. Both of these approaches exemplify the
principle of spatial locality by moving computation close to data. These
data are then processed in parallel.

**Learning objectives**

The student will be able to program applications using MapReduce and Spark.
For those new to Linux, this project will also expose you to compiling and running Java programs in a Linux environment. 

**How will I be graded?**

There will be a single pdf file submitted to Canvas and a Heinz cluster account available for inspection. The pdf will be clearly labelled. Your name and email will be at the top. ***Be sure to order and label the pdf
sections as described in the next 6 steps.***

Using the pdf, the grader will:

1. Open your single pdf file on Canvas.
2. Look at the screen shot of Google Earth from Part 1, Task 7.
3. Look at your single Spark program from Part 2, Task 6. This will be graded based on documentation and execution.
4. Look at the output screenshot of Part 2, Task 6. This is the Part 2 execution.
5. Look at your Heinz cluster user ID (studentxxx). This is labelled and in the pdf.
6. Look at your Heinz cluster password. This is also labelled and in the pdf.

Using the Heinz cluster:

7. Logon to your Heinz cluster account.
8. cd into Project5 and cd into Part_1.
9. cd into a task and grade it based upon
correct execution and documentation. All files, as described below,
should be available for inspection by the grader. In particular, the grader will be looking for the output files that resulted from the merge operations. The HDFS input and output directories on the cluster will be empty. The grader will be able to copy the input file to the cluster (using copyFromLocal) and will be able to deploy and execute the jar file without first having to delete the HDFS output directory. The input file, source code, and other task related artifacts will be available in the directory Project5/Part_1/TaskX (where X = 0,1,2,3,4,5,6,7).


**Helpful notes for later reference**

When documenting your code, follow the Documentation Example shown in the Course Information Module on Canvas.

[Documentation Example](./Documentation.md)

Your well documented programs will always begin with a block of comments
(using Javadoc style or regular style) that provide the name of the programmer and the purpose of the program.

On the Heinz High Performance Cluster, we will be working with two different
directory structures. The first is your local directory structure /home/userID.
The second is the HDFS directory structure /user/userID. We will compile code
on the first (/home/userID) and then deploy jar files to the second (/user/userID).

The input to the program must be stored on HDFS and the output from the program will be written to HDFS. So, we will be copying input from our local system to HDFS before running a jar. We will be copying the output from HDFS
to our local directory with the hadoop getMerge utility.

The code and example data for the MaxTemperature application is from
"Hadoop: The Definitive Guide, Second Edition, by Tom White.
Copyright 2011 Tom White, 978-1-449-38973-4."

We will be running Linux, Centos 6.3, on a Hadoop cluster of several
physical machines on a single rack at Heinz College.

To reach the hadoop cluster, you will need to ssh into
heinz-jumbo.heinz.cmu.local.

If you are working from home, first run the Cisco AnyConnect
Secure Mobility Client. This tool may be downloaded from here:

[Cisco Any Connect](https://www.cmu.edu/computing/software/all/cisco-anyconnect/index.html)

There are Linux text editors available (pico, nano, and vi).
You should spend a little time learning your text editor of choice. There
is plenty of help online.

To use nano, use these commands:

```
nano filename.java        starts the editor
<control> o               to save a file
<control> x               to exit nano.

```

On the cluster, your home directory is /home/userID.

The hadoop jars and binaries are at /usr/local/hadoop.

Your HDFS file system directories are at /user/userID/output.

Note that one is "usr" and the other is "user".

It is assumed that students with Windows machines will be running putty or
some other telnet tool to connect to the cluster. Mac users will likely
use ssh.

It is also assumed that each student has a user-ID and password for the Heinz cluster.

You should have established a new password when you completed the
Hadoop lab. Your user-ID should be on the grade book on Canvas. You should
communicate your password to the TA's by taking the quiz which asks for your
password. Also, note that your user ID and password need to be placed on the submission pdf.

If you need to transfer data from your machine to the cluster, be sure to use
sftp. The "copy and paste" approach will likely introduce hidden characters in your file. Here is an example execution of sftp.

```
$sftp userID@heinz-jumbo.heinz.cmu.local
sftp>put MaxTemperature.java
sftp>get MaxTemperature.java
```

Here are some basic examples that you will need to study before attempting to work on the cluster.
They are meant for reference. When working on the tasks below, you will need to use the commands
here (but with appropriate modifications based on the task.) These commands will not run unless
they are executed in the appropriate order and with appropriate resources. Spend some time
studying them and then begin to use those that you need to complete the tasks.

**Note: Use the up arrow in unix to select previously executed commands.**

The user wants to sort an employee file on the second column of a file and that column is numeric. We want high values near the top. The switch "2nr" means second column, numeric, reverse order. This is standard Linux.

```
sort -k 2nr employee.txt

```
The user wants to copy an existing directory and all of its content to a new directory. The destination directory is created with this command. The "R" is for recursive. This is standard Linux.

```
cp -R source_dir destination_dir

```

The user would like to see a list of files on the Hadoop distributed file system under /user/userID/input.

Note again the use of "user" and not "usr". "user" is a reference to the directory in HDFS. "usr" is a reference
to your local directory.

```
$hadoop dfs -ls /user/userID/input/

```

An input file needs to be placed under HDFS. This file will be used for subsequent map reduce processing.
The HDFS file and directory are created. If they already exist, then this command will return an 'already exists'
message.

```
$hadoop dfs -copyFromLocal /home/userID/input/1902.txt /user/userID/input/1902.txt

```

Look at the contents of a file on HDFS.

```
$hadoop dfs -cat /user/userID/input/testFile

```

Remove a local directory of Java classes that may contain Java packages. Typically, the code in temperature_classes is already compiled and exists
in a directory that corresponds to Java package names. This is standard Linux.

```
$rm -r temperature_classes
```

Create a directory for Java classes. This is standard Linux.

```
$mkdir temperature_classes
```

Compile three Java classes using a library of Hadoop classes stored in
a jar. The classes directory (./temperature_classes) is the
target of the compile and may be populated with a directory structure
that corresponds to Java packages. The classes directory (.temperature_classes) is also consulted during the compile. The Java files are in the current directory. The directory temperature_classes exists as a subdirectory of the current directory. This is all standard Linux.

These commands assume that you are in a directory just above temperature_classes.
So, before running the first compile, if we execute an 'ls' command, we would see:

```
$ls
temperature_classes MaxTemperatureMapper.java MaxTemperatureReducer.java MaxTemperature.java

$javac -classpath  /usr/local/hadoop/hadoop-core-1.2.1.jar:./temperature_classes -d temperature_classes MaxTemperatureMapper.java

$javac -classpath  /usr/local/hadoop/hadoop-core-1.2.1.jar:./temperature_classes -d temperature_classes MaxTemperatureReducer.java

$javac -classpath  /usr/local/hadoop/hadoop-core-1.2.1.jar:./temperature_classes -d temperature_classes MaxTemperature.java

```

Remove an existing jar file using standard Linux.

```
$rm temperature.jar
```

Create a new jar file called temperature.jar. It will include all of the classes found in temperature_classes. Note the "." at the end of the line. The dot is important here. It refers
to the current directory. Note too that temperature_classes must be a subdirectory of the current directory. This is standard Linux.

```
$jar -cvf temperature.jar -C  temperature_classes/  .

```

Remove the output directory from the distributed file system.
```
$hadoop dfs -rmr /user/userID/output
```

Merge and copy files from the Hadoop Distributed File system to the client.

```
$hadoop dfs -getmerge /user/userID/output aCoolLocalFile
```

You may view what jobs are running on the cluster with the command:
```
$hadoop job -list

```

Kill a job that is not making progress (you may need to do this):
```

$bin/hadoop job -kill job_201310251241_0754
You will need the Job ID.

```

Execute a map reduce job on the cluster of machines. The file temperature.jar holds the map reduce code. Next,
a path to the class with a main routine is provided. Then, the input and output is specified. The input is a file
that must exist on the distributed file system and the output is a directory that will be created. It must
not exist before running the command or you will receive an exception.

```
$hadoop jar /home/userID/temperature.jar edu.cmu.andrew.mm6.MaxTemperature  /user/userID/input/combinedYears.txt /user/userID/output

```

We wish to get a copy of the content on the output directory. The first and third commands are standard Linux.
```
$mkdir coolProjectOutput

$hadoop dfs -getmerge /user/userID/output ~/coolProjectOutput/

$cat ~/coolProjectOutput/output

```

Note that the code below is defined within Java packages. So, when you compile
the source code the compiled (.class files) will be placed within directories
and sub directories.

:checkered_flag:**The proper submission of Project 5 is worth points on your Project 5 grade. In order to submit properly, your directory structure must be as described here:**

Make a directory in your home directory called Project5.

```
cd
mkdir Project5

```

Within the Project5 directory, make an additional subdirectory with the
name Part_1. Within the Part_1 directory make the following subdirectories: Task0,
Task1, Task2 and so on, all the way through Task8. Place all Task0 work within the
Task0 directory. Place all Task1 work within the Task1 directory and so on, all the way through Task8.

For Task0,

```
cd Project5
mkdir Part_1
cd Part_1
mkdir Task0
cd Task0

```

:checkered_flag:**This is the beginning of the actual assignment.**

## Part 1 Map Reduce Programming

### Task 0   

Compile and execute a MapReduce job developed from WordCount.java. The file
WordCount.java is found under /home/public/. The code is also available at
the bottom of this document. Copy it to your home directory. Compile it and
generate a jar file called wordcount.jar. Deploy the jar file and test it
against /home/public/words.txt. The file words.txt will need to be copied
to HDFS using Hadoop's copyFromLocal command.

Note, you have more than one output file on the cluster. Each output file is
generated by a reducer.

Examine the outputs of the three reducers with:

```
hadoop dfs -cat /user/userID/output/part-r-00000
hadoop dfs -cat /user/userID/output/part-r-00001
hadoop dfs -cat /user/userID/output/part-r-00002

```

The final output should be merged and left in
your /home/userID/Project5/Part_1/Task0/Task0Output file.

The grader will be looking for this merged result file.


### Task 1  

Working from WordCount.java, build and deploy a MapReduce application
named LetterCounter.java and place it into a jar file called lettercount.jar.
This program will compute the total number of each letter in the words.txt file. The output from the reducer will need to be merged and then sorted. The most
frequently occurring letter and its count will appear at the top of the file - sorted by decreasing frequency. The letter "e" is the most common letter and will appear at the top of the file.

The result will be left in your /home/userID/Project5/Part_1/Task1/Task1Output file. The grader will be looking for this merged result file.

Note that
WordCount.java (Task 0) uses a call to nextToken() on the iterator. Without this call,
the program will enter an infinite loop and will need to be killed.

### Task 2  

Modify the WordCount code so that it searches through words.txt and outputs any word that contains the string "fact". This is not a case sensitive search.
The final output will be a single file containing a list of words such as
artifact, artifactitious, and benefactive. If the word "Fact" appeared in the input (it does not), it too would be output. As was noted, the search is not case sensitive.

All of these words will be listed in the following file:
/home/userID/Project5/Part_1/Task2/Task2Output. The grader will be looking for this merged result file.

Here is the start of the final output with a few words that contain "fact":

```
artifact
artifactitious
benefactive
benefactor
bilifaction
:
:

```

It is expected that you will need to browse the web for help with this. That is OK but be sure to site your sources in the code.

### Task 3


In this task, we will use a fairly large dataset from Tom White's book.
The data file is located at "/home/public/combinedYears.txt". It contains thousands of temperature readings
(in Celsius times 10) in the USA by year from 1901 to 1902. The year is specified
in positions 15-18. The temperature is specified beginning at column 87 and begins
with a plus or minus character. Four digits are used for the temperature. Be sure
to study the code below to see how it references these locations.

Below are three files: MaxTemperature.java, MaxTemperatureMapper.java and MaxTemperatureReducer.java. These files may be found at /home/public. Copy these files to your
/home/userID/Project5/Part_1/Task3 directory.

Run this application against the data set under /home/public/combinedYears.txt. Your jar file will
be named temperature.jar.  The output should be left in your
/home/userID/Project5/Part_1/Task3/Task3Output file. The grader will be looking for this merged result file.

### Task 4

Modify the code from Task 3 and build a minimum temperature application. Note that the
temperatures in this file are degrees Celsius * 10.  Your jar file will be named
mintemperature.jar.  The output should be left in your
/home/userID/Project5/Part_1/Task4/Task4Output
file. The grader will be looking for this merged result file.

### Task 5

Within the /home/public directory, there is a file called P1V.txt.

P1V.txt is a tab delimited text file with a individual criminal offense incidents
from January 1990 through December 1999 for serious violent crimes (FBI Part 1) in Pittsburgh.

The first two columns (X,Y) represent State Plane (projected, rectilinear) coordinates (measured
in feet) specifying the location of the crime.
The third column is the time.
The fourth column is a street address.
The fifth column is the type of offense (aggravated assault, Robbery, Rape, Etc.)
The sixth column is the date.
The seventh column is the 2000 census tract.

Write a MapReduce application that finds the total number of rapes and
robberies. If there were 100 rapes and 50 robberies then this program
would generate the value 150 to an output file.

Your jar file will be named rapesplusrobberies.jar. The output should be left
in your /home/userID/Project5/Part_1/Task6/Task5Output file. The grader will be looking for this merged result file.

### Task 6

Modify your solution to Task 5 so that it finds the total number of aggravated assault crimes that occurred within
200 meters of 3803 Forbes Avenue in Oakland. This location has the (X,Y) coordinates of
(1354326.897,411447.7828). Use these coordinates and the Pythagorean theorem to decide if a particular
aggravated assault occurred within 200 meters of 3803 Forbes Avenue. State plane coordinates are measured in feet.
Your code is testing on meters.

Your jar file will be named oaklandcrimestats.jar. The output should be left
in your /home/userID/Project5/Part_1/Task6/Task6Output file. The grader will be looking for this merged result file.

### Task 7


In Task 7, the input file is named CrimeLatLonXYTabs.txt. It can be copied from the /home/public directory.
Its format is similar to P1V.txt but also includes the latitude and longitude of each crime.

CrimeLatLonXYTabs.txt is a tab delimited text file with a individual criminal offense incidents
from January 1990 through December 1999 for serious violent crimes (FBI Part 1) in Pittsburgh.

The first two columns (X,Y) represent State Plane (projected, rectilinear) coordinates (measured
in feet) specifying the location of the crime.
The third column is the time.
The fourth column is a street address.
The fifth column is the type of offense (aggravated assault, rape, Etc.)
The sixth column is the date.
The seventh column is the 2000 census tract.
The eight column specifies the latitude.
The ninth column specifies the longitude.

These last two columns are used for viewing in GIS tools (such as Google Earth Pro).

Modify your solution to Task 5 so that it finds all of the aggravated assault
crimes that occurred within 300 meters of 3803 Forbes Avenue in Oakland. This location has
the (X,Y) coordinates of (1354326.897,411447.7828). Use these coordinates and the Pythagorean
theorem to decide if a particular aggravated assault occurred within 300 meters of 3803 Forbes
Avenue. State plane coordinates are measured in feet. Your code is testing on meters.

Your jar file will be named oaklandcrimestatskml.jar. The output file will be a well formed KML file
suitable for viewing in Google Earth. The KML file will be used to display each of these crimes on
a map. The output file will be left in your /home/userID/Project5/Part_1/Task7/Task7Output file. The grader will be looking for this merged result file.

You will also need to provide a screenshot showing Google Earth viewing the KML. Include the screenshot on your pdf that is submitted to Canvas.

You should consider limiting the number of reducers to 1. You can do this by
generating the same key from each mapper.

### Part 1 Summary

Part 1 will be graded by carefully inspecting tasks on the cluster.

### WordCount.java

```
// ======================= WordCount.java ==========================================
package org.myorg;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.*;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class WordCount extends Configured implements Tool {

        public static class WordCountMap extends Mapper<LongWritable, Text, Text, IntWritable>
        {
                private final static IntWritable one = new IntWritable(1);
                private Text word = new Text();

                @Override
                public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
                {
                        String line = value.toString();
                        StringTokenizer tokenizer = new StringTokenizer(line);
                        while(tokenizer.hasMoreTokens())
                        {
                                word.set(tokenizer.nextToken());
                                context.write(word, one);
                        }
                }
        }

        public static class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>
        {
                public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException
                {
                        int sum = 0;
                        for(IntWritable value: values)
                        {
                                sum += value.get();
                        }
                        context.write(key, new IntWritable(sum));
                }

        }

        public int run(String[] args) throws Exception  {

                Job job = new Job(getConf());
                job.setJarByClass(WordCount.class);
                job.setJobName("wordcount");

                job.setOutputKeyClass(Text.class);
                job.setOutputValueClass(IntWritable.class);

                job.setMapperClass(WordCountMap.class);
                job.setReducerClass(WordCountReducer.class);


                job.setInputFormatClass(TextInputFormat.class);
                job.setOutputFormatClass(TextOutputFormat.class);


                FileInputFormat.setInputPaths(job, new Path(args[0]));
                FileOutputFormat.setOutputPath(job, new Path(args[1]));

                boolean success = job.waitForCompletion(true);
                return success ? 0: 1;
        }


        public static void main(String[] args) throws Exception {
                // TODO Auto-generated method stub
                int result = ToolRunner.run(new WordCount(), args);
                System.exit(result);
        }

}

```
### MaxTemperatureMapper.java

```

// ============== MaxTemperatureMapper.java ================================
  package edu.cmu.andrew.mm6;
  import java.io.IOException;
	import org.apache.hadoop.io.IntWritable;
	import org.apache.hadoop.io.LongWritable;
	import org.apache.hadoop.io.Text;
	import org.apache.hadoop.mapred.MapReduceBase;
	import org.apache.hadoop.mapred.Mapper;
	import org.apache.hadoop.mapred.OutputCollector;
	import org.apache.hadoop.mapred.Reporter;

	public class MaxTemperatureMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
		private static final int MISSING = 9999;
		public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws 			IOException {

                        // Get line from input file. This was passed in by Hadoop as value.
                        // We have no use for the key (file offset) so we are ignoring it.

			String line = value.toString();

                        // Get year when weather data was collected. The year is in positions 15-18.
                        // This field is at a fixed position within a line.
			String year = line.substring(15, 19);

                        // Get the temperature too.

			int airTemperature;
			if (line.charAt(87) == '+') { // parseInt doesn't like leading plus signs
				airTemperature = Integer.parseInt(line.substring(88, 92));
			} else {
				airTemperature = Integer.parseInt(line.substring(87, 92));
                        }

                        // Get quality of reading. If not missing and of good quality then
                        // produce intermediate (year,temp).

                        String quality = line.substring(92, 93);
			if (airTemperature != MISSING && quality.matches("[01459]")) {

                             // for each year in input, reduce will be called with
                             // (year,[temp,temp,temp, ...])
                             // They key is year and the list of temps will be placed in an iterator.

				output.collect(new Text(year), new IntWritable(airTemperature)); }
			}
	 }
```
### MaxTemperatureReducer.java

```

=========== MaxTemperatureReducer.java ====================================================
	package edu.cmu.andrew.mm6;
  import java.io.IOException;
  import java.util.Iterator;
	import org.apache.hadoop.io.IntWritable;
	import org.apache.hadoop.io.Text;
	import org.apache.hadoop.mapred.MapReduceBase;
	import org.apache.hadoop.mapred.OutputCollector;
	import org.apache.hadoop.mapred.Reducer; import org.apache.hadoop.mapred.Reporter;

	public class MaxTemperatureReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {

		public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text,
                                   IntWritable> output, Reporter reporter) throws IOException {

                        // from the list of values, find the maximum
                        int maxValue = Integer.MIN_VALUE;
                        while (values.hasNext()) {
			     maxValue = Math.max(maxValue, values.next().get());
                        }
                        // emit (key = year, value = maxTemp = max for year)
			output.collect(key, new IntWritable(maxValue));
		}
	}

```
### MaxTemperature.java

```
// ======= And, to get it all running and tied together: MaxTemperature.java ============

  package edu.cmu.andrew.mm6;
	import org.apache.hadoop.fs.Path;
	import org.apache.hadoop.io.IntWritable;
	import org.apache.hadoop.io.Text;
	import org.apache.hadoop.mapred.FileInputFormat;
	import org.apache.hadoop.mapred.FileOutputFormat; import org.apache.hadoop.mapred.JobClient;
	import org.apache.hadoop.mapred.JobConf;

	public class MaxTemperature {
		public static void main(String[] args) throws IOException {
			if (args.length != 2) {
				System.err.println("Usage: MaxTemperature <input path> <output path>");
				System.exit(-1);
 			}
			JobConf conf = new JobConf(MaxTemperature.class);
			conf.setJobName("Max temperature");
			FileInputFormat.addInputPath(conf, new Path(args[0]));
			FileOutputFormat.setOutputPath(conf, new Path(args[1]));
			conf.setMapperClass(MaxTemperatureMapper.class);
			conf.setReducerClass(MaxTemperatureReducer.class);
			conf.setOutputKeyClass(Text.class);
			conf.setOutputValueClass(IntWritable.class);
			JobClient.runJob(conf);
		 }
	}

```

## Part 2 Spark Programming

Suppose we are given the following input file:
```
ABCD
EFGH
IJK LMN OP;/
QRST U
V WXYZ
WXYZ
abcd

cool beans
```

We will write a Spark program that generates the following counts:

```
Number of lines: 9
Number of words: 13
Number of distinct words: 12
Number of symbols: 51
Number of distinct symbols: 39
Number of distinct letters: 35
```

In this part, we will be running Spark within IntelliJ. This is similar to what
we did in Lab 9. However, in this part, we need to use JDK 8 rather than
JDK 16. There is a known issue with using Spark and JDK 16.

When you first run IntelliJ, be sure to select a JDK 8 compiler (JDK 1.8 is the same thing).

We will be using a data file found on the course schedule. This data file is "The Tempest" by William Shakespeare. The file is found at the following link:

[The Tempest](http://www.andrew.cmu.edu/course/95-702/homework/data/SparkDataFiles/TheTempest.txt)

You need to download "The Tempest" to your local machine and set both your working directory and the file name in IntelliJ. See Lab 9 for detailed directions on setting up IntelliJ to run a Spark application with an input file.

[Lab 9](https://github.com/CMU-Heinz-95702/lab9-MapReduceAndSpark)

### Part 2 Summary

Write a Java program that uses Spark to read The Tempest and perform various calculations. The name of the program is TempestAnalytics.java.

Simply add each task below to the code in the file TempestAnalytics.java. This one file will contain all of the functionality listed here as separate tasks.

Include your labelled and well-formatted code in the pdf submission. Include an output interaction as well. The output will be a screen scrape of the IntelliJ output window. At the bottom, the interaction will show the results of searching for the string "love" in "The Tempest". The string "love" appears, for example, in the word "cloven". The string "love" does not appear in the string "Love". Our search is case-sensitive. See Task 6.

The screen scrape of the output must include all of the "Info" lines that are generated by IntelliJ. Your program output will be interspersed in this output.

For debugging and development, it is a good idea (but not required) to pepper your development code with lines that write RDD's to a file. It is fine if you leave such code in your final submission. The following is an example from my solution:

```
 pairData.saveAsTextFile("002_WordsPairedWith1");

```

### Documentation

TempestAnalytics.java needs to be well documented. It must begin with the author's name and an overall description. Each line of code needs to be described in your own words.

### Task 0.

Using the count method of the JavaRDD class, display the number of lines in "The Tempest".

Write this output to the screen with System.out.println().

### Task 1.

Using the split method of the java String class and the flatMap method of the JavaRDD class,
use the count method of the JavaRDD class to display the number of words in The Tempest. So that we are all on the same page, be sure to use
the string "[^a-zA-Z]+" as the regular expression delimiter in your split method.

In Task 1 and Task 2, you might also make good use of a filter function:

```
Function<String, Boolean> filter = k -> ( !k.isEmpty());

```

Write this output to the screen with System.out.println().


### Task 2.

Using some of the work you did above and the JavaRDD distinct() and count() methods, display
the number of distinct words in The Tempest.

Write this output to the screen with System.out.println().

### Task 3.

Use the split method with a regular expression of "" and a flatmap to find the number of symbols in The Tempest.

Write this output to the screen with System.out.println().

### Task 4.

Find the number of distinct symbols in The Tempest.

Write this output to the screen with System.out.println().

### Task 5.

Find the number of distinct letters in The Tempest.

Write this output to the screen with System.out.println().

### Task 6.

This is an interactive piece. Ask your user to enter a word and show all of the lines of The Tempest that contain that word. The search will be case-sensitive. If, for example, the user enters the word "love", she would see such lines as:

```
BOATSWAIN  None that I more love than myself. You are
Of all the world I loved, and to him put
So dear the love my people bore me, nor set
:
:

```
But she would see no lines with an uppercase "Love".

Interact with the user and write this output to the screen with System.out.println().

### Part 2 Summary

Part 2 will be graded by carefully inspecting the single program and the output (including INFO lines).

### Debugging

Output from System.out.println() statements is available in log files.  
To view the log visit:

http://heinz-jumbo.heinz.cmu.local:50030/jobtracker.jsp

Find your completed job in the list. On the left, click on the job ID.
Select the map or reduce task.
Select the task number and then the task logs.
