package nl.jorisdgff.wikipediaminer.downloader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Downloader {

    private static Downloader instance;
    private ArrayList<FileToDownload> filesToDownload;

    private Downloader(){

        filesToDownload = new ArrayList<FileToDownload>();
    }

    public void startDownloading(DownloadListener listener) throws IOException{

        Document doc = Jsoup.connect(FileToDownload.basePath).get();
        Pattern p = Pattern.compile("enwiki-latest-pages-articles[1-9]+.xml-.+.bz2$");
        Elements hyperlinks = doc.select("a");

        for(Element element : hyperlinks){

            String href = element.attr("href");
            Matcher m = p.matcher(href);

            if(m.find()){

                filesToDownload.add(new FileToDownload(href));
            }
        }

        for(FileToDownload file : filesToDownload){

            file.download(listener);
        }
    }

    public ArrayList<FileToDownload> getFilesToDownload() {
        return filesToDownload;
    }

    public static Downloader getInstance() {

        if(instance == null){

            instance = new Downloader();
        }

        return instance;
    }
}