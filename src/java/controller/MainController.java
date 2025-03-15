/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.GameDAO;
import dao.GenresDAO;
import dao.UsersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.GameDTO;
import model.GenreDTO;
import model.UsersDTO;

/**
 *
 * @author Quoc Bao
 */
public class MainController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String HOME_PAGE = "homePage.jsp";
    private static final String REGITER_PAGE = "registerPage.jsp";
    private static final String GAME_MANAGEMENT_PAGE = "gameManagement.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        String action = request.getParameter("action");
        try {
            if (action == null) {
                url = LOGIN_PAGE;
            } else {
                switch (action) {
                    case "login":
                        url = processLogin(request, response);
                        break;
                    case "logout":
                        url = processLogout(request, response);
                        break;
                    case "register":
                        url = REGITER_PAGE;
                        break;
                    case "insertNewUser":
                        url = processRegister(request, response);
                        break;
                    case "loadtop5":
                        processTop5Sell(request, response);
                        url = "top5.jsp";
                        break;
                    case "gameManagement": // Hiển thị trang quản lý trò chơi
                        url = processGameManagement(request, response);
                        break;
                    case "addGame": // Thêm trò chơi mới
                        url = processAddGame(request, response);
                        break;
                    default:
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    protected String processLogin(HttpServletRequest request, HttpServletResponse response) {
        String url = LOGIN_PAGE;
        String title = "";
        String username = request.getParameter("txtmail");
        String password = request.getParameter("txtpassword");
        if (username != null && password != null) {
            UsersDTO user = null;
            UsersDAO d = new UsersDAO();
            user = d.checkLogin(username, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                url = processList(request, response);
            } else {
                request.setAttribute("ERROR", "Email or password invalid!");
            }
        }
        return url;
    }

    protected String processLogout(HttpServletRequest request, HttpServletResponse response) {
        String url = HOME_PAGE;
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            session.invalidate();
            request.setAttribute("NOTI", "Logout successfully!!!");
            url = LOGIN_PAGE;
        }
        return url;
    }

    protected String processRegister(HttpServletRequest request, HttpServletResponse response) {
        String url = REGITER_PAGE;
        String email = request.getParameter("txtmail");
        String username = request.getParameter("txtusername");
        String password = request.getParameter("txtpassword");
        UsersDAO d = new UsersDAO();
        UsersDTO user = new UsersDTO();

        int userId = d.getMaxID();
        userId++;

        user.setUserId(userId);
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        user.setIsAdmin(0);
        user.setIsBlocked(0);
        user.setDateOfBirth("2005-09-20");
        user.setAddress("o dau do");

        if (d.addNewUser(user)) {
            url = LOGIN_PAGE;
            request.setAttribute("NOTI", "Register Successfully!");
        }
        return url;
    }

    protected String processList(HttpServletRequest request, HttpServletResponse response) {
        String title = "";
        String url = LOGIN_PAGE;
        GameDAO d = new GameDAO();
        ArrayList<GameDTO> list = d.list(title);
        HttpSession session = request.getSession();
        session.setAttribute("listGame", list);
        url = HOME_PAGE;
        return url;
    }

    protected void processTop5Sell(HttpServletRequest request, HttpServletResponse response) {
        GameDAO dao = new GameDAO();
        dao.loadTop5();
    }

    protected String processGameManagement(HttpServletRequest request, HttpServletResponse response) {
        String url = GAME_MANAGEMENT_PAGE; // Trang mặc định là gameManagement.jsp
        try {
            // Kiểm tra session và quyền admin
            HttpSession session = request.getSession(false); // Không tạo session mới nếu chưa có
            if (session == null || session.getAttribute("user") == null) {
                request.setAttribute("NOTI", "Please log in first!");
                return LOGIN_PAGE;
            }

            // Lấy thông tin người dùng từ session
            UsersDTO user = (UsersDTO) session.getAttribute("user");
            if (user == null || user.getIsAdmin() != 1) {
                request.setAttribute("NOTI", "You must be an admin to access this page!");
                return LOGIN_PAGE;
            }

            // Lấy danh sách thể loại từ GenresDAO
            GenresDAO genresDAO = new GenresDAO();
            ArrayList<GenreDTO> genreList = genresDAO.getAllGenres();
            if (genreList == null || genreList.isEmpty()) {
                request.setAttribute("NOTI", "No genres available. Please add genres first!");
            } else {
                request.setAttribute("genreList", genreList); // Lưu danh sách thể loại vào request
            }

        } catch (Exception e) {
            request.setAttribute("NOTI", "Error loading game management page: " + e.getMessage());
            e.printStackTrace();
            url = LOGIN_PAGE; // Quay về trang login nếu có lỗi
        }
        return url;
    }

    protected String processAddGame(HttpServletRequest request, HttpServletResponse response) {
        String url = GAME_MANAGEMENT_PAGE;
        try {
            HttpSession session = request.getSession();
            UsersDTO user = (UsersDTO) session.getAttribute("user");
            if (user == null || user.getIsAdmin() != 1) {
                request.setAttribute("NOTI", "You must be an admin to perform this action!");
                return LOGIN_PAGE;
            }

            String title = request.getParameter("title");
            if (title == null || title.trim().isEmpty()) {
                request.setAttribute("NOTI", "Tiêu đề là bắt buộc!");
                return url;
            }

            String[] genreIdsStr = request.getParameterValues("genres");
            if (genreIdsStr == null || genreIdsStr.length == 0) {
                request.setAttribute("NOTI", "Phải chọn ít nhất một thể loại!");
                return url;
            }
            
            String priceStr = request.getParameter("price");
            double price;
            try {
                price = Double.parseDouble(priceStr);
                if (price <= 0) {
                    request.setAttribute("NOTI", "Giá phải lớn hơn 0!");
                    return url;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("NOTI", "Định dạng giá không hợp lệ!");
                return url;
            }

            String description = request.getParameter("description");
            String minSpec = request.getParameter("minSpec");
            String maxSpec = request.getParameter("maxSpec");
            String publisher = request.getParameter("publisher");
            String releaseDateStr = request.getParameter("releaseDate");
            String isDlcStr = request.getParameter("isDlc");

            // Validate release date format
            Date releaseDate = null;
            if (releaseDateStr != null && !releaseDateStr.trim().isEmpty()) {
                releaseDate = Date.valueOf(releaseDateStr);
            }

            int isDlc = (isDlcStr != null && isDlcStr.equals("1")) ? 1 : 0;

            String coverImageUrl = "default_image.jpg";

            GameDTO game = new GameDTO();
            game.setTitle(title);
            game.setDescription(description);
            game.setPrice(price);
            game.setPublisher(publisher);
            game.setReleaseDate(releaseDate);
            game.setCoverImageUrl(coverImageUrl);
            game.setIsDlc(isDlc);
            game.setMinSpec(minSpec);
            game.setMaxSpec(maxSpec);

            ArrayList<Integer> genreIds = new ArrayList<>();
            for (String genreIdStr : genreIdsStr) {
                genreIds.add(Integer.parseInt(genreIdStr));
            }

            GameDAO gameDAO = new GameDAO();
            if (gameDAO.addGame(game, genreIds)) {
                request.setAttribute("NOTI", "Thêm trò chơi thành công!");
            } else {
                request.setAttribute("NOTI", "Thêm trò chơi thất bại!");
            }

            GenresDAO genresDAO = new GenresDAO();
            request.setAttribute("genreList", genresDAO.getAllGenres());

        } catch (Exception e) {
            request.setAttribute("NOTI", "Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        return url;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
