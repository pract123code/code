class FIFO {
    public static void main(String[] args) {

        // Page reference string
        int pages[] = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1};

        int frameCount = 3;             // Number of frames (memory slots)
        int frames[] = new int[frameCount]; // Array to store pages in frames
        int pageFaults = 0;             // Count of page faults
        int nextReplace = 0;            // Index to replace next (FIFO order)

        // Initialize all frames as empty (-1)
        for (int i = 0; i < frameCount; i++) {
            frames[i] = -1;
        }

        System.out.println("Page\tFrames");

        // Go through each page
        for (int i = 0; i < pages.length; i++) {
            int currentPage = pages[i];
            boolean found = false;

            // Check if the page is already present
            for (int j = 0; j < frameCount; j++) {
                if (frames[j] == currentPage) {
                    found = true;  // Page hit (no fault)
                    break;
                }
            }

            // If not found → page fault
            if (!found) {
                frames[nextReplace] = currentPage;   // Replace oldest page
                nextReplace = (nextReplace + 1) % frameCount; // Move circularly
                pageFaults++;
            }

            // Print current page and frame contents
            System.out.print(currentPage + "\t");
            for (int j = 0; j < frameCount; j++) {
                if (frames[j] == -1)
                    System.out.print("- ");
                else
                    System.out.print(frames[j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nTotal Page Faults = " + pageFaults);
    }
}
