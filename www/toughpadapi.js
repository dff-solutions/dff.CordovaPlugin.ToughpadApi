/**
 * JavaScript interface to abstract
 * the usage of the ToughpadApi.
 *
 * @module com/dff/cordova/plugins/ToughpadApi
 */

'use strict';

var cordova = require('cordova');
var feature = "ToughpadApi";
var self = {};

var actions = ["onLog", "onBarcodeRead", "getBarcodeReader"];

function createActionFunction (action) {
    return function (success, error, args) {
        cordova.exec(success, error, feature, action, [args]);
    }
}

actions.forEach(function (action) {
    self[action] = createActionFunction(action);
});

module.exports = self;
