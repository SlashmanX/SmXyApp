package com.phonegap.plugins.SmXy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import com.phonegap.api.Plugin;
import com.phonegap.api.PluginResult;
import com.phonegap.api.PluginResult.Status;


public class SmXyAddArticlePlugin extends Plugin {
	public static final String ACTION="add_post";
	@Override
	public PluginResult execute(String action, JSONArray data, String callbackId) {

		PluginResult result = null;
		if (ACTION.equals(action)) {
			try {
				Log.d("SmXyAddArticlePlugin", "In plugin");
				String title = data.getString(0);
				String tags = data.getString(1);
				String article = data.getString(2);
				String url = data.getString(3);
				
				sendArticle(title, tags,article,url);
				result = new PluginResult(Status.OK);
			} catch (JSONException jsonEx) {
				Log.d("SmXyAddArticlePlugin", "Got JSON Exception "
						+ jsonEx.getMessage());
				result = new PluginResult(Status.JSON_EXCEPTION);
			}
		} else {
			result = new PluginResult(Status.INVALID_ACTION);
			Log.d("SmXyAddArticlePlugin", "Invalid action : "+action+" passed");
		}
		return result;
	}
	
	public void sendArticle(String title, String tags, String article, String url) 
    {              
    	Map<String, String> kvPairs = new HashMap<String, String>();
 
    	kvPairs.put("title",title);
    	kvPairs.put("article",article);
    	kvPairs.put("tags",tags);
    	
                               
 
        try {
 
        		HttpResponse re = HTTP_Request.doPost(url, kvPairs);
        		
        		String result = EntityUtils.toString(re.getEntity());
 
        	} catch (ClientProtocolException e) {
 
        		Log.d("SMXY", "CPE");
 
            } catch (IOException e) {
 
            	Log.d("SMXY", "IOE");
 
                // Do something
 
            }
 
        }

}