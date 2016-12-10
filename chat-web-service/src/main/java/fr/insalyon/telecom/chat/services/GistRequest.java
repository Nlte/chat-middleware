package fr.insalyon.telecom.chat.services;

import java.util.*;

public class GistRequest {

  private String description;
  private HashMap<String,HashMap<String,String>> files;
  

  public GistRequest(String content) {
    this.description = "pouet";
    this.files = new HashMap<String,HashMap<String,String>>();
    
    HashMap file = new HashMap<String,String>();
    
	file.put("content",content);
    
    files.put("notre super conversation",file);
    
    System.out.println("GistRequest constructor "+description+" "+files);
  }

  public String getdescription() {
    return description;
  }

  public void setdescription(String description) {
    this.description = description;
  }
  
  public HashMap<String,HashMap<String,String>>  getfiles() {
    return files;
  }

  public void setfiles(HashMap<String,HashMap<String,String>>  files) {
    this.files = files;
  }

}
