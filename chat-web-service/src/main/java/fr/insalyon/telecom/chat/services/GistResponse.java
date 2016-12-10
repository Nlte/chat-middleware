package fr.insalyon.telecom.chat.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.*;


@JsonIgnoreProperties(ignoreUnknown = true)
public class GistResponse {

  @JsonProperty("html_url")
  private String url;

  public void setUrl(String url) {
    this.url = url;
  }
  
   public String getUrl() {
    return this.url;
  }
}

	
