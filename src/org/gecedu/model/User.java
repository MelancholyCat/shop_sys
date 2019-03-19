package org.gecedu.model;

import java.util.Date;

public class User {
	
	private Integer id;
	private String name;
	private String phone;
	private String password;
	private Integer gender;
	private String province;
	private String city;
	private Date createTime;
	private Date updateTime;
	
	public User() {
	}
	
	
	
	public User(Integer id, String name, String phone,Integer gender, String province, String city,
			Date createTime, Date updateTime) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.gender = gender;
		this.province = province;
		this.city = city;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}



	public User(Integer id, String name, String phone, String password, Integer gender, String province, String city) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.password = password;
		this.gender = gender;
		this.province = province;
		this.city = city;
	}

	public User(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
	

}
