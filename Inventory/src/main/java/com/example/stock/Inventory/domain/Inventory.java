package com.example.stock.Inventory.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "inventory")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Inventory implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	 
	 
	 public Inventory(String code, String rent, String batch, int stock, String deal, String free, String mrp,
			String rate, Date exp, String company, String supplier) {
		super();
		this.code = code;
		this.rent = rent;
		this.batch = batch;
		this.stock = stock;
		this.deal = deal;
		this.free = free;
		this.mrp = mrp;
		this.rate = rate;
		this.exp = exp;
		this.company = company;
		this.supplier = supplier;
	}
	 @Id
	@Column(name = "code")
	  private String code;
	 @Column(name = "rent")
	  private String rent;
	 @Column(name = "batch")
	  private String batch;
	 @Column(name = "stock")
	  private int stock;
	 @Column(name = "deal")
	  private String deal;
	 @Column(name = "free")
	  private String free;
	 @Column(name = "mrp")
	  private String mrp;
	 @Column(name = "rate")
	  private String rate;
	 @Column(name = "exp")	
	  private Date exp;
	 @Column(name = "company")
	  private String company;
	 @Column(name = "supplier")
	  private String supplier;
	 
}

