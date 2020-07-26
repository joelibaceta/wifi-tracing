'use strict'

const mongoose = require('mongoose');

params = {
  dbUser:  "dbuser",
  dbPassword: "securepassword",
  dbHost: "cluster0.qyvjh.mongodb.net",
  dbPort: "",
  dbName: "wifitracing"
}

const MONGO_URI = `mongodb+srv://${params.dbUser}:${params.dbPassword}@${params.dbHost}:${params.dbPort}/${params.dbName}?retryWrites=true&w=majority`;

const initialize = async () => {

    const options = {
        useCreateIndex: true,
        useNewUrlParser: true,
        useUnifiedTopology: true
    };

    mongoose.Promise = global.Promise;
    mongoose.connect(MONGO_URI, options)
        .then(() => {
            console.log('Mongo Connection Status: Success')
        }).catch(err => {
            console.log(MONGO_URI)
            console.log('Mongo Connection Status:' + err);
        }
    );

}

module.exports = { initialize };