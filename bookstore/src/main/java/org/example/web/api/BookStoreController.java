package org.example.web.api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class BookStoreController {

  @RequestMapping(
        value="/recommended")
  public String readingList() {
        return "book 1, book 2, book 3";
  }
}
