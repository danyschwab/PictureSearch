package br.com.danyswork.picturesearch.model;


public class Picture {

    private String user;
    private String tags;
    private String previewURL;
    private int favorites;
    private int likes;
    private int comments;

    public String getUser() {
        return user;
    }

    public String getTags() {
        return tags;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public int getFavorites() {
        return favorites;
    }

    public int getLikes() {
        return likes;
    }

    public int getComments() {
        return comments;
    }
}
