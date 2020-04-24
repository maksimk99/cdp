function formAdd() {
    $.ajax({
        type: "POST",
        url: "/messages/post",
        data: $('#formAdd').serialize(),
        success: function (response) {
            $('#listMessages').replaceWith(createListMessages(response));
        },
        error: function (error) {
            alert("Error!!!\n" + error.responseText);
        }
    });
};

function formUpdate() {
    $.ajax({
        type: "PUT",
        url: "/messages/put",
        data: $('#formUpdate').serialize(),
        success: function (response) {
            $('#listMessages').replaceWith(createListMessages(response));
        },
        error: function (error) {
            alert("Error!!!\n" + error.responseText);
        }
    });
};

function formDelete() {
    $.ajax({
        type: "DELETE",
        url: "/messages/delete",
        data: $('#formDelete').serialize(),
        success: function (response) {
            $('#listMessages').replaceWith(createListMessages(response));
        },
        error: function (error) {
            alert("Error!!!\n" + error.responseText);
        }
    });
};

function createListMessages(response) {
    $('#viewsAmount').text(response[0]);
    var listMessages = "<table id=\"listMessages\" style=\"border: none\">";
    $.each(response[1], function (index, value) {
        listMessages += "\n<tr style=\"text-align: left\">\n" +
            "            <th>" + index + "</th>\n" +
            "            <th style=\"padding-left: 12px\">" + value + "</th>\n" +
            "        </tr>\n"
    });
    listMessages += "</table>";
    return listMessages;
}


