package com.webfruits.controller.admin.product;

import com.webfruits.model.CategoryModel;
import com.webfruits.model.ProductModel;
import com.webfruits.service.CategoryService;
import com.webfruits.service.ICategoryService;
import com.webfruits.service.IProductService;
import com.webfruits.service.ProductService;
import com.webfruits.util.FileUploadUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin-edit-product"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class EditProductServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "assets/images/uploads";
    private IProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idProduct = Integer.parseInt(req.getParameter("productId"));
        ProductModel product = productService.findOne(idProduct);

        ICategoryService categoryService = new CategoryService();
        List<CategoryModel> categoryList = categoryService.findAll();

        req.setAttribute("product", product);
        req.setAttribute("categoryList", categoryList);

        req.getRequestDispatcher("admin/product/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        try {
            String appPath = req.getServletContext().getRealPath("");
            String uploadPath = appPath + File.separator + UPLOAD_DIR;

            // Lấy ảnh mới nếu có
            Part thumbnailPart = req.getPart("thumbnail");
            String thumbnailFileName = null;

            if (thumbnailPart != null && thumbnailPart.getSize() > 0) {
                thumbnailFileName = FileUploadUtil.saveFile(thumbnailPart, uploadPath);
            }

            // Dữ liệu từ form
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String priceStr = req.getParameter("price");
            String pricesaleStr = req.getParameter("pricesale");
            String quantityStr = req.getParameter("quantity");
            String categoryIdStr = req.getParameter("category");
            String oldThumbnail = req.getParameter("oldThumbnail");

            double price = priceStr != null && !priceStr.isEmpty() ? Double.parseDouble(priceStr) : 0;
            Double pricesale = (pricesaleStr != null && !pricesaleStr.isEmpty()) ? Double.parseDouble(pricesaleStr) : null;
            int quantity = quantityStr != null && !quantityStr.isEmpty() ? Integer.parseInt(quantityStr) : 0;
            int categoryId = categoryIdStr != null && !categoryIdStr.isEmpty() ? Integer.parseInt(categoryIdStr) : 0;

            ProductModel product = new ProductModel();
            product.setId(id);
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setPricesale(pricesale);
            product.setQuantity(quantity);
            product.setCategoryId(categoryId);
            product.setThumbnail(thumbnailFileName != null ? "assets/images/uploads/" + thumbnailFileName : oldThumbnail);

            // Debug log
            System.out.println("Cập nhật product ID = " + id);
            System.out.println("Ảnh cập nhật = " + product.getThumbnail());

            boolean success = productService.update(product);
            if (success) {
                resp.sendRedirect(req.getContextPath() + "/admin-product");
            } else {
                resp.getWriter().println("Cập nhật thất bại.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Lỗi khi cập nhật sản phẩm", e);
        }
    }
}
