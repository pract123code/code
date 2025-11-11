import java.util.*;

class first {
    public static void main(String[] args) {

        int blockSize[] = {100, 50, 30, 120, 35};
        int processSize[] = {20, 60, 70, 40};

        int m = blockSize.length;  // number of blocks
        int n = processSize.length; // number of processes

        int allocate[] = new int[n]; // store which block each process gets

        // initially, no block is allocated
        for (int i = 0; i < n; i++) {
            allocate[i] = -1;
        }

        // First Fit Logic
        for (int i = 0; i < n; i++) {        // for each process
            for (int j = 0; j < m; j++) {    // check each block
                if (blockSize[j] >= processSize[i]) {  // if block fits
                    allocate[i] = j;                   // assign block j
                    blockSize[j] -= processSize[i];    // reduce space
                    break;                             // move to next process
                }
            }
        } 

        // Display allocation results
        System.out.println("Process No.\tProcess Size\tBlock No.");
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocate[i] != -1)
                System.out.println(allocate[i] + 1);
            else
                System.out.println("Not Allocated");
        }
    }
}
