import java.io.*;
public class ShortestJob {
    private int numberOfProcesses;
    private int[] burstTimes;
    public ShortestJob(int numberOfProcesses, int[] burstTimes) throws IOException
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

        /* 5.  Create a ganttChart array variable that will map which time slots are used
        for what process. Will be set to the same size of the totalTimes variable.
.       */
        int[] ganttChart = new int[totalTime];

        // 6. This is the meat of the program. Create a for loop that will go until the totalTime.
        for(int i = 0; i < totalTime; i++)
        {
            // 6a. Search the smallest burst time process
            int currentProcess = 0;
            int min = 99999;
            for(int j = 1; j <= this.numberOfProcesses; j++)
            {
                if(this.burstTimes[j] < min && this.burstTimes[j] != 0)
                {
                    min = this.burstTimes[j];
                    currentProcess = j;
                }
            }

            /* 6b. Mark on the ganttChart that the index has been taken by the currentProcess.
            (The current position on the gantt chart is set to the for loop’s ‘i’ variable and
            is set to currentProcess. */
            ganttChart[i] = currentProcess;

            // 6c. Reduce burstTimes (set on the current process’ index)
            this.burstTimes[currentProcess]--;

            // 6d. Add onto the turnAroundTime (set on the current process’ index)
            for(int j = 1; j <= this.numberOfProcesses; j++)
            {
                if(this.burstTimes[j] != 0 || j == currentProcess)
                {
                    turnAroundTime[j]++;
                }
            }

            // 6e. Call printHelper object to print out the average turnaround time.
            printHelper.generateGanttChart(totalTime, currentProcess, ganttChart, i);

        }

        // 7 (Final Step). Get the average turnaround time.
        printHelper.getTurnaroundTime(turnAroundTime, this.numberOfProcesses);
    }



}