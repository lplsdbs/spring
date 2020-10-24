package com.ccb.portal.entity.Credit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 角色实体
 * @author linyu
 * 2018-08-21
 *st
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xmltest")
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement
	private String id;
	
	/**
	 * 角色编号
	 */
	@XmlElement
	private String rlNo;
	
	/**
	 * 角色名称
	 */
	@XmlElement
	private String rlNm;
	
	public Role() {
		super();
	}
	public Role(String id, String rlNo, String rlNm) {
		super();
		this.id = id;
		this.rlNo = rlNo;
		this.rlNm = rlNm;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRlNo() {
		return rlNo;
	}
	public void setRlNo(String rlNo) {
		this.rlNo = rlNo;
	}
	public String getRlNm() {
		return rlNm;
	}
	public void setRlNm(String rlNm) {
		this.rlNm = rlNm;
	}

	@Override
	public String toString() {
		return "Role{" +
				"id='" + id + '\'' +
				", rlNo='" + rlNo + '\'' +
				", rlNm='" + rlNm + '\'' +
				'}';
	}
}
