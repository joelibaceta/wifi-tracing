
const mongoose = require('mongoose');
const PositionSchema =  require('./schemas/position.schema')

const Position = mongoose.model('Position', PositionSchema);

module.exports = Position;
