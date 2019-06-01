function changeLock(e, formId, closableModal) {
    var $from = $('#'+formId);

    $from.validate();

    if (!$from.valid()) {
        return;
    }
    document.getElementById(closableModal).style.display = 'none';

    var dataPost = "";
    $from.find("input[name]").each(function (index, node) {

    dataPost += node.name;
    dataPost += "=";
    dataPost += node.value;
    dataPost += "&";

    });
    var $this = $(e);

    $.ajax({
        type: "POST",
        url: "/ajaxController",
        data: dataPost,
        dataType: "json",


        success: function (data, textStatus, jqXHR) {
            if (data.success === false) {
                document.getElementById("modal_lock_wrong").style.display = 'inherit';
            } else {
                window.location.reload();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            document.getElementById("modal_lock_error").style.display = 'inherit';
        },
        beforeSend: function (jqXHR, settings) {},
        complete: function (jqXHR, textStatus) {}
    });
}

function changeRole(e, formId, closableModal) {
    var $from = $('#'+formId);

    $from.validate();

    if (!$from.valid()) {
        return;
    }
    document.getElementById(closableModal).style.display = 'none';

    var dataPost = "";
    $from.find("input[name]").each(function (index, node) {
        if (node.name !== "userType" || node.checked ) {
            dataPost += node.name;
            dataPost += "=";
            dataPost += node.value;
            dataPost += "&";
        }
    });
    var $this = $(e);

    $.ajax({
        type: "POST",
        url: "/ajaxController",
        data: dataPost,
        dataType: "json",


        success: function (data, textStatus, jqXHR) {
            if (data.success === false) {
                document.getElementById("modal_role_wrong").style.display = 'inherit';
            } else {
                window.location.reload();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            document.getElementById("modal_role_error").style.display = 'inherit';
        },
        beforeSend: function (jqXHR, settings) {},
        complete: function (jqXHR, textStatus) {}
    });
}

function changeBonuses(e, formId, closableModal) {

    var $from = $('#'+formId);

    $from.validate();

    if (!$from.valid()) {
        return;
    }
    document.getElementById(closableModal).style.display = 'none';

    var bonus = parseInt(document.getElementById("bonus").value);

    if (isNaN(bonus) || bonus < 1 || bonus > 1000) {
        document.getElementById("modal_bonus_wrong").style.display = 'inherit';
        return;
    }

    var dataPost = "";

    $from.find("input[name]").each(function (index, node) {
        if (node.name !== "command" || node.checked) {
            dataPost +=`${node.name}=${node.value}&`;
        }
    });

    var $this = $(e);

    $.ajax({
        type: "POST",
        url: "/ajaxController",
        data: dataPost,
        dataType: "json",


        success: function (data, textStatus, jqXHR) {
            if (data.success === false) {
                document.getElementById("modal_bonus_wrong").style.display = 'inherit';
            } else {
                window.location.reload();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            document.getElementById("modal_bonus_error").style.display = 'inherit';
        },
        beforeSend: function (jqXHR, settings) {},
        complete: function (jqXHR, textStatus) {}
    });
}
