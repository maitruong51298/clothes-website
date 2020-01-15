/*
	@author:Quang Truong
	@date: Jan 15, 2020
*/

package com.jwatgroupb.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bill")
public class BillEntity extends BaseEntity{
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	private UserEntity userEntity;

	@OneToMany(mappedBy = "billEntity")
	private List<BillDetailEntity> listBillDetails = new ArrayList<>();
	
	@Column(name = "totalmoney")
	private float totalMoney;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "deliverydate")
	private Date deliveryDate;
	
	@Column(name = "receiver")
	private String receiver;
	
	@Column(name= "address")
	private String address;
	
	@Column(name="phonenumber")
	private String phonenumber;
	
	@Column(name = "note")
	private String note;

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public List<BillDetailEntity> getListBillDetails() {
		return listBillDetails;
	}

	public void setListBillDetails(List<BillDetailEntity> listBillDetails) {
		this.listBillDetails = listBillDetails;
	}


	public float getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(float totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
}
