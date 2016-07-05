package com.dff.cordova.plugin.toughpadapi.barcode.model;

import org.json.JSONException;
import org.json.JSONObject;

public class BarcodeData {
	public static JSONObject toJson(com.panasonic.toughpad.android.api.barcode.BarcodeData barcodedata) throws JSONException {
		JSONObject jsonBarcodeData = new JSONObject();
		
		jsonBarcodeData.put("binaryData", barcodedata.getBinaryData());
		jsonBarcodeData.put("encoding", barcodedata.getEncoding());
		jsonBarcodeData.put("symbology", barcodedata.getSymbology());
		jsonBarcodeData.put("textData", barcodedata.getTextData());
		
		return jsonBarcodeData;
	}	
		
}
