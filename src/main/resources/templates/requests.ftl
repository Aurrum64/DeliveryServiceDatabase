<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Requests" heightTop=70 heightBottom=500>
    <div class="container mt-5 ml-5">
        <h4>Заявки пользователей:</h4>
        <table class="table table-striped" style="width: 800px">
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
                <#if request.isConsidering()>
                    <td>
                        <div class="form-group row">
                            <form action="/approveRequest" method="post">
                                <input type="hidden" name="requestId" value="${request.requestId}">
                                <#if request.courierRequest>
                                    <input type="hidden" name="professionChoice" value="courier">
                                <#elseif request.managerRequest>
                                    <input type="hidden" name="professionChoice" value="manager">
                                </#if>
                                <input type="hidden" name="source" value="requests">
                                <input type="hidden" name="authorName" value="${request.authorName}">
                                <button type="submit" class="btn btn-light-green ml-3">Одобрить</button>
                            </form>
                            <form action="/rejectRequest" method="post">
                                <input type="hidden" name="requestId" value="${request.requestId}">
                                <#if request.courierRequest>
                                    <input type="hidden" name="professionChoice" value="courier">
                                <#elseif request.managerRequest>
                                    <input type="hidden" name="professionChoice" value="manager">
                                </#if>
                                <input type="hidden" name="source" value="requests">
                                <input type="hidden" name="authorName" value="${request.authorName}">
                                <button type="submit" class="btn btn-deep-orange ml-3">Отклонить</button>
                            </form>
                        </div>
                    </td>
                </#if>
                </tr>
            <#else>
                <tr>
                    <th scope="row">Список заявок от пользователей пока пуст :(</th>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@defaultPage.defaultPageTemplate>