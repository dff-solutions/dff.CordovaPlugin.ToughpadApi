package com.dff.cordova.plugin.toughpadapi.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.panasonic.toughpad.android.api.barcode.BarcodeReader;

public class BarcodeReaderEnable extends ToughpadApiAction {
	private static final String TAG = "com.dff.cordova.plugin.toughpadapi.action.BarcodeReaderEnable";
	public static final String ACTION_NAME = "barcodeReaderEnable";

	public static final String JSON_ARG_NAME = "name";
	public static final String JSON_ARG_TIMEOUT = "timeout";
	public static final String[] JSON_ARGS = { JSON_ARG_NAME };

	public BarcodeReaderEnable(String action, JSONArray args, CallbackContext callbackContext,
	        CordovaInterface cordova) {
		super(action, args, callbackContext, cordova);
	}

	@Override
	public void run() {
		super.run();

		try {
			JSONObject jsonArgs = super.checkJsonArgs(this.args, JSON_ARGS);

			String name = jsonArgs.getString(JSON_ARG_NAME);
			long timeout = jsonArgs.optLong(JSON_ARG_TIMEOUT, 10000);

			BarcodeReader barcodeReader = super.findBarcodeReaderByName(name);

			if (barcodeReader != null) {
				barcodeReader.enable(timeout);
				this.callbackContext.success();
			}
			else {
				throw new Exception("barcode reader not found: " + name);
			}
		}
		catch (JSONException e) {
			CordovaPluginLog.e(TAG, e.getMessage(), e);
			this.callbackContext.error(e.getMessage());
		}
		catch (Exception e) {
			CordovaPluginLog.e(TAG, e.getMessage(), e);
			this.callbackContext.error(e.getMessage());
		}
	}
}
