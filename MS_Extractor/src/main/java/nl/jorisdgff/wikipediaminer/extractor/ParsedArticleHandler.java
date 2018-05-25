package nl.jorisdgff.wikipediaminer.extractor;

import nl.jorisdgff.wikipediaminer.common.WikipediaArticle;

public interface ParsedArticleHandler {

    public void handleArticle(WikipediaArticle article);
}