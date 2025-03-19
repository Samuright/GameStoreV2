/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CouponsDTO;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class CouponsDAO {

    public static List<CouponsDTO> list() {
        List<CouponsDTO> couponList = new ArrayList<CouponsDTO>();
        try {
            Connection con = DBUtils.getConnection();
            String sql = "SELECT * from coupons";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int couponId = rs.getInt("couponId");
                String code = rs.getString("code");
                int discountPercentage = rs.getInt("discountPercentage");
                String startDate = rs.getString("startDate");
                String endDate = rs.getString("endDate");
                int usageLimit = rs.getInt("usageLimit");
                int timesUsed = rs.getInt("timesUsed");
                int isActive = rs.getInt("isActive");
                int orderId = rs.getInt("orderId");

                CouponsDTO coupons = new CouponsDTO();
                coupons.setCounponId(couponId);
                coupons.setCode(code);
                coupons.setDiscountPercent(discountPercentage);
                coupons.setStarDate(startDate);
                coupons.setEndDate(endDate);
                coupons.setUsageLimit(usageLimit);
                coupons.setTimesUsed(timesUsed);
                coupons.setIsActive(isActive);
                coupons.setOrderId(orderId);

                couponList.add(coupons);
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error in servlet. Details:" + ex.getMessage());
            ex.printStackTrace();
        }
        return couponList;
    }

    public boolean validateCoupon(String code) {
        List<CouponsDTO> couponList = new ArrayList<CouponsDTO>();
        try {
            Connection con = DBUtils.getConnection();
            String sql = "SELECT * FROM coupons WHERE code = ? AND isActive = 1";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                boolean isActive = rs.getBoolean("isActive");
                java.sql.Date startDate = rs.getDate("startDate");
                java.sql.Date endDate = rs.getDate("endDate");
                int usageLimit = rs.getInt("usageLimit");
                int timesUsed = rs.getInt("timesUsed");

                if (!isActive) {
                    return false;
                }
                if (usageLimit <= timesUsed) {
                    return false;
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }
    
    public CouponsDTO getCouponDetails(String code) {
        
        try  {
            Connection con = DBUtils.getConnection();
            String sql = "SELECT * from coupons WHERE code = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new CouponsDTO(
                        rs.getInt("couponId"),
                        rs.getString("code"),
                        rs.getInt("discountPercentage"),
                        rs.getString("startDate"),
                        rs.getString("endDate"),
                        rs.getInt("usageLimit"),
                        rs.getInt("timesUsed"),
                        rs.getInt("isActive"),
                        rs.getInt("orderId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean updateCouponUsage(int couponId) {
        
        try  {
            Connection con = DBUtils.getConnection();
            String sql = "UPDATE coupons SET timesUsed = timesUsed + 1 WHERE couponId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, couponId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public int getDiscount(String code) {
        int discount = 0;
        try {
            Connection con = DBUtils.getConnection();
            String sql = "SELECT discountPercentage FROM coupons where code = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            discount = rs.getInt("discountPercentage");
        }
        
        con.close();
        } catch (Exception e) {
        }
        return discount;
    }
}
