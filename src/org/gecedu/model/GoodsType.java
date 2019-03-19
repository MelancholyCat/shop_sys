package org.gecedu.model;

import java.util.Date;

public class GoodsType {
	
	private Integer gtId;
	private String name;
	private Integer level;
	private Integer upperId;
	private Date createTime;
	private String imageName;
	private Integer orderNum;
	private String imageUrl;
	private String upperName;
	
	public GoodsType(Integer gtId, String name, Integer level, Integer upperId, Date createTime, String imageName,
			Integer orderNum, String imageUrl) {
		this.gtId = gtId;
		this.name = name;
		this.level = level;
		this.upperId = upperId;
		this.createTime = createTime;
		this.imageName = imageName;
		this.orderNum = orderNum;
		this.imageUrl = imageUrl;
	}
	public Integer getGtId() {
		return gtId;
	}
	public void setGtId(Integer gtId) {
		this.gtId = gtId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getUpperId() {
		return upperId;
	}
	public void setUpperId(Integer upperId) {
		this.upperId = upperId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getUpperName() {
		return upperName;
	}
	public void setUpperName(String upperName) {
		this.upperName = upperName;
	}

}
