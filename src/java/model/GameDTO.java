package model;

import java.io.Serializable;
import java.sql.Date;

public class GameDTO implements Serializable {

    private int gameId; // Sử dụng int vì gameId thường là số tự tăng
    private String title;
    private String description;
    private double price;
    private String publisher;
<<<<<<< HEAD
    private Date releaseDate; // Dạng chuỗi, có thể dùng định dạng "yyyy-MM-dd"
    private String coverImageUrl;
    private int isDlc; // 0: không phải DLC, 1: là DLC
    private String minSpec;
    private String maxSpec;
=======
    private Date releaseDate;
    private String coverImageUrl;
    private String trailerImageUrl;
    private int isDlc;
    private String platform;
    private String platformLogo;
    private String minSpec;
    private String maxSpec;
    private String screenshot;
>>>>>>> 0615987 (Thêm tính năng user, sửa GameDTO)

    // Constructor mặc định
    public GameDTO() {
    }

<<<<<<< HEAD
    // Constructor đầy đủ
    public GameDTO(int gameId, String title, String description, double price, String publisher,
            Date releaseDate, String coverImageUrl, int isDlc, String minSpec, String maxSpec) {
=======
    public GameDTO(String gameId, String title, String description, double price, String publisher, Date releaseDate, String coverImageUrl, String trailerImageUrl, int isDlc, String platform, String platformLogo, String minSpec, String maxSpec, String screenshot) {
>>>>>>> 0615987 (Thêm tính năng user, sửa GameDTO)
        this.gameId = gameId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.coverImageUrl = coverImageUrl;
<<<<<<< HEAD
        this.isDlc = isDlc;
        this.minSpec = minSpec;
        this.maxSpec = maxSpec;
    }

    public GameDTO(String gameId, String title, String description, String price, String publisher, String releaseDate, String coverImageUrl, int dlc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Getters và Setters
    public int getGameId() {
=======
        this.trailerImageUrl = trailerImageUrl;
        this.isDlc = isDlc;
        this.platform = platform;
        this.platformLogo = platformLogo;
        this.minSpec = minSpec;
        this.maxSpec = maxSpec;
        this.screenshot = screenshot;
    }

    public String getGameId() {
>>>>>>> 0615987 (Thêm tính năng user, sửa GameDTO)
        return gameId;
    }

    public void setGameId(int gameId) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

<<<<<<< HEAD
    
    public String getPublisher() {
        return publisher;
=======
    public Date getReleaseDate() {
        return releaseDate;
>>>>>>> 0615987 (Thêm tính năng user, sửa GameDTO)
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

<<<<<<< HEAD
=======
    public String getTrailerImageUrl() {
        return trailerImageUrl;
    }

    public void setTrailerImageUrl(String trailerImageUrl) {
        this.trailerImageUrl = trailerImageUrl;
    }

>>>>>>> 0615987 (Thêm tính năng user, sửa GameDTO)
    public int getIsDlc() {
        return isDlc;
    }

    public void setIsDlc(int isDlc) {
        this.isDlc = isDlc;
    }

<<<<<<< HEAD
=======
    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPlatformLogo() {
        return platformLogo;
    }

    public void setPlatformLogo(String platformLogo) {
        this.platformLogo = platformLogo;
    }

>>>>>>> 0615987 (Thêm tính năng user, sửa GameDTO)
    public String getMinSpec() {
        return minSpec;
    }

    public void setMinSpec(String minSpec) {
        this.minSpec = minSpec;
    }

    public String getMaxSpec() {
        return maxSpec;
    }

    public void setMaxSpec(String maxSpec) {
        this.maxSpec = maxSpec;
    }

<<<<<<< HEAD

=======
    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

   
>>>>>>> 0615987 (Thêm tính năng user, sửa GameDTO)
}
