<#include "security.ftl">
<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark scrolling-navbar">
    <a class="navbar-brand" href="/"><strong>Delivery Service</strong></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/">Overview</a>
            </li>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user">Users</a>
                </li>
            </#if>
            <#if isAdmin || isManager || isCourier>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Management
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <#if isAdmin || isManager>
                            <a class="dropdown-item" href="/orderDetails">Order details</a>
                        </#if>
                        <#if isAdmin || isCourier || isManager>
                            <a class="dropdown-item" href="/couriers">Couriers</a>
                        </#if>
                        <#if isAdmin || isManager>
                            <a class="dropdown-item" href="/managers">Managers</a>
                        </#if>
                        <#if isAdmin>
                            <a class="dropdown-item" href="/orders">Orders</a>
                        </#if>
                        <#if isAdmin>
                            <a class="dropdown-item" href="/callcentre">Call centre</a>
                        </#if>
                        <#if isAdmin>
                            <a class="dropdown-item" href="/locations">Locations</a>
                        </#if>
                        <#if isAdmin>
                            <a class="dropdown-item" href="/clients">Clients</a>
                        </#if>
                        <#if isAdmin>
                            <a class="dropdown-item" href="/products">Products</a>
                        </#if>
                    </div>
                </li>
            </#if>
        </ul>
        <#if isEnabled>
            <div class="navbar-text mr-3">Hi, ${name}!</div>
            <div>
                <form action="/logout" method="post">
                    <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                    <button type="submit" class="btn btn-outline-info">Sign Out</button>
                </form>
            </div>
        <#else>
            <div class="navbar-text mr-3">Hi, guest!</div>
            <form>
                <input type="button" class="btn btn-outline-info mr-3" value="Sign In" onClick='location.href="/login"'>
            </form>
            <form>
                <input type="button" class="btn btn-outline-info mr-2" value="Sign Up" onClick='location.href="/registration"'>
            </form>
        </#if>
    </div>
</nav>
