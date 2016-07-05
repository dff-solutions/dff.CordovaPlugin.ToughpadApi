package com.dff.cordova.plugin.toughpadapi.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;

import com.dff.cordova.plugin.common.action.CordovaAction;

public class ToughpadApiAction extends CordovaAction {

	public ToughpadApiAction(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova) {
		super(action, args, callbackContext, cordova);
	}

}
