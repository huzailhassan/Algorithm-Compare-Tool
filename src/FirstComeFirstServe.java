import java.io.*;
import java.util.Arrays;

public class FirstComeFirstServe {
    private int numberOfProcesses;
    private int[] burstTimes;
    public FirstComeFirstServe(int numberOfProcesses, int[] burstTimes) throws IOException
    {
        // 1. Use class scoped variables.
        this.numberOfProcesses = numberOfProcesses;
        this.burstTimes = burstTimes;

        /* 2. Create an object of the printHelper class that we will use to generate the gantt
        chart and print the average turnaround time */
        PrintHelper printHelper = new PrintHelper();

        /* 3. Create a turnAroundTime array variable. Also create the burstTimes array variable,
        which will be working in accordance to a turnAroundTime array that will be created to
        store the turnaround time for each process. (turnAroundTime[i] will have the turnaround
        time for burstTimes[i + 1]). The turnaround time will be used at the end of the constructor
        to print the average turnaround time.
         */
        int[] turnAroundTime = new int[this.numberOfProcesses + 1];


        /* 4. Add up all the burst times from all the processes in totalTime integer value.  */
        int totalTime = 0;
        for(int i = 1; i <= this.numberOfProcesses; i++)
        {
            totalTime += this.burstTimes[i];
        }

        /* 5. Create a ganttChart array variable that will map which time slots are used for what process.
        Will be set to the same size of the totalTimes variable. */
        int[] ganttChart = new int[totalTime];

        // 6. Create currentProcess integer variable and set it to 1.
        int currentProcess = 1;

        // 7. This is the meat of the program. Every cycle, the ganttChart array will be updated with whatever index marked by the process number.
        for(int i = 0; i < totalTime; i++)
        {
            /* 7a. Mark on the ganttChart that the index has been taken by the currentProcess.
            (The current position on the gantt chart is set to the for loop’s ‘i’ variable and
            is set to currentProcess. */
            ganttChart[i] = currentProcess;

            // 7b.  Reduce burstTimes (set on the current process’ index)
            this.burstTimes[currentProcess]--;

            // 7c. Add onto the turnAroundTime (set on the current process’ index)
            for(int j = 1; j <= this.numberOfProcesses; j++)
            {
                if(this.burstTimes[j] != 0 || j == currentProcess)
                {
                    turnAroundTime[j]++;
                }
            }

            // 7d. Call printHelper object to print the part onto the gantt chart.
            printHelper.generateGanttChart(totalTime, currentProcess, ganttChart, i);

            // 7e. If the burstTimes for the current process is finished, increase currentProcess by 1
            if(this.burstTimes[currentProcess] == 0)
                currentProcess++;

        }
        System.out.println();

        // 8 (Final Step). Get the average turnaround time.
        printHelper.getTurnaroundTime(turnAroundTime, numberOfProcesses);
    }
}