<%-- 
    Document   : login
    Created on : Mar 9, 2025, 3:07:49 PM
    Author     : Quoc Bao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <title>Login Page</title>
   
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #1e2730;
            color: #ffffff;
        }
        
        .header {
            background-color: #0f1419;
            padding: 15px;
            text-align: left;
            border-top: 2px solid #6a3ce0;
        }
        
        .logo {
            font-size: 24px;
            font-weight: bold;
            letter-spacing: 2px;
        }
        
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 30px 20px;
        }
        
        .title {
            font-size: 32px;
            margin-bottom: 30px;
            letter-spacing: 5px;
        }
        
        .form-container {
            background-color: #383e47;
            padding: 40px;
            width: 100%;
            max-width: 450px;
            border-radius: 4px;
        }
        
        .form-group {
            margin-bottom: 20px;
        }
        
        input[type="text"], 
        input[type="email"], 
        input[type="password"] {
            width: 100%;
            padding: 15px;
            box-sizing: border-box;
            background-color: #1e3148;
            border: none;
            color: #ffffff;
            border-radius: 4px;
            font-size: 14px;
        }
        
        input::placeholder {
            color: #8b9cb3;
            font-family: monospace;
        }
        
        .submit-btn {
            background-color: #0062cc;
            color: white;
            padding: 12px 25px;
            border: none;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
            letter-spacing: 1px;
            margin-top: 10px;
            border: 2px solid black;
        }
        
        .submit-btn:hover {
            background-color: #0056b3;
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
    <%
        if (request.getAttribute("ERROR") != null) {
    %>
    <h3><%= request.getAttribute("ERROR")%></h3>
    <%
    } else if (request.getAttribute("NOTI") != null) {

    %>
    <h3>${requestScope.NOTI}</h3>
    <%        
        }
    %>
    
    <div class="header">
        <div class="logo">DKEY</div>
    </div>
    
    <div class="container">
        <h1 class="title">SIGN IN</h1>    
    <div class="form-container">
        <form action="MainController" method="POST">
            <div class="form-group">
            <input type="hidden" name ="action" value ="login"/>
            </div>
            <div class="form-group">
            <input type ="text" name ="txtmail" placeholder="Email address" required=""/><br/>
            </div>
            <div class="form-group">
            <input type ="text" name ="txtpassword" placeholder="Password" required=""/>
            </div>
            <div style="text-align: center;">
            <input type="submit" value="Login" class="submit-btn"/>
            </div>
        </form>
    </div>
    
        <p class="title">OR</p>
    <form action="MainController" method ="POST">
        <input type="hidden" name ="action" value="register"/>
        <input type="submit" value="Register" class="submit-btn"/>
    </form>
    </div>
    
    <!-- Footer -->
        <footer class="footer">
            
                <div class="row" style="padding-left:15%">
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
