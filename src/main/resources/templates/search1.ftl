<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<#import "parts/interface/jumbotron.ftl" as jumbotron>
<@defaultPage.defaultPageTemplate pageName="Search" heightTop=65 heightBottom=200>
    <@jumbotron.jumbotron image="https://mdbootstrap.com/img/Photos/Others/gradient1.jpg"
    pageName="Search page">
    </@jumbotron.jumbotron>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form action="/searchFilter" method="post" class="form-inline">
                <div class="form-group row">
                    <div class="md-form form-lg ml-4">
                        <input type="text" name="id" id="input" class="form-control form-control-lg">
                        <label for="input">${placeholder1}</label>
                    </div>
                    <div class="md-form form-lg ml-3">
                        <input type="text" name="firstparam" id="inpu" class="form-control form-control-lg">
                        <label for="inpu">${placeholder2}</label>
                    </div>
                    <div class="md-form form-lg ml-3">
                        <input type="text" name="secondparam" id="inp" class="form-control form-control-lg">
                        <label for="inp">${placeholder3}</label>
                    </div>
                    <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                    <div>
                        <button type="submit" class="btn btn-primary ml-3">Найти</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</@defaultPage.defaultPageTemplate>