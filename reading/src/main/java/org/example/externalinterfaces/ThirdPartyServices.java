package org.example.externalinterfaces;
import org.example.exception.TestException;
import org.example.model.Book;
import org.springframework.http.ResponseEntity;
import com.netflix.hystrix.exception.HystrixBadRequestException;

import java.util.Collection;

public interface ThirdPartyServices {


    // get all recommended books.
    String getRecommendedBooks();

    // get recommended book ( Assume this service has delay)
    String getRecommendedBooks_delay();

    // get recommended book ( Assume this service returns no response )
    String getRecommendedBooks_noresponse();

    // get recommended book ( Assume this service fails )
    String getBookDetailsBasedOnId(String id, Throwable e) throws Exception;





}
