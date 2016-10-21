package com.dff.cordova.plugin.toughpadapi.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;

import com.dff.cordova.plugin.common.action.CordovaAction;
import com.panasonic.toughpad.android.api.barcode.BarcodeReader;
import com.panasonic.toughpad.android.api.barcode.BarcodeReaderManager;

public class ToughpadApiAction extends CordovaAction {

	public ToughpadApiAction(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova) {
		super(action, args, callbackContext, cordova);
	}

	/**
	 * Looks up all barcode readers and returns the one with the given name.
	 *
	 * @param deviceName
	 *            Name of the barcode reader to find.
	 * @return Found barcode reader or null if not found.
	 */
	protected BarcodeReader findBarcodeReaderByName(String deviceName) {
		for (BarcodeReader barcodeReader : BarcodeReaderManager.getBarcodeReaders()) {
			if (deviceName.equals(barcodeReader.getDeviceName())) {
				return barcodeReader;
			}
		}

		return null;
	}
}
