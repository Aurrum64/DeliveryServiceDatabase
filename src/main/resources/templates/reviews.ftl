<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<#import "parts/interface/jumbotron.ftl" as jumbotron>
<@defaultPage.defaultPageTemplate pageName="Reviews" heightTop=65 heightBottom=200>
    <@jumbotron.jumbotron image="https://mdbootstrap.com/img/Photos/Others/gradient1.jpg"
    pageName="Reviews">
    </@jumbotron.jumbotron>
    <div class="container mt-5 ml-5" style="width: 1000px">
        <section class="mb-4">
            <h2 class="h1-responsive font-weight-bold text-center my-4">Оставьте отзыв о нашем сервисе!</h2>
            <p class="text-center w-responsive mx-auto mb-5">Если у вас есть какие-либо пожелания по улучшению
                работы нашего сервиса или вы хотите оставить отзыв о работе наших курьеров, вы можете заполнить эту
                форму.</p>
            <div class="row">
                <div class="col-md-9 mb-md-0 mb-5">
                    <form id="addReviews">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="md-form mb-0">
                                    <input type="text" id="addClientName" class="form-control">
                                    <label for="addClientName" class="">Ваше имя</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="md-form mb-0">
                                    <input type="text" id="addOrderId" class="form-control">
                                    <label for="addOrderId" class="">Номер вашего заказа</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="md-form mb-0">
                                    <input type="text" id="addReviewSubject" class="form-control">
                                    <label for="addReviewSubject" class="">Тема отзыва</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="md-form amber-textarea active-amber-textarea-2">
                                    <i class="fas fa-pencil-alt prefix"></i>
                                    <textarea id="addReview" class="md-textarea form-control" rows="3"></textarea>
                                    <label for="addReview">Ваш отзыв:</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">Оставить отзыв</button>
                        </div>
                    </form>
                </div>
                <div class="col-md-3 text-center">
                    <ul class="list-unstyled mb-0">
                        <li><i class="fas fa-map-marker-alt fa-2x"></i>
                            <p>Кожевническая ул., 7, Москва</p>
                        </li>
                        <li><i class="fas fa-phone mt-4 fa-2x"></i>
                            <p>+ 01 234 567 89</p>
                        </li>
                        <li><i class="fas fa-envelope mt-4 fa-2x"></i>
                            <p>delivery@service.com</p>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
    </div>
    <div class="jumbotron jumbotron-fluid mt-5">
        <div class="text-black text-center">
            <h5 class="display-4">Отзывы о нашем сервисе</h5>
        </div>
    </div>
    <div class="container mt-5 ml-5" style="width: 800px">
        <div id="reviewsList"></div>
    </div>
    <script src="/js/reviews/addReviews.js" type="text/javascript"></script>
</@defaultPage.defaultPageTemplate>