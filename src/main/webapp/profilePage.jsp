<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="vi_VN" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>SEA FRUITS</title>

    <!-- Swiper CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />

    <!-- Swiper JS -->
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" />
    <link rel="stylesheet" href="assets/css/grid.css" />
    <link rel="stylesheet" href="assets/css/style.css" />

    <style>
        .container-profile {
            max-width: 600px;
            margin: 30px auto;
            background: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 16px rgba(0,0,0,0.05);
            margin-top: 100px;
            margin-bottom: 50px;
        }
        .container-profile h2, h3 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 15px;
            font-size: 18px;
        }
        .container-profile .profile-info {
            text-align: center;
            margin-bottom: 30px;
        }
        .container-profile .profile-info img {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            border: 3px solid #3498db;
            object-fit: cover;
            margin-bottom: 10px;
        }
        .container-profile .profile-info p {
            margin: 4px 0;
            font-size: 14px;
            color: #555;
        }
        .container-profile form {
            margin-bottom: 30px;
        }
        .container-profile form input[type="password"],
        .container-profile form input[type="file"] {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 14px;
            transition: border-color 0.3s;
        }
        .container-profile form input:focus {
            outline: none;
            border-color: #3498db;
        }
        .container-profile form button {
            background-color: #3498db;
            color: white;
            padding: 10px 16px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 14px;
            transition: background-color 0.3s;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 6px;
        }
        .container-profile form button:hover {
            background-color: #2980b9;
        }
        .container-profile hr {
            border: none;
            border-top: 1px solid #eee;
            margin: 30px 0;
        }
    </style>
    
</head>

<body>
    <%@include file="./inc/header.jsp" %>

    <div id="main">
        <div class="container-profile">
            <h2><i class="fas fa-user-circle"></i> Hồ sơ cá nhân</h2>
            
            <div class="profile-info">
                <img src="${user.avatar}" alt="Avatar">
                <p><strong>Email:</strong> ${user.email}</p>
                <p><strong>Vai trò:</strong> ${user.role}</p>
            </div>

            <c:if test="${not empty error}">
                <p style="color:red; text-align:center">${error}</p>
            </c:if>
            <c:if test="${not empty success}">
                <p style="color:green; text-align:center">${success}</p>
            </c:if>

            <hr>
            <h3><i class="fas fa-lock"></i> Đổi mật khẩu</h3>
            <form action="<c:url value='/update-profile' />" method="post">
                <input type="hidden" name="typeProfile" value="user">
                <input type="hidden" name="action" value="updatePassword">
                <input type="password" name="currentPassword" placeholder="Mật khẩu hiện tại" required>
                <input type="password" name="newPassword" placeholder="Mật khẩu mới" required>
                <input type="password" name="confirmPassword" placeholder="Nhập lại mật khẩu mới" required>
                <button type="submit"><i class="fas fa-key"></i> Cập nhật</button>
            </form>

            <hr>
            <h3><i class="fas fa-image"></i> Cập nhật ảnh đại diện</h3>
            <form action="<c:url value='/update-profile' />" method="post" enctype="multipart/form-data">
                <input type="hidden" name="typeProfile" value="user">
                <input type="hidden" name="action" value="updateAvatar">
                <input type="file" name="avatar" accept="image/*" required>
                <button type="submit"><i class="fas fa-upload"></i> Cập nhật ảnh</button>
            </form>
        </div>
    </div>

    <%@include file="./inc/footer.jsp" %>


    
    
</body>

</html>
