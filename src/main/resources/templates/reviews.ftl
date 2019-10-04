<#import "parts/defaultPageTemplate.ftl" as defaultPage>
<#import "parts/interface/jumbotron.ftl" as jumbotron>
<@defaultPage.defaultPageTemplate pageName="Reviews" heightTop=140 heightBottom=200>

    <section class="mb-4">
        <h2 class="h1-responsive font-weight-bold text-center my-4">Оставьте отзыв о нашем сервисе!</h2>
        <p class="text-center w-responsive mx-auto mb-5">Если у вас есть какие-либо пожелания по улучшению
            работы нашего сервиса или вы хотите оставить отзыв о работе наших курьеров, вы можете заполнить эту
            форму.</p>
        <div class="container">
            <div class="row">
                <div class="col">
                </div>
                <div class="col-md-9 mb-md-0 mb-5">
                    <#--                    <form id="addReviews">
                                            <div class="row mt-5">
                                                <div class="col-md-4">
                                                    <div class="md-form mb-0">
                                                        <input type="text" id="addClientName" value="${authorName}" class="form-control"
                                                               disabled>
                                                        <label for="addClientName" class="">Ваше имя</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class="md-form mb-0">
                                                        <input type="text" id="addOrderId" value="${orderDetailsId}"
                                                               class="form-control" disabled>
                                                        <label for="addOrderId" class="">Номер вашего заказа</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <select id="addRatingFromClient" class="browser-default custom-select mb-0 mt-4">
                                                        <option selected>Оценка</option>
                                                        <option value="1">1</option>
                                                        <option value="2">2</option>
                                                        <option value="3">3</option>
                                                        <option value="4">4</option>
                                                        <option value="5">5</option>
                                                    </select>
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
                                                    <button type="submit" class="btn btn-pink">Оставить отзыв</button>
                                                </div>
                                        </form>-->
                    <form action="/addReviews" method="post">
                        <div class="row mt-5">
                            <div class="col-md-4">
                                <div class="md-form mb-0">
                                    <input type="text" name="authorName" id="addAuthorName" value="${authorName}"
                                           class="form-control" disabled>
                                    <input type="hidden" name="authorName" value="${authorName}">
                                    <label for="addAuthorName" class="">Ваше имя</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="md-form mb-0">
                                    <input type="text" name="orderDetailsId" id="addOrderDetailsId"
                                           value="${orderDetailsId}"
                                           class="form-control" disabled>
                                    <input type="hidden" name="orderDetailsId" value="${orderDetailsId}">
                                    <label for="addOrderDetailsId" class="">Номер вашего заказа</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <select name="ratingFromClient" class="browser-default custom-select mb-0 mt-4">
                                    <option selected>Оценка</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="md-form amber-textarea active-amber-textarea-2">
                                    <i class="fas fa-pencil-alt prefix"></i>
                                    <textarea name="reviewFromClient" id="addReview" class="md-textarea form-control"
                                              rows="3"></textarea>
                                    <label for="addReview">Ваш отзыв:</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-pink">Оставить отзыв</button>
                        </div>
                    </form>
                </div>
                <div class="col">
                </div>
            </div>
        </div>
    </section>

<#--<script src="/js/reviews/addReviews.js" type="text/javascript"></script>
<script src="/js/reviews/ratingStars.js" type="text/javascript"></script>-->
</@defaultPage.defaultPageTemplate>