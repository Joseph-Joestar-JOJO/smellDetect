package com.ruoyi.issues;

import it.unisa.bsic.bean.issues.Comment;

import java.util.Date;
import java.util.Vector;

public class Issue {

	private String bugId;
	private String type;
	private String summary;
	private String description;
	private String status;
	private String resolution;
	private Date created;
	private Date updated;
	private Date resolved;
	private String priority;
	private String link;
	private Vector<String> changedFiles;
	private Vector<Comment> comments;
	
	
	public String getBugId() {
		return bugId;
	}
	public void setBugId(String bugId) {
		this.bugId = bugId;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResolution() {
		return resolution;
	}
	public String getPriority(){
		return priority;
	}
	public void setPriority(String priority){
		this.priority=priority;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	@Override
	public String toString(){
		return this.bugId + " " + this.summary + " " + this.status + " " + 
				this.resolution + " " + this.created + " " + this.updated;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Date getResolved() {
		return resolved;
	}
	public void setResolved(Date resolved) {
		this.resolved = resolved;
	}
	public Vector<String> getChangedFiles() {
		return changedFiles;
	}
	public void setChangedFiles(Vector<String> changedFiles) {
		this.changedFiles = changedFiles;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Vector<Comment> getComments() {
		return comments;
	}
	public void setComments(Vector<Comment> comments) {
		this.comments = comments;
	}
	
	@Override
	public boolean equals(Object o){
		return this.description.equals(((Issue)o).getDescription());
	}
	
}
