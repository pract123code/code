import java.util.*;

class SJF_Preemptive_Simple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int pid[] = new int[n];
        int at[] = new int[n];
        int bt[] = new int[n];
        int rt[] = new int[n]; // remaining time
        int ct[] = new int[n]; // completion time
        int tat[] = new int[n];
        int wt[] = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nProcess " + (i + 1));
            pid[i] = i + 1;
            System.out.print("Arrival Time: ");
            at[i] = sc.nextInt();
            System.out.print("Burst Time: ");
            bt[i] = sc.nextInt();
            rt[i] = bt[i];
            
        }
        

        int time = 0, completed = 0;

        while (completed < n) {
            int shortest = -1, minTime = 9999;

            // find process with smallest remaining time which has arrived
            for (int i = 0; i < n; i++) {
                if (at[i] <= time && rt[i] > 0 && rt[i] < minTime) {
                    minTime = rt[i];
                    shortest = i;
                }
            }

            if (shortest == -1) { // no process yet
                time++;
                continue;
            }

            rt[shortest]--; // execute for 1 unit
            time++;

            // when process finishes
            if (rt[shortest] == 0) {
                completed++;
                ct[shortest] = time;
                tat[shortest] = ct[shortest] - at[shortest];
                wt[shortest] = tat[shortest] - bt[shortest];
            }
        }

        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" +
                    ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }

        sc.close();
    }
}
