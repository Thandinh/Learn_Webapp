<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Edit User</title>
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
                            <a href="<c:url value='admin-user?action=index ' />"><i style="font-size: 20px; padding: 15px;" class="fa-solid fa-arrow-left"></i></a>
                         

                            <div class="card-header">
                                <h4 class="card-title">Edit User</h4>
                                
                            </div>
                            <img class="mt-3 mb-3 ml-3" src="${user.avatar}" alt="Avatar" width="80" height="80" style="border-radius: 50%;">
                            <div class="card-body">
                                
                                <div class="basic-form">
                                   <form action="<c:url value='admin-user' />" method="post">
                                        <input type="hidden" name="action" value="edit">
                                        <input type="hidden" name="avatar" value="${user.avatar}">
                                        <div class="form-group">
                                            <label>Email: </label>
                                            <input type="email"
                                                class="form-control input-default"
                                                name="email"
                                                placeholder="email"
                                                value="${user.email != null ? user.email : ''}" readonly />
                                                <c:if test="${not empty errorEmail}">
                                                    <p style="color: red;">${errorEmail}</p>
                                                </c:if>
                                        </div>

                                        <div class="form-group">
                                            <label>Password (leave blank to keep current):</label>
                                            <input type="text"
                                                class="form-control input-default"
                                                name="password"
                                                placeholder="New Password (optional)" />
                                        </div>

                                        <div class="form-group">
                                            <label>Role: </label>
                                            <select class="form-control" name="role">
                                                <option value="user" ${user.role == 'user' ? 'selected' : ''}>User</option>
                                                <option value="admin" ${user.role == 'admin' ? 'selected' : ''}>Admin</option>
                                            </select>
                                        </div>

                                        <div class="form-group">
                                            <label>Status: </label>
                                            <select class="form-control" name="status">
                                                <option value="1" ${user.status == 1 ? 'selected' : ''}>Active</option>
                                                <option value="0" ${user.status == 0 ? 'selected' : ''}>Inactive</option>
                                            </select>
                                        </div>

                                        <button type="submit" class="btn btn-primary mb-2">Update</button>
                                    </form>

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

    <script>
        document.querySelector('.custom-file-input').addEventListener('change', function(e) {
            var fileName = document.getElementsByClassName('custom-file-input')[0].files[0].name;
            var nextSibling = e.target.nextElementSibling
            nextSibling.innerText = fileName
        })
    </script>

</body>

</html>