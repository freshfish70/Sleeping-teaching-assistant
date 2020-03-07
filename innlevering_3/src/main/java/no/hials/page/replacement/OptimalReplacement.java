package no.hials.page.replacement;

import java.util.ArrayList;
import java.util.List;

/**
 * Optimal Replacement algorithm
 * Fill in your code in this class!
 */
public class OptimalReplacement extends ReplacementAlgorithm {

    // TODO - add some state variables here, if you need any

    @Override
    protected void reset() {
        System.out.println("reset");
        // TODO - do preparation/initilization here, if needed
    }

    @Override
    public int process(String referenceString) {
        List<Integer> pageReferences = Tools.stringToArray(referenceString);
        if (pageReferences == null) return 0;

        int replacements = 0; // How many page replacements made
        int maxFrames = this.frames.length;
        for (int i = 0; i < pageReferences.size(); i++) {
            int newPage = pageReferences.get(i);
            int frameIndex = 0;
            boolean pageInserted = false;
            while (frameIndex < maxFrames && this.frames[frameIndex] != newPage && !pageInserted) {
                if (this.frames[frameIndex] == EMPTY) {
                    this.frames[frameIndex] = newPage;
                    pageInserted = true;
                } else {
                    int longestIndex = EMPTY;
                    int replaceWith = EMPTY;

                    for (int j = 0; j < this.frames.length; j++) {
                        int currentFrameToFind = this.frames[j];
                        for (int k = i + 1; k < pageReferences.size(); k++) {
                            if (pageReferences.get(k) == currentFrameToFind) {
                                if (longestIndex < j) {
                                    longestIndex = j;
                                    replaceWith = currentFrameToFind;
                                }
                            }
                        }
                    }

                    if (longestIndex != EMPTY && replaceWith != EMPTY){
                        this.frames[frameIndex] = replaceWith;
                        pageInserted = true;
                    }
                }
                frameIndex++;
            }
            StringBuilder b = new StringBuilder();
            for (int j = 0; j < frames.length; j++) {
                b.append("FRAME: ");
                b.append(frames[j]);
                b.append(" : ");
            }
            System.out.println(b.toString());
        }

        return replacements;
    }


    // TODO - create any helper methods here if you need any

}
