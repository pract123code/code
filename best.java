import java.util.*;

class Best {
    public static void main(String[] args) {

        // Direct input
        int blockSize[] = {100, 50, 30, 120, 35};
        int processSize[] = {20, 60, 70, 40};
        int m = blockSize.length;
        int n = processSize.length;

        int allocate[] = new int[n];
        for (int i = 0; i < n; i++)
            allocate[i] = -1; // Initially not allocated

        // Best Fit logic
        for (int i = 0; i < n; i++) {
            int bestIdx = -1;
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (bestIdx == -1 || blockSize[j] < blockSize[bestIdx])
                        bestIdx = j;
                }
            }
            if (bestIdx != -1) {
                allocate[i] = bestIdx;
                blockSize[bestIdx] -= processSize[i];
            }
        }
          // Display output
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
