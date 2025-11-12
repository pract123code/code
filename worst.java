          import java.util.*;

class Worst {
    public static void main(String[] args) {

        int blockSize[] = {100, 500, 200, 300, 600};
        int processSize[] = {212, 417, 112, 426};

        int m = blockSize.length; // number of blocks
        int n = processSize.length; // number of processes

        int allocation[] = new int[n]; // store allocated block for each process

        // Initially, no process is allocated
        for (int i = 0; i < n; i++) {
            allocation[i] = -1;
        }

        // Worst Fit Logic
        for (int i = 0; i < n; i++) {       // for each process
            int worstIdx = -1;              // index of largest fitting block

            for (int j = 0; j < m; j++) {   // check each block
                if (blockSize[j] >= processSize[i]) {
                    if (worstIdx == -1 || blockSize[j] > blockSize[worstIdx]) {
                        worstIdx = j;       // pick the largest block
                    }
                }
            }

            // allocate process to the largest block found
            if (worstIdx != -1) {
                allocation[i] = worstIdx;
                blockSize[worstIdx] -= processSize[i]; // reduce size of that block
            }
        }

        // Display result
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
