package org.example.service;

import org.example.exception.TestException;

public interface ReadingService {

  String readingList();
  String readingList_withdelay();
  String readingList_withnoresponse();
  String getBookDetailsBasedOnId(String id) throws Exception;

}
