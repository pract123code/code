import java.util.*;
class PriorityScheduling {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int pid[] = new int[n];
        int bt[] = new int[n];
        int pr[] = new int[n];
        int wt[] = new int[n];
        int tat[] = new int[n];
        int ct[] = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time and priority for process " + (i + 1) + ": ");
            bt[i] = sc.nextInt();
            pr[i] = sc.nextInt();
            pid[i] = i + 1;
        }

        // Sort by priority (lower number = higher priority)
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (pr[j] > pr[j + 1]) {
                    int temp = pr[j];
                     pr[j] = pr[j + 1]; 
                     pr[j + 1] = temp;
                    temp = bt[j]; bt[j] = bt[j + 1]; bt[j + 1] = temp;
                    temp = pid[j]; pid[j] = pid[j + 1]; pid[j + 1] = temp;
                }
            }
        }

        // Calculate waiting time and turnaround time
        wt[0] = 0;
        for (int i = 1; i < n; i++)
            wt[i] = wt[i - 1] + bt[i - 1];

        for (int i = 0; i < n; i++)
            tat[i] = wt[i] + bt[i];

        // Display result
        System.out.println("\nPID\tBT\tPR\tWT\tTAT");
        for (int i = 0; i < n; i++)
            System.out.println(pid[i] + "\t" + bt[i] + "\t" + pr[i] + "\t" + wt[i] + "\t" + tat[i]);
 }
}
