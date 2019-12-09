<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<#import "parts/interface/jumbotron.ftl" as jumbotron>
<@defaultPage.defaultPageTemplate pageName="Search" heightTop=65 heightBottom=200>
    <@jumbotron.jumbotron image="https://mdbootstrap.com/img/Photos/Others/gradient1.jpg"
    pageName="Search page">
    </@jumbotron.jumbotron>
    <#if filterCheck??>
    <#--{{#filterCheck}}-->
        <div class="alert alert-danger alert-dismissible fade show" role="alert" style="width: 736px">
            <#--{{filterCheck}}-->
            ${filterCheck!}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    <#--{{/filterCheck}}-->
    </#if>
    <#if managersTable??>
        <div class="container mt-5 ml-5">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Имя</th>
                    <th scope="col">Фамилия</th>
                    <th scope="col">Почта</th>
                    <th scope="col">Сотовый</th>
                    <th scope="col">Зарплата</th>
                    <th scope="col">Трудоустроился</th>
                    <th scope="col">Премиальные</th>
                    <th scope="col">Автор</th>
                </tr>
                </thead>
                <tbody>
                <#list managers as manager>
                    <tr>
                        <th scope="row">${manager.managerId}</th>
                        <td>${manager.firstName}</td>
                        <td>${manager.lastName}</td>
                        <td>${manager.email}</td>
                        <td>${manager.phoneNumber}</td>
                        <td>${manager.salary}</td>
                        <td>${manager.hireDate}</td>
                        <td>${manager.premium}</td>
                        <td>${manager.authorName}</td>
                    </tr>
                <#else>
                    <tr>
                        <th scope="row">No manager was found!</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </#if>
    <#if productsTable??>
        <div class="container mt-5 ml-5">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Название</th>
                    <th scope="col">Цена</th>
                    <th scope="col">Кол-во</th>
                </tr>
                </thead>
                <tbody>
                <#list products as product>
                    <tr>
                        <th scope="row">${product.productId}</th>
                        <td>${product.title}</td>
                        <td>${product.price}</td>
                        <td>${product.amount}</td>
                    </tr>
                <#else>
                    <tr>
                        <th scope="row">List of products is empty yet!</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </#if>
    <#if ordersTable??>
        <div class="container mt-5 ml-5">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Автор</th>
                    <th scope="col">Дата</th>
                    <th scope="col">Адрес</th>
                    <th scope="col">Комментарий</th>
                    <th scope="col">Статус</th>
                </tr>
                </thead>
                <tbody>
                <#list orderDetails as order>
                    <tr>
                        <th scope="row">${order.orderDeteailsId}</th>
                        <td>${order.author}</td>
                        <td>${order.orderDate}</td>
                        <td>${order.orderAddress}</td>
                        <td>${order.comment}</td>
                        <td>${order.status}</td>
                    </tr>
                <#else>
                    <tr>
                        <th scope="row">List of products is empty yet!</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </#if>
    <#if couriersTable??>
        <div class="container mt-5 ml-5">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Имя</th>
                    <th scope="col">Фамилия</th>
                    <th scope="col">Почта</th>
                    <th scope="col">Сотовый</th>
                    <th scope="col">Рейтинг</th>
                    <th scope="col">Зарплата</th>
                    <th scope="col">Трудоустроился</th>
                    <th scope="col">Премия</th>
                    <th scope="col">Департамент</th>
                    <th scope="col">X</th>
                    <th scope="col">Y</th>
                    <th scope="col">Автор</th>
                </tr>
                </thead>
                <tbody>
                <#list couriers as courier>
                <#--{{#couriers}}-->
                    <tr>
                        <th scope="row">${courier.courierId}</th>
                        <td>${courier.firstName}</td>
                        <td>${courier.lastName}</td>
                        <td>${courier.email}</td>
                        <td>${courier.phoneNumber}</td>
                        <td>${courier.rating}</td>
                        <td>${courier.salary}</td>
                        <td>${courier.hireDate}</td>
                        <td>${courier.premium}</td>
                        <td>${courier.departmentId}</td>
                        <td>${courier.latitude}</td>
                        <td>${courier.longitude}</td>
                        <td>${courier.authorName}</td>
                    </tr>
                <#--{{/couriers}}-->
                <#else>
                <#--{{^couriers}}-->
                    <tr>
                        <th scope="row">List of couriers is empty yet!</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                <#--{{/couriers}}-->
                </#list>
                </tbody>
            </table>
        </div>
    </#if>
    <#if locationsTable??>
        <div class="container mt-5 ml-5">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Город</th>
                    <th scope="col">Район</th>
                    <th scope="col">Улица</th>
                    <th scope="col">Здание</th>
                </tr>
                </thead>
                <tbody>
                <#list locations as location>
                    <tr>
                        <th scope="row">${location.locationId}</th>
                        <td>${location.city}</td>
                        <td>${location.district}</td>
                        <td>${location.street}</td>
                        <td>${location.building}</td>
                    </tr>
                <#else>
                    <tr>
                        <th scope="row">List of locations is empty yet!</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </#if>
</@defaultPage.defaultPageTemplate>