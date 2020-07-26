var express = require('express');
var router = express.Router();

router.get('/save', function(req, res, next) {

  points = req.body.points;

  i1 = points[0]["x"]
  i2 = points[1]["x"]
  i3 = points[2]["x"]
  j1 = points[0]["y"]
  j2 = points[1]["y"]
  j3 = points[2]["y"]

  res.send('respond with a resource');
});

module.exports = router;