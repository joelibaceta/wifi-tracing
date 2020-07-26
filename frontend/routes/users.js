var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res, next) {

  // res.send('respond with a resource');
  const HARD_CODED_ROUTER_POSITIONS =  {"x": 7, "y": 4};
  res.json( HARD_CODED_ROUTER_POSITIONS);

});

module.exports = router;
