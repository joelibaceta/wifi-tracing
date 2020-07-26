var express = require('express');
var router = express.Router();


HARD_CODED_ROUTER_POSITIONS = [
  {"x": 4.5, "y": 4},
  {"x": 0, "y": 0},
  {"x": 7, "y": 4},
] 


 
router.post('/save', function(req, res, next) { 

  points = HARD_CODED_ROUTER_POSITIONS // For demo purpose only
  
  distances = req.body.distances;

  x1 = points[0]["x"]
  x2 = points[1]["x"]
  x3 = points[2]["x"]
  y1 = points[0]["y"]
  y2 = points[1]["y"]
  y3 = points[2]["y"]
  r1 = parseFloat(distances[0])
  r2 = parseFloat(distances[1])
  r3 = parseFloat(distances[2])
 
  A = 2*x2 - 2*x1
  B = 2*y2 - 2*y1
  C = r1**2 - r2**2 - x1**2 + x2**2 - y1**2 + y2**2
  D = 2*x3 - 2*x2
  E = 2*y3 - 2*y2
  F = r2**2 - r3**2 - x2**2 + x3**2 - y2**2 + y3**2

  x = (C*E - F*B) / (E*A - B*D)
  y = (C*D - A*F) / (B*D - A*E)

  console.log("x: " + x + " y: " + y)

  res.json({x: x, y: y});
});

module.exports = router;