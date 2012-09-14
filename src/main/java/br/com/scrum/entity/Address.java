package br.com.scrum.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable {

	private static final long serialVersionUID = 7194348807404290417L;
	
	@Column(name = "CITY", length = 60)
	private String city;
	
	@Column(name = "STREET", length = 60)
	private String street;
	
	@Column(name = "UF", length = 4)
	private String uf;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}		
	
}
