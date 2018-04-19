package org.example.webclient.api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.net.URI;
import org.springframework.web.client.RestTemplate;
import org.example.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class ReadingController {

@Autowired
private ReadingService readingService;


  @RequestMapping(
        value="/to-read")
  public String readingList() {
        return readingService.readingList();
  }
}
