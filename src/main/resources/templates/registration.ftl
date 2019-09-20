<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Registration" heightTop=0 heightBottom=0>
    <div class="view">
        <img src="https://images.wallpaperscraft.ru/image/megapolis_noch_gorodskoj_pejzazh_139112_1920x1080.jpg"
             class="img-fluid"
             alt="" style="width: 100%; height: 100%">
        <div class="mask pattern-1 flex-center waves-effect waves-light">
            <div class="card" style="width: 420px">
                <h3 class="card-header info-color white-text text-center py-4">
                    <strong>Registration</strong>
                </h3>
                <div class="card-body">

                    <#if errorMessage??>
                        <div class="alert alert-danger alert-dismissible fade show mt-3 ml-2" role="alert"
                             style="width: 363px">
                            ${errorMessage!}
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </#if>

                    <#if successMessage??>
                        <div class="alert alert-success alert-dismissible fade show mt-3 ml-2" role="alert"
                             style="width: 363px">
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
                            <label for="materialFormCardNameEx" class="font-weight-light">Ваше имя...</label>
                        </div>

                        <div class="md-form">
                            <i class="fa fa-envelope prefix grey-text"></i>
                            <input type="email" name="email" id="materialFormCardEmailEx" class="form-control">
                            <label for="materialFormCardEmailEx" class="font-weight-light">Ваша почта...</label>
                        </div>

                        <div class="md-form">
                            <i class="fa fa-lock prefix grey-text"></i>
                            <input type="password" name="password" id="materialFormCardPasswordEx" class="form-control">
                            <label for="materialFormCardPasswordEx" class="font-weight-light">Ваш пароль...</label>
                        </div>

                        <#--<input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
                        <div class="text-center py-4 mt-3">
                            <button class="btn btn-outline-info" type="submit">Sign Up</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@defaultPage.defaultPageTemplate>