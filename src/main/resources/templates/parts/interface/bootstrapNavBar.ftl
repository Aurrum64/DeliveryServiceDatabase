<#include "security.ftl">
<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark scrolling-navbar">
    <a class="navbar-brand" href="/"><strong>Delivery Service</strong></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if !isEnabled>
                <li class="nav-item">
                    <a class="nav-link" href="/">На главную</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/ourLocation">Расположение</a>
                </li>
            </#if>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user">Пользователи</a>
                </li>
            </#if>
            <#if isAdmin || isManager>
                <li class="nav-item">
                    <a class="nav-link" href="/requests">Заявки</a>
                </li>
            </#if>
            <#if isAdmin || isManager || isCourier>
                <li class="nav-item">
                    <a class="nav-link" href="/notifications">Уведомления</a>
                </li>
            </#if>
            <#if isAdmin || isManager || isCourier>
                <li class="nav-item">
                    <#if isAdmin || isManager>
                        <a class="nav-link" href="/logistics">Логистика</a>
                    </#if>
                    <#if isCourier>
                        <a class="nav-link" href="/logistics">Карта заказов</a>
                    </#if>
                </li>
            </#if>
            <#if isAccountActivated>
                <#if isAdmin>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Управление
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="/couriers">Курьеры</a>
                            <a class="dropdown-item" href="/managers">Менеджеры</a>
                            <a class="dropdown-item" href="/orders">Заказы</a>
                            <a class="dropdown-item" href="/orderDetails">Информация о заказах</a>
                            <a class="dropdown-item" href="/callcentre">Кол-центр</a>
                            <a class="dropdown-item" href="/clients">Клиенты</a>
                            <a class="dropdown-item" href="/products">Продукты</a>
                            <a class="dropdown-item" href="/locations">Локации</a>
                            <a class="dropdown-item" href="/reviewsList">Отзывы</a>
                        </div>
                    </li>
                </#if>
            </#if>
        </ul>
        <#if isEnabled>
            <div class="navbar-text mr-3">Привет, ${name}!</div>
            <div class="btn-group" role="group">
                <button id="btnGroupDrop1" type="button" class="btn btn-outline-info lighten-2 dropdown-toggle"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-user"
                       aria-hidden="true"></i>
                </button>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuOffset"
                     style="width: 100px">
                    <a class="dropdown-item" href="/profile">Профиль</a>
                    <a class="dropdown-item" href="/orderDelivery">Заказы</a>
                    <div class="dropdown-divider"></div>
                    <form action="/logout" method="post">
                        <button type="submit" class="btn btn-outline-info ml-2" style="width: 140px">Выход</button>
                    </form>
                </div>
            </div>
        <#else>
            <div class="navbar-text mr-3">Привет!</div>
            <form>
                <input type="button" class="btn btn-outline-info mr-3" value="Вход" onClick='location.href="/login"'>
            </form>
            <form>
                <input type="button" class="btn btn-outline-info mr-2" value="Регистрация"
                       onClick='location.href="/registration"'>
            </form>
        </#if>
    </div>
</nav>
