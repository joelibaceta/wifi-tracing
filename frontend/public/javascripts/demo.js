setInterval(function () {
    getLastLocaleWifi();
}, 3000);


async function getLastLocaleWifi() {
    let response = await fetch('http://localhost:3000/users');

    if (response.ok) { // if HTTP-status is 200-299
        // get the response body (the method explained below)
        let json = await response.json();
        printPointsInToSVG(json)
    } else {
        alert("HTTP-Error: " + response.status);
    }
}

function printPointsInToSVG(item) {
    var x = item.x - 8.5;
    var y = item.x - 8.5;
    var element = document.getElementById("People");
    element.setAttribute("transform", 'translate(' + item.x + ' ' + item.y + ')');
    /*
    var line1 = document.getElementById("Línea_1");
    line1.setAttribute("transform", 'translate(' + item.x + ' ' + item.x + ')');
    var line2 = document.getElementById("Línea_2");
    line2.setAttribute("transform", 'translate(' + item.x + ' ' + item.x + ')');
    var line3 = document.getElementById("Línea_3");
    line3.setAttribute("transform", 'translate(' + item.x + ' ' + item.x + ')');
    // circle#People(data-name='People 2' cx='8.5' cy='8.5' r='8.5' transform='translate(413 121)' fill='#6db979')
     */

}

