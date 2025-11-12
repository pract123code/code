import java.util.*;

class NextFitSimple {
    public static void main(String[] args) {

        int blockSize[] = {5, 10, 20};
        int processSize[] = {10, 20, 5};

        int m = blockSize.length;  // number of blocks
        int n = processSize.length; // number of processes

        int allocation[] = new int[n];  // store block number for each process
        Arrays.fill(allocation, -1);    // initially, not allocated

        int j = 0;  // start checking from first block

        // Next Fit Logic
        for (int i = 0; i < n; i++) {   // for each process
            int count = 0;              // to avoid infinite loop

            while (count < m) {         // check all blocks once in circular order
                if (blockSize[j] >= processSize[i]) {
                    allocation[i] = j;               // allocate block j
                    blockSize[j] -= processSize[i];  // reduce remaining space
                    break;                           // move to next process
                }
                j = (j + 1) % m;  // go to next block circularly
                count++;
            }
        }

        // Display results
        System.out.println("Process No.\tProcess Size\tBlock No.");
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1)
                System.out.println(allocation[i] + 1);
            else
                System.out.println("Not Allocated");
        }
    }
}
