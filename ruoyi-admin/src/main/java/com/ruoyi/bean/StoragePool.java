package com.ruoyi.bean;

import it.unisa.bsic.bean.Storage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class StoragePool implements Serializable{


	private static final long serialVersionUID = -8246189630636657516L;
	
	//Snapshot
	public List<Storage> blobSnapshots;
	public List<Storage> cdsbpSnapshots;
	public List<Storage> complexClassSnapshots;
	public List<Storage> longMethodSnapshots;
	public List<Storage> spaghettiCodeSnapshots;

	//Trend
	public List<Storage> blobTrends;
	public List<Storage> cdsbpTrends;
	public List<Storage> complexClassTrends;
	public List<Storage> longMethodTrends;
	public List<Storage> spaghettiCodeTrends;

	//Time
	public Storage blobTime;
	public Storage cdsbpTime;
	public Storage complexClassTime;
	public Storage longMethodTime;
	public Storage spaghettiCodeTime;
	
	
	public boolean save(String path){
		try{
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in "+path);
		}catch(IOException i){
			i.printStackTrace();
		}
		return true;
	}

}
