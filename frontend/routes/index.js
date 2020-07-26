var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {


  let points = [
    { translate: '94 88', color: '#f60600', name: '1'},
    { translate: '385 338', color: '#f60600', name: '2'},
    { translate: '708 330', color: '#f60600', name: '3'}
  ];

  res.render('index', { title: 'Express', points });
});

module.exports = router;
