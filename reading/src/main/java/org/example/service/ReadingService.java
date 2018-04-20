package org.example.service;
import org.json.simple.JSONObject;

public interface ReadingService {

  String readingList();
  String readingList_withdelay();
  String readingList_withtimeout();
  String readingList_threadrejection();

}
