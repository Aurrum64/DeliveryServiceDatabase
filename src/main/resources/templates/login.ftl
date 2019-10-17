<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Авторизация" heightTop=0 heightBottom=0>
    <div class="view" onClick=false>
        <img src="https://images.wallpaperscraft.ru/image/nochnoj_gorod_vid_sverkhu_zdaniya_zheleznaya_doroga_118814_1920x1080.jpg"
             class="img-fluid"
             alt="" style="width: 100%; height: 100%">
        <div class="mask pattern-1 flex-center waves-effect waves-light">
            <div class="card" style="width: 420px">
                <h3 class="card-header info-color white-text text-center py-4">
                    <strong>Авторизация</strong>
                </h3>
                <div class="card-body">
                    <#if successMessage??>
                        <div class="alert alert-success alert-dismissible fade show mt-3 ml-2" role="alert"
                             style="width: 363px">
                            <span><b>${successMessage!}</b><br>
                        Для окончательной активации необходимо перезайти в учётную запись,
                                если вы уже авторизованы.</span>
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </#if>
                    <#if errorMessage??>
                        <div class="alert alert-danger alert-dismissible fade show mt-3 ml-2" role="alert"
                             style="width: 363px">
                            ${errorMessage!}
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </#if>
                    <#if error??>
                        <div class="alert alert-danger alert-dismissible fade show mt-3 ml-2" role="alert"
                             style="width: 363px">
                    <span>  Invalid username or password. Please, <a href="/registration"
                                                                     class="alert-link">register</a>, if you haven’t done so yet.</span>
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </#if>
                    <#if logout??>
                        <div class="alert alert-info alert-dismissible fade show mt-3 ml-2" role="alert"
                             style="width: 363px">
                            You have been logged out.
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </#if>

                    <form action="/login" method="post">
                        <div class="md-form">
                            <i class="fa fa-user prefix grey-text"></i>
                            <input type="text" name="username" id="materialFormCardNameEx" class="form-control">
                            <label for="materialFormCardNameEx" class="font-weight-light">Ваш логин...</label>
                        </div>
                        <div class="md-form">
                            <i class="fa fa-lock prefix grey-text"></i>
                            <input type="password" name="password" id="materialFormCardPasswordEx"
                                   class="form-control">
                            <label for="materialFormCardPasswordEx" class="font-weight-light">Ваш пароль...</label>
                        </div>
                        <div class="text-center py-4 mt-3">
                            <button class="btn btn-outline-info" type="submit">Войти</button>
                        </div>
                        <div class="text-center">
                            <p>У вас еще нет учетной записи?
                                <a href="/registration">Зарегистрироваться</a>
                            </p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@defaultPage.defaultPageTemplate>