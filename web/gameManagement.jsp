<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Game Management</title>
        
    </head>
    <body>
        <div class="container">
            <h2>Game Management - Add New Game</h2>

            <form action="MainController" method="POST">
                <div class="form-group">
                    <label for="title">Tên trò chơi</label>
                    <input type="text" id="title" name="title" required>
                </div>

                <div class="form-group">
                    <label for="description">Mô tả</label>
                    <textarea id="description" name="description" required></textarea>
                </div>

                <div class="form-group">
                    <label for="price">Giá</label>
                    <input type="number" step="0.01" id="price" name="price" required>
                </div>

                <div class="form-group">
                    <label for="minSpec">Cấu hình tối thiểu</label>
                    <input type="text" id="minSpec" name="minSpec" required>
                </div>

                <div class="form-group">
                    <label for="maxSpec">Cấu hình tối đa</label>
                    <input type="text" id="maxSpec" name="maxSpec" required>
                </div>

                <div class="form-group">
                    <label for="publisher">Nhà phát hành</label>
                    <input type="text" id="publisher" name="publisher">
                </div>

                <div class="form-group">
                    <label for="releaseDate">Ngày phát hành</label>
                    <input type="date" id="releaseDate" name="releaseDate">
                </div>

                <div class="form-group">
                    <label for="isDlc">Là DLC?</label>
                    <select id="isDlc" name="isDlc">
                        <option value="0">Không</option>
                        <option value="1">Có</option>
                    </select>
                </div>

                <div class="form-group">
                    <label>Thể loại</label>
                    <select multiple name="genre" required>
                        <c:forEach var="genre" items="${genreList}">
                            <option value="${genre.genreId}">${genre.genreName}</option>
                        </c:forEach>
                    </select>
                </div>

                <input type="hidden" name="action" value="addGame">
                <button type="submit">Thêm trò chơi</button>
            </form>

            <c:if test="${not empty NOTI}">
                <p>${NOTI}</p>
            </c:if>
        </div>
    </body>
</html>