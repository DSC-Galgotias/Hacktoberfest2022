package com.sandeepgupta.NEWS;

public class News {
//title des url urltoimage publishat content
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setContent(String content) {
        this.content = content;
    }
    // allNews.add(new News(title,description,url,urlToImage,publishedAt,content));
    public News(String title,String description,String url,String urlToImage,String publishedAt){

        setDescription(description);
        setPublishedAt(publishedAt);
        setTitle(title);
        setUrl(url);
        setUrlToImage(urlToImage);
    }
}
