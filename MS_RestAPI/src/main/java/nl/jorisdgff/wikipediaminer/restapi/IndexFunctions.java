package nl.jorisdgff.wikipediaminer.restapi;


import nl.jorisdgff.wikipediaminer.common.Message;
import nl.jorisdgff.wikipediaminer.indexing.IndexingFacade;
import nl.jorisdgff.wikipediaminer.indexing.IndexingProgress;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/indexfunctions")
public class IndexFunctions {

    @RequestMapping("/startIndexing")
    @ResponseBody
    public Message startIndexing() throws IOException, InterruptedException{

        return IndexingFacade.getInstance().startIndexing();
    }

    @RequestMapping("/getProgress")
    @ResponseBody
    public IndexingProgress getProgress(){

        return IndexingFacade.getInstance().getProgress();
    }
}