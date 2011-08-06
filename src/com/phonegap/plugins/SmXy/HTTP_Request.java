 
package com.phonegap.plugins.SmXy;
 
 
 
import java.io.IOException;
 
import java.util.ArrayList;
 
import java.util.Iterator;
 
import java.util.List;
 
import java.util.Map;
 
 
 
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
 
import org.apache.http.NameValuePair;
 
import org.apache.http.client.ClientProtocolException;
 
import org.apache.http.client.HttpClient;
 
import org.apache.http.client.entity.UrlEncodedFormEntity;
 
import org.apache.http.client.methods.HttpPost;
 
import org.apache.http.impl.client.DefaultHttpClient;
 
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;

import android.util.Log;
 
/**
 
 * A refactoring of the code provided by Moons on this page
 
 * http://www.anddev.org/doing_http_post_w ... t5911.html
 
 *
 
 * Allows to send POST requests to a configurable server
 
 *
 
 * @author Moons, Wadael
 
 *
 
 */
 
public class HTTP_Request {
 
 
 
	public static HttpResponse doPost(String url, Map<String, String> kvPairs) throws ClientProtocolException, IOException {
    HttpParams params = new BasicHttpParams();
    
    params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
    
    HttpClient httpclient = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(url);

    if (kvPairs != null && kvPairs.isEmpty() == false) {
    	
       List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(kvPairs.size());
       
       String k, v;
       Iterator<String> itKeys = kvPairs.keySet().iterator();
       
       while (itKeys.hasNext()) {
          k = itKeys.next();
          Log.d("iterator", "Data: "+ kvPairs.get(k));
          v = kvPairs.get(k);
          nameValuePairs.add(new BasicNameValuePair(k, v));
       }
       
       httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
    }
    HttpResponse response;
    response = httpclient.execute(httppost);
    return response;
 }
 
 
 
}