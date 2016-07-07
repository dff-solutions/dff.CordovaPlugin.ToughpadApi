package com.dff.cordova.plugin.toughpadapi;

import java.util.concurrent.TimeoutException;

import org.json.JSONException;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.AbstractPluginListener;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.panasonic.toughpad.android.api.barcode.BarcodeData;
import com.panasonic.toughpad.android.api.barcode.BarcodeException;
import com.panasonic.toughpad.android.api.barcode.BarcodeListener;
import com.panasonic.toughpad.android.api.barcode.BarcodeReader;
import com.panasonic.toughpad.android.api.barcode.BarcodeReaderManager;

public class ToughpadApiBarcodeListener extends AbstractPluginListener implements BarcodeListener {
	public static final String LOG_TAG = "dff.cordova.plugin.toughpadapi.ToughpadApiBarcodeListener"; 
	public static final String ACTION_NAME = "onBarcodeRead";
	
	public void initialize() {
		CordovaPluginLog.d(LOG_TAG, "initialize");
		
		for (BarcodeReader barcodeReader : BarcodeReaderManager.getBarcodeReaders()) {
			CordovaPluginLog.d(LOG_TAG, "listen to " + barcodeReader.getDeviceName());
			try {
				barcodeReader.enable(10000);
				barcodeReader.addBarcodeListener(this);
			}
			catch (BarcodeException e) {
				CordovaPluginLog.e(LOG_TAG, e.getMessage(), e);
			}
			catch (TimeoutException e) {
				CordovaPluginLog.e(LOG_TAG, e.getMessage(), e);
			}			
		}
	}
	
	public void destroy() {
		CordovaPluginLog.d(LOG_TAG, "destroy");
		
		for (BarcodeReader barcodeReader : BarcodeReaderManager.getBarcodeReaders()) {
			CordovaPluginLog.d(LOG_TAG, "remove from " + barcodeReader.getDeviceName());
			barcodeReader.removeBarcodeListener(this);
		}
		
		if (this.callback != null) {
			this.callback.success();
		}
	};

	@Override
	public void onRead(BarcodeReader barcodeReader, BarcodeData barcodeData) {
		CordovaPluginLog.d(LOG_TAG, "onRead: " + barcodeReader.getDeviceName() + " - " + barcodeData.getSymbology());
		
		JSONObject jsonOnRead = new JSONObject();
		
		try {
			jsonOnRead.put("barcodeReader", com.dff.cordova.plugin.toughpadapi.barcode.model.BarcodeReader.toJson(barcodeReader));
			jsonOnRead.put("barcodeData", com.dff.cordova.plugin.toughpadapi.barcode.model.BarcodeData.toJson(barcodeData));
			
			this.sendPluginResult(jsonOnRead);
		}
		catch (JSONException e) {
			CordovaPluginLog.e(LOG_TAG, e.getMessage(), e);
			this.sendPluginResult(e);
		}
		catch (BarcodeException e) {
			CordovaPluginLog.e(LOG_TAG, e.getMessage(), e);
			this.sendPluginResult(e);
		}
	}

}
