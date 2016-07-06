package com.dff.cordova.plugin.toughpadapi.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.panasonic.toughpad.android.api.barcode.BarcodeException;
import com.panasonic.toughpad.android.api.barcode.BarcodeReader;
import com.panasonic.toughpad.android.api.barcode.BarcodeReaderManager;

public class GetBarcodeReaders extends ToughpadApiAction {
	public static final String ACTION_NAME = "getBarcodeReaders";

	public GetBarcodeReaders(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova) {
		super(action, args, callbackContext, cordova);
	}

	@Override
	public void run() {
		super.run();
		
		try {
			JSONArray jsonBarcodeReaders = new JSONArray();
			
			for (BarcodeReader barcodeReader : BarcodeReaderManager.getBarcodeReaders()) {
				jsonBarcodeReaders.put(com.dff.cordova.plugin.toughpadapi.barcode.model.BarcodeReader.toJson(barcodeReader));
			}			
			
			this.callbackContext.success(jsonBarcodeReaders);
		}
		catch(JSONException e) {
			CordovaPluginLog.e(this.getClass().getName(), e.getMessage(), e);
			this.callbackContext.error(e.getMessage());
		}
		catch(BarcodeException e) {
			CordovaPluginLog.e(this.getClass().getName(), e.getMessage(), e);
			this.callbackContext.error(e.getMessage());			
		}
	}
}
