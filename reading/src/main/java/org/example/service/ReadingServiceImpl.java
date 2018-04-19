package org.example.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.client.RestTemplate;

import org.example.service.ReadingService;
import java.net.URI;

@Service
public class ReadingServiceImpl implements ReadingService {

@Autowired
private RestTemplate restTemplate;

//
@Override
@HystrixCommand(fallbackMethod="reliable")
public String readingList() {
      URI uri = URI.create("http://localhost:8090/recommended");
      return restTemplate.getForObject(uri, String.class);
}

public String reliable() {
   return "Cloud Native Java (O'Reilly)";
 }



}
