package com.ruoyi.github;

import it.unisa.bsic.bean.issues.Issue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Pattern;

public class IssueConverter {

	public Vector<Issue> convertInIssueBeans(String pJSON) {
		Vector<Issue> issues = new Vector<Issue>();

		Pattern newLine = Pattern.compile("\n");
		Pattern comma = Pattern.compile(";");

		String lines[] = newLine.split(pJSON);
		int indexLine = 0;

		for(String line: lines) {
			if(indexLine == 0) 
				indexLine++;
			else {
				String fields[] = comma.split(line);

				Issue issue = new Issue();
				issue.setBugId(fields[0]);
				issue.setSummary(fields[3]);
				issue.setStatus(fields[4]);
				issue.setDescription(fields[6]);
				issue.setType(fields[11]);

				SimpleDateFormat formatter = new SimpleDateFormat("Mmm/dd/yyyy");

				String year=fields[7].substring(fields[7].length()-4);
				String stringDate = fields[7].substring(4);
				stringDate = stringDate.substring(0, 6);

				stringDate = stringDate+" " + year;
				stringDate = stringDate.replace(stringDate.substring(0, 3), this.convertMonthInNumber(stringDate.substring(0, 3)));
				stringDate = stringDate.replaceAll(" ", "/");

				Date date;
				try {
					date = formatter.parse(stringDate);
					issue.setCreated(this.convertDate(date));
				} catch (ParseException e) {
					e.printStackTrace();
				}

				issues.add(issue);
			}
		}

		return issues;
	}

	private Date convertDate(Date pDate) {
		SimpleDateFormat sdfSource = new SimpleDateFormat("dd/MM/yy");
		String newDate = sdfSource.format(pDate);

		try {
			Date date = sdfSource.parse(newDate);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String convertMonthInNumber(String pValue) {
		if(pValue.equals("Jan")) return "01";
		else if(pValue.equals("Feb")) return "02";
		else if(pValue.equals("Mar")) return "03";
		else if(pValue.equals("Apr")) return "04";
		else if(pValue.equals("May")) return "05";
		else if(pValue.equals("Jun")) return "06";
		else if(pValue.equals("Jul")) return "07";
		else if(pValue.equals("Aug")) return "08";
		else if(pValue.equals("Sep")) return "09";
		else if(pValue.equals("Oct")) return "10";
		else if(pValue.equals("Nov")) return "11";
		else return "12";
	}
}
