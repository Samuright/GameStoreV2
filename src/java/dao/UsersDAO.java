/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UsersDTO;
import utils.DBUtils;

/**
 *
 * @author Quoc Bao
 */
public class UsersDAO {

    public UsersDTO checkLogin(String email, String password) {
        UsersDTO rs = null;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = " SELECT username, userId, dateOfBirth, isAdmin, isBlocked, address \n"
                        + " FROM users \n"
                        + " WHERE email = ? AND password = ? ";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, email);
                st.setString(2, password);
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        String username = table.getString("username");
                        int userId = table.getInt("userId");
                        String dob = table.getDate("dateOfBirth") + "";
                        //int isAdmin = table.getInt("isAdmin");
                        String address = table.getString("address");
                        //int isBlocked = table.getInt("isBlocked");
                        rs = new UsersDTO(username, password, userId, email, dob, 1, address, 0);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rs;
    }

    public boolean addNewUser(UsersDTO user) {
        Connection cn = DBUtils.getConnection();
        boolean bool = false;
        try {
            if (cn != null) {
                String sql = "INSERT INTO [dbo].[users](userId, email, username, password, dateOfBirth, isAdmin, address) "
                        + "VALUES(?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setInt(1, user.getUserId());
                st.setString(2, user.getEmail());
                st.setString(3, user.getUsername());
                st.setString(4, user.getPassword());
                st.setString(5, user.getDateOfBirth());
                st.setInt(6, user.getIsAdmin());
                st.setString(7, user.getAddress());
                st.executeUpdate();
                bool = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bool;
    }
    
    public int getMaxID(){
        Connection cn = null;
        Integer maxID = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT MAX(userId) AS [MAX] FROM users";
                PreparedStatement st = cn.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                if(rs.next()){
                    maxID = rs.getInt("MAX");
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxID;
    }
//    public void saveLogin(String email, String remember) {
//        Connection cn = null;
//        try {
//            cn = DBUtils.getConnection();
//            if (cn != null) {
//                String sql = "UPDATE users SET rememberLogin = ? WHERE email = ?";
//                PreparedStatement st = cn.prepareStatement(sql);
//                st.setString(1, remember);
//                st.setString(2, email);
//                st.executeUpdate();
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//    }
//
//    public UsersDTO getUserSaved(String remember) {
//        UsersDTO user = null;
//        Connection cn = null;
//        try {
//            cn = DBUtils.getConnection();
//            if (cn != null) {
//                String sql = "SELECT username, userId, dateOfBirth, isAdmin, isBlocked\n"
//                        + "FROM users\n"
//                        + "WHERE rememberLogin = ?";
//                PreparedStatement st = cn.prepareStatement(sql);
//                st.setString(1, remember);
//                ResultSet rs = st.executeQuery();
//                if (rs != null) {
//                    String password = rs.getString("password");
//                    String username = rs.getString("username");
//                    String email = rs.getString("email");
//                    String userId = rs.getString("userId");
//                    String dateOfBirth = rs.getString("dateOfBirth");
//                    int isAdmin = rs.getInt("isAdmin");
//                    int isBlocked = rs.getInt("isBlocked");
//                    user = new UsersDTO(username, password, userId, email, dateOfBirth, isAdmin, isBlocked);
//                }
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                if (cn != null) {
//                    cn.close();
//                }
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return user;
//    }
//
//    public void checkLogout(String remember) {
//        try {
//            Connection cn = DBUtils.getConnection();
//            if (cn != null) {
//                String sql = "UPDATE users SET rememberLogin = NONE";
//                PreparedStatement st = cn.prepareStatement(sql);
//                st.executeUpdate(sql);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//    }
}
