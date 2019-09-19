function starsOfRating(rating) {
    let ratingStars;
    if (rating === 1) {
        return ratingStars =
            "      <i class=\"fas fa-star red-text\"> </i>\n" +
            "      <i class=\"fas fa-star black-text\"> </i>\n" +
            "      <i class=\"fas fa-star black-text\"> </i>\n" +
            "      <i class=\"fas fa-star black-text\"> </i>\n" +
            "      <i class=\"fas fa-star black-text\"> </i><br><br>";
    } else if (rating === 2) {
        return ratingStars =
            "      <i class=\"fas fa-star orange-text\"> </i>\n" +
            "      <i class=\"fas fa-star orange-text\"> </i>\n" +
            "      <i class=\"fas fa-star black-text\"> </i>\n" +
            "      <i class=\"fas fa-star black-text\"> </i>\n" +
            "      <i class=\"fas fa-star black-text\"> </i><br><br>";
    } else if (rating === 3) {
        return ratingStars =
            "      <i class=\"fas fa-star yellow-text\"> </i>\n" +
            "      <i class=\"fas fa-star yellow-text\"> </i>\n" +
            "      <i class=\"fas fa-star yellow-text\"> </i>\n" +
            "      <i class=\"fas fa-star black-text\"> </i>\n" +
            "      <i class=\"fas fa-star black-text\"> </i><br><br>";
    } else if (rating === 4) {
        return ratingStars =
            "      <i class=\"fas fa-star green-text\"> </i>\n" +
            "      <i class=\"fas fa-star green-text\"> </i>\n" +
            "      <i class=\"fas fa-star green-text\"> </i>\n" +
            "      <i class=\"fas fa-star green-text\"> </i>\n" +
            "      <i class=\"fas fa-star black-text\"> </i><br><br>";
    } else if (rating === 5) {
        return ratingStars =
            "      <i class=\"fas fa-star green-text\"> </i>\n" +
            "      <i class=\"fas fa-star green-text\"> </i>\n" +
            "      <i class=\"fas fa-star green-text\"> </i>\n" +
            "      <i class=\"fas fa-star green-text\"> </i>\n" +
            "      <i class=\"fas fa-star green-text\"> </i><br><br>";
    }
}