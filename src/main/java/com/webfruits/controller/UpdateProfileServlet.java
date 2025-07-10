package com.webfruits.controller;

import com.webfruits.model.UserModel;
import com.webfruits.service.IUserService;
import com.webfruits.service.UserService;
import com.webfruits.util.PasswordUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,  // 1MB
        maxFileSize = 1024 * 1024 * 5,    // 5MB
        maxRequestSize = 1024 * 1024 * 10 // 10MB
)
@WebServlet(urlPatterns = {"/update-profile"})
public class UpdateProfileServlet extends BaseServlet {
    private final IUserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        UserModel user = (UserModel) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("dang-nhap");
            return;
        }

        String typeProfile = req.getParameter("typeProfile");
        String action = req.getParameter("action");

        if (action != null) {
            switch (action) {
                case "updatePassword":
                    updatePassword(req, user, session);
                    break;
                case "updateAvatar":
                    updateAvatar(req, user, session);
                    break;
            }
        }

        // Điều hướng dựa trên loại người dùng
        if ("admin".equals(typeProfile)) {
            resp.sendRedirect(req.getContextPath() + "/admin-profile");
        } else {
            resp.sendRedirect(req.getContextPath() + "/thong-tin-ca-nhan");
        }
    }

    private void updatePassword(HttpServletRequest req, UserModel user, HttpSession session) {
        String currentPassword = req.getParameter("currentPassword");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");

        if (!PasswordUtils.checkPassword(currentPassword, user.getPassword())) {
            session.setAttribute("error", "Mật khẩu hiện tại không đúng");
        } else if (newPassword.length() < 3) {
            session.setAttribute("error", "Mật khẩu mới phải có ít nhất 3 ký tự");
        } else if (newPassword.equals(currentPassword)) {
            session.setAttribute("error", "Mật khẩu mới không được trùng với mật khẩu cũ");
        } else if (!newPassword.equals(confirmPassword)) {
            session.setAttribute("error", "Mật khẩu mới không khớp");
        } else {
            String hashed = PasswordUtils.hashPassword(newPassword);
            user.setPassword(hashed);
            boolean updated = userService.update(user);
            if (updated) {
                session.setAttribute("user", user);
                session.setAttribute("success", "Đổi mật khẩu thành công");
            } else {
                session.setAttribute("error", "Đổi mật khẩu thất bại");
            }
        }
    }

    private void updateAvatar(HttpServletRequest req, UserModel user, HttpSession session) throws ServletException, IOException {
        final String UPLOAD_DIR = "assets/images/uploads/avatar";
        Part avatarPart = req.getPart("avatar");

        if (avatarPart == null || avatarPart.getSize() == 0) {
            session.setAttribute("error", "Vui lòng chọn file ảnh để tải lên");
            return;
        }

        String fullName = avatarPart.getSubmittedFileName();
        if (fullName == null || fullName.isEmpty()) {
            session.setAttribute("error", "Tên file không hợp lệ");
            return;
        }

        String fileName = Paths.get(fullName).getFileName().toString();
        String appPath = req.getServletContext().getRealPath("/");
        String uploadPath = appPath + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String newFileName = UUID.randomUUID().toString() + "_" + fileName;
        String filePath = uploadPath + File.separator + newFileName;

        try {
            avatarPart.write(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            session.setAttribute("error", "Lỗi khi lưu file ảnh");
            return;
        }

        String avatarPath = UPLOAD_DIR + "/" + newFileName;
        user.setAvatar(avatarPath);
        boolean updated = userService.update(user);
        if (updated) {
            session.setAttribute("user", user);
            session.setAttribute("success", "Cập nhật ảnh đại diện thành công");
        } else {
            session.setAttribute("error", "Cập nhật avatar thất bại");
        }
    }
}
