package com.wingnity.jsonparsingtutorial;

public class Book {


    private String recordid;
    private String creationdate;
    private String thumbnail;
    private String weblink;
    private String author;
    private String publisher;
    private String source;
    private String title;



    public Book() {
        // TODO Auto-generated constructor stub
    }

    public Book(String recordid, String creationdate, String thumbnail, String weblink,
                String author, String publisher, String source, String title) {
        super();
        this.recordid = recordid;
        this.creationdate = creationdate;
        this.thumbnail = thumbnail;
        this.weblink = weblink;
        this.author = author;
        this.publisher = publisher;
        this.source = source;
        this.title = title;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public void setCreationdate(String creationdate) {
        this.creationdate = creationdate;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setWeblink(String weblink) {
        this.weblink = weblink;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecordid() {

        return recordid;
    }

    public String getCreationdate() {
        return creationdate;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getWeblink() {
        return weblink;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }
}
