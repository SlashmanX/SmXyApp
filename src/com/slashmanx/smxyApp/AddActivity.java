package com.slashmanx.smxyApp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;

import com.phonegap.DroidGap;
import com.phonegap.plugins.SmXy.HTTP_Request;

import android.util.Log;
import android.webkit.WebView;

public class AddActivity 
{
    private WebView mAppView;
    private DroidGap mGap;

    public AddActivity(DroidGap gap, WebView view)
    {
        mAppView = view;
        mGap = gap;
    }    
    public String sendArticle(String title, String tags, String article, String url) 
    {              
    	Map<String, String> kvPairs = new HashMap<String, String>();
 
    	kvPairs.put("title",title);
    	kvPairs.put("article",article);
    	kvPairs.put("tags",tags);
    	
    	String result = "Error!";
                               
 
        try {
 
        		HttpResponse re = HTTP_Request.doPost(url, kvPairs);
        		
        		result = EntityUtils.toString(re.getEntity());
 
        	} catch (ClientProtocolException e) {
 
        		Log.d(this.getClass().getSimpleName(), "CPE");
 
            } catch (IOException e) {
 
            	Log.d(this.getClass().getSimpleName(), "IOE");
 
                // Do something
 
            }
            
            return result;
 
        }
}