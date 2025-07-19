package com.apex.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
@ConfigurationProperties(prefix = "news.api")
public class NewsApiProperties {

  private String key;
  private Base base = new Base();
  private String country;
  private Url url = new Url();

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public Base getBase() {
    return base;
  }

  public void setBase(Base base) {
    this.base = base;
  }

  public String getCountry() { // New getter for country
    return country;
  }

  public void setCountry(String country) { // New setter for country
    this.country = country;
  }

  public Url getUrl() {
    return url;
  }

  public void setUrl(Url url) {
    this.url = url;
  }

  public static class Base {
    private String url;

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }
  }

  public static class Url {
    private Content content = new Content();

    public Content getContent() {
      return content;
    }

    public void setContent(Content content) {
      this.content = content;
    }
  }

  public static class Content {
    private Top top = new Top();

    public Top getTop() {
      return top;
    }

    public void setTop(Top top) {
      this.top = top;
    }
  }

  public static class Top {
    private String headlines;

    public String getHeadlines() {
      return headlines;
    }

    public void setHeadlines(String headlines) {
      this.headlines = headlines;
    }
  }
}
