package com.mycompany.app;
import java.io.IOException;

import org.json.JSONObject;

public class App {
	public static void main(String[] args) throws IOException{
		OpenStack openStack = new OpenStack();

		JSONObject obj = new JSONObject(openStack.KeystoneAuthentication());
		String tokenId= obj.getJSONObject("access").getJSONObject("token").getString("id");
		
		openStack.NovaCreateServer(tokenId);
		
	}
}

