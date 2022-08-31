public class PrintHelper {
    public void generateGanttChart(int totalTime, int currentProcess, int[] ganttChart, int currentTime) {
        // A. Between values for gantt chart
        if(currentTime != 0)
        {
            // This groups the same slices of a process.
            if(currentProcess != ganttChart[currentTime - 1])
            {
                System.out.print("=== " + currentTime + " ===P" + currentProcess + "");
            }
        }
        // B. Starting the gantt chart （always 0 because we are assuming arrival times are always 0.
        else
            System.out.print(0 + " ===P" + currentProcess);
        // C. Finalizing the gantt chart
        if(currentTime == totalTime - 1)
            System.out.print("=== " + (currentTime + 1));
    }
    public void getTurnaroundTime(int[] turnAroundTime, int numberOfProcesses) {
        System.out.println();
        float totalWaitTime = 0;
        for(int i = 1; i <= numberOfProcesses; i++)
        {
            totalWaitTime += turnAroundTime[i];
        }
        totalWaitTime = totalWaitTime / numberOfProcesses;
        System.out.println("√ Average Turnaround Time: " + totalWaitTime + " + ");
    }
}
