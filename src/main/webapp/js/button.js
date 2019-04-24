
function clickMinus(id) {

    let counter = $(`#counter${id}`);
    let number = parseInt(counter.text());


    if (number === 1) {
        $(`#pod${id}`).html(`<button type='button' id='basket' class='btn btn-secondary' onclick="clickBasket(${id})" >В корзину</button>`);

    } else {
        counter.text(number-1);
    }

}

function clickPlus(id) {
    let counter = $(`#counter${id}`);
    let number = parseInt(counter.text());
    counter.text(number+1);
}

function clickBasket(id) {
    $(`#pod${id}`).html(`<button type='button' id='minus${id}' onclick='clickMinus(${id})' style="height: 38px" class='btn btn-secondary'>-</button>` +
        `<button type='button' style="height: 38px" class='btn btn-secondary'>` +
        `<p id='counter${id}'>1</p></button>` +
        `<button type='button' id='plus${id}' onclick='clickPlus(${id})' style="height: 38px" class='btn btn-secondary'>+</button>`);
}