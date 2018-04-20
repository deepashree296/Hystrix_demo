package org.example.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.client.RestTemplate;

import org.example.service.ReadingService;
import java.net.URI;
import org.json.simple.JSONObject;

@Service
public class ReadingServiceImpl implements ReadingService {

@Autowired
private RestTemplate restTemplate;

//
@Override
@HystrixCommand(fallbackMethod="reliable")
public  String readingList() {
      URI uri = URI.create("http://localhost:3030/recommended");
      return restTemplate.getForObject(uri, String.class);
}

@Override
@HystrixCommand(fallbackMethod="reliable",
                commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
                    }
)
public  String readingList_withdelay() {
      URI uri = URI.create("http://localhost:3030/recommended-withdelay");
      return restTemplate.getForObject(uri, String.class);
}

@Override
@HystrixCommand(fallbackMethod="reliable")
public  String readingList_withtimeout() {
      URI uri = URI.create("http://localhost:3030/recommended-withnoresponse");
      return restTemplate.getForObject(uri, String.class);
}

@Override
@HystrixCommand(fallbackMethod="reliable")
public  String readingList_threadrejection() {
      URI uri = URI.create("http://localhost:3030/recommended-withdelay");
      return restTemplate.getForObject(uri, String.class);
}


@Override
@HystrixCommand()
public  String readingList_witherror() {
    URI uri = URI.create("http://localhost:3030/recommended-withservererror");
    return restTemplate.getForObject(uri, String.class);
}

public String reliable() {
  return "fallback";
 }



}
