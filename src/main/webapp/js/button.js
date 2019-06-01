function clickMinus(id, btnTxt) {

    let counter = $(`#counter${id}`);
    let number = parseInt(counter.text());
    let dataString = `productId=${id}&command=REMOVE_PRODUCT`;


    $.ajax({
        type: "POST",
        url: "/ajaxController",
        data: dataString,
        dataType: "json",

        success: function (data, textStatus, jqXHR) {
            if (number === 1) {
                $(`#pod${id}`).html(`<div class='btn-group mr-2 pod' id='pod${id}' role='group' aria-label='First group'>` +
                    `<button type='button'` +
                    ` class='w3-button w3-black w3-border w3-border-black w3-round-large w3-text-amber' id='basket${id}'` +
                    ` onclick='clickBasket(${id},"${btnTxt}")'>` +
                    ` ${btnTxt}</button></div>`);
            } else {
                counter.text(number - 1);
            }
        },

        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
        },

        beforeSend: function (jqXHR, settings) {
        },
        complete: function (jqXHR, textStatus) {
        }

    });
}

function clickPlus(id) {
    let counter = $(`#counter${id}`);
    let number = parseInt(counter.text());
    let dataString = `productId=${id}&command=ADD_PRODUCT`;

    $.ajax({
        type: "POST",
        url: "/ajaxController",
        data: dataString,
        dataType: "json",

        success: function (data, textStatus, jqXHR) {
            counter.text(number + 1);
        },

        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
        },

        beforeSend: function (jqXHR, settings) {
        },
        complete: function (jqXHR, textStatus) {
        }

    });
}

function clickBasket(id, btnTxt) {
    let dataString = `productId=${id}&command=ADD_PRODUCT`;

    $.ajax({
        type: "POST",
        url: "/ajaxController",
        data: dataString,
        dataType: "json",

        success: function (data, textStatus, jqXHR) {
            $(`#pod${id}`).html(`<button type='button' id='minus${id}' onclick='clickMinus(${id},"${btnTxt}")' style="height: 38px" class='w3-button w3-black w3-text-amber border-right-round'>-</button>` +
                `<button type='button' style="height: 38px" class='w3-button w3-black w3-text-amber'>` +
                `<p id='counter${id}'>1</p></button>` +
                `<button type='button' id='plus${id}' onclick='clickPlus(${id})' style="height: 38px" class='w3-button w3-black w3-text-amber border-left-round'>+</button>`);

        },

        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
        },

        beforeSend: function (jqXHR, settings) {
        },
        complete: function (jqXHR, textStatus) {
        }

    });
}

function clickMinusUpdate(id, btnTxt) {

    let counter = $(`#counter${id}`);
    let number = parseInt(counter.text());
    let dataString = `productId=${id}&command=REMOVE_PRODUCT`;


    $.ajax({
        type: "POST",
        url: "/ajaxController",
        data: dataString,
        dataType: "json",

        success: function (data, textStatus, jqXHR) {
            if (number === 1) {
                $(`#pod${id}`).html(`<div class='btn-group mr-2 pod' id='pod${id}' role='group' aria-label='First group'>` +
                    `<button type='button'` +
                    ` class='w3-button w3-black w3-border w3-border-black w3-round-large w3-text-amber count' id='basket${id}'` +
                    ` onclick='clickBasketUpdate(${id},"${btnTxt}")'>` +
                    ` ${btnTxt}</button></div>`);
            } else {
                counter.text(number - 1);
            }
            updateOrderPrice();
        },

        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
        },

        beforeSend: function (jqXHR, settings) {
        },
        complete: function (jqXHR, textStatus) {
        }

    });
}

function clickPlusUpdate(id) {
    let counter = $(`#counter${id}`);
    let number = parseInt(counter.text());
    let dataString = `productId=${id}&command=ADD_PRODUCT`;

    $.ajax({
        type: "POST",
        url: "/ajaxController",
        data: dataString,
        dataType: "json",

        success: function (data, textStatus, jqXHR) {
            counter.text(number + 1);
            updateOrderPrice();
        },

        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
        },

        beforeSend: function (jqXHR, settings) {
        },
        complete: function (jqXHR, textStatus) {
        }

    });
}

function clickBasketUpdate(id, btnTxt) {
    let dataString = `productId=${id}&command=ADD_PRODUCT`;

    $.ajax({
        type: "POST",
        url: "/ajaxController",
        data: dataString,
        dataType: "json",

        success: function (data, textStatus, jqXHR) {
            $(`#pod${id}`).html(`<button type='button' id='minus${id}' onclick='clickMinusUpdate(${id},"${btnTxt}")' style="height: 38px" class='w3-button w3-black w3-text-amber border-right-round'>-</button>` +
                `<button type='button' style="height: 38px" class='w3-button w3-black w3-text-amber'>` +
                `<p id='counter${id}' class="count">1</p></button>` +
                `<button type='button' id='plus${id}' onclick='clickPlusUpdate(${id})' style="height: 38px" class='w3-button w3-black w3-text-amber border-left-round'>+</button>`);
            updateOrderPrice();
        },

        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
        },

        beforeSend: function (jqXHR, settings) {
        },
        complete: function (jqXHR, textStatus) {
        }

    });
}

function updateOrderPrice() {
    let sum = 0;

    let countList = $(".count");
    let priceList = $(".price");

    for (let i = 0; i < countList.length; i++) {
        let count = parseInt(countList[i].innerText);
        count = !isNaN(count) ? count : 0;
        let price = parseFloat(priceList[i].innerText.replace("$", ""));
        sum += price * count;
    }
    // $('.orderPrice').html(sum+"$");
    $('.orderPrice').html(`${sum}$`+
        `<input type='hidden' value='${sum}' name='orderPrice'>`);
}