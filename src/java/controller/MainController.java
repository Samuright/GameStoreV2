/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CouponsDAO;
import dao.GameDAO;
import dao.GenreDAO;
import dao.GenresDAO;
import dao.UsersDAO;
import dao.WishlistDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.GameDTO;
import model.GenreDTO;
import model.UsersDTO;
import model.WishlistDTO;

/**
 *
 * @author Quoc Bao
 */
public class MainController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String HOME_PAGE = "homePage.jsp";
    private static final String REGISTSER_PAGE = "registerPage.jsp";
    private static final String GAME_MANAGEMENT_PAGE = "gameManagement.jsp";
    private static final String USER_PAGE = "userPage.jsp";
    private static final String EDIT_USER_PAGE = "editUser.jsp";
    private static final String WISHLIST_PAGE = "wishlist.jsp";
    private static final String SHOPPING_CART_PAGE = "shoppingCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        String action = request.getParameter("action");

        HttpSession session = request.getSession(false);
        if (!"login".equals(action)) {
            if (session == null || session.getAttribute("user") == null) {
                response.sendRedirect("login.jsp");
                return;
            }
        }
        try {
            if (action == null) {
                action = "";
            }
            switch (action) {
                case "login":
                    url = processLogin(request, response);
                    break;
                case "logout":
                    url = processLogout(request, response);
                    break;
                case "register":

                    url = processRegister(request, response);
                    break;
                case "detail":
                    url = processGameDetail(request, response);
                    break;
                case "listUser":
                    url = processListUser(request, response);
                    break;
                case "editUser":
                    url = processUserEdit(request, response);
                    break;
                case "update":
                    url = processUpdateUser(request, response);
                    break;
                case "loadtop5":
                    processTop5Sell(request, response);
                    url = "top5.jsp";
                    break;
                case "":
                case "list":
                    handleList(request, response);

                    break;
                case "cartList":
                    handleCartList(request, response);

                    break;
                case "addToCart":
                    handleAddToCart(request, response);

                    break;
                case "delete":
                    handleCartDelete(request, response);

                    break;
                case "quantity":
                    handleQuantity(request, response);

                    break;
                case "applyCoupon":
                    handleApplyCoupon(request, response);

                    break;
                case "addToWishlist":
                    handleAddToWishlist(request, response);

                    break;
                case "wishlistList":
                    handlewishlistList(request, response);

                    break;
                case "wishlistDelete":
                    handlewishlistDelete(request, response);

                    break;
                case "updateQuantity":
                    handlewishlistQuantity(request, response);

                    break;

                default:

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
                url = processBestSeller(request, response);
                try {
                    handleList(request, response); // Set full game list and forward
                } catch (ServletException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
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
        String url = REGISTSER_PAGE;
        String email = request.getParameter("txtmail");
        String username = request.getParameter("txtusername");
        String password = request.getParameter("txtpassword");
        String passwordCheck = request.getParameter("txtpasscheck");
        String dob = request.getParameter("txtdob");
        UsersDAO d = new UsersDAO();
        UsersDTO user = new UsersDTO();
        UsersDTO checkUser = d.getUserSaved(email);

        if (checkUser != null) {
            request.setAttribute("ERROR", "User email existed");
            return url;
        }

        if (password.equals(passwordCheck)) {
            int userId = d.getMaxID();
            userId++;

            user.setUserId(userId);
            user.setEmail(email);
            user.setPassword(password);
            user.setUsername(username);
            user.setIsAdmin(0);
            user.setIsBlocked(0);
            user.setDateOfBirth(dob);
            user.setUserImg("");
            user.setWallet(0);

            if (d.addNewUser(user)) {
                url = LOGIN_PAGE;
                request.setAttribute("NOTI", "Register Successfully!");
            }
        } else {
            request.setAttribute("ERROR", "Password not match!");
            url = REGISTSER_PAGE;
        }

        return url;
    }

    protected String processListUser(HttpServletRequest request, HttpServletResponse response) {
        String url = HOME_PAGE;
        String keyword = "";
        ArrayList<UsersDTO> list = new ArrayList<>();
        UsersDAO d = new UsersDAO();
        list = d.listUser(keyword);
        if (list != null && !list.isEmpty()) {
            request.setAttribute("listUser", list);
            url = USER_PAGE;
        } else {
            request.setAttribute("ERROR", "User empty!");

        }
        return url;
    }

    protected String processUserEdit(HttpServletRequest request, HttpServletResponse response) {
        String oldMail = request.getParameter("oldEmail");
        UsersDTO user = new UsersDTO();
        UsersDAO d = new UsersDAO();
        user = d.getUserSaved(oldMail);
        request.setAttribute("userToUpdate", user);
        return "editUser.jsp";
    }

    protected String processUpdateUser(HttpServletRequest request, HttpServletResponse response) {
        String url = USER_PAGE;
        String date = request.getParameter("date");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String checkPassword = request.getParameter("checkPassword");
        String username = request.getParameter("username");

        String oldMail = request.getParameter("oldEmail");
        UsersDAO d = new UsersDAO();
        UsersDTO user = new UsersDTO();
        user = d.getUserSaved(oldMail);
        user.setDateOfBirth(date);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setWallet(user.getWallet());
        user.setIsBlocked(user.getIsBlocked());
        user.setIsAdmin(user.getIsAdmin());
        user.setUserImg(user.getUserImg());

        if (!password.equals(checkPassword)) {
            url = processUserEdit(request, response);
            request.setAttribute("ERROR", "Password not match!");
            return url;
        }

        if (d.updateUser(user, oldMail)) {
            url = processListUser(request, response);
        }

        return url;
    }

    protected String processBestSeller(HttpServletRequest request, HttpServletResponse response) {
        String url = LOGIN_PAGE;
        ArrayList<GameDTO> list = new ArrayList<>();
        GameDAO d = new GameDAO();
        list = d.listBestSeller();
        if (list != null && !list.isEmpty()) {
            HttpSession session = request.getSession();
            session.setAttribute("listGame", list);
            url = HOME_PAGE;
        } else {
            request.setAttribute("ERROR", "List empty!");
        }
        return url;
    }

    protected String processGameDetail(HttpServletRequest request, HttpServletResponse response) {
        String url = processGameList(request, response);
        GameDAO d = new GameDAO();
        ArrayList<GameDTO> game = null;
        String title = request.getParameter("title");
        game = d.list(title);
        if (game != null) {
            url = "gameDetail.jsp";
            request.setAttribute("game", game);
        }

        return url;
    }

    protected String processGameList(HttpServletRequest request, HttpServletResponse response) {
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

            // Lấy danh sách thể loại từ genreDAO
            GenreDAO genreDAO = new GenreDAO();
            ArrayList<GenreDTO> genreList = genreDAO.getAllgenre();
            if (genreList == null || genreList.isEmpty()) {
                request.setAttribute("NOTI", "No genre available. Please add genre first!");
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

            String[] genreIdsStr = request.getParameterValues("genre");

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

            GenreDAO genreDAO = new GenreDAO();
            request.setAttribute("genreList", genreDAO.getAllgenre());

        } catch (Exception e) {
            request.setAttribute("NOTI", "Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        return url;
    }

    protected void handleList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        GameDAO dao = new GameDAO();
        List<GameDTO> list = dao.list();
        request.setAttribute("gameslist", list);

        request.getRequestDispatcher("homePage.jsp").forward(request, response);
    }

    protected void handleCartList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        GameDAO gameDAO = new GameDAO();
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return;
        }

        if (action.equals("cartList")) {
            List<GameDTO> shoppingCartList = (List<GameDTO>) session.getAttribute("shoppingCartList");

            if (shoppingCartList == null) {
                shoppingCartList = new ArrayList<>();
                session.setAttribute("shoppingCartList", shoppingCartList);
            }

            request.setAttribute("shoppingCartList", shoppingCartList);
            request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
        }
    }

    protected void handleAddToCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        GameDAO gameDAO = new GameDAO();
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return;
        }

        if (action.equals("addToCart")) {
            List<GameDTO> shoppingCartList = (List<GameDTO>) session.getAttribute("shoppingCartList");

            if (shoppingCartList == null) {
                shoppingCartList = new ArrayList<>();
            }

            int id = Integer.parseInt(request.getParameter("id"));
            GameDTO games = GameDAO.load(id);

            if (games != null && shoppingCartList.stream().noneMatch(g -> g.getGameId() == games.getGameId())) { //de check xem co trung id trong shopping cart, neu co thi chi dc add 1 lan
                games.setOriginalPrice(games.getPrice());
                shoppingCartList.add(games);
            }
            List<GameDTO> list = GameDAO.list();
            session.setAttribute("shoppingCartList", shoppingCartList);
            request.setAttribute("gameslist", list);
            request.getRequestDispatcher("homePage.jsp").forward(request, response);

        }
    }

    protected void handleCartDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        GameDAO gameDAO = new GameDAO();
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return;
        } else if (action.equals("delete")) {
            List<GameDTO> shoppingCartList = (List<GameDTO>) session.getAttribute("shoppingCartList");

            if (shoppingCartList == null) {
                shoppingCartList = new ArrayList<>();
            }

            int id = Integer.parseInt(request.getParameter("id"));
            shoppingCartList.removeIf(game -> game.getGameId() == id); //xoa game co voi id
            session.setAttribute("shoppingCartList", shoppingCartList);
            request.setAttribute("shoppingCartList", shoppingCartList);
            request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
        }
    }

    protected void handleQuantity(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        GameDAO gameDAO = new GameDAO();
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return;
        }

        if (action.equals("quantity")) {
            int gameId = Integer.parseInt(request.getParameter("id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            List<GameDTO> shoppingCartList = (List<GameDTO>) session.getAttribute("shoppingCartList");

            if (shoppingCartList != null) {
                for (GameDTO game : shoppingCartList) {
                    if (game.getGameId() == gameId) {
                        game.setQuantity(quantity); // Ensure GamesDTO has a setQuantity method
                        break;
                    }
                }
            }

            session.setAttribute("shoppingCartList", shoppingCartList);
            request.setAttribute("shoppingCartList", shoppingCartList);
            request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
        }
    }

    protected void handleApplyCoupon(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        GameDAO gameDAO = new GameDAO();
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return;
        }

        if (action.equals("applyCoupon")) {
            String code = request.getParameter("code");
            CouponsDAO dao = new CouponsDAO();

            List<GameDTO> shoppingCartList = (List<GameDTO>) session.getAttribute("shoppingCartList");
            boolean check = dao.validateCoupon(code);
            if (!check) {
                request.setAttribute("errorCode", "Code khong hop le hoac het hieu luc");
                request.setAttribute("shoppingCartList", shoppingCartList);
                request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
                return;
            } else {
                int discount = dao.getDiscount(code);
                for (GameDTO game : shoppingCartList) {
                    double discountedPrice = game.getOriginalPrice() * (1 - discount / 100.0);
                    game.setPrice(discountedPrice);
                }
                request.setAttribute("couponApplied", true);
            }

            session.setAttribute("shoppingCartList", shoppingCartList);
            request.setAttribute("shoppingCartList", shoppingCartList);
            request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
        }
    }

    protected void handleAddToWishlist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return;
        }

        if (action.equals("addToWishlist")) {
            UsersDTO user = (UsersDTO) session.getAttribute("user");
            int userId = user.getUserId();
            int gameId = Integer.parseInt(request.getParameter("id"));
            int quantity = 1;

            WishlistDAO dao = new WishlistDAO();

            if (dao.isGameInWishlist(userId, gameId)) {
                request.setAttribute("wishlistError", "Game is already in wishlist");
                GameDAO gameDAO = new GameDAO();
                List<GameDTO> list = gameDAO.list();
                request.setAttribute("gameslist", list);
                request.getRequestDispatcher("homePage.jsp").forward(request, response);
                return;
            } else {
                boolean checkAdd = dao.insert(userId, gameId, quantity);
                if (!checkAdd) {
                    request.setAttribute("wishlistError", "Failed to add game to wishlist.");
                } else {
                    request.setAttribute("wishlistError", "Game added to wishlist successfully!");
                }
            }
            GameDAO gameDAO = new GameDAO();
            List<GameDTO> list = gameDAO.list();
            request.setAttribute("gameslist", list);
            request.getRequestDispatcher("homePage.jsp").forward(request, response);
        }
    }

    protected void handlewishlistList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return;
        }

        if (action.equals("wishlistList")) {
            UsersDTO user = (UsersDTO) session.getAttribute("user");
            int userId = user.getUserId();

            WishlistDAO wishlistDAO = new WishlistDAO();
            List<WishlistDTO> wishlist = wishlistDAO.list();

            List<WishlistDTO> userWishlist = new ArrayList<>();
            for (WishlistDTO item : wishlist) {
                if (item.getUserId() == userId) {
                    userWishlist.add(item);
                }
            }
            GameDAO gameDAO = new GameDAO();
            List<GameDTO> gamesList = new ArrayList<>();
            for (WishlistDTO item : userWishlist) {
                GameDTO game = gameDAO.load(item.getGameId());
                if (game != null) {
                    gamesList.add(game); // Add game details to gamesList
                }
            }
            request.setAttribute("gamesList", gamesList);
            request.setAttribute("wishlistGames", userWishlist);
            request.getRequestDispatcher("wishlist.jsp").forward(request, response);
        }
    }

    protected void handlewishlistDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        GameDAO gameDAO = new GameDAO();
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return;
        }
        if (action.equals("wishlistDelete")) {
            WishlistDAO dao = new WishlistDAO();
            int cartId = Integer.parseInt(request.getParameter("cartId"));
            dao.delete(cartId);

            UsersDTO user = (UsersDTO) session.getAttribute("user");
            int userId = user.getUserId();

            List<WishlistDTO> wishlist = dao.list();
            List<WishlistDTO> userWishlist = new ArrayList<>();
            for (WishlistDTO item : wishlist) {
                if (item.getUserId() == userId) {
                    userWishlist.add(item);
                }
            }

            List<GameDTO> gamesList = new ArrayList<>();
            for (WishlistDTO item : userWishlist) {
                GameDTO game = gameDAO.load(item.getGameId());
                if (game != null) {
                    gamesList.add(game);
                }
            }

            request.setAttribute("gamesList", gamesList);
            request.setAttribute("wishlistGames", userWishlist);
            request.getRequestDispatcher("wishlist.jsp").forward(request, response);
        }
    }

    protected void handlewishlistQuantity(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        GameDAO gameDAO = new GameDAO();
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return;
        }
        if (action.equals("updateQuantity")) {
            int cartId = Integer.parseInt(request.getParameter("cartId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            WishlistDAO dao = new WishlistDAO();
            dao.updateQuantity(cartId, quantity);

            UsersDTO user = (UsersDTO) session.getAttribute("user");
            int userId = user.getUserId();

            List<WishlistDTO> wishlist = dao.list();
            List<WishlistDTO> userWishlist = new ArrayList<>();
            for (WishlistDTO item : wishlist) {
                if (item.getUserId() == userId) {
                    userWishlist.add(item);
                }
            }

            List<GameDTO> gamesList = new ArrayList<>();
            for (WishlistDTO item : userWishlist) {
                GameDTO game = gameDAO.load(item.getGameId());
                if (game != null) {
                    gamesList.add(game);
                }
            }

            request.setAttribute("gamesList", gamesList);
            request.setAttribute("wishlistGames", userWishlist);
            request.getRequestDispatcher("wishlist.jsp").forward(request, response);
        }
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
