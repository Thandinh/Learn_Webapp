# Sea Fruits WebApp (Jsp/Servlet)

## Giới thiệu
Sea Fruits WebApp là một ứng dụng web bán hàng trực tuyến các loại trái cây, được xây dựng với Java Servlet, JSP, JSTL, JDBC và triển khai theo mô hình MVC. Ứng dụng hỗ trợ quản lý sản phẩm, giỏ hàng, đặt hàng, quản trị viên, và nhiều tính năng khác.

## Tính năng chính
- Xem danh sách sản phẩm, chi tiết sản phẩm
- Đăng ký, đăng nhập, cập nhật hồ sơ người dùng
- Thêm, sửa, xóa sản phẩm (admin)
- Quản lý danh mục, đơn hàng, người dùng (admin)
- Thêm sản phẩm vào giỏ hàng, đặt hàng
- Thống kê doanh thu, đơn hàng, người dùng (admin dashboard)
- Tìm kiếm, phân trang, lọc sản phẩm
- Upload ảnh sản phẩm, avatar người dùng

## Kiến trúc & Công nghệ
- **Backend:** Java Servlet, JSP, JSTL, JDBC
- **Frontend:** JSP, HTML, CSS, JavaScript, Bootstrap, FontAwesome
- **Database:** MySQL/MariaDB (file mẫu: `src/database/database.sql`)
- **Build tool:** Maven (`pom.xml`)
- **Mô hình:** MVC (Model-View-Controller)

## Cấu trúc thư mục
- `src/main/java/com/webfruits/` - Code Java (controller, dao, model, service, filter, util)
- `src/main/webapp/` - Giao diện JSP, tài nguyên tĩnh (css, js, images)
- `src/database/database.sql` - File khởi tạo CSDL mẫu
- `pom.xml` - Khai báo dependencies Maven

## Hướng dẫn cài đặt & chạy thử
1. Clone project về máy
2. Import vào IDE (IntelliJ IDEA, Eclipse...)
3. Cài đặt MySQL/MariaDB, import file `src/database/database.sql`
4. Cấu hình kết nối DB trong `DBConnect.java` nếu cần
5. Build project với Maven: `mvn clean package`
6. Deploy file WAR lên Tomcat 10+ hoặc server hỗ trợ Jakarta Servlet 6+
7. Truy cập: `http://localhost:8080/demo-webapp/`

## Tài khoản mẫu
- Admin: `abc1@gmail.com` / mật khẩu đã mã hóa (xem DB)
- User: `abc@gmail.com` / mật khẩu đã mã hóa (xem DB)

## Tác giả & Giấy phép
- Tác giả: Đinh Giáp Thân
- License: MIT

---
> Dự án mẫu học tập Java Web, phục vụ mục đích demo và thực hành.
