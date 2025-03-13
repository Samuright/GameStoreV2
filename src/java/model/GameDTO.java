/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Quoc Bao
 */
public class GameDTO implements Serializable{
    private String gameId;
    private String title;
    private String description;
    private String price;
    private String publisher;
    private String releaseDate;
    private String coverImageUrl;
    private int isDlc;

    public GameDTO() {
    }

    public GameDTO(String gameId, String title, String description, String price, String publisher, String releaseDate, String coverImageUrl, int isDlc) {
        this.gameId = gameId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.coverImageUrl = coverImageUrl;

        this.isDlc = isDlc;
    }
    
    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }


    public int getIsDlc() {
        return isDlc;
    }

    public void setIsDlc(int isDlc) {
        this.isDlc = isDlc;
    }
    
    
}
