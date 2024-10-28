package FileSearch;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Scanner;
//import java.util.Set;
//
//public class StringPermutationsProject {
//
//    // Function to generate all permutations of the string
//    public static List<String> generatePermutations(String str) {
//        List<String> permutations = new ArrayList<>();
//        if (str == null || str.isEmpty()) {
//            return permutations; // Return empty list for null or empty input
//        }
//        permute(str.toCharArray(), 0, permutations);
//        return permutations;
//    }
//
//    // Recursive helper function to generate permutations by swapping characters
//    private static void permute(char[] chars, int currentIndex, List<String> permutations) {
//        if (currentIndex == chars.length - 1) {
//            permutations.add(new String(chars));
//        } else {
//            Set<Character> uniqueChars = new HashSet<>(); // To handle duplicates
//            for (int i = currentIndex; i < chars.length; i++) {
//                if (!uniqueChars.contains(chars[i])) { // Skip if character has already been used at this position
//                    uniqueChars.add(chars[i]);
//                    swap(chars, currentIndex, i);
//                    permute(chars, currentIndex + 1, permutations);
//                    swap(chars, currentIndex, i); // Backtrack
//                }
//            }
//        }
//    }
//
//    // Helper function to swap characters at two positions
//    private static void swap(char[] chars, int i, int j) {
//        char temp = chars[i];
//        chars[i] = chars[j];
//        chars[j] = temp;
//    }
//
//    // Main method for user interaction
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter a string to generate permutations: ");
//        String input = scanner.nextLine();
//        List<String> permutations = generatePermutations(input);
//        
//        System.out.println("Permutations:");
//        for (String permutation : permutations) {
//            System.out.println(permutation);
//        }
//    }
//}

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class StringPermutationsProject {

    // Function to generate all permutations of the string
    public static List<String> generatePermutations(String str) {
        List<String> permutations = new ArrayList<>();
        if (str == null || str.isEmpty()) {
            return permutations; // Return empty list for null or empty input
        }
        permute(str.toCharArray(), 0, permutations);
        return permutations;
    }

    // Recursive helper function to generate permutations by swapping characters
    private static void permute(char[] chars, int currentIndex, List<String> permutations) {
        if (currentIndex == chars.length - 1) {
            permutations.add(new String(chars)); // Base case: add permutation to list
        } else {
            Set<Character> uniqueChars = new HashSet<>(); // To handle duplicates
            for (int i = currentIndex; i < chars.length; i++) {
                if (!uniqueChars.contains(chars[i])) { // Skip if character has already been used at this position
                    uniqueChars.add(chars[i]);
                    swap(chars, currentIndex, i);
                    permute(chars, currentIndex + 1, permutations);
                    swap(chars, currentIndex, i); // Backtrack
                }
            }
        }
    }

    // Helper function to swap characters at two positions
    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    // Main method for user interaction and timing
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to generate permutations: ");
        String input = scanner.nextLine();
        
        // Start timing
        long startTime = System.nanoTime();
        
        // Generate permutations
        List<String> permutations = generatePermutations(input);
        
        // End timing
        long endTime = System.nanoTime();
        
        // Calculate the elapsed time in milliseconds
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds
        
        // Display results
        System.out.println("Permutations:");
        for (String permutation : permutations) {
            System.out.println(permutation);
        }
        
        System.out.println("\nTime taken: " + duration + " ms");
    }
}
