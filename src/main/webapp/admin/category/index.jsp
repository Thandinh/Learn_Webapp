<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Categories</title>
    <link rel="icon" type="image/png" sizes="16x16"
          href="<c:url value='/assets/admin/images/favicon.png' />">

    <link rel="stylesheet"
          href="<c:url value='/assets/admin/vendor/owl-carousel/css/owl.carousel.min.css' />">
    <link rel="stylesheet"
          href="<c:url value='/assets/admin/vendor/owl-carousel/css/owl.theme.default.min.css' />">
    <link href="<c:url value='/assets/admin/vendor/jqvmap/css/jqvmap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/assets/admin/css/style.css' />" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<body>

    <%@ include file="/admin/inc/preloader.jsp" %>
    <div id="main-wrapper">

        <%@ include file="/admin/inc/header.jsp" %>
        <%@ include file="/admin/inc/sidebar.jsp" %>
        <div class="content-body">
            <div class="container-fluid">

                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <a href="<c:url value='admin-order' />"><i style="font-size: 20px; padding: 15px;" class="fa-solid fa-arrow-left"></i></a>
                            <div class="card-header">
                                <h4 class="card-title">Categories</h4>
                            </div>
                          <div class="card-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead class="thead-primary">
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">name</th>
                                            <th scope="col">#</th>
                                            <th scope="col">#</th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="i" value="0" />

                                        <c:forEach items="${categoryList}" var="category">
                                            <c:set var="i" value="${i + 1}" />
                                           

                                            <tr>
                                                <th>${i}</th>
                                                <td style="color: black;">${category.name}</td>
                                                
                                                <td>
                                                    <form action="<c:url value='admin-category' />" method="get">
                                                        <input type="hidden" name="action" value="update">
                                                        <input type="hidden" name="categoryId" value="${category.id}">
                                                        <button type="submit" style="border: none;" class="badge badge-dark">Edit</button>
                                                    </form>
                                                </td>
                                                <td>
                                                    <form action="<c:url value='admin-category' />" method="post">
                                                        <input type="hidden" name="action" value="delete">
                                                        <input type="hidden" name="categoryId" value="${category.id}">
                                                        <button type="submit" style="border: none;" class="badge badge-danger">Delete</button>
                                                    </form>    
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <%@ include file="/admin/inc/footer.jsp" %>
        </div>
    <script src="<c:url value='/assets/admin/vendor/global/global.min.js' />"></script>
    <script src="<c:url value='/assets/admin/js/quixnav-init.js' />"></script>
    <script src="<c:url value='/assets/admin/js/custom.min.js' />"></script>

    <script src="<c:url value='/assets/admin/vendor/raphael/raphael.min.js' />"></script>
    <script src="<c:url value='/assets/admin/vendor/morris/morris.min.js' />"></script>

    <%-- Other chart-related scripts (ensure correct order if there are dependencies) --%>
    <script src="<c:url value='/assets/admin/vendor/circle-progress/circle-progress.min.js' />"></script>
    <script src="<c:url value='/assets/admin/vendor/chart.js/Chart.bundle.min.js' />"></script>
    <script src="<c:url value='/assets/admin/vendor/gaugeJS/dist/gauge.min.js' />"></script>
    <script src="<c:url value='/assets/admin/vendor/flot/jquery.flot.js' />"></script>
    <script src="<c:url value='/assets/admin/vendor/flot/jquery.flot.resize.js' />"></script>
    <script src="<c:url value='/assets/admin/vendor/owl-carousel/js/owl.carousel.min.js' />"></script>
    <script src="<c:url value='/assets/admin/vendor/jqvmap/js/jquery.vmap.min.js' />"></script>
    <script src="<c:url value='/assets/admin/vendor/jqvmap/js/jquery.vmap.usa.js' />"></script>
    <script src="<c:url value='/assets/admin/vendor/jquery.counterup/jquery.counterup.min.js' />"></script>

    <script src="<c:url value='/assets/admin/js/dashboard/dashboard-1.js' />"></script>



</body>

</html>