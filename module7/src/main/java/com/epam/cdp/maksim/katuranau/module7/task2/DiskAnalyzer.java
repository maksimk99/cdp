package com.epam.cdp.maksim.katuranau.module7.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiskAnalyzer {

    private static final String FILE = "file";
    private static final String FOLDER = "folder";
    private File file;
    private List<File> fileList;

    public DiskAnalyzer(String fileName) {
        this.file = new File(fileName);
        this.fileList = new ArrayList<>();
        retrieveFiles(file);
    }

    public void pathToFileWithMaxCountOfSCharacter() {
        System.out.println("\nPath to the file with the maximum number of letters ‘s’ in the name:");
        fileList.stream().max(Comparator.comparingInt(file -> getCountOfSCharacter(file.getName())))
                .ifPresent(file -> System.out.println(file.getAbsolutePath()));
    }

    public void top5LargestFileSizes() {
        System.out.println("\nTop 5 largest file sizes:");
        fileList.stream()
                .sorted(Comparator.comparingLong(File::length)).limit(5)
                .forEach(file -> System.out.println(file.getAbsolutePath()));
    }

    public void averageFileSize() {
        System.out.print("\nThe average file size in the specified directory or any subdirectories: ");
        fileList.stream().mapToLong(File::length).average().ifPresent(System.out::println);
    }

    public void numberOfFilesAndFoldersBrokenByFirstLettersOfAlphabet() {
        Map<Character, Map<String, Integer>> fileAndFolderCount = new HashMap<>();
        retrievePackagesAndFilesCount(file, fileAndFolderCount);

        System.out.println("\nThe number of files and folders, broken by the first letters of the alphabet:");
        fileAndFolderCount.forEach(((character, map) -> System.out.println("'" + character + "' : "
                        + map.get(FILE) + " files and " + map.get(FOLDER) + " folders")));
    }

    private void retrieveFiles(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    retrieveFiles(file);
                }
            }
        } else {
            fileList.add(dir);
        }
    }

    private void retrievePackagesAndFilesCount(File dir, Map<Character, Map<String, Integer>> fileAndFolderCount) {
        if (dir.isDirectory()) {
            increaseCount(dir, fileAndFolderCount, FOLDER);
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    retrievePackagesAndFilesCount(file, fileAndFolderCount);
                }
            }
        }
        else {
            increaseCount(dir, fileAndFolderCount, FILE);
        }
    }

    private void increaseCount(File dir,  Map<Character, Map<String, Integer>> fileAndFolderCount, String type) {
        char firstCharacter = dir.getName().toLowerCase().charAt(0);
        if (Character.isLetter(firstCharacter)) {
            Map<String, Integer> map = fileAndFolderCount.getOrDefault(firstCharacter,
                    new HashMap<String, Integer>() {{
                        put(FILE, 0);
                        put(FOLDER, 0);
                    }});
            map.put(type, map.get(type) + 1);
            fileAndFolderCount.put(firstCharacter, map);
            int i = 0;
        }
    }

    private int getCountOfSCharacter(String string) {
        return (int) string.chars().filter(ch -> ch == 's').count();
    }
}
