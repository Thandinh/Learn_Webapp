<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tất cả sản phẩm</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="stylesheet" href="assets/css/grid.css">
    <link rel="stylesheet" href="assets/css/products.css">
</head>

<body>
<%@include file="./inc/header.jsp" %>

<div id="main" class="allProducts">
    <div class="grid wide">
        <div class="navig">
            <a href="trang-chu">Trang chủ</a>
            <i class="fa-solid fa-chevron-right"></i>
            <a class="item active" href="">Tất cả sản phẩm</a>
        </div>

        <div class="row row-main">
            <div class="col l-9">
                <div class="header-allProducts">
                    <h2>Tất cả sản phẩm</h2>
                    <div class="sort-options">
                        Sắp xếp theo:
                        <select name="sort-product" id="">
                            <option value="">Mặc định</option>
                            <option value="az">A - Z</option>
                            <option value="za">Z - A</option>
                            <option value="tangdan">Giá tăng dần</option>
                            <option value="giamdan">Giá giảm dần</option>
                            <option value="">Hàng mới nhất</option>
                            <option value="">Hàng cũ nhất</option>
                        </select>
                    </div>
                </div>

                <div class="row products">
                    <c:forEach var="product" items="${productList}">
                        <div class="col l-4">
                            <div class="item">
                                <div class="box-img">
                                    <a href="<c:url value='/chi-tiet-san-pham?productId=${product.id}'/>">
                                        <img src="${product.thumbnail}" alt="${product.name}">
                                        <div class="hover-overlay">
                                            <div class="hover-overlay__box-icon">
                                                <div class="hover-overlay__eye"><i class="fa-solid fa-eye"></i></div>
                                                <div class="hover-overlay__bag"><i class="fa-solid fa-bag-shopping"></i></div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                                <h3 class="nameProduct">
                                    <a href="<c:url value='/chi-tiet-san-pham?productId=${product.id}'/>">${product.name}</a>
                                </h3>
                                <div>
                                    <span class="price-sale">
                                        <fmt:formatNumber value="${product.pricesale}" type="number" groupingUsed="true"/>₫
                                    </span>
                                    <del class="price">
                                        <fmt:formatNumber value="${product.price}" type="number" groupingUsed="true"/>₫
                                    </del>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <ul class="listPage">
                    <c:forEach var="i" begin="1" end="${numberPage}">
                        <li class="${i == page ? 'active' : ''}">
                            <a style="display: block; color: black;" href="san-pham?page=${i}">${i}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <div class="col l-3">
                <div class="box box-menu mb-35">
                    <div class="title">DANH MỤC</div>
                    <ul class="content">
                        <li><a href="">Trang chủ</a></li>
                        <li><a href="">Giới thiệu</a></li>
                        <li><a href="">Sản phẩm</a></li>
                        <li><a href="">Tin tức</a></li>
                        <li><a href="">Liên hệ</a></li>
                    </ul>
                </div>

                <div class="box mb-35">
                    <div class="title">THƯƠNG HIỆU</div>
                    <div class="content">
                        <label class="custom-checkbox"><input type="checkbox"><span></span> Fresh</label>
                        <label class="custom-checkbox"><input type="checkbox"><span></span> H-Food</label>
                        <label class="custom-checkbox"><input type="checkbox"><span></span> Thu Minh</label>
                        <label class="custom-checkbox"><input type="checkbox"><span></span> Tom Food</label>
                        <label class="custom-checkbox"><input type="checkbox"><span></span> VinFruits</label>
                    </div>
                </div>

                <div class="box mb-35">
                    <div class="title">GIÁ CẢ</div>
                    <div class="content">
                        <label class="custom-checkbox"><input type="radio" name="price" value="duoi100"><span></span> Giá dưới 100.000đ</label>
                        <label class="custom-checkbox"><input type="radio" name="price" value="100den200"><span></span> 100.000đ - 200.000đ</label>
                        <label class="custom-checkbox"><input type="radio" name="price" value="200den300"><span></span> 200.000đ - 300.000đ</label>
                        <label class="custom-checkbox"><input type="radio" name="price" value="300den400"><span></span> 300.000đ - 400.000đ</label>
                        <label class="custom-checkbox"><input type="radio" name="price" value="400den500"><span></span> 400.000đ - 500.000đ</label>
                        <label class="custom-checkbox"><input type="radio" name="price" value="500den1trieu"><span></span> 500.000đ - 1.000.000đ</label>
                        <label class="custom-checkbox"><input type="radio" name="price" value="tren1trieu"><span></span> Giá trên 1.000.000đ</label>
                    </div>
                </div>

                <div class="box">
                    <div class="title">MÀU SẮC</div>
                    <div class="content">
                        <div class="color-picker">
                            <label class="color-option yellow"><input type="checkbox" name="color"><span></span></label>
                            <label class="color-option purple"><input type="checkbox" name="color"><span></span></label>
                            <label class="color-option red"><input type="checkbox" name="color"><span></span></label>
                            <label class="color-option green"><input type="checkbox" name="color"><span></span></label>
                            <label class="color-option pink"><input type="checkbox" name="color"><span></span></label>
                            <label class="color-option orange"><input type="checkbox" name="color"><span></span></label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="spacing-footer"></div>
<%@include file="./inc/footer.jsp" %>

<script>
    // Logic lọc giá giữ nguyên nếu dùng JavaScript xử lý giá trị radio
    const radioFilterPrice = document.querySelectorAll('input[type="radio"][name="price"]');
    radioFilterPrice.forEach(radio => {
        radio.addEventListener('click', (event) => {
            const isChecked = event.target.checked;
            if (isChecked && radio.dataset.wasChecked === 'true') {
                event.target.checked = false;
                radio.dataset.wasChecked = 'false';
            } else {
                radio.dataset.wasChecked = 'true';
                document.querySelectorAll(`input[name="${radio.name}"]`).forEach(otherRadio => {
                    if (otherRadio !== radio) {
                        otherRadio.dataset.wasChecked = 'false';
                    }
                });
            }
            handleEventFilter();
        });
    });

    function HoverProducts() {
        const products = document.querySelectorAll('.products .item');
        if (products) {
            products.forEach((product) => {
                const nameElement = product.querySelector('.products .nameProduct');
                const boxImg = product.querySelector('.products .box-img');
                const overlay = product.querySelector('.hover-overlay');
                if (!overlay) return;

                let hoverCount = 0;

                const showOverlay = () => {
                    hoverCount++;
                    overlay.style.display = 'flex';
                };

                const hideOverlay = () => {
                    hoverCount--;
                    if (hoverCount === 0) overlay.style.display = 'none';
                };

                nameElement.addEventListener('mouseenter', showOverlay);
                nameElement.addEventListener('mouseleave', hideOverlay);
                boxImg.addEventListener('mouseenter', showOverlay);
                boxImg.addEventListener('mouseleave', hideOverlay);
            });
        }
    }

    HoverProducts();
</script>
</body>
</html>
