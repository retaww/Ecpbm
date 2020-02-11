package com.ecpbm.pojo;

import java.util.HashSet;
import java.util.Set;

public class Functions implements Comparable<Functions> {
	private int id;
	private String name;
	private int parentid;
	private boolean isleaf;
	private Set ais = new HashSet();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public boolean isIsleaf() {
		return isleaf;
	}
	public void setIsleaf(boolean isleaf) {
		this.isleaf = isleaf;
	}
	public Set getAis() {
		return ais;
	}
	public void setAis(Set ais) {
		this.ais = ais;
	}

	public int compareTo(Functions o) {
		// TODO Auto-generated method stub
		return ((Integer)this.getId()).compareTo((Integer)(o.getId()));
	}
	
}
