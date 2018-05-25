package nl.jorisdgff.wikipediaminer.downloader;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import nl.jorisdgff.wikipediaminer.common.Config;

public class FileToDownload {

    public static final String basePath = "https://dumps.wikimedia.org/enwiki/latest/";

    public final String filename;
    private URL url;

    private int completeFileSize;
    private int downloadedFileSize;
    //private double progress;

    public FileToDownload(String filename) throws IOException{

        this.filename = filename;

        url = new URL(basePath + filename);

        HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
        completeFileSize = httpConnection.getContentLength();
        httpConnection.disconnect();

        downloadedFileSize = 0;
    }

    void download(DownloadListener listener) throws IOException{

        HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());



        FileOutputStream fis = new FileOutputStream(Config.baseDirectory + filename);

        BufferedInputStream bis = new BufferedInputStream(httpConnection.getInputStream());

        byte[] buffer = new byte[1024];

        int count=0;

        while((count = bis.read(buffer,0,1024)) != -1){

            downloadedFileSize += count;




            fis.write(buffer, 0, count);
        }

        fis.close();
        bis.close();

        listener.fileDownloaded(new FileDownloadedEvent(filename));
    }

    public int getCompleteFileSize() {
        return completeFileSize;
    }

    public int getDownloadedFileSize() {
        return downloadedFileSize;
    }
}