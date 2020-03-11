package no.hials.page.replacement;

import java.util.List;

/**
 * Optimal Replacement algorithm Fill in your code in this class!
 */
public class OptimalReplacement extends ReplacementAlgorithm {

    @Override
    protected void reset() {
        System.out.println("reset");
    }

    @Override
    public int process(String referenceString) {
        List<Integer> pageReferences = Tools.stringToArray(referenceString);
        if (pageReferences == null)
            return 0;

        int replacements = 0; // How many page replacements made

        for (int i = 0; i < pageReferences.size(); i++) {
            int newPage = pageReferences.get(i);
            int nextReferenceIndex = i + 1;

            boolean pageInserted = doInsertIfEmptySlot(newPage);

            if (!pageInserted) {
                boolean pageIsInStack = isPageInFramesStack(newPage);
                if (!pageIsInStack) {
                    this.tryReplacePage(nextReferenceIndex, newPage, pageReferences);
                    replacements++;
                }
            }
            printCurrentFrame();
        }

        return replacements;
    }

    /**
     * Try replace a page in the page frames stack with a new one. It tries to find
     * a page from the page frames stack that is not gonna be used in the feature
     * from the pageReference list. If it finds a page in the page frame stack that
     * is not going to be used replace that frame with the new frame. If it all
     * frames are gonna be used in the featur,e find the page that is going to be
     * used last in the feature, and replace that page with the new page.
     * 
     * @param nextReferenceIndex the index of the next page of the pageReference
     * @param newPage            the new page to insert
     * @param pageReferences     page reference list
     */
    private void tryReplacePage(int nextReferenceIndex, int newPage, List<Integer> pageReferences) {
        int longestIndex = EMPTY;
        int indexToReplace = EMPTY;

        for (int j = 0; j < this.frames.length; j++) {
            int currentFrameToFind = this.frames[j];
            int frameIndexInFeature = frameExistInFeature(nextReferenceIndex, currentFrameToFind, pageReferences);
            if (frameIndexInFeature < 0) {
                indexToReplace = j;
                break;
            }
            for (int k = nextReferenceIndex; k < pageReferences.size(); k++) {
                if (pageReferences.get(k) == currentFrameToFind) {
                    if (longestIndex < k) {
                        longestIndex = k;
                        indexToReplace = j;
                    }
                    break;
                }
            }
        }
        this.frames[indexToReplace] = newPage;
    }

    /**
     * Checks if a frame exists in the feature. If it does return the index at its
     * location in the page reference else -1
     * 
     * @param startindex     the index to start at in the pageReference list
     * @param frameTofind    the frame to find in the reference
     * @param pageReferences the page reference list to search
     * @return index in page reference or -1 if not found
     */
    private int frameExistInFeature(int startindex, int frameTofind, List<Integer> pageReferences) {
        for (int i = startindex; i < pageReferences.size(); i++) {
            if (pageReferences.get(i) == frameTofind)
                return i;
        }
        return -1;
    }

    /**
     * Checks if the frame already exists in the page frame stack, returns true if
     * it exist else false
     * 
     * @param page the page to check if exists in the frame stack
     * @return true if exists elese false
     */
    private boolean isPageInFramesStack(int page) {
        for (int index = 0; index < this.frames.length; index++) {
            if (this.frames[index] == page) {
                return true;
            }
        }
        return false;
    }

    /**
     * Inserts a new frame int o the frame stack if there is an empty spot. Returns
     * true if it was inserted, else false.
     * 
     * @param newPage the page to try to insert
     * @return true if inserted, else false
     */
    private boolean doInsertIfEmptySlot(int newPage) {
        for (int index = 0; index < this.frames.length; index++) {
            if (this.frames[index] == EMPTY) {
                this.frames[index] = newPage;
                return true;
            }
        }
        return false;
    }

    /**
     *
     */
    private void printCurrentFrame(){
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < this.frames.length; j++) {
            sb.append("FRAME:  ");
            sb.append(frames[j]);
            sb.append("  :  ");
        }
        System.out.println(sb.toString());
    }

}
