package FileSearch;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class RecursiveFileSearch {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java FileSearcher <directory-path> <case-sensitive (true/false)> <file-names...>");
            return;
        }

        String directoryPath = args[0];
        boolean caseSensitive = Boolean.parseBoolean(args[1]);
        String[] fileNames = new String[args.length - 2];
        System.arraycopy(args, 2, fileNames, 0, fileNames.length);

        File directory = new File(directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("The specified directory does not exist or is not a directory.");
            return;
        }

        Map<String, Integer> fileCounts = searchFiles(directory, fileNames, caseSensitive);
        
        for (String fileName : fileNames) {
            int count = fileCounts.getOrDefault(fileName, 0);
            if (count > 0) {
                System.out.println("File '" + fileName + "' found " + count + " times.");
            } else {
                System.out.println("File '" + fileName + "' not found.");
            }
        }
    }

    public static Map<String, Integer> searchFiles(File directory, String[] fileNames, boolean caseSensitive) {
        Map<String, Integer> fileCounts = new HashMap<>();
        for (String fileName : fileNames) {
            fileCounts.put(fileName, 0);
        }
        searchFilesRecursive(directory, fileNames, caseSensitive, fileCounts);
        return fileCounts;
    }

    private static void searchFilesRecursive(File directory, String[] fileNames, boolean caseSensitive, Map<String, Integer> fileCounts) {
        File[] files = directory.listFiles();
        if (files == null) {
            System.out.println("Error accessing directory: " + directory.getAbsolutePath());
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                searchFilesRecursive(file, fileNames, caseSensitive, fileCounts);
            } else {
                for (String fileName : fileNames) {
                    if (caseSensitive ? file.getName().equals(fileName) : file.getName().equalsIgnoreCase(fileName)) {
                        System.out.println("File found: " + file.getAbsolutePath());
                        fileCounts.put(fileName, fileCounts.get(fileName) + 1);
                    }
                }
            }
        }
    }
}