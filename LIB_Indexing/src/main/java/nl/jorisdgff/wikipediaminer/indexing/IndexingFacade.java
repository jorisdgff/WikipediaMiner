package nl.jorisdgff.wikipediaminer.indexing;

import nl.jorisdgff.wikipediaminer.common.Message;
import nl.jorisdgff.wikipediaminer.common.WikipediaArticle;
import nl.jorisdgff.wikipediaminer.downloader.DownloadListener;
import nl.jorisdgff.wikipediaminer.downloader.Downloader;
import nl.jorisdgff.wikipediaminer.downloader.FileDownloadedEvent;
import nl.jorisdgff.wikipediaminer.extractor.Extractor;
import nl.jorisdgff.wikipediaminer.extractor.ParsedArticleHandler;

import nl.jorisdgff.wikipediaminer.saver.Saver;

import java.io.IOException;

public class IndexingFacade implements DownloadListener, ParsedArticleHandler {

    private static IndexingFacade instance;

    public Message startIndexing() {

        new Thread(() -> {

            try{

                Downloader.getInstance().startDownloading(this);
            }catch (IOException ex){

                System.err.println(ex);
            }




        }).start();

         return  new Message("indexingstarted");
    }

    public void fileDownloaded(FileDownloadedEvent event) {

        System.out.println("File downloaded" + event.name);
        Extractor.getInstance().extractFile(event.name, this);
    }

    public void handleArticle(WikipediaArticle article) {

        System.out.println("Article retrieved " + article.getTitle());
        Saver.getInstance().saveArticle(article);
    }

    public IndexingProgress getProgress(){

        return new IndexingProgress(Downloader.getInstance().getFilesToDownload());
    }

    public static IndexingFacade getInstance() {

        if(instance == null){

            instance = new IndexingFacade();
        }

        return instance;
    }


}