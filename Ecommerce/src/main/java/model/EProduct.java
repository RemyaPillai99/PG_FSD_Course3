package model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class EProduct {
	/*ID bigint primary key auto_increment, 
name varchar(100), 
price decimal(10,2),
date_added timestamp default now()) ;*/
	private BigInteger ID;
	private String name;
	private BigDecimal price;
	private Date date;
	public BigInteger getID() {
		return ID;
	}
	public void setID(BigInteger iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
	
	

}
