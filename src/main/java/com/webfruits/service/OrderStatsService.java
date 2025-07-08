package com.webfruits.service;

import com.webfruits.dao.IOrderStatsDAO;
import com.webfruits.dao.OrderStatsDAO;

import java.util.List;

public class OrderStatsService implements IOrderStatsService{
    private IOrderStatsDAO orderStatsDAO;

    public OrderStatsService() {
        this.orderStatsDAO = new OrderStatsDAO();
    }

    @Override
    public double getRevenueByDate(String date) {
        return orderStatsDAO.getRevenueByDate(date);
    }

    @Override
    public int getOrderCountByDate(String date) {
        return orderStatsDAO.getOrderCountByDate(date);
    }

    @Override
    public double getRevenueByMonth(String yearMonth) {
        return orderStatsDAO.getRevenueByMonth(yearMonth);
    }

    @Override
    public int getOrderCountByMonth(String yearMonth) {
        return orderStatsDAO.getOrderCountByMonth(yearMonth);
    }

    @Override
    public List<Object[]> getDailyStats() {
        return orderStatsDAO.getDailyStats();
    }
}
