'use strict'
const mongoose = require('mongoose');
const { Long } = require('bson');
const Schema = mongoose.Schema; 

let PositionSchema = new Schema({
    id: {type: String, required: [true, 'The id is required.']},
    x: {type: String, required: [true, 'The x is required.']},
    y: {type: String, required: [true, 'The y is required.']},
    timestamp: {type: Long},
    created_at: {type: String}
}); 

module.exports = PositionSchema;