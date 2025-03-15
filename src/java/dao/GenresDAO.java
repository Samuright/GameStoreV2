package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.GenreDTO;
import utils.DBUtils;

public class GenresDAO {

    public ArrayList<GenreDTO> getAllGenres() {
        ArrayList<GenreDTO> genreList = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT genreId, name FROM genres";
                PreparedStatement st = cn.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    int genreId = rs.getInt("genreId");
                    String name = rs.getString("name");
                    GenreDTO genre = new GenreDTO(genreId, name);
                    genreList.add(genre);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return genreList;
    }

    // Thêm thể loại mới (nếu cần)
    public boolean addGenre(String name) {
        Connection cn = null;
        boolean success = false;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                // Lấy max genreId
                String sqlMax = "SELECT MAX(genreId) AS maxId FROM genres";
                PreparedStatement stMax = cn.prepareStatement(sqlMax);
                ResultSet rs = stMax.executeQuery();
                int genreId = 1;
                if (rs.next()) {
                    genreId = rs.getInt("maxId") + 1;
                }

                // Thêm thể loại mới
                String sql = "INSERT INTO genres (genreId, name) VALUES (?, ?)";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setInt(1, genreId);
                st.setString(2, name);
                int rows = st.executeUpdate();
                success = rows > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }
}