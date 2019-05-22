function clickMinus(id,btnTxt) {

    let counter = $(`#counter${id}`);
    let number = parseInt(counter.text());


    if (number === 1) {
        $(`#pod${id}`).html(`<button type='button' id='basket' class='w3-button w3-black w3-border w3-border-black w3-round-large w3-text-amber' onclick="clickBasket(${id},'${btnTxt}')">${btnTxt}</button>`);
    } else {
        counter.text(number-1);
    }

}

function clickPlus(id) {
    let counter = $(`#counter${id}`);
    let number = parseInt(counter.text());
    counter.text(number+1);
}

function clickBasket(id,btnTxt) {
    $(`#pod${id}`).html(`<button type='button' id='minus${id}' onclick='clickMinus(${id},"${btnTxt}")' style="height: 38px" class='w3-button w3-black w3-text-amber border-right-round'>-</button>` +
        `<button type='button' style="height: 38px" class='w3-button w3-black w3-text-amber'>` +
        `<p id='counter${id}'>1</p></button>` +
        `<button type='button' id='plus${id}' onclick='clickPlus(${id})' style="height: 38px" class='w3-button w3-black w3-text-amber border-left-round'>+</button>`);
}