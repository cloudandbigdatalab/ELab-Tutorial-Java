package com.mycompany.app;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class OpenStack {
	public String KeystoneAuthentication() throws IOException{
		String url="http://192.168.0.67:5000/v2.0/tokens";
		URL object=new URL(url);
		HttpURLConnection con = (HttpURLConnection) object.openConnection();

		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/json; charset=utf8");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("POST");
		//Send request 
		DataOutputStream wr = new DataOutputStream ( con.getOutputStream ()); 
		wr.writeBytes ("{"+
		   " \"auth\":"+ "{"+
		       " \"tenantName\": \"admin\",\"passwordCredentials\":"+" {"+
		           " \"username\": \"admin\",\"password\": \"secrete\" "
		        +"}"+"}"+"}"); 
		wr.flush (); 
		wr.close ();
		return printData(con);
	}
	public void NovaCreateServer(String tokenId) throws IOException{
		// creating server
		String url2= "http://192.168.0.67:8774/v2.1/fe2ac41def8448e2a913388eb3e8481d/servers";
		URL object2=new URL(url2);
		HttpURLConnection con2 = (HttpURLConnection) object2.openConnection();

		con2.setDoOutput(true);
		con2.setDoInput(true);
		con2.setRequestProperty("Content-Type", "application/json; charset=utf8");
		con2.setRequestProperty("Accept", "application/json");
		con2.setRequestProperty("X-Auth-Token",tokenId);
		con2.setRequestProperty("X-OpenStack-Nova-API-Version","2.25");
		con2.setRequestMethod("POST");

		//Send request 
		DataOutputStream wr2 = new DataOutputStream (con2.getOutputStream ()); 
		wr2.writeBytes ("{"+
		   " \"server\":" +"{"+
		      "  \"name\":\"Farhan-server-28\",\"imageRef\": \" http://192.168.0.67:8774/v2.1/fe2ac41def8448e2a913388eb3e8481d/images/71441919-e19b-48ba-b04c-1828dbfabf32\",\"flavorRef\": \"http://192.168.0.67:8774/v2.1/fe2ac41def8448e2a913388eb3e8481d/flavors/1\", \"metadata\": " +"{"+
		          "  \"My Server Name\": \"Apache1\" "+
		        "}"+
		    "}"+
		"}"
				);
		wr2.flush (); 
		wr2.close ();
		System.out.println(printData(con2));
	}
	public String printData(HttpURLConnection con) throws IOException{
		//Get Response    
		InputStream is = con.getInputStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		String line;
		StringBuffer response = new StringBuffer(); 
		while((line = rd.readLine()) != null) {
		 response.append(line);
		 response.append('\r');
		}
		rd.close();
		return response.toString();
	}
}

