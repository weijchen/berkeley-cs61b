package com.company;

import java.io.File;
import java.util.*;

public class DemoCollections {

    /** Returns a lower case version of the string with all characters except letters removed. */
    public static String cleanString(String s) {
        return s.toLowerCase().replaceAll("[^a-z]", "");
    }

    /** Gets a list of all words in the file. */
     public static List<String> getWords(String inputFlieName) {
         File file = new File(inputFlieName);
         List<String> words = new ArrayList<String>();
         Scanner in = new Scanner(file);
         while (in.hasNextLine()) {
             String nextWord = cleanString((in.nextLine()));
             System.out.println(nextWord);
//             words.add(nextWord);
         }
         return null;
//         return words;
     }

     /** Returns the count of the number of unique words in words. */
     public static int countUniqueWords(List<String> words) {
         Set<String> wordSet = new HashSet<String>();
         /** normal for loop */
         for (int i = 0; i < words.size(); i ++) {
             String ithWord = words.get(i);
             wordSet.add(ithWord);
         }

         /** enhanced for loop */
         for (String ithWord : words) {
             wordSet.add(ithWord);
         }
         return wordSet.size();
     }

     /** Returns a map (a.k.a. dictionary) that tracks the count of all target words in words */
     public static Map<String, Integer> collectWordCount(List<String> words, List<String> targets) {
         Map<String, Integer> counts = new HashMap<String, Integer>();
         for (String t : targets) {     // initialization
            counts.put(t, 0);
         }

         for (String s : words) {
             if (counts.containsKey(s)) {
                 int oldCount = counts.get(s);
                 counts.put(s, oldCount + 1);
             }
         }
        return counts;
     }

    public static void main(String[] args) {
        List<String> w = getWords("test.txt");
//        List<String> w = getWords("~/Dev/Learning/data-structure/berkeley-cs61b/spring2018/note/lec4/Extends/src/com/company/test.txt");
        System.out.println(w);
    }
}
