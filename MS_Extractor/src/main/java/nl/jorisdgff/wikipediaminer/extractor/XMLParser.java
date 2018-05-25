package nl.jorisdgff.wikipediaminer.extractor;

import nl.jorisdgff.wikipediaminer.common.WikipediaArticle;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParser extends DefaultHandler {

    private WikipediaArticle currentArticle;
    private String currentField;
    private ParsedArticleHandler handler;

    public XMLParser(ParsedArticleHandler handler){

        this.handler = handler;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        currentField = "";

        if(qName.equals("page")){

            currentArticle = new WikipediaArticle();
        }else if(currentArticle != null){

            if(qName.equals("title") || qName.equals("text")){

                currentField = qName;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if(qName.equals("page")){

            handler.handleArticle(currentArticle);


            currentArticle = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        String s = new String(ch, start, length).trim();

        if(currentField.equals("title")){

            if(!s.equals("")){

                currentArticle.setTitle(s);
            }
        }else if(currentField.equals("text")){

            if(!s.equals("")){

                currentArticle.addText(s);
            }
        }
    }
}