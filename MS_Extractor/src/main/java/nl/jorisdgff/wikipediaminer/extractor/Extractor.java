package nl.jorisdgff.wikipediaminer.extractor;

import nl.jorisdgff.wikipediaminer.common.Config;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Extractor {

    public static final String targetPath = "D:\\WikipediaMining\\";

    public static Extractor instance;

    private Extractor(){

    }

    public void extractFile(String filename, ParsedArticleHandler handler) {

        new Thread(() -> {

            String xmlfilename = extract(filename);
            readXML( xmlfilename,  handler);
        }).start();
    }

    private String extract(String filename){

        try{

            String targetFilename = filename.replace(".bz2", "");

            FileInputStream in = new FileInputStream(targetPath + filename);
            FileOutputStream out = new FileOutputStream(targetPath + targetFilename);
            BZip2CompressorInputStream bzIn = new BZip2CompressorInputStream(in);
            final byte[] buffer = new byte[1024];
            int n = 0;

            while (-1 != (n = bzIn.read(buffer))) {

                out.write(buffer, 0, n);
            }

            out.close();
            bzIn.close();

            return targetFilename;
        }catch (IOException ex){

            System.err.println(ex);
            return "";
        }
    }

    private void readXML(String filename, ParsedArticleHandler handler)  {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        try {

            SAXParser saxParser = saxParserFactory.newSAXParser();
            XMLParser xmlParser = new XMLParser(handler);
            saxParser.parse(new File(Config.baseDirectory + filename), xmlParser);

        } catch (ParserConfigurationException | SAXException | IOException e) {

            System.err.println(e);
        }
    }

    public static Extractor getInstance() {

        if(instance == null){

            instance = new Extractor();
        }

        return instance;
    }
}