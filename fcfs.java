import java.util.*;

class FCFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int pid[] = new int[n];   // process IDs
        int at[] = new int[n];    // arrival time
        int bt[] = new int[n];    // burst time
        int ct[] = new int[n];    // completion time
        int tat[] = new int[n];   // turnaround time
        int wt[] = new int[n];    // waiting time

        // --- Step 1: Input ---
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Process " + (i + 1));
            pid[i] = i + 1;
            System.out.print("Arrival Time: ");
            at[i] = sc.nextInt();
            System.out.print("Burst Time: ");
            bt[i] = sc.nextInt();

        }


        // --- Step 2: Sort by Arrival Time ---
for (int i = 0; i < n - 1; i++) {
    for (int j = i + 1; j < n; j++) {
        if (at[i] > at[j]) {
            // swap AT, BT, and PID
            int temp;
            temp = at[i]; at[i] = at[j]; at[j] = temp;
            temp = bt[i]; bt[i] = bt[j]; bt[j] = temp;
            temp = pid[i]; pid[i] = pid[j]; pid[j] = temp;
        }
    }
}

        // --- Step 3: Calculate CT, TAT, WT ---
        ct[0] = at[0] + bt[0]; // first process
        for (int i = 1; i < n; i++) {
            if (at[i] > ct[i - 1])                //This means → the next process arrives after the previous one finished.
                ct[i] = at[i] + bt[i]; // CPU idle till next process arrives
            else
                ct[i] = ct[i - 1] + bt[i];
        }

        for (int i = 0; i < n; i++) {
            tat[i] = ct[i] - at[i];  // Turnaround time
            wt[i] = tat[i] - bt[i];  // Waiting time
        }

        // --- Step 4: Display Table ---
        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++)
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);

        // --- Step 5: Gantt Chart ---
        System.out.print("\nGantt Chart: ");
        for (int i = 0; i < n; i++)
            System.out.print("P" + pid[i] + " | ");
        System.out.println();

        System.out.print("0");
        for (int i = 0; i < n; i++)
            System.out.print("   " + ct[i]);
    }
}
