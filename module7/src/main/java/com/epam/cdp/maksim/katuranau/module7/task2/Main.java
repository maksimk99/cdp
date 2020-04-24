package com.epam.cdp.maksim.katuranau.module7.task2;

import java.io.File;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menu(getDiskAnalyzer());
    }

    private static DiskAnalyzer getDiskAnalyzer() {
        System.out.println("Please write path to analyse:");
        String dirName = scanner.nextLine();
        while (dirName.isEmpty() || !(new File(dirName).isDirectory())) {
            System.out.println("There is not such folder, please, try again or press enter to exit:");
            dirName = scanner.nextLine();
            if (dirName.isEmpty()) {
                System.out.println("Good buy!");
                System.exit(1);
            }
        }
        return new DiskAnalyzer(dirName);
    }

    private static void menu(DiskAnalyzer diskAnalyzer) {
        System.out.println("\n\nChoose function:\n"
                + "1: Path to the file with the maximum number of letters ‘s’ in the name\n"
                + "2: Top 5 largest file sizes\n"
                + "3: The average file size in the specified directory or any subdirectories\n"
                + "4: The number of files and folders, broken by the first letters of the alphabet\n"
                + "0: Exit\n");
        switch (scanner.nextLine()) {
            case "1": {
                diskAnalyzer.pathToFileWithMaxCountOfSCharacter();
                break;
            }
            case "2": {
                diskAnalyzer.top5LargestFileSizes();
                break;
            }
            case "3": {
                diskAnalyzer.averageFileSize();
                break;
            }
            case "4": {
                diskAnalyzer.numberOfFilesAndFoldersBrokenByFirstLettersOfAlphabet();
                break;
            }
            case "0": {
                System.out.println("Good buy!");
                System.exit(1);
            }
            default: {
                System.out.println("There is not such function, try again:");
                menu(diskAnalyzer);
            }
        }
        menu(diskAnalyzer);
    }
}
