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
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/admin-create-product"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,  // 1MB
        maxFileSize = 5 * 1024 * 1024,    // 5MB
        maxRequestSize = 10 * 1024 * 1024 // 10MB
)
public class CreateProductServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "assets/images/uploads";
    private IProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ICategoryService categoryService = new CategoryService();
        List<CategoryModel> categoryList = categoryService.findAll();

        req.setAttribute("categoryList", categoryList);
        req.getRequestDispatcher("admin/product/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Đặt encoding để tránh lỗi khi gửi form có tiếng Việt
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        try {
            // 2. Lấy thư mục lưu file upload
            String appPath = req.getServletContext().getRealPath("");
            String uploadPath = appPath + File.separator + UPLOAD_DIR;

            // 3. Lấy file ảnh thumbnail
            Part thumbnailPart = req.getPart("thumbnail");
            String thumbnailFileName = null;
            if(thumbnailPart != null && thumbnailPart.getSize() > 0) {
                thumbnailFileName = FileUploadUtil.saveFile(thumbnailPart, uploadPath);
            }

            // 4. Lấy dữ liệu các trường khác từ form
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String priceStr = req.getParameter("price");
            String pricesaleStr = req.getParameter("pricesale");
            String quantityStr = req.getParameter("quantity");
            String categoryIdStr = req.getParameter("category");

            // 5. Chuyển đổi dữ liệu
            // 5. Chuyển đổi dữ liệu
            double price = priceStr != null && !priceStr.isEmpty() ? Double.parseDouble(priceStr) : 0;
            Double pricesale = (pricesaleStr != null && !pricesaleStr.isEmpty()) ? Double.parseDouble(pricesaleStr) : null;
            int quantity = quantityStr != null && !quantityStr.isEmpty() ? Integer.parseInt(quantityStr) : 0;
            int categoryId = categoryIdStr != null && !categoryIdStr.isEmpty() ? Integer.parseInt(categoryIdStr) : 0;

            // 6. Tạo model product
            ProductModel product = new ProductModel();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setPricesale(pricesale);
            product.setQuantity(quantity);
            product.setCategoryId(categoryId);
            product.setThumbnail("assets/images/uploads/" + thumbnailFileName);
            product.setView(0);

            // 7. Gọi DAO lưu vào database
            int newProductId = productService.insert(product);
            if (newProductId > 0) {
                resp.sendRedirect(req.getContextPath() + "/admin-product");
            } else {
                PrintWriter out = resp.getWriter();
                out.println("<h3>Tạo sản phẩm thất bại!</h3>");
                out.println("<a href='admin-create-product'>Thử lại</a>");
            }
        } catch (Exception e) {
            PrintWriter out = resp.getWriter();
            out.println("<h3>Tạo sản phẩm thất bại!</h3>");
            out.println("<a href='admin-create-product'>Thử lại</a>");
            throw new ServletException("Lỗi khi tạo sản phẩm", e);
        }

    }
}
