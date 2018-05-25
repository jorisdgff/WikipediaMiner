package nl.jorisdgff.wikipediaminer.indexing;

public class ItemIndexingProgress {

    public final String filename;
    public final int filesize;
    public final int progress;

    public ItemIndexingProgress(String filename, int filesize, int progress){

        this.filename = filename;
        this.filesize = filesize;
        this.progress = progress;
    }
}
