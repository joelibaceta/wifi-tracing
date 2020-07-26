var express = require('express');
var router = express.Router();

router.get('/save', function(req, res, next) {

  points = req.body.points;

  

  res.send('respond with a resource');
});

module.exports = router;