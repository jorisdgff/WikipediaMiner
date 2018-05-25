package nl.jorisdgff.wikipediaminer.common;

public class WikipediaArticle {

    private String title;
    private String text;

    public WikipediaArticle(){

        text = "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {

        return text;
    }

    public void addText(String text) {

        this.text += text;
    }
}