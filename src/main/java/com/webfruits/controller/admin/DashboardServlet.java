package com.webfruits.controller.admin;

import com.webfruits.service.IOrderStatsService;
import com.webfruits.service.IUserService;
import com.webfruits.service.OrderStatsService;
import com.webfruits.service.UserService;
import com.webfruits.util.DateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin-home"})
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IOrderStatsService orderStatsService = new OrderStatsService();
        IUserService userService = new UserService();

        double todayRevenue = orderStatsService.getRevenueByDate(DateUtil.getCurrentDate());
        int todayOrders = orderStatsService.getOrderCountByDate(DateUtil.getCurrentDate());
        double thisMonthRevenue = orderStatsService.getRevenueByMonth(DateUtil.getCurrentMonth());
        int thisMonthOrders = orderStatsService.getOrderCountByMonth(DateUtil.getCurrentMonth());
        int countUsers = userService.countUsers();

        /*chart*/
        List<Object[]> statsList = orderStatsService.getDailyStats();
        req.setAttribute("statsList", statsList);

        /*chart*/


        req.setAttribute("todayRevenue", todayRevenue);
        req.setAttribute("todayOrders", todayOrders);
        req.setAttribute("thisMonthRevenue", thisMonthRevenue);
        req.setAttribute("thisMonthOrders", thisMonthOrders);
        req.setAttribute("countUsers", countUsers);
        req.getRequestDispatcher("admin/dashboard.jsp").forward(req, resp);
    }
}
