package com.web.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="bill_item")
public class BillItem {
	
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bill_item_id")	
	private long billItemId;
	
	@Column(name="bill_item")
	private String billItem;
	
	@Column(name="bill_item_qty")
	private int billItemQty;
	
	@Column(name="bill_item_rate")
	private float billItemRate;	
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "bill_id")
    public BillDetail billDetail;

	public long getBillItemId() {
		return billItemId;
	}

	public void setBillItemId(long billItemId) {
		this.billItemId = billItemId;
	}

	public String getBillItem() {
		return billItem;
	}

	public void setBillItem(String billItem) {
		this.billItem = billItem;
	}

	public int getBillItemQty() {
		return billItemQty;
	}

	public void setBillItemQty(int billItemQty) {
		this.billItemQty = billItemQty;
	}

	public float getBillItemRate() {
		return billItemRate;
	}

	public void setBillItemRate(float billItemRate) {
		this.billItemRate = billItemRate;
	}

	public BillDetail getBillDetail() {
		return billDetail;
	}

	public void setBillDetail(BillDetail billDetail) {
		this.billDetail = billDetail;
	}

	

}
