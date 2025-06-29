<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
        <header>
            <div class="top-header container">
                <div class="top-items">
                    <div class="item">
                        <a href="">
                            <img class="p-3" src="assets/images/header_1.webp" alt="">
                            CÀ RỐT
                        </a>
                    </div>
                    <div class="item">
                        <a href="">
                            <img class="p-3" src="assets/images/header_2.webp" alt="">
                            KHOAI TÂY
                        </a>
                    </div>
                    <div class="item">
                        <a href="">
                            <img class="p-3" src="assets/images/header_3.webp" alt="">
                            DƯA HẤU
                        </a>
                    </div>
                    <div class="item">
                        <a href="">
                            <img class="p-3" src="assets/images/header_4.webp" alt="">
                            TRÁI CAM
                        </a>
                    </div>
                    <div class="item">
                        <a href="">
                            <img class="p-3" src="assets/images/header_5.webp" alt="">
                            ĐU ĐỦ
                        </a>
                    </div>
                    <div class="item">
                        <a href="">
                            <img class="p-3" src="assets/images/header_6.webp" alt="">
                            CÀ CHUA
                        </a>
                    </div>
                </div>


            </div>

            <div class="body-header container">
                <div class="top-bar">
                    <div>
                        <a href="trang-chu"><img src="assets/images/logo.webp" alt=""></a>
                    </div>

                    <div>

                        <c:if test="${sessionScope.user == null}">
                            <a class="user" href="dang-ky">Đăng ký</a>
                            <span class="space">|</span>
                            <a class="user" href="dang-nhap">Đăng nhập</a>
                        </c:if>

                        <c:if test="${sessionScope.user != null}">
                            <a style="font-size:25px; color: #2c6c36"><i class="fa-solid fa-user"></i></a>
                            <span class="space">|</span>
                            <a class="user" href="dang-xuat">Đăng xuất</a>
                        </c:if>

                        <div class="cart-icon">
                            <a href="<c:url value='/gio-hang'/>">
                                <i class="fas fa-shopping-basket cart-btn"></i>
                                <span class="cart-count">${countCart}</span>
                            </a>

                            <div class="mini-cart" id="miniCart">
                                <c:if test="${not empty cart}">
                                    <div class="mini-cart-body">
                                        <c:forEach items="${cart}" var="item">
                                            <div class="cart-mini-item">
                                                <span class="close-item"><i class="fa-solid fa-xmark"></i></span>
                                                <a href="#" class="img-item"><img src="${item.product.thumbnail}" alt=""></a>
                                                <div class="info-item">
                                                    <a href="#" class="info-item_name">${item.product.name}</a>
                                                    <div class="info-item_price">
                                                        <strong>
                                                            <fmt:formatNumber value="${item.price}" type="number" groupingUsed="true" />
                                                                                                        ₫
                                                        </strong>
                                                        <span>x ${item.quantity}</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>


                                    </div>

                                    <div class="mini-cart-footer">
                                        <div class="total">
                                            <span>Tổng tiền:</span>
                                            <strong id="totalPrice">
                                                 <fmt:formatNumber value="${totalCart}" type="number" groupingUsed="true" />
                                            </strong>
                                        </div>
                                        <button class="checkout-btn">Tiến hành thanh toán</button>
                                    </div>
                                </c:if>
                            </div>


                        </div>

                    </div>
                </div>

                <div class="bottom-bar">
                    <div class="item item-category">
                        <h3><i class="fa-solid fa-bars-staggered"></i> DANH MỤC SẢN PHẨM</h3>
                        <div class="submenu">
                            <c:forEach var="category" items="${categoryList}">
                                <a href="danh-muc?categoryId=${category.id}">${category.name}</a>
                            </c:forEach>
                        </div>
                    </div>


                    <ul class="item">
                        <li><a href="trang-chu">Trang chủ</a></li>
                        <li><a href="">Giới thiệu</a></li>
                        <li><a href="san-pham">Sản phẩm</a></li>
                        <li><a href="">Tin tức</a></li>
                        <li><a href="">Liên hệ</a></li>
                    </ul>

                    <div class="item">
                        <form method="get" action="san-pham">
                            <input name="keyword" type="text" placeholder="Tìm sản phẩm..." value="${param.keyword}">
                            <button type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
                        </form </div>

                    </div>
                </div>
        </header>