package com.webfruits.service;

import java.util.List;

public interface IOrderStatsService {
    double getRevenueByDate(String date);
    int getOrderCountByDate(String date);
    double getRevenueByMonth(String yearMonth);
    int getOrderCountByMonth(String yearMonth);
    List<Object[]> getDailyStats();
}
