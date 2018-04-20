package org.example.webclient.api;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.example.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;


@RestController

public class ReadingController {

@Autowired
private ReadingService readingService;


// case when dependency service gives normal response
@RequestMapping(
       value="/to-read",
       method=RequestMethod.GET,
       produces=MediaType.APPLICATION_JSON_VALUE
       )
public ResponseEntity<String> getreadingList() {

       String readingList = readingService.readingList();
       return new ResponseEntity<String>(
                      readingList,
                      HttpStatus.OK);
}

// case when dependency service has latency and responds after sometime
@RequestMapping(
       value="/to-read-delay",
       method=RequestMethod.GET
       )
public ResponseEntity<String> getreadingList_timedelay() {

       String readingList = readingService.readingList_withdelay();
       return new ResponseEntity<String>(
                      readingList, HttpStatus.OK);
}


@RequestMapping(
       value="/to-read-timeout",
       method=RequestMethod.GET
       )
public ResponseEntity<String> getreadingList_timeout() {

       String readingList = readingService.readingList_withtimeout();
       return new ResponseEntity<String>(
                      readingList, HttpStatus.OK);
}


@RequestMapping(
        value="/to-read-servererror",
        method=RequestMethod.GET
       )
public ResponseEntity<String> getreadingList_servererror() {

       String readingList = readingService.readingList_witherror();
       return new ResponseEntity<String>(
                     readingList, HttpStatus.OK);
       }

}
