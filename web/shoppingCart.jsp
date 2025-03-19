<%-- 
    Document   : shoppingCart
    Created on : Mar 2, 2025, 4:56:26 PM
    Author     : Admin
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.GameDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Shopping Cart - DKEY</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome for icons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <style>
            body {
                background-color: #1a2433;
                color: white;
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
            }

            .header {
                background-color: #0f1923;
                border-bottom: 1px solid #2a3a4a;
                padding: 6px 0;
            }

            .logo {
                font-size: 24px;
                font-weight: bold;
                line-height: 1;
                color: white; /* Set color to white to match theme */
                text-decoration: none; /* Remove underline */
            }

            .search-container {
                position: relative;
                width: 100%;
                max-width: 300px;
                margin: 0 auto;
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
                padding: 1px 0;
            }

            .nav-link {
                color: #8499b1;
                text-transform: uppercase;
                font-weight: bold;
                font-size: 14px;
                padding: 4px 8px;
                line-height: 1;
            }

            .nav-link:hover {
                color: white;
            }

            .container {
                width: 80%;
                margin: 0 auto;
                padding: 20px;
            }

            .wishlist-title {
                font-size: 24px;
                text-align: center;
                margin: 20px 0;
                text-transform: uppercase;
                letter-spacing: 2px;
                color: #e0e0ff;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                background-color: #16213e;
                border-radius: 5px;
                overflow: hidden;
            }

            th, td {
                padding: 15px;
                text-align: left;
                border-bottom: 1px solid #4a4a6a;
            }

            th {
                background-color: #0d0d1a;
                color: #e0e0ff;
                text-transform: uppercase;
                font-size: 14px;
            }

            td img {
                width: 100px;
                height: 150px;
                object-fit: cover;
                border-radius: 5px;
            }

            td a {
                color: #ffffff;
                text-decoration: none;
                font-size: 16px;
            }

            td a:hover {
                color: #e0e0ff;
            }

            input[type="number"] {
                background-color: #4a4a6a;
                border: none;
                padding: 5px 10px;
                color: #ffffff;
                border-radius: 5px;
                width: 60px;
                font-size: 14px;
            }

            input[type="text"] {
                background-color: #4a4a6a;
                border: none;
                padding: 5px 10px;
                color: #ffffff;
                border-radius: 5px;
                font-size: 14px;
                width: 200px;
            }

            input[type="submit"] {
                background-color: #5b42f3;
                color: #ffffff;
                border: none;
                padding: 8px 15px;
                border-radius: 5px;
                cursor: pointer;
                font-size: 14px;
                text-transform: uppercase;
            }

            input[type="submit"]:hover {
                background-color: #4935cc;
            }

            .message {
                text-align: center;
                margin: 10px 0;
            }

            .checkout-section {
                text-align: right;
                margin: 20px 0;
                display: flex;
                justify-content: flex-end;
                gap: 20px;
                align-items: center;
            }

            .coupon-section {
                margin: 0;
            }

            .total-price {
                font-size: 18px;
                color: #e0e0ff;
            }

            .checkout-btn input[type="submit"] {
                background-color: #2196F3;
                padding: 10px 30px;
                font-size: 16px;
            }

            .checkout-btn input[type="submit"]:hover {
                background-color: #1976D2;
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
                    <div class="col-md-2 col-3">
                        <a href="MainController?action=list" class="logo">DKEY</a>
                    </div>
                    <div class="col-md-8 col-6 text-center">
                        <div class="search-container">
                            <input type="text" class="form-control search-input" placeholder="SEARCH">
                            <span class="search-icon"><i class="fas fa-search"></i></span>
                        </div>
                    </div>
                    <div class="col-md-2 col-3">
                        <div class="d-flex justify-content-end gap-3">
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

        <!-- Main Content -->
        <h1 class="wishlist-title">MY CART</h1>

        <div class="container">
            <div class="message">
                <%
                    Boolean couponApplied = (Boolean) request.getAttribute("couponApplied");
                    if (request.getAttribute("MESSAGE") != null) {
                        out.println("<p style='color:#4caf50'>" + request.getAttribute("MESSAGE") + "</p>");
                    }
                    if (request.getAttribute("ERROR") != null) {
                        out.println("<p style='color:#ff5555'>" + request.getAttribute("ERROR") + "</p>");
                    }
                    if (request.getAttribute("errorCode") != null) {
                        out.println("<p style='color:#ff5555'>" + request.getAttribute("errorCode") + "</p>");
                    }
                %>
            </div>

            <table>
                <tr>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Action</th>
                </tr>
                <%
                    List<GameDTO> list = (List<GameDTO>) request.getAttribute("shoppingCartList");
                    double totalPrice = 0.0;
                    if (list != null && !list.isEmpty()) {
                        for (GameDTO games : list) {
                            pageContext.setAttribute("games", games);
                            double itemPrice = (couponApplied != null && couponApplied) ? 
                                games.getPrice() * games.getQuantity() : 
                                games.getOriginalPrice() * games.getQuantity();
                            totalPrice += itemPrice;
                %>
                <tr>
                    <td>
                        <img src="<%= games.getCoverImageUrl()%>" alt="Game Cover">
                        <a href="MainController?id=${games.gameId}">${games.title}</a>
                    </td>
                    <td>
                        <form action="MainController" method="POST">
                            <input name="action" value="quantity" type="hidden">
                            <input name="id" value="${games.gameId}" type="hidden">
                            <input type="number" name="quantity" value="${games.quantity}" min="1" required>
                            <input type="submit" value="Update">
                        </form>
                    </td>
                    <td>
                        <%= String.format("%.2f", itemPrice) %>$
                    </td>
                    <td>
                        <form action="MainController" method="POST">
                            <input name="action" value="delete" type="hidden">
                            <input name="id" value="${games.gameId}" type="hidden">
                            <input type="submit" value="Delete">
                        </form>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="4" style="text-align: center; color: #b0b0d0;">Your cart is empty.</td>
                </tr>
                <%
                    }
                %>
            </table>

            <div class="checkout-section">
                <div class="coupon-section">
                    <%
                        if (couponApplied == null || !couponApplied) {
                    %>
                    <form action="MainController" method="POST">
                        <input type="hidden" name="action" value="applyCoupon">
                        <input type="text" name="code" placeholder="DISCOUNT CODE" required>
                        <input type="submit" value="Apply Coupon">
                    </form>
                    <%
                        } else {
                    %>
                    <p>Coupon has been applied!</p>
                    <%
                        }
                    %>
                </div>
                <div class="total-price">
                    Total: <%= String.format("%.2f", totalPrice) %>$
                </div>
                <div class="checkout-btn">
                    <form action="MainController" method="POST">
                        <input type="hidden" name="action" value="checkout">
                        <input type="submit" value="Checkout">
                    </form>
                </div>
            </div>
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
