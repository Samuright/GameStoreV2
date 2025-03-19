package model;

import java.io.Serializable;
import java.sql.Date;

public class GameDTO implements Serializable {

    private int gameId; // Sử dụng int vì gameId thường là số tự tăng
    private String title;
    private String description;
    private double price;
    private String publisher;
    private Date releaseDate;
    private String coverImageUrl;
    private String trailerImageUrl;
    private int isDlc;
    private String platform;
    private String platformLogo;
    private String minSpec;
    private String maxSpec;
    private String screenshot;
    private int quantity = 1;
    private double originalPrice ;

    // Constructor mặc định
    public GameDTO() {
    }

    public GameDTO(int gameId, String title, String description, double price, String publisher, Date releaseDate, String coverImageUrl, String trailerImageUrl, int isDlc, String platform, String platformLogo, String minSpec, String maxSpec, String screenshot) {
        this.gameId = gameId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.coverImageUrl = coverImageUrl;
        this.trailerImageUrl = trailerImageUrl;
        this.isDlc = isDlc;
        this.platform = platform;
        this.platformLogo = platformLogo;
        this.minSpec = minSpec;
        this.maxSpec = maxSpec;
        this.screenshot = screenshot;
    }

    public GameDTO(int gameId, String title, String description, double price, String publisher, Date releaseDate, String coverImageUrl, int isDlc) {
        this.gameId = gameId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.coverImageUrl = coverImageUrl;
        this.isDlc = isDlc;
    }

    public GameDTO(int gameId, String title, String description, double price, String publisher, Date releaseDate, String coverImageUrl, String trailerImageUrl, int isDlc) {
        this.gameId = gameId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.coverImageUrl = coverImageUrl;
        this.trailerImageUrl = trailerImageUrl;
        this.isDlc = isDlc;
    }

    public int getGameId() {
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public String getTrailerImageUrl() {
        return trailerImageUrl;
    }

    public void setTrailerImageUrl(String trailerImageUrl) {
        this.trailerImageUrl = trailerImageUrl;
    }

    public int getIsDlc() {
        return isDlc;
    }

    public void setIsDlc(int isDlc) {
        this.isDlc = isDlc;
    }

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

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }
    
}
