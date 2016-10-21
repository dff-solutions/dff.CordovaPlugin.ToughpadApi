package com.dff.cordova.plugin.toughpadapi.barcode.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.panasonic.toughpad.android.api.barcode.BarcodeException;

public class BarcodeReader {
	public static final String LOG_TAG = "com.dff.cordova.plugin.toughpadapi.barcode.model.BarcodeReader";

	public static JSONObject toJson(com.panasonic.toughpad.android.api.barcode.BarcodeReader barcodeReader)
	        throws JSONException, BarcodeException {
		JSONObject jsonBarcodeReader = new JSONObject();

		jsonBarcodeReader.put("deviceName", barcodeReader.getDeviceName());
		jsonBarcodeReader.put("isEnabled", barcodeReader.isEnabled());
		jsonBarcodeReader.put("isExternal", barcodeReader.isExternal());
		jsonBarcodeReader.put("isHardwareTriggerAvailable", barcodeReader.isHardwareTriggerAvailable());

		if (barcodeReader.isEnabled()) {
			try {
				int barcodeType = barcodeReader.getBarcodeType();
				String barcodeTypeName;

				switch (barcodeType) {
					case com.panasonic.toughpad.android.api.barcode.BarcodeReader.BARCODE_TYPE_CAMERA:
						barcodeTypeName = "BARCODE_TYPE_CAMERA";
						break;
					case com.panasonic.toughpad.android.api.barcode.BarcodeReader.BARCODE_TYPE_ONE_DIMENSIONAL:
						barcodeTypeName = "BARCODE_TYPE_ONE_DIMENSIONAL";
						break;
					case com.panasonic.toughpad.android.api.barcode.BarcodeReader.BARCODE_TYPE_TWO_DIMENSIONAL:
						barcodeTypeName = "BARCODE_TYPE_TWO_DIMENSIONAL";
						break;
					default:
						barcodeTypeName = "";
						break;
				}

				jsonBarcodeReader.put("barcodeType", barcodeType);
				jsonBarcodeReader.put("barcodeTypeName", barcodeTypeName);

				jsonBarcodeReader.put("batteryCharge", barcodeReader.getBatteryCharge());
				jsonBarcodeReader.put("deviceFirmwareVersion", barcodeReader.getDeviceFirmwareVersion());
				jsonBarcodeReader.put("deviceSerialNumber", barcodeReader.getDeviceSerialNumber());
				jsonBarcodeReader.put("isBatteryCharging", barcodeReader.isBatteryCharging());
				jsonBarcodeReader.put("isHardwareTriggerEnabled", barcodeReader.isHardwareTriggerEnabled());
			}
			catch (IllegalStateException e) {
				CordovaPluginLog.e(LOG_TAG, e.getMessage(), e);
			}
		}

		return jsonBarcodeReader;
	}
}
