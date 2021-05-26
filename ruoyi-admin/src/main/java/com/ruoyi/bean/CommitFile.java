package com.ruoyi.bean;

import it.unisa.bsic.bean.Commit;

public class CommitFile {
	private it.unisa.bsic.bean.Commit commit;
	private String file;

	public it.unisa.bsic.bean.Commit getCommit() {
		return commit;
	}
	public void setCommit(Commit commit) {
		this.commit = commit;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
}
