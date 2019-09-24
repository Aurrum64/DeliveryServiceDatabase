<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Registration" heightTop=70 heightBottom=500>
    <div class="container mt-5 ml-5">
        <h4>Заявки пользователей:</h4>
        <table class="table table-striped" style="width: 500px">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Заявка на должность</th>
                <th scope="col">Статус</th>
                <th scope="col">Заявитель</th>
            </tr>
            </thead>
            <tbody>
            <#list usersRequests as request>
                <tr>
                    <th scope="row">${request.requestId}</th>
                    <#if request.courierRequest>
                        <td>Курьер</td>
                    <#elseif request.managerRequest>
                        <td>Менеджер</td>
                    </#if>
                    <#if request.isConsidering()>
                        <td>В рассмотрении</td>
                    </#if>
                    <#if request.isApproved()>
                        <td>Одобрена</td>
                    </#if>
                    <#if request.isRejected()>
                        <td>Отклонена</td>
                    </#if>
                    <td>${request.authorName}</td>
                </tr>
            <#else>
                <tr>
                    <th scope="row">Список заявок от пользователей пока пуст :(</th>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@defaultPage.defaultPageTemplate>