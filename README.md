# dff.CordovaPlugin.ToughpadApi

## Reference

http://business.panasonic.com/support-computerstablets-downloads-sdk

## Installation

    ionic plugin add https://github.com/dff-solutions/dff.CordovaPlugin.ToughpadApi.git
    
## API
Touchpad JavaScript api is available via global `ToughpadApi`


### onBarcodeRead
    /**
     * @function
     * @name onBarcodeRead
     * @param {function} success Callback for success
     * @param {function} error Callback for error
     * @param {Object} args Named arguments
     */
    ToughpadApi
        .onBarcodeRead(function (barcode) {
            console.log(barcode);
        }, function(reason) {
            console.error(reason);
        });
    
### getBarcodeReaders
    /**
     * @function
     * @name getBarcodeReaders
     * @param {function} success Callback for success
     * @param {function} error Callback for error
     * @param {Object} args Named arguments
     */
    ToughpadApi
        .getBarcodeReaders(function (readers) {
            console.log(readers);
        }, function(reason) {
            console.error(reason);
        });
    
### barcodeReaderDisable
    /**
     * @function
     * @name barcodeReaderDisable
     * @param {function} success Callback for success
     * @param {function} error Callback for error
     * @param {Object} args Named arguments
     * @param {string} args.name Name of barcode scanner to use
     */
    ToughpadApi
        .barcodeReaderDisable(function () {
            console.log("disabled");
        }, function(reason) {
            console.error(reason);
        }, {
            name: "some scanner"
        });
    
### barcodeReaderEnable
    /**
     * @function
     * @name barcodeReaderEnable
     * @param {function} success Callback for success
     * @param {function} error Callback for error
     * @param {Object} args Named arguments
     * @param {string} args.name Name of barcode scanner to use
     */
    ToughpadApi
        .barcodeReaderEnable(function () {
            console.log("enabled");
        }, function(reason) {
            console.error(reason);
        }, {
            name: "some scanner"
        });
    
### barcodeReaderPressSoftwareTrigger
    /**
     * @function
     * @name barcodeReaderPressSoftwareTrigger
     * @param {function} success Callback for success
     * @param {function} error Callback for error
     * @param {Object} args Named arguments
     * @param {string} args.name Name of barcode scanner to use
     * @param {boolean} args.press If software trigger should be enabled or disabled
     */
    ToughpadApi
        .barcodeReaderPressSoftwareTrigger(function () {
            console.log();
        }, function(reason) {
            console.error(reason);
        }, {
            name: "some scanner",
            press: true
        });
    
### barcodeReaderSetHardwareTriggerEnabled
    /**
     * @function
     * @name barcodeReaderSetHardwareTriggerEnabled
     * @param {function} success Callback for success
     * @param {function} error Callback for error
     * @param {Object} args Named arguments
     * @param {string} args.name Name of barcode scanner to use
     * @param {boolean} args.enable If hardware trigger should be enabled or disabled
     */
    ToughpadApi
        .barcodeReaderSetHardwareTriggerEnabled(function () {
            console.log("enabled/disabled");
        }, function(reason) {
            console.error(reason);
        }, {
            name: "some scanner",
            enable: true
        });
