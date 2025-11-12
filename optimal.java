import java.util.*;

class Optimal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        
        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();

       
        System.out.print("Enter reference string size: ");
        int size = sc.nextInt();

        int[] ref = new int[size];
        int[] memory = new int[frames];
        Arrays.fill(memory, -1); // initialize memory as empty (-1)

        
        System.out.println("Enter reference string:");
        for (int i = 0; i < size; i++) {
            ref[i] = sc.nextInt();
        }

        int hits = 0, faults = 0;

       
        for (int i = 0; i < size; i++) {
            int currentPage = ref[i];
            boolean pageFound = false;

            // Step 5: Check if page already exists in memory
            for (int j = 0; j < frames; j++) {
                if (memory[j] == currentPage) {
                    pageFound = true;
                    hits++;
                    break;
                }
            }

            // Step 6: If page not found → page fault
            if (!pageFound) {
                faults++;

                // Find an empty slot first
                boolean emptyFound = false;
                for (int j = 0; j < frames; j++) {
                    if (memory[j] == -1) {
                        memory[j] = currentPage;
                        emptyFound = true;
                        break;
                    }
                }

                // If no empty slot → replace page using OPTIMAL logic
                if (!emptyFound) {
                    int[] nextUse = new int[frames];
                    Arrays.fill(nextUse, Integer.MAX_VALUE);

                    // For each page in memory, find next time it will be used
                    for (int j = 0; j < frames; j++) {
                        for (int k = i + 1; k < size; k++) {
                            if (memory[j] == ref[k]) {
                                nextUse[j] = k;
                                break;
                            }
                        }
                    }

                    // Replace the page that will not be used for the longest time
                    int indexToReplace = 0;
                    for (int j = 1; j < frames; j++) {
                        if (nextUse[j] > nextUse[indexToReplace]) {
                            indexToReplace = j;
                        }
                    }

                    memory[indexToReplace] = currentPage;
                }
            }

            // Step 7: Print current memory status
            System.out.print(currentPage + " -> ");
            for (int j = 0; j < frames; j++) {
                if (memory[j] != -1)
                    System.out.print(memory[j] + " ");
                else
                    System.out.print("- ");
            }
            System.out.println();
        }

        // Step 8: Display results
        System.out.println("\nTotal Hits: " + hits);
        System.out.println("Total Faults: " + faults);
        System.out.printf("Hit Ratio: %.2f\n", (float) hits / size);
    }
}
