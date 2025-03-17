/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Quoc Bao
 */
public class UsersDTO {

    private String username;
    private String password;
    private int userId;
    private String email;
    private String dateOfBirth;
    private int isAdmin;
    private int isBlocked;
    private double wallet;
    private String userImg;

    public UsersDTO() {
    }

    public UsersDTO(String username, String password, int userId, String email, String dateOfBirth, int isAdmin, int isBlocked, double wallet, String userImg) {
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.isAdmin = isAdmin;
        this.isBlocked = isBlocked;
        this.wallet = wallet;
        this.userImg = userImg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(int isBlocked) {
        this.isBlocked = isBlocked;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    
}
