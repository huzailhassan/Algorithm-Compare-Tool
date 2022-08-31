import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RoundRobin {
    private int numberOfProcesses;
    private int[] burstTimes;
    private int slice;
    public RoundRobin(int numberOfProcesses, int[] burstTimes, int slice) throws IOException {
        // 1. Use class scoped variables.
        this.numberOfProcesses = numberOfProcesses;
        this.burstTimes = burstTimes;
        this.slice = slice;

        /* 2. Create an object of the printHelper class that we will use to generate the gantt
        chart and print the average turnaround time. */
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

        /* 6. Create a currentSlice integer variable. This will be used to show how much of the slice has
        finished for the process specific to that cycle. Create currentProcess integer variable and set it to 1. */
        int currentProcess = 1;
        int currentSlice = 0;

        // 7. This is the meat of the program. Create a for loop that will go until the totalTime.
        for(int i = 0; i < totalTime; i++)
        {
            /* 7a. Mark on the ganttChart that the index has been taken by the currentProcess.
            (The current position on the gantt chart is set to the for loop’s ‘i’ variable and
            is set to currentProcess. */
            ganttChart[i] = currentProcess;

            // 7b. Reduce burstTimes (set on the current process’ index)
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

            /* 7e. If currentSlice equals the slice, the process is finished for that round.
            Set currentSlice to 0.  If not, increase currentSlice by 1 */
            currentSlice++;
            if(currentSlice == slice || this.burstTimes[currentProcess] == 0)
            {
                currentSlice = 0;

                // 7f. If the burstTimes for the current process is finished, increase currentProcess by 1
                for(int j = 1; j <= this.numberOfProcesses; j++)
                {
                    currentProcess++;
                    // 7g. Check if once cycle has been done yet. If yes, change currentProcess back to 1, so we can do the cycle of processes over again.
                    if(currentProcess == (this.numberOfProcesses + 1))
                        currentProcess = 1;
                    // 7h. If the process still has burst time left, break out of the for loop.
                    if(this.burstTimes[currentProcess] != 0)
                        break;
                }
            }
        }

        // 8 (Final Step). Get the average turnaround time.
        printHelper.getTurnaroundTime(turnAroundTime, numberOfProcesses);

    }
}
