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
import model.WishlistDTO;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class WishlistDAO {

    public static List<WishlistDTO> list() {
        List<WishlistDTO> wishlistList = new ArrayList<WishlistDTO>();
        try {
            Connection con = DBUtils.getConnection();
            String sql = "SELECT wishlistId, userId, gameId, quantity from wishlistCart";
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int cartId = rs.getInt("wishlistId");
                int userId = rs.getInt("userId");
                int gameId = rs.getInt("gameId");
                int quantity = rs.getInt("quantity");

                WishlistDTO wishlist = new WishlistDTO();
                wishlist.setCartId(cartId);
                wishlist.setUserId(userId);
                wishlist.setGameId(gameId);
                wishlist.setQuantity(quantity);

                wishlistList.add(wishlist);
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error in servlet. Details:" + ex.getMessage());
            ex.printStackTrace();
        }
        return wishlistList;
    }

    public boolean isGameInWishlist(int userId, int gameId) {
        try {
            Connection con = DBUtils.getConnection();
            String sql = "SELECT 1 FROM wishlistCart WHERE userId = ? AND gameId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, gameId);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // If a row exists, the game is already in the wishlist
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean insert(int userId, int gameId, int quantity) {
        try {
            Connection con = DBUtils.getConnection();
            String sql = "Insert into wishlistCart (wishlistId, userId, gameId, quantity) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            int id = generateId();
            ps.setInt(1, id);
            ps.setInt(2, userId);
            ps.setInt(3, gameId);
            ps.setInt(4, quantity);
            int rowsAffected = ps.executeUpdate();
            con.close();
            return rowsAffected > 0; //true neu insert thanh cong
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean delete(int cartId) {
        try {
            Connection con = DBUtils.getConnection();
            String sql = "DELETE FROM wishlistCart WHERE wishlistId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cartId);
            int rowsAffected = ps.executeUpdate();
            con.close();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static int generateId() {
        String sql = "Select Max(wishlistId) from wishlistCart";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int id = 1;
            if (rs.next()) {
                int maxId = rs.getInt(1);
                if (!rs.wasNull()) {
                    id = maxId + 1;
                }
            }
            con.close();
            return id;
        } catch (Exception e) {
        }
        return -1;
    }

    public boolean updateQuantity(int cartId, int quantity) {
        String sql = "UPDATE wishlistCart SET quantity = ? WHERE wishlistId = ?";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, cartId);
            int rowsUpdated = ps.executeUpdate();
            con.close();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
