package user;

import java.time.LocalDate;

public class UserServiceResponse {
	private String name;
	private String surname;
	private String email;
	private LocalDate dob;
	private String wallet;
	
	public UserServiceResponse() {}
	
	public UserServiceResponse(String name, String surname, String email, LocalDate dob, String wallet) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.dob = dob;
		this.wallet = wallet;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getWallet() {
		return wallet;
	}
	public void setWallet(String wallet) {
		this.wallet = wallet;
	}
}
