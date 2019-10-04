<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Order details" heightTop=80 heightBottom=200>
    <#include "parts/interface/security.ftl">
    <div class="container mt-5 ml-5">
        <div class="text mt-5 mb-3">
            <h4>Отзывы:</h4>
        </div>
        <table class="table table-striped" style="width: 600px">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Номер заказа</th>
                <th scope="col">Автор отзыва</th>
                <th scope="col">Рейтинг</th>
                <th scope="col">Отзыв</th>
            </tr>
            </thead>
            <tbody id="reviewsList">
            </tbody>
        </table>
    </div>
    <script src="/js/reviews/reviewsList.js" type="text/javascript"></script>
</@defaultPage.defaultPageTemplate>