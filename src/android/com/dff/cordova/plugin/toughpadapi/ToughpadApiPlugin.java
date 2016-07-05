package com.dff.cordova.plugin.toughpadapi;

import java.util.HashMap;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

import com.dff.cordova.plugin.common.CommonPlugin;
import com.dff.cordova.plugin.common.action.CordovaAction;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.toughpadapi.action.ToughpadApiAction;
import com.panasonic.toughpad.android.api.ToughpadApi;
import com.panasonic.toughpad.android.api.ToughpadApiListener;

public class ToughpadApiPlugin extends CommonPlugin implements ToughpadApiListener {
	private static final String LOG_TAG = "com.dff.cordova.plugin.packagepay.ToughpadApiPlugin";
	private HashMap<String, Class<? extends ToughpadApiAction>> actions = new HashMap<String, Class<? extends ToughpadApiAction>>();
	private ToughpadApiBarcodeListener barcodeListener; 
	
	public ToughpadApiPlugin() {
		super();
	}
	
   /**
	* Called after plugin construction and fields have been initialized.
	*/
	@Override
	public void pluginInitialize() {
		super.pluginInitialize();
		this.barcodeListener = new ToughpadApiBarcodeListener();
		ToughpadApi.initialize(this.cordova.getActivity().getApplicationContext(), this);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		this.barcodeListener.destroy();
		ToughpadApi.destroy();
	}
	
	@Override
	public void onApiConnected(int arg0) {
		this.barcodeListener.initialize();
		CordovaPluginLog.i(LOG_TAG, "ToughpadApi connected");
	}

	@Override
	public void onApiDisconnected() {
		CordovaPluginLog.i(LOG_TAG, "ToughpadApi disconnected");
	}
   
    /**
    * Executes the request.
    *
    * This method is called from the WebView thread.
    * To do a non-trivial amount of work, use:
    * cordova.getThreadPool().execute(runnable);
    *
    * To run on the UI thread, use:
    * cordova.getActivity().runOnUiThread(runnable);
    *
    * @param action The action to execute.
    * @param args The exec() arguments.
    * @param callbackContext The callback context used when calling back into JavaScript.
    * @return Whether the action was valid.
    */
	@Override
    public boolean execute(String action
    		, final JSONArray args
    		, final CallbackContext callbackContext)
        throws JSONException {
		CordovaPluginLog.i(LOG_TAG, "call for action: " + action + "; args: " + args);
		
    	CordovaAction cordovaAction = null;

    	if (ToughpadApiBarcodeListener.ACTION_NAME.equals(action)) {
    		this.barcodeListener.setCallBack(callbackContext);
    		return true;
    	}

    	if (cordovaAction != null) {
    		cordova.getThreadPool().execute(cordovaAction);
            return true;
    	}    	

        return super.execute(action, args, callbackContext);
    }
}