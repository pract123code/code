import java.util.*;

class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int pid[] = new int[n];
        int bt[] = new int[n];
        int rem_bt[] = new int[n];
        int wt[] = new int[n];
        int tat[] = new int[n];

        System.out.print("Enter time quantum: ");
        int tq = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            bt[i] = sc.nextInt();
            rem_bt[i] = bt[i];  // remaining time = burst time initially
            pid[i] = i + 1;
        }

        int t = 0; // current time

        // Keep looping until all processes are done
        while (true) {
            boolean done = true;

            for (int i = 0; i < n; i++) {
                if (rem_bt[i] > 0) {
                    done = false; // there is a pending process

                    if (rem_bt[i] > tq) {
                        t += tq;          // run for one time quantum
                        rem_bt[i] -= tq;  // decrease remaining time
                    } else {
                        t += rem_bt[i];          // finish the process
                        wt[i] = t - bt[i];       // waiting time = total time - burst time
                        rem_bt[i] = 0;           // process completed
                    }
                }
            }

            if (done) // all processes finished
                break;
        }

        // Calculate turnaround time
        for (int i = 0; i < n; i++)
            tat[i] = bt[i] + wt[i];

        // Display results
        System.out.println("\nPID\tBT\tWT\tTAT");
        for (int i = 0; i < n; i++)
            System.out.println(pid[i] + "\t" + bt[i] + "\t" + wt[i] + "\t" + tat[i]);
    }
}
