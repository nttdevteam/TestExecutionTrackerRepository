package com.nttdata.testexecutiontracker.entity;

import java.util.List;

public class JGrid {

	private String page;
	private int total;
	private String records;
	private List<Data> rows;
	private UserData userdata;

	public class UserData
	{
		private int amount;
		private int tax;
		private int total;
		private String name;
		public int getAmount() {
			return amount;
		}
		public void setAmount(int amount) {
			this.amount = amount;
		}
		public int getTax() {
			return tax;
		}
		public void setTax(int tax) {
			this.tax = tax;
		}
		public int getTotal() {
			return total;
		}
		public void setTotal(int total) {
			this.total = total;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	
		public UserData(int amount, int tax, int total, String name)
		{
			this.amount=amount;
			this.tax=tax;
			this.total=total;
			this.name=name;
		}
	}

	public class Data
	{
		private String id;
		private List<String> cell;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public List<String> getCell() {
			return cell;
		}
		public void setCell(List<String> cell) {
			this.cell = cell;
		}
		
		public Data(String id, List cell)
		{
			this.id=id;
			this.cell=cell;
		}
	}
	
	
	
	
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public String getRecords() {
		return records;
	}
	public void setRecords(String records) {
		this.records = records;
	}

	public List getRows() {
		return rows;
	}
	public void setRows(List<Data> rows) {
		this.rows = rows;
	}


	public UserData getUserData() {
		return userdata;
	}
	public void setUserData(UserData userdata) {
		this.userdata = userdata;
	}

	
	
}
