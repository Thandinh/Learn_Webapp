<div class="quixnav">
    <div class="quixnav-scroll">
        <ul class="metismenu" id="menu">
            <li class="nav-label first">Main Menu</li>
            <li><a class="" href="<c:url value='admin-home' />" aria-expanded="false"><i class="fa-solid fa-house"></i><span class="nav-text">Dashboard</span></a>
               
            </li>
            <li class="nav-label">Apps</li>
            <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i class="fa-brands fa-product-hunt"></i><span class="nav-text">Products</span></a>
                <ul aria-expanded="false">
                    <li><a href="<c:url value='/admin-product?action=index ' />">All</a></li>
                    <li><a href="<c:url value='/admin-product?action=create ' />">Add</a></li>
                </ul>
            </li>
            <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i class="fa-solid fa-layer-group"></i><span class="nav-text">Categories</span></a>
                <ul aria-expanded="false">
                    <li><a href="<c:url value='/admin-category?action=index' />">All</a></li>
                    <li><a href="<c:url value='/admin-category?action=create' />">Add</a></li>
                </ul>
            </li>
            <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i class="fa-solid fa-users"></i><span class="nav-text">Users</span></a>
                <ul aria-expanded="false">
                    <li><a href="<c:url value='/admin-user?action=index' />">All</a></li>
                    <li><a href="<c:url value='/admin-user?action=create ' />">Add</a></li>
                </ul>
            </li>
            <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i class="fa-solid fa-cart-arrow-down"></i><span class="nav-text">Orders</span></a>
                <ul aria-expanded="false">
                    <li><a href="<c:url value='/admin-order?action=index ' />">All</a></li>
                </ul>
            </li>
            
           
        </ul>
    </div>


</div>