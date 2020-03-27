package no.hials.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Some helping tools
 * @author Girts Strazdins 
 * girts.strazdins@gmail.com
 * 2016-03-11 
 */
public class Tools {

    /**
     * Converts a comma-separated string to integer list
     * Cuts out all spaces, ignores empty elements, letters and other non-numeric info
     * If there is an error, returns null
     * @param s
     * @return 
     */
    public static List<Integer> stringToArray(String s) {        
        ArrayList<Integer> elements = new ArrayList<>();

        if (s == null) return null;
        // Remove all spaces
        s = s.replace(" ", "");
        if (s.equals("")) return elements;
        
        // Split the string by commas
        String[] parts = s.split(",");
        
        // Convert to integer type, take onli valid integers
        for (int i = 0; i < parts.length; ++i) {
            try {
                if (parts[i] != null && !parts[i].equals("")) {
                    int v = Integer.parseInt(parts[i]);
                    elements.add(v);
                }
            } catch (NumberFormatException e) {
                // Just skip this value
            }
        }               
        
        return elements;
    }
    
}
