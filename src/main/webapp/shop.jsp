<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="vi_VN" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>SEA FRUITS</title>

    <!-- Swiper CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />

    <!-- Swiper JS -->
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" />
    <link rel="stylesheet" href="assets/css/grid.css" />
    <link rel="stylesheet" href="assets/css/style.css" />
</head>

<body>
    <%@include file="./inc/header.jsp" %>

    <div id="main">
        <div id="banner">
            <a href="">
                <img src="assets/images/slider_1.webp" alt="" />
            </a>
        </div>

        <div id="box-banners">
            <div class="item">
                <a href="">
                    <img src="assets/images/banner_1.webp" alt="" />
                </a>
            </div>
            <div class="item">
                <a href="">
                    <img src="assets/images/banner_2.webp" alt="" />
                </a>
            </div>
            <div class="item">
                <a href="">
                    <img src="assets/images/banner_3.webp" alt="" />
                </a>
            </div>
        </div>

        <div id="productsTab" class="container">
            <h2 class="heading-box">SẢN PHẨM</h2>

            <div class="grid wide products tab-content">
                <div class="tab-pane active">
                    <div class="row">
                        <c:forEach items="${productList}" var="product">
                            <div class="col l-3 m-4 c-6">
                                <div class="item">
                                    <div class="box-img">
                                        <a href="<c:url value='/chi-tiet-san-pham?productId=${product.id}'/>" class="">
                                            <img src="${product.thumbnail}" alt="" />
                                            <div class="hover-overlay">
                                                <div class="hover-overlay__box-icon">
                                                    <div class="hover-overlay__eye">
                                                        <i class="fa-solid fa-eye"></i>
                                                    </div>
                                                    <div class="hover-overlay__bag" onclick="">
                                                        <i class="fa-solid fa-bag-shopping"></i>
                                                    </div>
                                                </div>
                                            </div>
                                        </a>
                                    </div>

                                    <h3 class="nameProduct">
                                        <a href="<c:url value='/chi-tiet-san-pham?productId=${product.id}'/>">${product.name}</a>
                                    </h3>
                                    <div>
                                        <span class="price-sale">
                                            <fmt:formatNumber value="${product.pricesale}" type="number" groupingUsed="true" />
                                            ₫
                                        </span>
                                        <del class="price">
                                            <fmt:formatNumber value="${product.price}" type="number" groupingUsed="true" />
                                            ₫
                                        </del>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

        <div id="banner2">
            <a href="">
                <img src="assets/images/banner.webp" alt="" />
            </a>
        </div>

        <div id="policy">
            <div class="box container">
                <div class="box-item">
                    <div class="img-item">
                        <img src="assets/images/policy1.webp" alt="" />
                    </div>
                    <h3>Giao hàng miễn phí</h3>
                    <p>Với đơn hàng trên 300.000đ</p>
                </div>
                <div class="box-item">
                    <div class="img-item">
                        <img src="assets/images/policy2.webp" alt="" />
                    </div>
                    <h3>Hỗ trợ 24/7</h3>
                    <p>Nhanh chóng thuận tiện</p>
                </div>
                <div class="box-item">
                    <div class="img-item">
                        <img src="assets/images/policy3.webp" alt="" />
                    </div>
                    <h3>Đổi trả trong 3 ngày</h3>
                    <p>Hấp dẫn chưa từng có</p>
                </div>
                <div class="box-item">
                    <div class="img-item">
                        <img src="assets/images/policy4.webp" alt="" />
                    </div>
                    <h3>Giá tiêu chuẩn</h3>
                    <p>Tiết kiệm 10% giá thị trường</p>
                </div>
            </div>
        </div>

        <div id="products-trend">
            <h2 class="heading-box">SẢN PHẨM TIN DÙNG</h2>

            <div class="swiper mySwiper container">
                <div class="swiper-wrapper container-slide">

                    <div class="swiper-slide">
                        <a href="" class="box-slide__item">
                            <div class="">
                                <img src="assets/images/chuoi1.webp" alt="" />
                            </div>

                            <div class="box-slide__item-content">
                                <h3>Chuối Laba nhập khẩu Thái Lan</h3>
                                <p><span class="price">30.000đ</span> <del>90.000đ</del></p>
                            </div>
                        </a>
                    </div>

                    <c:forEach items="${productList}" var="item">
                        <div class="swiper-slide">
                            <a href="<c:url value='/chi-tiet-san-pham?productId=${item.id}'/>" class="box-slide__item">
                                <div class="">
                                    <img src="${item.thumbnail}" alt="" />
                                </div>

                                <div class="box-slide__item-content">
                                    <h3>${item.name}</h3>
                                    <p>
                                        <span class="price-sale">
                                            <fmt:formatNumber value="${item.pricesale}" type="number" groupingUsed="true" />
                                            ₫
                                        </span>
                                        <del class="price">
                                            <fmt:formatNumber value="${item.price}" type="number" groupingUsed="true" />
                                            ₫
                                        </del>
                                    </p>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>

                <!-- Pagination (dấu chấm) -->
                <div class="swiper-pagination"></div>
            </div>
        </div>

        <div id="latest-news">
            <h2 class="heading-box">TIN TỨC MỚI</h2>

            <div class="container news-container">
                <div class="grid wide">
                    <div class="row">
                        <div class="col l-6">
                            <div class="news-item1">
                                <a href="">
                                    <img src="assets/images/news1.webp" alt="" />
                                    <h3>Bí quyết bảo quản nho đen trong tủ lạnh tươi lâu hơn</h3>
                                    <span><i class="fa-solid fa-user"></i> Bởi Sea Team</span>
                                    <span><i class="fa-solid fa-calendar-days"></i> 18/02/2025</span>
                                </a>
                            </div>
                        </div>
                        <div class="col l-6">
                            <div class="news-item">
                                <a href="" class="news-item__box">
                                    <div>
                                        <img src="assets/images/news2.webp" alt="" />
                                    </div>

                                    <div class="news-item__boxTitle">
                                        <h3>4 lý do bạn nên ăn dâu tây phòng tránh ung thư hiệu quả</h3>
                                        <span><i class="fa-solid fa-user"></i> Bởi Sea Team</span>
                                        <span><i class="fa-solid fa-calendar-days"></i> 18/02/2025</span>
                                    </div>
                                </a>
                            </div>

                            <div class="news-item">
                                <a href="" class="news-item__box">
                                    <div>
                                        <img src="assets/images/news3.webp" alt="" />
                                    </div>

                                    <div class="news-item__boxTitle">
                                        <h3>Công dụng của chanh ngâm mật ong chữa ho hiệu quả</h3>
                                        <span><i class="fa-solid fa-user"></i> Bởi Sea Team</span>
                                        <span><i class="fa-solid fa-calendar-days"></i> 18/02/2025</span>
                                    </div>
                                </a>
                            </div>

                            <div class="news-item">
                                <a href="" class="news-item__box">
                                    <div>
                                        <img src="assets/images/news4.webp" alt="" />
                                    </div>

                                    <div class="news-item__boxTitle">
                                        <h3>Những loại trái cây Nhật đắt như vàng ròng đổ bộ về Việt Nam</h3>
                                        <span><i class="fa-solid fa-user"></i> Bởi Sea Team</span>
                                        <span><i class="fa-solid fa-calendar-days"></i> 18/02/2025</span>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="banner-ins">
            <h2 class="heading-box">@Instagram</h2>
            <a href="#">
                <div>
                    <img src="assets/images/ins_1.webp" alt="" />
                    <img src="assets/images/ins_2.webp" alt="" />
                    <img src="assets/images/ins_3.webp" alt="" />
                    <img src="assets/images/ins_4.webp" alt="" />
                    <img src="assets/images/ins_5.webp" alt="" />
                    <img src="assets/images/ins_6.webp" alt="" />
                </div>
            </a>
        </div>
    </div>

    <%@include file="./inc/footer.jsp" %>

    <script>
        function slideProducts() {
            var swiper = new Swiper(".mySwiper", {
                slidesPerView: 3, // Hiển thị 3 sản phẩm mỗi lần
                spaceBetween: 20, // Khoảng cách giữa các sản phẩm
                loop: true, // Chạy lặp vô hạn
                autoplay: {
                    delay: 3000, // Tự động chạy sau 3 giây
                    disableOnInteraction: false,
                },
                // navigation: {
                //     nextEl: ".swiper-button-next",
                //     prevEl: ".swiper-button-prev",
                // },
                pagination: {
                    el: ".swiper-pagination",
                    clickable: true,
                },
            });
        }

        function HoverProducts() {
            const products = document.querySelectorAll(".products .item");

            if (products) {
                products.forEach((product) => {
                    const nameElement = product.querySelector(".products .nameProduct");
                    const boxImg = product.querySelector(".products .box-img");
                    const overlay = product.querySelector(".hover-overlay");

                    if (!overlay) return;

                    let hoverCount = 0;

                    const showOverlay = function () {
                        hoverCount++;
                        overlay.style.display = "flex";
                    };

                    const hideOverlay = function () {
                        hoverCount--;
                        if (hoverCount === 0) overlay.style.display = "none";
                    };

                    nameElement.addEventListener("mouseenter", showOverlay);
                    nameElement.addEventListener("mouseleave", hideOverlay);
                    boxImg.addEventListener("mouseenter", showOverlay);
                    boxImg.addEventListener("mouseleave", hideOverlay);
                });
            }
        }

        document.addEventListener("DOMContentLoaded", function () {
            slideProducts();
            HoverProducts();
        });
    </script>
</body>

</html>
