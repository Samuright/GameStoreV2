/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class CouponsDTO {
    private int counponId, discountPercent, usageLimit, timesUsed, isActive, orderId;
    private String code, starDate, endDate;

    public int getCounponId() {
        return counponId;
    }

    public void setCounponId(int counponId) {
        this.counponId = counponId;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getUsageLimit() {
        return usageLimit;
    }

    public void setUsageLimit(int usageLimit) {
        this.usageLimit = usageLimit;
    }

    public int getTimesUsed() {
        return timesUsed;
    }

    public void setTimesUsed(int timesUsed) {
        this.timesUsed = timesUsed;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStarDate() {
        return starDate;
    }

    public void setStarDate(String starDate) {
        this.starDate = starDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public CouponsDTO(int counponId, String code, int discountPercent,  String starDate, String endDate, int usageLimit, int timesUsed, int isActive, int orderId) {
        this.counponId = counponId;
        this.discountPercent = discountPercent;
        this.usageLimit = usageLimit;
        this.timesUsed = timesUsed;
        this.isActive = isActive;
        this.orderId = orderId;
        this.code = code;
        this.starDate = starDate;
        this.endDate = endDate;
    }

    public CouponsDTO() {
    }
    
}
