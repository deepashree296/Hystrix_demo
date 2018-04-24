package org.example.externalinterfaces;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import org.aspectj.weaver.ast.Test;
import org.example.exception.TestException;
import org.example.model.Book;
import java.net.URI;
import java.util.*;
import org.example.externalinterfaces.ThirdPartyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class ThirdPartyServicesImpl  implements ThirdPartyServices{

    @Autowired
    private RestTemplate restTemplate;


    // get all recommended books. Make a request to third party API
    public String getRecommendedBooks() {
        URI uri = URI.create("http://localhost:3030/recommended");
        String responseEntity = restTemplate.postForObject(uri, null, String.class);
        return responseEntity;

    }


    // get recommended book ( Assume this service has delay)
    @HystrixCommand(fallbackMethod="defaultReadingList", commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
    public String getRecommendedBooks_delay() {
        URI uri = URI.create("http://localhost:3030/recommended_withdelay");
        String responseEntity = restTemplate.postForObject(uri, null, String.class);
        return responseEntity;

    }


    // get recommended book ( Assume this service returns no response )
    @HystrixCommand(fallbackMethod="defaultReadingList", commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
    public String getRecommendedBooks_noresponse() {
        URI uri = URI.create("http://localhost:3030/recommended_withnorespopnse");
        String responseEntity = restTemplate.postForObject(uri, null, String.class);
        return responseEntity;
    }

    // get book details
    // exception can be either made to propagate to fallback method, or back to the caller
    @HystrixCommand(fallbackMethod="resultNotAvailable", ignoreExceptions = {HttpClientErrorException.class},
            commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="2"),
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="30000")}
    )
    public String getBookDetailsBasedOnId(String id, Throwable e) throws Exception {

            URI uri = URI.create("http://localhost:3030/bookid-withvalidation");
            Map<String, String> parametersMap = new HashMap<String, String>();
            parametersMap.put("bookId", id);
            String responseEntity = restTemplate.postForObject(uri, parametersMap, String.class);
            return responseEntity;

    }

    public String defaultReadingList(){
        return "Sapiens, This is your brain on Music";
    }

    // fallback method in case of getBookDetailsById
    public String resultNotAvailable(String id, Throwable e) {
        return "Result not available. Please try after some time";
    }




}
