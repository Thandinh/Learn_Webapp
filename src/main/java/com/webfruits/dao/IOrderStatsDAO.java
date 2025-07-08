package com.webfruits.dao;

import java.util.List;

public interface IOrderStatsDAO {
    double getRevenueByDate(String date);
    int getOrderCountByDate(String date);
    double getRevenueByMonth(String yearMonth);
    int getOrderCountByMonth(String yearMonth);
    List<Object[]> getDailyStats();
}
