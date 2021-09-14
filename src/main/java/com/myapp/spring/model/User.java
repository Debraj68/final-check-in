package com.myapp.spring.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="devopsUsers")
public class User {
   
     @Id
    //@GeneratedValue(strategy =GenerationType.AUTO)
     @Column(name="PNR")
	 private Integer pnr;
     
     @Column(name="SEAT_NO")
     @NotNull
	 private String seatNo;
     
     @Column(name="BAGGAGE_WEIGHT")
     @NotNull
	 private Integer baggageWeight;
     
     @Column(name="EMAIL")
     @NotNull
	 private String email;
     
     @Column(name="FIRST_NAME")
     @NotNull
	 private String firstName;
     
     @Column(name="LAST_NAME")
     @NotNull
	 private String lastName;
	 
	 
	
	public User() {
		
	}



	public User(@NotNull Integer pnr, @NotNull String seatNo, @NotNull Integer baggageWeight ,
			@NotNull String email,@NotNull String firstName,@NotNull String lastName) {
		this.pnr = pnr;
		this.seatNo = seatNo;
		this.baggageWeight = baggageWeight;
		this.email = email;
		this.firstName=firstName;
		this.lastName=lastName;
	}


    public Integer getPnr() {
		return pnr;
	}



	public void setPnr(Integer pnr) {
		this.pnr = pnr;
	}



	public String getSeatNo() {
		return seatNo;
	}



	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}



	public Integer getBaggageWeight() {
		return baggageWeight;
	}



	public void setBaggageWeight(Integer baggageWeight) {
		this.baggageWeight = baggageWeight;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	@Override
	public int hashCode() {
		return Objects.hash(pnr,seatNo,baggageWeight,email,firstName,lastName);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		return Objects.equals(pnr, other.pnr) && Objects.equals(seatNo, other.seatNo)
				&& Objects.equals(baggageWeight, other.baggageWeight) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [pnr=");
		builder.append(pnr);
		builder.append(", seatNo=");
		builder.append(seatNo);
		builder.append(", baggageWeight =");
		builder.append(baggageWeight);
		builder.append(", email=");
		builder.append(email);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
