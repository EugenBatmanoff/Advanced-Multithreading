package com.eugen.fjp.filescan;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <folder>");
            return;
        }

        var folder = new File(args[0]);
        if (!folder.isDirectory()) {
            System.out.println("Error: " + args[0] + " is not a directory.");
            return;
        }

        var pool = new ForkJoinPool();
        var totalFolders = pool.invoke(new FolderCounter(folder)) + 1; // +1 for the root folder
        var processedFolders = new AtomicInteger(0);
        var task = new FolderScanner(folder, processedFolders, totalFolders);
        new Thread(() -> {
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    String line = scanner.nextLine();
                    if (line.equalsIgnoreCase("c")) {
                        pool.shutdownNow();
                        break;
                    }
                }
            }
        }).start();
        var stats = pool.invoke(task);
        System.out.println("\n" + stats);
    }
}