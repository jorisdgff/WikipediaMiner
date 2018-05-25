package nl.jorisdgff.wikipediaminer.indexing;

import nl.jorisdgff.wikipediaminer.downloader.FileToDownload;

import java.io.File;
import java.util.ArrayList;

public class IndexingProgress {

    public final ArrayList<ItemIndexingProgress> itemProgress;

    public IndexingProgress(ArrayList<FileToDownload> files){

        this.itemProgress = new ArrayList<>();

        for(FileToDownload file : files){

            int progress = (int) ((double) file.getDownloadedFileSize() / (double) file.getCompleteFileSize() * 100);

            ItemIndexingProgress p = new ItemIndexingProgress(file.filename, file.getCompleteFileSize() / 1024 / 1024, progress);
            itemProgress.add(p);
        }
    }
}