function addGoodsToBasket(goodsId) {
    $.ajax({
        type: "POST",
        url: "/basket?goodsId=" + goodsId,
        dataType: "html",
        success: function (response) {
            location.reload();
        },
        error: function (response) {
            var parsed = $.parseHTML(response.responseText);
            $('#content').replaceWith($(parsed).filter("#errorContent"));
        }
    });
}

function removeGoodsFromBasket(goodsId) {
    $.ajax({
        type: "DELETE",
        url: "/basket?goodsId=" + goodsId,
        success: function (response) {
            location.reload();
        },
        error: function (response) {
            var parsed = $.parseHTML(response.responseText);
            $('#content').replaceWith($(parsed).filter("#errorContent"));
        }
    });
}

function removeGoods(goodsId) {
    $.ajax({
        type: "DELETE",
        url: "/goods?goodsId=" + goodsId,
        success: function (response) {
            location.reload();
        },
        error: function (error) {
            var parsed = $.parseHTML(response.responseText);
            $('#content').replaceWith($(parsed).filter("#errorContent"));
        }
    });
}

function removeOrder(orderId) {
    $.ajax({
        type: "DELETE",
        url: "/order?orderId=" + orderId,
        success: function (response) {
            location.reload();
        },
        error: function (error) {
            var parsed = $.parseHTML(response.responseText);
            $('#content').replaceWith($(parsed).filter("#errorContent"));
        }
    });
}
