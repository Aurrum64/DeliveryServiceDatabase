<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<@defaultPage.defaultPageTemplate pageName="Authorization" heightTop=0 heightBottom=0>
    <div class="view" onClick=false>
        <img src="https://images.wallpaperscraft.ru/image/nochnoj_gorod_vid_sverkhu_zdaniya_zheleznaya_doroga_118814_1920x1080.jpg" class="img-fluid"
             alt="" style="width: 100%; height: 100%">
        <div class="mask pattern-1 flex-center waves-effect waves-light">
            <div class="card" style="width: 400px">
                <h3 class="card-header info-color white-text text-center py-4">
                    <strong>Authorization</strong>
                </h3>
                <div class="card-body">
                    <#if error??>
                        <div class="alert alert-danger alert-dismissible fade show mt-3 ml-2" role="alert"
                             style="width: 414px">
                    <span>  Invalid username or password. Please, <a href="/registration"
                                                                     class="alert-link">register</a>, if you haven’t done so yet.</span>
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </#if>
                    <#if logout??>
                        <div class="alert alert-info alert-dismissible fade show mt-3 ml-2" role="alert"
                             style="width: 414px">
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
                            <label for="materialFormCardNameEx" class="font-weight-light">Your name...</label>
                        </div>

                        <div class="md-form">
                            <i class="fa fa-lock prefix grey-text"></i>
                            <input type="password" name="password" id="materialFormCardPasswordEx"
                                   class="form-control">
                            <label for="materialFormCardPasswordEx" class="font-weight-light">Your
                                password...</label>
                        </div>

                        <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                        <div class="text-center py-4 mt-3">
                            <button class="btn btn-outline-info" type="submit">Sign In</button>
                        </div>

                        <div class="text-center">
                            <p>Not a member?
                                <a href="/registration">Register</a>
                            </p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
<#--        <div class="container mt-5 ml-5" style="width: 500px">
            <div class="card">
                <h3 class="card-header info-color white-text text-center py-4">
                    <strong>Authorization</strong>
                </h3>
                <div class="card-body">
                    <#if error??>
                        <div class="alert alert-danger alert-dismissible fade show mt-3 ml-2" role="alert"
                             style="width: 414px">
                    <span>  Invalid username or password. Please, <a href="/registration"
                                                                     class="alert-link">register</a>, if you haven’t done so yet.</span>
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </#if>
                    <#if logout??>
                        <div class="alert alert-info alert-dismissible fade show mt-3 ml-2" role="alert"
                             style="width: 414px">
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
                            <label for="materialFormCardNameEx" class="font-weight-light">Your name...</label>
                        </div>

                        <div class="md-form">
                            <i class="fa fa-lock prefix grey-text"></i>
                            <input type="password" name="password" id="materialFormCardPasswordEx" class="form-control">
                            <label for="materialFormCardPasswordEx" class="font-weight-light">Your password...</label>
                        </div>

                        &lt;#&ndash;<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>&ndash;&gt;
                        <div class="text-center py-4 mt-3">
                            <button class="btn btn-outline-info" type="submit">Sign In</button>
                        </div>

                        <div class="text-center">
                            <p>Not a member?
                                <a href="/registration">Register</a>
                            </p>
                        </div>
                    </form>
                </div>
            </div>
        </div>-->
</@defaultPage.defaultPageTemplate>