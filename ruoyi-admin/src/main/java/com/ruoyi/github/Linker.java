package com.ruoyi.github;

import it.unisa.bsic.bean.Change;
import it.unisa.bsic.bean.Commit;
import it.unisa.bsic.bean.issues.Issue;
import com.ruoyi.metrics.CosineSimilarity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Linker {

	public List<Commit> linkBugToCommit(Issue pIssue, List<Commit> pCommits) {
		if((pIssue.getStatus().contains("close")) || (pIssue.getStatus().contains("fix"))) {
			return this.linkClosedOrFixedIssue(pIssue, pCommits);
		} else {
			return this.linkOpenIssue(pIssue, pCommits);
		}
	}

	private List<Commit> linkOpenIssue(Issue pIssue, List<Commit> pCommits) {
		List<Commit> relatedCommits=new ArrayList<Commit>();

		for(Commit commit: pCommits) {
			for(Change change: commit.getChanges()) {
				Pattern searcher = Pattern.compile(change.getFile().getPath().substring(0, change.getFile().getPath().length()-5));
				Matcher matcher = searcher.matcher(pIssue.getDescription() + " " +pIssue.getSummary());

				if(matcher.find()) {
					relatedCommits.add(commit);
				}	
			}
		}

		return relatedCommits;
	}

	private List<Commit> linkClosedOrFixedIssue(Issue pIssue, List<Commit> pCommits) {
		CosineSimilarity similarity=new CosineSimilarity();
		List<Commit> relatedCommits=new ArrayList<Commit>();

		String[] issueInfo = new String[2];
		issueInfo[0] = pIssue.getBugId();
		issueInfo[1] = pIssue.getDescription() + " " +pIssue.getSummary();

		for(Commit commit: pCommits) {
			String[] commitInfo=new String[2];
			commitInfo[0] = commit.getSubject();
			commitInfo[1] = commit.getSubject() + " " + commit.getBody();

			try {
				double cosine = similarity.computeSimilarity(issueInfo, commitInfo);
				if(cosine > 0.10) {
					relatedCommits.add(commit);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return relatedCommits;
	}
}
