package com.ruoyi.bean;

public class Artefact {
	
	private String name;
	private String history;
	
	public Artefact(String name, String history){
		this.name = name;
		this.history = history;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getHistory() {
		return history;
	}
	
	public void setHistory(String history) {
		this.history = history;
	}
	
	public void appendHistory(String newContent){
		history += newContent;
	}
	
	@Override
	public boolean equals(Object obj) {
		Artefact other = (Artefact) obj;
		if(name.equalsIgnoreCase(other.getName())){
			return true;
		} else {
			return false;
		}
	}
	
	
}
