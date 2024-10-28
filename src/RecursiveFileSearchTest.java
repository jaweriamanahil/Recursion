import org.junit.Test;

import FileSearch.RecursiveFileSearch;

import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class RecursiveFileSearchTest {

    // Helper method to set up a test directory structure
    private Path createTestDirectoryStructure() throws IOException {
        Path tempDir = Files.createTempDirectory("testDir");

        // Create subdirectories and files
        Files.createDirectories(tempDir.resolve("subDir1"));
        Files.createDirectories(tempDir.resolve("subDir2"));

        Files.createFile(tempDir.resolve("file1.txt"));
        Files.createFile(tempDir.resolve("subDir1/file2.txt"));
        Files.createFile(tempDir.resolve("subDir2/file1.txt"));  // Duplicate name to check count functionality

        return tempDir;
    }

    @Test
    public void testFileFoundSingleCaseSensitive() throws IOException {
        Path testDir = createTestDirectoryStructure();
        Map<String, Integer> result = RecursiveFileSearch.searchFiles(testDir.toFile(), new String[]{"file1.txt"}, true);

        Assert.assertEquals(2, (int) result.get("file1.txt"));  // "file1.txt" should be found twice
    }

    @Test
    public void testFileNotFound() throws IOException {
        Path testDir = createTestDirectoryStructure();
        Map<String, Integer> result = RecursiveFileSearch.searchFiles(testDir.toFile(), new String[]{"nonexistent.txt"}, true);

        Assert.assertEquals(0, (int) result.get("nonexistent.txt"));  // File should not be found
    }

    @Test
    public void testFileFoundCaseInsensitive() throws IOException {
        Path testDir = createTestDirectoryStructure();
        Map<String, Integer> result = RecursiveFileSearch.searchFiles(testDir.toFile(), new String[]{"FILE1.TXT"}, false);

        Assert.assertEquals(2, (int) result.get("FILE1.TXT"));  // Case-insensitive search for "file1.txt" should find 2 occurrences
    }

    @Test
    public void testMultipleFilesSearch() throws IOException {
        Path testDir = createTestDirectoryStructure();
        Map<String, Integer> result = RecursiveFileSearch.searchFiles(testDir.toFile(), new String[]{"file1.txt", "file2.txt"}, true);

        Assert.assertEquals(2, (int) result.get("file1.txt"));
        Assert.assertEquals(1, (int) result.get("file2.txt"));
    }

    @Test
    public void testInvalidDirectory() {
        File invalidDirectory = new File("nonExistentDirectory");
        Assert.assertFalse(invalidDirectory.exists());

        Map<String, Integer> result = RecursiveFileSearch.searchFiles(invalidDirectory, new String[]{"file1.txt"}, true);

        Assert.assertTrue(result.isEmpty());  // No results should be returned for a non-existent directory
    }
}
