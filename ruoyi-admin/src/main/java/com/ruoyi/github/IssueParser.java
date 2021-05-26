package com.ruoyi.github;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IssueParser {

	public String parse(String pContent) {

		if(pContent.equals("{\"total_count\":0,\"items\":[]}")) {
			return "";
		} else {
			try {
				pContent = pContent.substring(pContent.indexOf("["), pContent.length());
				return parseContent(pContent);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return "";
	}

	private String parseContent(String pContent) throws ParseException {
		String issueFile="ID;Milestone;Number;Title;State;Assignee;Body;Created_At;Closed_At;Comments_URL;HTML_URL;Type\n";

		try {
			JSONArray array= new JSONArray(pContent);

			for(int i=0; i<array.length(); i++) {
				JSONObject jApp = array.getJSONObject(i);

				if(jApp.has("id")) {
					issueFile+=jApp.getInt("id")+";";
				} else issueFile+="NO_ID_SPECIFIED;";

				if(jApp.has("milestone")) {
					issueFile+=parseMilestone(""+jApp.get("milestone"))+";";
				} else issueFile+="NO_VERSION_SPECIFIED;";			

				if(jApp.has("number")) {
					issueFile+=jApp.getInt("number")+";";
				} else issueFile+="NO_NUMBER_SPECIFIED;";

				if(jApp.has("title")) {
					issueFile+=jApp.getString("title").replaceAll(";", " ")+";";
				} else issueFile+="NO_TITLE_SPECIFIED;";

				if(jApp.has("state")) {
					issueFile+=jApp.getString("state").replaceAll(";", " ")+";";
				} else issueFile+="NO_STATE_SPECIFIED;";

				if(jApp.has("assignee")) {
					issueFile+=parseAssignee(""+jApp.get("assignee")).replaceAll(";", " ")+";";
				} else issueFile+="NO_ASSIGNEE_SPECIFIED;";

				if(jApp.has("body")) {
					issueFile+=jApp.getString("body").replaceAll("\n", " ").replaceAll("\r", "").replaceAll(";", " ")+";";
				} else issueFile+="NO_BODY_SPECIFIED;";

				if(jApp.has("created_at")) {
					issueFile+=parseDate(""+jApp.get("created_at"))+";";
				} else issueFile+="NO_DATE_SPECIFIED;";

				if(jApp.has("closed_at")) {
					issueFile+=parseDate(""+jApp.get("closed_at"))+";";
				} else issueFile+="NO_DATE_SPECIFIED;";

				if(jApp.has("comments_url")) {
					issueFile+=jApp.getString("comments_url").replaceAll(";", " ")+";";
				} else issueFile+="NO_COMMENTS;";

				if(jApp.has("html_url")) {
					issueFile+=jApp.getString("html_url").replaceAll(";", " ")+";";
				} else issueFile+="NO_URL;";
				
				if(jApp.has("labels")) {
					JSONArray labels= jApp.getJSONArray("labels");
					
					if(labels.length() == 0) {
						issueFile+="enhancement\n";
					} else {
						for(int k=0; k<labels.length(); k++) {
							JSONObject obj = labels.getJSONObject(k);
							
							if(obj.has("name")) {
								issueFile+=obj.getString("name").replaceAll(";", " ")+"\n";
							} 
						} 
					}
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		Pattern newLine = Pattern.compile("\n");
		String[] lines = newLine.split(issueFile);
		String cleanedContent="";
		
		for(String line: lines) {
			if(line.equals("enhancement") || line.equals("new-feature")) {
				// do nothing...
			} else {
				cleanedContent+=line+"\n";
			}
		}
		
		return cleanedContent;
	}

	private String parseDate(String pDate) throws ParseException {
		if(pDate.equals("null"))
			return "NO_DATE_SPECIFIED";

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		date = df.parse(pDate.substring(0, 10));

		return ""+date;
	}

	private String parseAssignee(String pAssignee) {
		String assignee="";

		if(pAssignee.equals("null"))
			return "NO_ASSIGNEE_SPECIFIED";

		assignee = pAssignee.substring(pAssignee.indexOf("\"login\":"), pAssignee.length());
		assignee = assignee.substring(0, assignee.indexOf(","));

		assignee = assignee.replace("login", "");
		assignee = assignee.replaceAll("\"", "");
		assignee = assignee.replaceAll(":", "");

		return assignee;
	}

	private String parseMilestone(String pMilestone) {
		String milestone="";

		if(pMilestone.equals("null"))
			return "NO_VERSION_SPECIFIED";


		milestone = pMilestone.substring(pMilestone.indexOf("\"title\":"), pMilestone.length());
		milestone = milestone.substring(0, milestone.indexOf(","));

		milestone = milestone.replace("title", "");
		milestone = milestone.replaceAll("\"", "");

		return milestone;
	}
}
