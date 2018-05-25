package nl.jorisdgff.wikipediaminer.downloader;

public interface DownloadListener {

    public void fileDownloaded(FileDownloadedEvent event);
}