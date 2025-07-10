package com.webfruits.controller.admin;

import com.webfruits.model.CategoryModel;
import com.webfruits.service.CategoryService;
import com.webfruits.service.ICategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin-category"})
public class CategoryServlet extends HttpServlet {
    private ICategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action != null) {
            switch (action) {
                case "index":
                    index(req, resp);
                    break;
                case "create":
                    showCreate(req, resp);
                    break;
                case "update":
                    showUpdate(req, resp);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action != null) {
            switch (action) {
                case "create":
                    categoryCreate(req, resp);
                    break;
                case "delete":
                    categoryDelete(req, resp);
                    break;
                case "update":
                    categoryUpdate(req, resp);
                    break;
            }
        }
    }

    private void showUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        CategoryModel category = categoryService.findOne(categoryId);
        req.setAttribute("category", category);
        req.getRequestDispatcher("admin/category/edit.jsp").forward(req, resp);
    }

    private void showCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("admin/category/create.jsp").forward(req, resp);
    }

    private void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CategoryModel> categoryList = categoryService.findAll();
        req.setAttribute("categoryList", categoryList);

        req.getRequestDispatcher("admin/category/index.jsp").forward(req, resp);
    }

    private void categoryUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        String name = req.getParameter("name");
        CategoryModel categoryUpdate = new CategoryModel();
        categoryUpdate.setId(categoryId);
        categoryUpdate.setName(name);

        categoryService.update(categoryUpdate);
        resp.sendRedirect(req.getContextPath() + "/admin-category?action=index");
    }

    private void categoryDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        int categoryId = Integer.parseInt(req.getParameter("categoryId"));

        categoryService.delete(categoryId);
        resp.sendRedirect(req.getContextPath() + "/admin-category?action=index");
    }

    private void categoryCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String name = req.getParameter("name");
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setName(name);
        categoryService.insert(categoryModel);
        resp.sendRedirect(req.getContextPath() + "/admin-category?action=index");
    }
}
