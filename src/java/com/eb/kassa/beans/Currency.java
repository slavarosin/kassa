package com.eb.kassa.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Entity
@Table(name = "currencies")
@Transactional
public class Currency implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private String sign;

	private String code;

	private Boolean def;

	public Boolean getDef() {
		return def;
	}

	public void setDef(Boolean def) {
		this.def = def;
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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return getName() != null ? getName().toString() : super.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Currency))
			return false;

		Currency curr = (Currency) obj;

		return curr.getId().equals(getId());
	}

	@Override
	public int hashCode() {
		return ObjectUtils.nullSafeHashCode(id);
	}
}
