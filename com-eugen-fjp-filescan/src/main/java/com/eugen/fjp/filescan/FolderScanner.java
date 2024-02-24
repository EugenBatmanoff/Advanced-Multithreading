package com.eugen.fjp.filescan;

import java.io.File;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

public class FolderScanner extends RecursiveTask<FolderStatistics> {
    private final File folder;
    private final AtomicInteger processedFolders;
    private final int totalFolders;

    public FolderScanner(File folder, AtomicInteger processedFolders, int totalFolders) {
        this.folder = folder;
        this.processedFolders = processedFolders;
        this.totalFolders = totalFolders;
    }

    @Override
    protected FolderStatistics compute() {
        FolderStatistics stats = new FolderStatistics();
        var files = folder.listFiles();
        if (files != null) {
            for (var file : files) {
                if (file.isDirectory()) {
                    stats.incrementFolderCount();
                    var task = new FolderScanner(file, processedFolders, totalFolders);
                    task.fork();
                    stats.add(task.join());
                    var processed = processedFolders.incrementAndGet();
                    printProgressBar(processed, totalFolders);
                } else {
                    stats.incrementFileCount();
                    stats.addFileSize(file.length());
                }
            }
        }
        return stats;
    }

    private void printProgressBar(int processed, int total) {
        var percent = 100 * processed / total;
        var bar = new StringBuilder("[");
        for (var i = 0; i < 100; i++) {
            if (i < percent) {
                bar.append("#");
            } else {
                bar.append(".");
            }
        }
        bar.append("] (").append(processed).append("/").append(total).append(")");
        System.out.print("\r" + bar);
    }
}