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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.GameDTO;
import utils.DBUtils;

/**
 *
 * @author Quoc Bao
 */
public class GameDAO {

    public ArrayList<GameDTO> list(String title) {
        ArrayList<GameDTO> listGame = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT gameId, title, description, price, publisher, releaseDate, coverImageUrl, isDlc\n"
                        + "FROM games\n"
                        + "WHERE TITLE LIKE ? ";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, "%" + title + "%");
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        title = table.getString("title");
                        String gameId = table.getString("gameId");
                        String description = table.getString("description");
                        double price = table.getDouble("price");
                        String publisher = table.getString("publisher");
                        Date releaseDate = table.getDate("releaseDate");
                        String coverImageUrl = table.getString("coverImageUrl");
                        int isDlc = table.getInt("isDlc");
                        GameDTO game = new GameDTO(gameId, title, description, price, publisher, releaseDate, coverImageUrl,isDlc);
                        listGame.add(game);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return listGame;
    }
    public ArrayList<GameDTO> loadTop5() {
        ArrayList<GameDTO> listGame = new ArrayList<>();
        Connection con = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT top 5 gameId, title, description, price, publisher, releaseDate, coverImageUrl, isDlc from games ORDER BY price DESC ";
                PreparedStatement st = con.prepareStatement(sql);
                
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                       
                        String gameId = table.getString("gameId");
                        String title = table.getString("title");
                        String description = table.getString("description");
                        double price = table.getDouble("price");
                        String publisher = table.getString("publisher");
                        Date releaseDate = table.getDate("releaseDate");
                        String coverImageUrl = table.getString("coverImageUrl");
                        int isDlc = table.getInt("isDlc");
                        GameDTO game = new GameDTO(gameId, title, description, price, publisher, releaseDate, coverImageUrl,isDlc);
                        listGame.add(game);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
            
        return listGame;
    }
}
