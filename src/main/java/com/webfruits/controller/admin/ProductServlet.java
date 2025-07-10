package com.webfruits.controller.admin;

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


@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,  // 1MB
        maxFileSize = 5 * 1024 * 1024,    // 5MB
        maxRequestSize = 10 * 1024 * 1024 // 10MB
)
@WebServlet(urlPatterns = {"/admin-product"})
public class ProductServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "assets/images/uploads";
    private IProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String action = req.getParameter("action");
        if(action != null) {
            switch (action) {
                case "index":
                    showProducts(req, resp);
                    break;
                case "create":
                    showCreate(req, resp);
                    break;
                case "edit":
                    showEdit(req, resp);
                    break;
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String action = req.getParameter("action");
        if(action != null) {
            switch (action) {
                case "create":
                    createProduct(req, resp);
                    break;
                case "edit":
                    editProduct(req, resp);
                    break;
                case "delete":
                    productDelete(req, resp);
                    break;
            }
        }
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
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
                resp.sendRedirect(req.getContextPath() + "/admin-product?action=index");
            } else {
                resp.getWriter().println("Cập nhật thất bại.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Lỗi khi cập nhật sản phẩm", e);
        }
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
                resp.sendRedirect(req.getContextPath() + "/admin-product?action=index");
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

    private void showProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductModel> productList = productService.findAll();
        req.setAttribute("productList", productList);
        req.getRequestDispatcher("admin/product/index.jsp").forward(req, resp);
    }

    private void showCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ICategoryService categoryService = new CategoryService();
        List<CategoryModel> categoryList = categoryService.findAll();

        req.setAttribute("categoryList", categoryList);
        req.getRequestDispatcher("admin/product/create.jsp").forward(req, resp);
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idProduct = Integer.parseInt(req.getParameter("productId"));
        ProductModel product = productService.findOne(idProduct);

        ICategoryService categoryService = new CategoryService();
        List<CategoryModel> categoryList = categoryService.findAll();

        req.setAttribute("product", product);
        req.setAttribute("categoryList", categoryList);

        req.getRequestDispatcher("admin/product/edit.jsp").forward(req, resp);
    }



    private void productDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));
        IProductService productService = new ProductService();
        productService.delete(productId);
        resp.sendRedirect(req.getContextPath() + "/admin-product?action=index");
    }
}
