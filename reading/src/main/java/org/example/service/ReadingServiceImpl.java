package org.example.service;

import org.example.exception.TestException;
import org.example.externalinterfaces.ThirdPartyServices;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;


@Service
public class ReadingServiceImpl implements ReadingService {

@Autowired
private ThirdPartyServices thirdPartyServices;

@Autowired
private RestTemplate restTemplate;


@Override
public String readingList() {
    String books = thirdPartyServices.getRecommendedBooks();
    return books;
}

@Override
public String readingList_withdelay() {
    String books = thirdPartyServices.getRecommendedBooks_delay();
    return books;
}

public  String readingList_withnoresponse() {
    String books = thirdPartyServices.getRecommendedBooks_noresponse();
    return books;

}

    public  String getBookDetailsBasedOnId(String id) throws Exception {
        String books = thirdPartyServices.getBookDetailsBasedOnId(id, null);
        return books;
    }

}
