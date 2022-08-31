import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        // 1. Get the Job.txt
        BufferedReader in = new BufferedReader(new FileReader("job.txt"));
        String str;

        List<String> list = new ArrayList<String>();
        while ((str = in.readLine()) != null) {
            list.add(str);
        }

        // 2. Get every other line in the loaded file
        String[] stringArr = list.toArray(new String[0]);
        ArrayList<String> stringArrNew = new ArrayList<String>();
        for (int i = 1; i < stringArr.length; i = i + 2) {
            stringArrNew.add(stringArr[i]);
        }

        // 3. Convert the string arraylist to an integer arraylist
        ArrayList<Integer> intArr = new ArrayList<Integer>();

        for (int i = 0; i < stringArrNew.size(); i++) {
            intArr.add(Integer.parseInt(stringArrNew.get(i)));
        }

        // 4. We need an array that will have the process IDs. The first element is '1', second '2', and so on till the amount of jobs
        ArrayList<Integer> v = new ArrayList<>(0);
        for (int i = 0; i < intArr.size(); i++)
            v.add(i + 1);

        // 5. Convert the processes Integer arraylist to a primitive int  array
        int numberOfProcesses = v.size();
        if (numberOfProcesses <= 30) {
            // 6. Convert the burst-time Integer arraylist to a primitive int array Burst times
            int[] burst_time = intArr.stream().mapToInt(i -> i).toArray();

            int[] new_burst_time = new int[burst_time.length + 1];
            new_burst_time[1] = burst_time[0];
            if (numberOfProcesses - 1 >= 0)
                System.arraycopy(burst_time, 1, new_burst_time, 2, numberOfProcesses - 1);

            FirstComeFirstServe firstComeFirstServe = new FirstComeFirstServe(numberOfProcesses, new_burst_time);

            new_burst_time = new int[burst_time.length + 1];
            new_burst_time[1] = burst_time[0];
            if (numberOfProcesses - 1 >= 0)
                System.arraycopy(burst_time, 1, new_burst_time, 2, numberOfProcesses - 1);

            ShortestJob shortestJob = new ShortestJob(numberOfProcesses, new_burst_time);

            new_burst_time = new int[burst_time.length + 1];
            new_burst_time[1] = burst_time[0];
            if (numberOfProcesses - 1 >= 0)
                System.arraycopy(burst_time, 1, new_burst_time, 2, numberOfProcesses - 1);

            RoundRobin roundRobin = new RoundRobin(numberOfProcesses, new_burst_time, 2);

            new_burst_time = new int[burst_time.length + 1];
            new_burst_time[1] = burst_time[0];
            if (numberOfProcesses - 1 >= 0)
                System.arraycopy(burst_time, 1, new_burst_time, 2, numberOfProcesses - 1);

            RoundRobin roundRobin2 = new RoundRobin(numberOfProcesses, new_burst_time, 5);

//         7. Display possible Algorithms
            System.out.println("Which Algorithm?");
            System.out.println("a. First-Come-First-Serve (FCFS)");
            System.out.println("b. Shortest-Job-First (SJF)");
            System.out.println("c. Round-Robin with Time Slice = 2 (RR-2)");
            System.out.println("d. Round-Robin with Time Slice = 5 (RR-5)");

            // 8. Create scanner object for input
            Scanner keyboard = new Scanner(System.in);
            String input = keyboard.nextLine();




//            switch (input) {
//                case "a" -> {
//                    FirstComeFirstServe firstComeFirstServe = new FirstComeFirstServe(numberOfProcesses, new_burst_time);
//                }
//                case "b" -> {
//                    ShortestJob shortestJob = new ShortestJob(numberOfProcesses, new_burst_time);
//                }
//                case "c" -> {
//                    RoundRobin roundRobin = new RoundRobin(numberOfProcesses, new_burst_time, 2);
//                }
//                case "d" -> {
//                    RoundRobin roundRobin = new RoundRobin(numberOfProcesses, new_burst_time, 5);
//                }
//                default -> System.out.println("Invalid Choice");
//            }
        }
        else {
            System.out.println("Sorry, we do not accept more than 30 jobs.");
        }

    }
}





