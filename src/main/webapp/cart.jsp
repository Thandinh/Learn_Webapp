<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giỏ hàng SeaFruits</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="stylesheet" href="assets/css/grid.css">
    <link rel="stylesheet" href="assets/css/cart.css">
</head>

<body>
    <%@include file="./inc/header.jsp" %>

    <div id="main">
        <div class="container">
            <div class="navig">
                <a href="<c:url value='/trang-chu'/>">Trang chủ</a>
                <i class="fa-solid fa-chevron-right"></i>
                <a class="item active" href="#">Giỏ hàng</a>
            </div>

            <h2 class="heading-cart">Giỏ hàng của bạn</h2>

            <div class="cart-container">

                <div class="header-cart">
                    <div class="title-product">Sản phẩm</div>
                    <div class="title-price">Giá</div>
                    <div class="title-quantity">Số lượng</div>
                    <div class="title-total">Thành tiền</div>
                </div>

                <div class="body-cart">
                    <c:forEach items="${cart}" var="item">
                        <div class="item-cart">
                            <div class="item-content">
                                <form action="gio-hang" method="POST">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="productId" value="${item.productId}">
                                    <div class="item-content_delete" onclick="this.closest('form').submit()">
                                        <i class="fa-solid fa-xmark"></i>
                                    </div>
                                </form>
                                <a href="#" class="item-content_image">
                                    <img src="${item.product.thumbnail}" alt="${item.product.name}">
                                </a>
                                <a href="#" class="item-content_name">${item.product.name}</a>
                            </div>
                            <div class="item-price">
                                <fmt:formatNumber value="${item.price}" type="number" groupingUsed="true"/>₫
                            </div>
                            
                            <div class="item-quantity">
                                <form action="gio-hang" method="post">
                                    <input type="hidden" name="action" value="update">
                                    <input type="hidden" name="productId" value="${item.productId}">
                                    <input type="hidden" name="typeChange" value="">

                                    <button onclick="changeQuantity('decrease', this.form)">-</button>
                                    <span>${item.quantity}</span>
                                    <button onclick="changeQuantity('increase', this.form)">+</button>
                                </form>
                            </div>
                            <div class="item-total">
                                <fmt:formatNumber value="${item.price * item.quantity}" type="number" groupingUsed="true"/>₫
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <div class="footer-cart">
                    <div class="total-cart">
                        <span>Tổng số thành tiền: </span>
                        <strong id="totalCart">
                            <fmt:formatNumber value="${totalCart}" type="number" groupingUsed="true"/>₫
                        </strong>
                    </div>

                    <div class="button-cart">
                        <button onclick="window.location.href='san-pham'">Tiếp tục mua hàng</button>
                        <button onclick="window.location.href='dat-hang'">Tiến hành thanh toán</button>
                    </div>
                </div>
            
            </div>
        </div>
    </div>

    <div id="spacing-footer"></div>

    <%@include file="./inc/footer.jsp" %>

    <script>
        <c:if test="${not empty sessionScope.CheckoutSuccess}">
            alert("${sessionScope.CheckoutSuccess}");
            <c:remove var="CheckoutSuccess" scope="session" />
        </c:if>
        function changeQuantity(action, form) {
            form.querySelector('input[name="typeChange"]').value = action;
            form.submit();
        }
    </script>
</body>

</html>
