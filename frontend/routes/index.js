var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {


  let points = [
    { translate: '94 88', color: '#f60600', name: '1'},
    { translate: '385 338', color: '#f60600', name: '2'},
    { translate: '500 200', color: '#f60600', name: '3'},
    { translate: '100 330', color: '#f60600', name: '3'},
    { translate: '200 330', color: '#f60600', name: '3'},
    { translate: '300 330', color: '#f60600', name: '3'},
    { translate: '400 330', color: '#f60600', name: '3'},
    { translate: '500 330', color: '#f60600', name: '3'},
    { translate: '708 330', color: '#f60600', name: '3'}
  ];

  res.render('index', { title: 'Express', points });
});

module.exports = router;
