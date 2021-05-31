package user;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="users")
public class User {
	@Id @Column(name="ID")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="surname")
	private String surname;
	
	@Column(name="email")
	private String email;
	
	@Column(name="date_of_birth")
	private LocalDate dob;
	
	@JsonProperty( value = "password", access = JsonProperty.Access.WRITE_ONLY)
	@Column(name="password")
	private String password;
	
	@Column(name="wallet")
	private String wallet;
	
	//Contructor ----------------------------------------------------------------------------------------------
	public User() {
	
	}
	public User(String name, String surname, String email, LocalDate dob, String password) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.dob = dob;
		this.password = password;
	}
	public User(String name, String surname, String email, LocalDate dob, String password, String wallet) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.dob = dob;
		this.password = password;
		this.wallet = wallet;
	}
	
	//getter Setter ----------------------------------------------------------------------------------------------
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWallet() {
		return wallet;
	}

	public void setWallet(String wallet) {
		this.wallet = wallet;
	}
	
	//methods ----------------------------------------------------------------------------------------------
	public int getAge() {
		return this.dob.until(LocalDate.now()).getYears();
	}
	
}
