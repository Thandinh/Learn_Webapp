<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết sản phẩm</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="stylesheet" href="assets/css/grid.css">
    <link rel="stylesheet" href="assets/css/product.css">
</head>

<body>
    <%@include file="./inc/header.jsp"%>

    <div id="main-detail">
        <div class="container">
            <div class="navig">
                <a href="trang-chu">Trang chủ</a>
                <i class="fa-solid fa-chevron-right"></i>
                <a class="item active" href="">${product.name}</a>
            </div>

            <div class="grid wide">
                <div class="row detail-product">
                    <div class="col l-6">
                        <div class="title">${product.name}</div>

                        <div class="sub-title">
                            <div>Thương hiệu<span>: H-Food</span></div>
                            <div>Tình trạng<span>: Còn hàng</span></div>
                        </div>

                        <div class="price">
                            <span id="price-sale">
                                <fmt:formatNumber value="${product.pricesale}" type="number" groupingUsed="true" />₫
                            </span>
                            <del id="price">
                                <fmt:formatNumber value="${product.price}" type="number" groupingUsed="true" />₫
                            </del>
                        </div>

                        <div class="description">
                            ${product.description}
                        </div>

                        <form action="<c:url value='/gio-hang'/>" method="POST">
                            <div class="add-to-cart">
                                <input type="hidden" name="action" value="create">
                                <input type="hidden" name="productId" value="${product.id}">
                                <input type="hidden" name="price" value="${product.pricesale}">
                                <div class="quantity">
                                    <button type="button" onclick="changeQuantity(-1)">-</button>
                                    <input type="text" id="quantity" name="quantity" value="1" readonly>
                                    <button type="button" onclick="changeQuantity(1)">+</button>
                                </div>

                                <button type="submit" class="btn-AddToCart">
                                    <i class="fa-solid fa-cart-shopping"></i>
                                    Thêm vào giỏ hàng
                                </button>
                            </div>
                        </form>
                    </div>

                    <div class="col l-6">
                        <div class="img">
                            <img src="${product.thumbnail}" alt="${product.name}">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="spacing-footer"></div>

    <%@include file="./inc/footer.jsp"%>

    <script>
        function changeQuantity(amount) {
            let quantityInput = document.querySelector('.add-to-cart .quantity input');
            let currentQuantity = parseInt(quantityInput.value);
            let newQuantity = currentQuantity + amount;
            if (newQuantity > 0) {
                quantityInput.value = newQuantity;
            }
        }
    </script>
</body>

</html>
