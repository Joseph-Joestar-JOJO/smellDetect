package com.ruoyi.github;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class IssueExtraction {

	public String getJSONIssues(String pAppName) {
		try {
			return this.readWebPageContent("https://api.github.com/search/issues?q="+ pAppName.replaceAll(" ", "%20"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}

	private String readWebPageContent(String pAddress) throws IOException {
		URL url = new URL(pAddress);
		URLConnection yc = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		String inputLine;
		StringBuilder builder = new StringBuilder();

		while ((inputLine = in.readLine()) != null) 
			builder.append(inputLine.trim());
		in.close();

		return builder.toString();
	}
}
