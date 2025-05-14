package com.example.bai06;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/submit-form")
public class FormServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String gender = req.getParameter("gender");
        String[] hobbies = req.getParameterValues("hobby");

        out.println("<html><body>");
        out.println("<h2>Thông tin nhập vào</h2>");
        out.println("<p><strong>Họ tên:</strong> " + fullname + "</p>");
        out.println("<p><strong>Email:</strong> " + email + "</p>");
        out.println("<p><strong>Giới tính:</strong> " + gender + "</p>");

        out.println("<p><strong>Sở thích:</strong></p>");
        if(hobbies != null) {
            for(String hobby: hobbies) {
                out.print(hobby + " ");
            }
        } else {
            out.print("Không có");
        }
        out.println("</p>");

        out.println("</body></html>");
    }
}
