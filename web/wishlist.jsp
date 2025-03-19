<%-- 
    Document   : wishlist
    Created on : Mar 13, 2025, 6:59:22 PM
    Author     : Admin
--%>

<%@page import="model.WishlistDTO"%>
<%@page import="dao.WishlistDAO"%>
<%@page import="java.util.List"%>
<%@page import="model.GameDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>My Wishlist - DKEY</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome for icons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <style>
            body {
                background-color: #1a2433;
                color: white;
                font-family: Arial, sans-serif;
            }

            .header {
                background-color: #0f1923;
                border-bottom: 1px solid #2a3a4a;
                padding: 15px 0;
            }

            .logo {
                font-size: 24px;
                font-weight: bold;
                color: white;
                text-decoration: none;
            }

            .search-container {
                position: relative;
                width: 100%;
                max-width: 300px;
            }

            .search-input {
                background-color: #374b61;
                border: none;
                border-radius: 20px;
                color: white;
                padding: 6px 35px 6px 15px;
                font-size: 14px;
                height: 32px;
            }

            .search-input::placeholder {
                color: #8499b1;
            }

            .search-input:focus {
                background-color: #374b61;
                box-shadow: none;
                border: none;
                color: white;
            }

            .search-icon {
                position: absolute;
                right: 10px;
                top: 50%;
                transform: translateY(-50%);
                color: #8499b1;
                font-size: 14px;
            }

            .nav-menu {
                background-color: #1e2a3a;
                padding: 8px 0;
            }

            .nav-link {
                color: #8499b1;
                text-transform: uppercase;
                font-weight: bold;
                padding: 10px 15px;
            }

            .nav-link:hover {
                color: white;
            }

            .section-title {
                border-bottom: 1px solid #374b61;
                padding-bottom: 10px;
                text-transform: uppercase;
                font-size: 24px;
                margin: 20px 0;
                text-align: center;
                color: #e0e0ff;
            }

            .wishlist-table {
                width: 80%;
                margin: 0 auto;
                border-collapse: collapse;
                background-color: #1e2a3a;
                border-radius: 8px;
                overflow: hidden;
            }

            .wishlist-table th, .wishlist-table td {
                padding: 15px;
                text-align: left;
                border-bottom: 1px solid #374b61;
            }

            .wishlist-table th {
                background-color: #0f1923;
                color: #e0e0ff;
                text-transform: uppercase;
                font-size: 14px;
            }

            .wishlist-table td img {
                width: 100px;
                height: 150px;
                object-fit: cover;
                border-radius: 5px;
            }

            .wishlist-table td a {
                color: #ffffff;
                text-decoration: none;
                font-size: 16px;
            }

            .wishlist-table td a:hover {
                color: #e0e0ff;
            }

            .quantity-select {
                background-color: #374b61;
                color: white;
                border: none;
                padding: 5px;
                border-radius: 5px;
                width: 60px;
            }

            .btn-primary {
                background-color: #5b42f3;
                border: none;
                padding: 8px 15px;
                border-radius: 5px;
                font-size: 14px;
            }

            .btn-primary:hover {
                background-color: #4935cc;
            }

            .empty-wishlist {
                text-align: center;
                color: #b0b0d0;
                padding: 20px;
                font-size: 18px;
            }

            .footer {
                background-color: #0f1923;
                padding: 30px 0;
                margin-top: 40px;
            }

            .footer h5 {
                color: #8499b1;
                margin-bottom: 15px;
                font-size: 14px;
                text-transform: uppercase;
            }

            .footer a {
                color: #5b6d83;
                text-decoration: none;
                font-size: 12px;
                display: block;
                margin-bottom: 8px;
            }

            .footer a:hover {
                color: #8499b1;
            }

            .copyright {
                background-color: #0f1923;
                border-top: 1px solid #2a3a4a;
                padding: 10px 0;
                color: #5b6d83;
                font-size: 12px;
            }
        </style>
    </head>
    <body>
        <!-- Header -->
        <div class="header">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-md-3">
                        <a href="MainController?action=list" class="logo">DKEY</a>
                    </div>
                    <div class="col-md-6">
                        <div class="search-container">
                            <input type="text" class="form-control search-input" placeholder="SEARCH">
                            <span class="search-icon"><i class="fas fa-search"></i></span>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="d-flex justify-content-end gap-4">
                            <a href="#" title="Profile" class="text-white"><i class="fas fa-user"></i></a>
                            <a href="MainController?action=cartList" title="Cart" class="text-white"><i class="fas fa-shopping-cart"></i></a>
                            <a href="MainController?action=wishlistList" title="Wishlist" class="text-white"><i class="fas fa-heart"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Navigation Menu -->
        <div class="nav-menu">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <ul class="nav justify-content-center">
                            <li class="nav-item"><a href="#" class="nav-link">PC</a></li>
                            <li class="nav-item"><a href="#" class="nav-link">PS</a></li>
                            <li class="nav-item"><a href="#" class="nav-link">XBOX</a></li>
                            <li class="nav-item"><a href="#" class="nav-link">NINTENDO</a></li>
                            <li class="nav-item"><a href="#" class="nav-link">SALE</a></li>
                            <li class="nav-item"><a href="#" class="nav-link">CATEGORY</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <!-- Wishlist Section -->
        <div class="container mb-5">
            <h2 class="section-title">MY WISHLIST</h2>

            <table class="wishlist-table">
                <tr>
                    <th>Id</th>
                    <th>Image</th>
                    <th>Game Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
                <%
                    List<GameDTO> gamesList = (List<GameDTO>) request.getAttribute("gamesList");
                    List<WishlistDTO> wishlistList = (List<WishlistDTO>) request.getAttribute("wishlistGames");

                    if (gamesList != null && !gamesList.isEmpty() && wishlistList != null && !wishlistList.isEmpty()) {
                        for (GameDTO games : gamesList) {
                            WishlistDTO matchingWishlist = null;
                            for (WishlistDTO wishlist : wishlistList) {
                                if (wishlist.getGameId() == games.getGameId()) {
                                    matchingWishlist = wishlist;
                                    break;
                                }
                            }
                            if (matchingWishlist != null) {
                                pageContext.setAttribute("games", games);
                                pageContext.setAttribute("wishlist", matchingWishlist);
                %>
                <tr>
                    <td>${games.gameId}</td>
                    <td><img src="<%= games.getCoverImageUrl()%>" alt="Game Cover"></td>
                    <td><a href="MainController?id=${games.gameId}">${games.title}</a></td>
                    <td><%= String.format("%.2f", games.getPrice() * matchingWishlist.getQuantity()) %>$</td>
                    <td>
                        <form action="MainController" method="POST">
                            <input type="hidden" name="action" value="updateQuantity">
                            <input type="hidden" name="cartId" value="${wishlist.cartId}">
                            <select name="quantity" class="quantity-select" onchange="this.form.submit()">
                                <% for (int i = 1; i <= 10; i++) { %>
                                <option value="<%= i %>" <%= (matchingWishlist.getQuantity() == i) ? "selected" : "" %>><%= i %></option>
                                <% } %>
                            </select>
                        </form>
                    </td>
                    <td>
                        <form action="MainController" method="POST">
                            <input name="action" value="wishlistDelete" type="hidden">
                            <input name="id" value="${games.gameId}" type="hidden">
                            <input name="cartId" value="${wishlist.cartId}" type="hidden">
                            <button type="submit" class="btn btn-primary">Delete</button>
                        </form>
                    </td>
                </tr>
                <%
                            }
                        }
                    } else {
                %>
                <tr>
                    <td colspan="6" class="empty-wishlist">Your wishlist is empty.</td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>

        <!-- Footer -->
        <footer class="footer">
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <h5>HELP</h5>
                        <a href="#">Player Support</a>
                        <a href="#">FAQs</a>
                        <a href="#">Contact</a>
                        <a href="#">Email: support@dkey.com</a>
                    </div>
                    <div class="col-md-4">
                        <h5>LEGAL</h5>
                        <a href="#">Terms of Service</a>
                        <a href="#">User Agreement</a>
                        <a href="#">Privacy Policy</a>
                    </div>
                    <div class="col-md-4">
                        <h5>COMMUNITY</h5>
                        <a href="#">DKEY NEXUS FORUM</a>
                        <a href="#">DISCORD</a>
                        <a href="#">REDDIT</a>
                    </div>
                </div>
            </div>
        </footer>

        <div class="copyright">
            <div class="container">
                <div class="text-center">
                    Copyright © 2025. DKEY Website. All Rights Reserved.
                </div>
            </div>
        </div>

        <!-- Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>