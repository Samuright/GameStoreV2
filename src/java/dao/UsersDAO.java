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
import java.util.ArrayList;
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
        UsersDTO user = null;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = " SELECT username, userId, dateOfBirth, isAdmin, userImg, isBlocked, wallet \n"
                        + " FROM users \n"
                        + " WHERE email = ? AND password = ? ";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, email);
                st.setString(2, password);
                ResultSet table = st.executeQuery();
                if (table != null) {
                    if (table.next()) {
                        String username = table.getString("username");
                        int userId = table.getInt("userId");
                        String dob = table.getDate("dateOfBirth") + "";
                        int isAdmin = table.getInt("isAdmin");
                        int isBlocked = table.getInt("isBlocked");
                        double wallet = table.getDouble("wallet");
                        String userImg = table.getString("userImg");
                        user = new UsersDTO(username, password, userId, email, dob, isAdmin, isBlocked, wallet, userImg);
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
        return user;
    }

    public boolean addNewUser(UsersDTO user) {
        Connection cn = DBUtils.getConnection();
        boolean bool = false;
        try {
            if (cn != null) {
                String sql = "INSERT INTO [dbo].[users](userId, email, username, password, dateOfBirth, isAdmin, userImg, wallet, isBlocked)\n"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setInt(1, user.getUserId());
                st.setString(2, user.getEmail());
                st.setString(3, user.getUsername());
                st.setString(4, user.getPassword());
                st.setString(5, user.getDateOfBirth());
                st.setInt(6, user.getIsAdmin());
                st.setString(7, user.getUserImg());
                st.setDouble(8, user.getWallet());
                st.setInt(9, user.getIsBlocked());
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

    public int getMaxID() {
        Connection cn = null;
        Integer maxID = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT MAX(userId) AS [MAX] FROM users";
                PreparedStatement st = cn.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    maxID = rs.getInt("MAX");
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxID;
    }
    
    public ArrayList<UsersDTO> listUser(String keyword){
        Connection cn = null;
        UsersDTO user = null;
        ArrayList<UsersDTO> list = new ArrayList<>();
        
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = " SELECT username, userId, password, email, dateOfBirth, isAdmin, userImg, isBlocked, wallet \n"
                        + " FROM users \n"
                        + " WHERE email LIKE ? ";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, "%" + keyword + "%");
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        String username = table.getString("username");
                        int userId = table.getInt("userId");
                        String dob = table.getDate("dateOfBirth") + "";
                        int isAdmin = table.getInt("isAdmin");
                        int isBlocked = table.getInt("isBlocked");
                        double wallet = table.getDouble("wallet");
                        String userImg = table.getString("userImg");
                        String password = table.getString("password");
                        String email = table.getString("email");
                        
                        user = new UsersDTO(username, password, userId, email, dob, isAdmin, isBlocked, wallet, userImg);
                        list.add(user);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public UsersDTO getUserSaved(String email) {
        UsersDTO user = null;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = " SELECT username, password, userId, dateOfBirth, isAdmin, userImg, isBlocked, wallet \n"
                        + " FROM users \n"
                        + " WHERE email = ? ";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, email);
                ResultSet table = st.executeQuery();
                if (table != null) {
                    if (table.next()) {
                        String password = table.getString("password");
                        String username = table.getString("username");
                        int userId = table.getInt("userId");
                        String dob = table.getDate("dateOfBirth") + "";
                        int isAdmin = table.getInt("isAdmin");
                        String userImg = table.getString("userImg");
                        int isBlocked = table.getInt("isBlocked");
                        double wallet = table.getDouble("wallet");
                        user = new UsersDTO(username, password, userId, email, dob, isAdmin, isBlocked, wallet, userImg);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return user;
    }
    
    public boolean updateUser(UsersDTO user, String oldMail){
        Connection cn = null;
        boolean bool = false;
        try {
            cn = DBUtils.getConnection();
            if(cn != null){
                String sql = "UPDATE [dbo].[users] SET email = ?, username = ?,"
                        + " password = ?, dateOfBirth = ? WHERE email = ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, user.getEmail());
                st.setString(2, user.getUsername());
                st.setString(3, user.getPassword());
                st.setString(4, user.getDateOfBirth());
                st.setString(5, oldMail);
                st.executeUpdate();
                bool = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(cn != null){
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bool;
    }

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
}
