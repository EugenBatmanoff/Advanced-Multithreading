package com.eugen.fjp.filescan;

public class FolderStatistics {
    private int fileCount;
    private int folderCount;
    private long totalSize;

    public void incrementFileCount() {
        fileCount++;
    }

    public void incrementFolderCount() {
        folderCount++;
    }

    public void addFileSize(long size) {
        totalSize += size;
    }

    public void add(FolderStatistics other) {
        this.fileCount += other.fileCount;
        this.folderCount += other.folderCount;
        this.totalSize += other.totalSize;
    }

    @Override
    public String toString() {
        String sizeStr;
        if (totalSize >= 1L << 40) {
            sizeStr = String.format("%.2f Tb", totalSize / (double) (1L << 40));
        } else if (totalSize >= 1L << 30) {
            sizeStr = String.format("%.2f Gb", totalSize / (double) (1L << 30));
        } else if (totalSize >= 1L << 20) {
            sizeStr = String.format("%.2f Mb", totalSize / (double) (1L << 20));
        } else if (totalSize >= 1L << 10) {
            sizeStr = String.format("%.2f Kb", totalSize / (double) (1L << 10));
        } else {
            sizeStr = totalSize + " bytes";
        }
        return "File count: " + fileCount + ", Folder count: " + folderCount + ", Total size: " + sizeStr;
    }
}