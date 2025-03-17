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
                        GameDTO game = new GameDTO(gameId, title, description, price, publisher, releaseDate, coverImageUrl, isDlc);
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
<<<<<<< HEAD

=======
                       
>>>>>>> 0615987 (Thêm tính năng user, sửa GameDTO)
                        String gameId = table.getString("gameId");
                        String title = table.getString("title");
                        String description = table.getString("description");
                        double price = table.getDouble("price");
                        String publisher = table.getString("publisher");
                        Date releaseDate = table.getDate("releaseDate");
                        String coverImageUrl = table.getString("coverImageUrl");
<<<<<<< HEAD

=======
>>>>>>> 0615987 (Thêm tính năng user, sửa GameDTO)
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

    public boolean addGame(GameDTO game, ArrayList<Integer> genreIds) throws SQLException {
        Connection cn = null;
        PreparedStatement st = null;
        boolean success = false;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                // Chèn vào bảng games
                String sql = "INSERT INTO games (title, description, price, coverImageUrl, minSpec, maxSpec, publisher, releaseDate, isDlc) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                st = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                st.setString(1, game.getTitle());
                st.setString(2, game.getDescription());
                st.setDouble(3, game.getPrice());
                st.setString(4, game.getCoverImageUrl());
                st.setString(5, game.getMinSpec());
                st.setString(6, game.getMaxSpec());
                st.setString(7, game.getPublisher());
                st.setDate(8, game.getReleaseDate());
                st.setInt(9, game.getIsDlc());
                int rows = st.executeUpdate();

                if (rows > 0) {
                    // Lấy gameId vừa tạo
                    try (ResultSet rs = st.getGeneratedKeys()) {
                        if (rs.next()) {
                            int gameId = rs.getInt(1);
                            game.setGameId(gameId);

                            // Chèn vào bảng games_genres
                            if (genreIds != null && !genreIds.isEmpty()) {
                                String sqlGenre = "INSERT INTO games_genres (gameId, genreId) VALUES (?, ?)";
                                st = cn.prepareStatement(sqlGenre);
                                for (Integer genreId : genreIds) {
                                    st.setInt(1, gameId);
                                    st.setInt(2, genreId);
                                    st.executeUpdate();
                                }
                            }
                            success = true;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (st != null) {
                st.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return success;
    }
}
