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

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,  // 1MB
        maxFileSize = 1024 * 1024 * 5,    // 5MB
        maxRequestSize = 1024 * 1024 * 10 // 10MB
)
@WebServlet(urlPatterns = {"/update-profile"})
public class UpdateProfileServlet extends BaseServlet {
    private IUserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        UserModel user = (UserModel) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("dang-nhap");
        }

        String typeProfile = req.getParameter("typeProfile");
        String action = req.getParameter("action");

        if(action != null) {
            switch (action) {
                case "updatePassword":
                    updatePassword(req, user, session);
                    break;
                case "updateAvatar":
                    updateAvatar(req, user, session);
                    break;
            }
        }

        if(typeProfile != null) {
            if(typeProfile.equals("admin")) {
                req.getRequestDispatcher("admin/user/profile.jsp").forward(req, resp);
            } else if(typeProfile.equals("user")) {
                super.doGet(req, resp);
                req.getRequestDispatcher("profilePage.jsp").forward(req, resp);
            }
        }

    }



    private void updatePassword(HttpServletRequest req, UserModel user, HttpSession session) {
        String currentPassword = req.getParameter("currentPassword");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");

        boolean valid = PasswordUtils.checkPassword(currentPassword, user.getPassword());
        if(!valid) {
            req.setAttribute("error", "Mật khẩu hiện tại không đúng");
        } else if (newPassword.length() < 3) {
            req.setAttribute("error", "Mật khẩu mới phải có ít nhất 3 ký tự");
        } else if (newPassword.equals(currentPassword)) {
            req.setAttribute("error", "Mật khẩu mới phải không được trùng với mật khẩu cũ");
        }
        else if(!newPassword.equals(confirmPassword)) {
            req.setAttribute("error", "Mật khẩu mới không khớp");
        } else {
            String hashed = PasswordUtils.hashPassword(newPassword);
            user.setPassword(hashed);

            userService.update(user);
            session.setAttribute("user", user);
            req.setAttribute("success", "Đổi mật khẩu thành công");
        }
    }


    private void updateAvatar(HttpServletRequest req, UserModel user, HttpSession session) throws ServletException, IOException {
        final String UPLOAD_DIR = "assets/images/uploads/avatar";
        Part avatarPart = req.getPart("avatar");

        if(avatarPart == null || avatarPart.getSize() == 0) {
            req.setAttribute("error", "Vui lòng chọn file ảnh để tải lên");
        }

        String fullName = avatarPart.getSubmittedFileName();
        if(fullName == null || fullName.isEmpty()) {
            req.setAttribute("error", "Tên file không hợp lệ");
            return;
        }
        String fileName = Paths.get(fullName).getFileName().toString();

        String appPath = req.getServletContext().getRealPath("/");
        // Tạo thư mục upload nếu chưa tồn tại
        String uploadPath = appPath + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Tạo tên file mới có thể thêm thời gian hoặc UUID để tránh trùng
        String newFileName = System.currentTimeMillis() + "_" + fileName;

        String filePath = uploadPath + File.separator + newFileName;

        try {
            avatarPart.write(filePath);
        } catch (IOException e) {
            req.setAttribute("error", "Lỗi khi lưu file ảnh");
            e.printStackTrace();

        }

        String avatarPath = UPLOAD_DIR + "/" + newFileName;
        user.setAvatar(avatarPath);
        boolean updated = userService.update(user);
        if (updated) {
            session.setAttribute("user", user);
            req.setAttribute("success", "Cập nhật ảnh đại diện thành công");
        } else {
            req.setAttribute("error", "Cập nhật avatar thất bại");
        }

    }
}
