setInterval(function () {
    getLastLocaleWifi();
}, 5000);


async function getLastLocaleWifi() {
    // let response = await fetch('http://localhost:3000/positions/lasted');
    let response = await fetch('http://198.199.73.28:3000/positions/lasted');

    if (response.ok) { // if HTTP-status is 200-299
        // get the response body (the method explained below)
        let json = await response.json();
        printPointsInToSVG(json)
    } else {
        alert("HTTP-Error: " + response.status);
    }
}

function printPointsInToSVG(item) {
     var element = document.getElementById("People");

     var x =  item.x * 25;
     var y =  item.y * 7.5;

    element.setAttribute("transform", 'translate(' + x + ' ' + y + ')');
}

