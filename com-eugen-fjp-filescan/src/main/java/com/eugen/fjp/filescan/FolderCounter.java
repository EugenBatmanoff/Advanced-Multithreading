package com.eugen.fjp.filescan;

import java.io.File;
import java.util.concurrent.RecursiveTask;

public class FolderCounter extends RecursiveTask<Integer> {
    private final File folder;

    public FolderCounter(File folder) {
        this.folder = folder;
    }

    @Override
    protected Integer compute() {
        var count = 0;
        var files = folder.listFiles();
        if (files != null) {
            for (var file : files) {
                if (file.isDirectory()) {
                    count += 1 + new FolderCounter(file).fork().join();
                }
            }
        }
        return count;
    }
}