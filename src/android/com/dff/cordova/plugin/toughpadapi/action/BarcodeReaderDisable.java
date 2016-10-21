package com.dff.cordova.plugin.toughpadapi.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.panasonic.toughpad.android.api.barcode.BarcodeReader;

public class BarcodeReaderDisable extends ToughpadApiAction {
	private static final String TAG = "com.dff.cordova.plugin.toughpadapi.action.BarcodeReaderDisable";
	public static final String ACTION_NAME = "barcodeReaderDisable";

	public static final String JSON_ARG_NAME = "name";
	public static final String JSON_ARG_ENABLE = "enable";
	public static final String[] JSON_ARGS = { JSON_ARG_NAME, JSON_ARG_ENABLE };

	public BarcodeReaderDisable(String action, JSONArray args, CallbackContext callbackContext,
	        CordovaInterface cordova) {
		super(action, args, callbackContext, cordova);
	}

	@Override
	public void run() {
		super.run();

		try {
			JSONObject jsonArgs = super.checkJsonArgs(this.args, JSON_ARGS);

			String name = jsonArgs.getString(JSON_ARG_NAME);
			boolean enable = jsonArgs.getBoolean(JSON_ARG_ENABLE);

			BarcodeReader barcodeReader = super.findBarcodeReaderByName(name);

			if (barcodeReader != null) {
				barcodeReader.setHardwareTriggerEnabled(enable);
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
