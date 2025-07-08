<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Focus - Bootstrap Admin Dashboard </title>
    <link rel="icon" type="image/png" sizes="16x16" href="<c:url value='/assets/admin/images/favicon.png' />">

    <link rel="stylesheet" href="<c:url value='/assets/admin/vendor/owl-carousel/css/owl.carousel.min.css' />">
    <link rel="stylesheet" href="<c:url value='/assets/admin/vendor/owl-carousel/css/owl.theme.default.min.css' />">
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
                    <div class="col-lg-3 col-sm-6">
                        <div class="card">
                            <div class="stat-widget-two card-body">
                                <div class="stat-content">
                                    <div class="stat-text">Today Revenue</div>
                                    <div class="stat-digit"> <fmt:formatNumber value="${todayRevenue}" type="number" groupingUsed="true" /> ₫</div>
                                </div>
                                <div class="progress">
                                    <div class="progress-bar progress-bar-success w-85" role="progressbar" aria-valuenow="85" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                        <div class="card">
                            <div class="stat-widget-two card-body">
                                <div class="stat-content">
                                    <div class="stat-text">Today Orders</div>
                                    <div class="stat-digit"> ${todayOrders}</div>
                                </div>
                                <div class="progress">
                                    <div class="progress-bar progress-bar-primary w-75" role="progressbar" aria-valuenow="78" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                        <div class="card">
                            <div class="stat-widget-two card-body">
                                <div class="stat-content">
                                    <div class="stat-text">This Month Revenue</div>
                                    <div class="stat-digit"> <fmt:formatNumber value="${thisMonthRevenue}" type="number" groupingUsed="true" /> ₫</div>
                                </div>
                                <div class="progress">
                                    <div class="progress-bar progress-bar-warning w-50" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                        <div class="card">
                            <div class="stat-widget-two card-body">
                                <div class="stat-content">
                                    <div class="stat-text">This Month Orders</div>
                                    <div class="stat-digit"> ${thisMonthOrders}</div>
                                </div>
                                <div class="progress">
                                    <div class="progress-bar progress-bar-danger w-65" role="progressbar" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                            </div>
                        </div>
                        </div>
                    </div>
                <div class="row">
                    <div class="col-xl-8 col-lg-8 col-md-8">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title"><b>6/2025</b></h4>
                            </div>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-xl-12 col-lg-8">
                                        <div id="morris-bar-chart"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-4 col-lg-4 col-md-4">
                        <div class="card">
                            <div class="card-body text-center">
                                <div class="m-t-10">
                                    <h4 class="card-title">Customer</h4>
                                    <h2 class="mt-3">${countUsers}</h2>
                                </div>
                                <div class="widget-card-circle mt-5 mb-5" id="info-circle-card">
                                    <i class="ti-control-shuffle pa"></i>
                                </div>
                                <ul class="widget-line-list m-b-15">
                                    <li class="border-right">92% <br><span class="text-success"><i
                                                class="ti-hand-point-up"></i> Positive</span></li>
                                    <li>8% <br><span class="text-danger"><i
                                                class="ti-hand-point-down"></i>Negative</span></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                
                
            </div>
        </div>
        <%@ include file="/admin/inc/footer.jsp" %>
        <script src="<c:url value='/assets/admin/vendor/global/global.min.js' />"></script>
        <script src="<c:url value='/assets/admin/js/quixnav-init.js' />"></script>
        <script src="<c:url value='/assets/admin/js/custom.min.js' />"></script>


        <script src="<c:url value='/assets/admin/vendor/raphael/raphael.min.js' />"></script>
        <script src="<c:url value='/assets/admin/vendor/morris/morris.min.js' />"></script>


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
            (function($) {
                "use strict";

                // Morris bar chart
                Morris.Bar({
                    element: 'morris-bar-chart',
                    data: [
                        <c:forEach var="row" items="${statsList}" varStatus="loop">
                            {
                                y: '${row[0]}',
                                a: ${row[2]},
                            }<c:if test="${!loop.last}">,</c:if>
                        </c:forEach>
                    ],
                    xkey: 'y',
                    ykeys: ['a'],
                    labels: ['Doanh thu'],
                    barColors: ['#FF007F'],
                    hideHover: 'auto',
                    gridLineColor: '#eef0f2',
                    resize: true
                });

        })(jQuery);
       </script>

       
</body>

</html>