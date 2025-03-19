<%-- 
    Document   : homePage
    Created on : Feb 23, 2025, 9:51:19 PM
    Author     : Quoc Bao
--%>

<%@page import="java.util.List"%>
<%@page import="model.GameDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.UsersDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>DKEY - Digital Game Store</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome for icons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <style>
            body {
                background-color: #1a2433;
                color: white;
            }

            .header {
                background-color: #0f1923;
                border-bottom: 1px solid #2a3a4a;
            }

            .logo {
                font-size: 24px;
                font-weight: bold;
            }

            .search-container {
                position: relative;
            }

            .search-input {
                background-color: #374b61;
                border: none;
                border-radius: 20px;
                color: white;
                padding-right: 40px;
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
                right: 15px;
                top: 10px;
                color: white;
            }

            .nav-menu {
                background-color: #1e2a3a;
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

            .featured-banner img {
                border-radius: 10px;
                width: 40%;
            }

            .section-title {
                border-bottom: 1px solid #374b61;
                padding-bottom: 10px;
                text-transform: uppercase;
            }

            .bestseller-card {
                background-color: #1e2a3a;
                border-radius: 8px;
                overflow: hidden;
                height: 100%;
                transition: transform 0.3s;
                display: flex;
                flex-direction: column;
            }

            .bestseller-card:hover {
                transform: translateY(-5px);
            }

            .game-img {
                width: 100%;
                height: 100%;
                padding: 9%;
                object-fit: contain;
                object-position: center;
                transition: transform 0.3s ease; /* Smooth zoom effect on hover */
            }

            .game-details {
                padding: 15px;
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                /*                height: 100%;*/
                flex-grow: 1;
            }

            .game-title {
                font-size: 16px;
                font-weight: bold;
                margin: 10px 0 5px 0;
            }

            .game-price {
                color: #8499b1;
                font-size: 14px;
                margin-bottom: 15px;
            }

            .btn-primary {
                background-color: #5b42f3;
                border: none;
                width: 100%;
                margin-top: auto;
            }

            .btn-primary:hover {
                background-color: #4935cc;
            }

            .coming-soon-card {
                background-color: #1e2a3a;
                border-radius: 8px;
                overflow: hidden;
                height: 100%;
                transition: transform 0.3s;
            }

            .coming-soon-card:hover {
                transform: translateY(-5px);
            }

            .coming-soon-img {
                width: 100%;
                height: 150px;
                object-fit: cover;
            }

            .coming-soon-details {
                padding: 15px;
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


            .image-container {
                position: relative;
                width: 100%; /* Full width of the parent */
                height: 300px; /* Set a fixed height */
                overflow: hidden; /* Hide overflow */
                border-radius: 8px 8px 0 0; /* Rounded corners on top only */
            }



        </style>
    </head>
    <body>
        <!-- Header -->
        <div class="header py-3">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-md-3">
                        <div class="logo">DKEY</div>
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
        <div class="nav-menu py-2 mb-4">
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

        <% if (request.getAttribute("wishlistError") != null) {%>
        <div class="alert alert-danger">
            <%= request.getAttribute("wishlistError")%>
        </div>
        <% } %>

        <!-- Featured Banner -->
        <div class="container mb-4">
            <div class="featured-banner">
                <img src="img/spiderman2.png" alt="Spider-Man 2 - Shop Now" class="img-fluid">
            </div>
        </div>

        <!-- Best Sellers Section -->
        <div class="container mb-5">
            <h2 class="section-title mb-4">BEST SELLERS</h2>

            <div class="row">
                <!-- First game - Dark Ages -->
                <div class="col-md-3 mb-4">
                    <div class="bestseller-card">                                   
                        <div class="image-container">
                            <img src="img/Doom.jpeg" alt="Dark Ages" class="game-img">
                        </div>                                        
                        <div class="game-details text-center">
                            <div>
                                <h3 class="game-title">Dark Ages</h3>
                                <p class="game-price">$29.99</p>
                            </div>
                            <button class="btn btn-primary">BUY NOW</button>
                        </div>
                    </div>
                </div>
                <!-- Second game - Spider-Man 2 -->
                <div class="col-md-3 mb-4">
                    <div class="bestseller-card">                                   
                        <div class="image-container">
                            <img src="img/spiderman2.png" alt="Spider-Man 2" class="game-img">
                        </div>                                        
                        <div class="game-details text-center">
                            <div>
                                <h3 class="game-title">Spider-Man 2</h3>
                                <p class="game-price">$59.99</p>
                            </div>
                            <button class="btn btn-primary">BUY NOW</button>
                        </div>
                    </div>
                </div>
                <!-- Third game - Dark Ages Expansion -->
                <div class="col-md-3 mb-4">
                    <div class="bestseller-card">                                   
                        <div class="image-container">
                            <img src="img/Doom.jpeg" alt="Dark Ages Expansion" class="game-img">
                        </div>                                        
                        <div class="game-details text-center">
                            <div>
                                <h3 class="game-title">Dark Ages Expansion</h3>
                                <p class="game-price">$19.99</p>
                            </div>
                            <button class="btn btn-primary">BUY NOW</button>
                        </div>
                    </div>
                </div>
                <!-- Fourth game - Dark Ages Deluxe -->
                <div class="col-md-3 mb-4">
                    <div class="bestseller-card">                                   
                        <div class="image-container">
                            <img src="img/Doom.jpeg" alt="Dark Ages Deluxe" class="game-img">
                        </div>                                        
                        <div class="game-details text-center">
                            <div>
                                <h3 class="game-title">Dark Ages Deluxe</h3>
                                <p class="game-price">$39.99</p>
                            </div>
                            <button class="btn btn-primary">BUY NOW</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!-- Games Section / Replace coming soon Section -->
        <div class="container mb-5">
            <h2 class="section-title mb-4">COMING SOON</h2>

            <div class="row justify-content-center"> <!-- Center the cards -->
                <%
                    List<GameDTO> list = (List<GameDTO>) request.getAttribute("gameslist");
                    if (list == null) {
                        out.println("<div class='alert alert-warning'>gameslist is null</div>");
                    } else if (list.isEmpty()) {
                        out.println("<div class='alert alert-warning'>No games available</div>");
                    } else {
                        // Limit to 4 games using subList or a counter
                        int maxGames = Math.min(list.size(), 8); // Display up to 8 games
                        for (int i = 0; i < maxGames; i++) {
                            GameDTO games = list.get(i);
                            pageContext.setAttribute("games", games);
                %>
                <div class="col-md-3 col-sm-6 mb-4"> <!-- Adjusted for responsiveness -->
                    <div class="bestseller-card">
                        <div class="image-container">
                            <img src="<%= games.getCoverImageUrl()%>" alt="<%= games.getTitle()%>" class="game-img">
                        </div>
                        <div class="game-details text-center">
                            <div>
                                <h3 class="game-title">
                                    <a href="MainController?id=${games.gameId}" class="text-white text-decoration-none">
                                        ${games.title}
                                    </a>
                                </h3>
                            </div>
                            <div class="d-flex flex-column gap-2">
                                <form action="MainController" method="POST">
                                    <input name="action" value="addToCart" type="hidden">
                                    <input type="hidden" name="id" value="${games.gameId}">
                                    <button type="submit" class="btn btn-primary">ADD TO CART</button>
                                </form>
                                <form action="MainController" method="POST">
                                    <input name="action" value="addToWishlist" type="hidden">
                                    <input type="hidden" name="id" value="${games.gameId}">
                                    <button type="submit" class="btn btn-primary">ADD TO WISHLIST</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <%
                        }
                    }
                %>
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