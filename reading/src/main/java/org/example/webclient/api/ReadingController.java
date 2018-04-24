package org.example.webclient.api;
import org.example.externalinterfaces.ThirdPartyServices;
import org.springframework.web.bind.annotation.*;
import org.example.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import java.util.HashMap;


@RestController

public class ReadingController {

    @Autowired
    private ReadingService readingService;
    private ThirdPartyServices thirdPartyServices;


    // case when dependency service gives normal response
    @RequestMapping(
            value = "/recommended",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getRecommendedReadingList() {
        return readingService.readingList();

    }

    // case when dependency service has latency and responds after sometime
    @RequestMapping(
            value = "/recommended_withdelay",
            method = RequestMethod.POST
    )
    public String getreadingList_timedelay() {

        String readingList = readingService.readingList_withdelay();
        return readingService.readingList_withdelay();
    }


    @RequestMapping(
            value = "/recommended_noresponse",
            method = RequestMethod.POST
    )
    public String getreadingList_noresponse() {
        return readingService.readingList_withnoresponse();

    }


    @RequestMapping(
            value = "/getBookDetailsBasedOnId",
            method = RequestMethod.POST)
    public String getBookDetailsBasedOnId(@RequestBody HashMap<String, String> map1) throws Exception {

        String book;
        try {
            book = readingService.getBookDetailsBasedOnId(map1.get("bookId"));
        } catch (Exception e) {
            throw e;
        }
        return book;
    }


}
