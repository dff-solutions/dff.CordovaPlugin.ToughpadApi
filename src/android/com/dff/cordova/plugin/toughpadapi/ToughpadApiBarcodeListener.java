package com.dff.cordova.plugin.toughpadapi;

import java.util.List;

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
	
	protected List<BarcodeReader> barcodeReaders;
	
	public void initialize() {
		CordovaPluginLog.d(LOG_TAG, "initialize");
		this.barcodeReaders = BarcodeReaderManager.getBarcodeReaders();
		
		for (BarcodeReader barcodeReader : this.barcodeReaders) {
			CordovaPluginLog.d(LOG_TAG, "initialize " + barcodeReader.getDeviceName());
			barcodeReader.clearBarcodeListener();
			barcodeReader.addBarcodeListener(this);
		}
	}
	
	public void destroy() {
		this.callback.success();
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
