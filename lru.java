import java.util.*;

class LRU
 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of pages and their reference string
        System.out.print("Enter number of pages: ");
        int totalPages = sc.nextInt();

        int[] pages = new int[totalPages];
        System.out.println("Enter reference string (page numbers):");
        for (int i = 0; i < totalPages; i++)
            pages[i] = sc.nextInt();

        // Input number of frames
        System.out.print("Enter number of frames: ");
        int frameCount = sc.nextInt();

        int[] frames = new int[frameCount];
        int[] lastUsed = new int[frameCount];  // to store last used time of each frame
        Arrays.fill(frames, -1); // initially all frames are empty

        int time = 0, pageFaults = 0;

        // Process each page one by one
        for (int page : pages) {
            time++; // increase time for every page access
            boolean found = false;

            // Check if page already present in any frame
            for (int i = 0; i < frameCount; i++) {
                if (frames[i] == page) {
                    found = true; // page hit
                    lastUsed[i] = time; // update time of last use
                    break;
                }
            }

            //  If page not found → page fault
            if (!found) {
                int replaceIndex = -1;

                // (a) Try to find an empty frame
                for (int i = 0; i < frameCount; i++) {
                    if (frames[i] == -1) {
                        replaceIndex = i;
                        break;
                    }
                }

                // (b) If no empty frame → find least recently used one
                if (replaceIndex == -1) {
                    int oldestTime = lastUsed[0];
                    replaceIndex = 0;
                    for (int i = 1; i < frameCount; i++) {
                        if (lastUsed[i] < oldestTime) {
                            oldestTime = lastUsed[i];
                            replaceIndex = i;
                        }
                    }
                }

                // Replace the page
                frames[replaceIndex] = page;
                lastUsed[replaceIndex] = time;
                pageFaults++;
            }

            // 3️⃣ Show current frame status
            System.out.print("After page " + page + " → ");
            for (int f : frames) {
                if (f == -1) System.out.print("[ ] ");
                else System.out.print("[" + f + "] ");
            }
            System.out.println();
        }

        System.out.println("\nTotal Page Faults = " + pageFaults);
        sc.close();
    }
}
