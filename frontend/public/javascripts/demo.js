console.log('gaaaaa!');
    ej1 = document.getElementById("mylienzo"); //Asigno a una variable el elemento del html que voy a usar
    lienzo1 = ej1.getContext("2d"); //Alisto el canvas para que funcione
    lienzo1.lineWidth = 2; //Defino el ancho de la linea en pixeles
    lienzo1.strokeStyle = '#000000'; //Defino el color en hexagesimal

// PRINT EJE X
    lienzo1.beginPath(); // Pongo el lápiz
    lienzo1.moveTo(150, 0); // lo ubicó para iniciar el dibujo
    lienzo1.lineTo(150, 300); // trazo la linea hasta este punto
    lienzo1.stroke(); // levanto el lápiz
    lienzo1.closePath(); // me alisto para realizar otra parte del dibujo

//PRINT EJE Y
    lienzo1.beginPath(); // Pongo el lápiz
    lienzo1.moveTo(10, 150 );// lo ubicó para iniciar el dibujo
    lienzo1.lineTo(300, 150);// trazo la linea hasta este punto
    lienzo1.stroke();// levanto el lápiz
    lienzo1.closePath();// me alisto para realizar otra parte del dibujo

