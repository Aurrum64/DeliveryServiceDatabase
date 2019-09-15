<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Registration" heightTop=100 heightBottom=200>
    <div class="container mt-5 ml-5" style="width: 500px">
        <div class="card">
            <h3 class="card-header info-color white-text text-center py-4">
                <strong>Registration</strong>
            </h3>
            <div class="card-body">

                <#if errorMessage??>
                    <div class="alert alert-danger alert-dismissible fade show mt-3 ml-2" role="alert"
                         style="width: 413px">
                        ${errorMessage!}
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </#if>

                <#if successMessage??>
                    <div class="alert alert-success alert-dismissible fade show mt-3 ml-2" role="alert"
                         style="width: 413px">
                        <span>${successMessage!}<br> Follow on <a href="/login"
                                                                  class="alert-link"> authorization page</a> to log in.</span>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </#if>

                <form action="/registration" method="post">
                    <div class="md-form">
                        <i class="fa fa-user prefix grey-text"></i>
                        <input type="text" name="username" id="materialFormCardNameEx" class="form-control">
                        <label for="materialFormCardNameEx" class="font-weight-light">Your name...</label>
                    </div>

                    <div class="md-form">
                        <i class="fa fa-lock prefix grey-text"></i>
                        <input type="password" name="password" id="materialFormCardPasswordEx" class="form-control">
                        <label for="materialFormCardPasswordEx" class="font-weight-light">Your password...</label>
                    </div>

                    <#--<input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
                    <div class="text-center py-4 mt-3">
                        <button class="btn btn-outline-info" type="submit">Sign Up</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@defaultPage.defaultPageTemplate>