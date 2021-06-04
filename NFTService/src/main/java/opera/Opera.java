package opera;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
@Table(name="opera")
public class Opera {
	//info to be saved in DB--------------------------------------------
	@Id @Column(name="Id")
	private String id;
	
	@Column(name="Title")
	private String title;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="Author")
	private String author;
	
	@Column(name="Price")
	private double price;
	
	@Column(name="Currency")
	private String currency;
	
	@Column(name="Status")
	private boolean status;

	@Column(name="Path")
	private String path;
	
	@Column(name="Type")
	private EnumType type;
	
	@Column(name="User_Id")
	@JsonProperty( value = "userId", access = JsonProperty.Access.WRITE_ONLY)
	private int userId;
	
	//info to send to front end ---------------------------------------------------------
	@Transient
	private String owner; 
	@JsonProperty( value = "encoding", access = JsonProperty.Access.WRITE_ONLY)
	@Transient
	private String encoding;
	@Transient
	private List<Category> listCategory;
	
	//constructor -----------------------------------------------------------------------
	public Opera(){};
	public Opera(String title, boolean status, double price, int userId) {
		this.title = title;
		this.status = status;
		this.price = price;
		this.userId = userId;
	}
	
	
	//methods getter/setter -------------------------------------------------------------
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public EnumType getType() {
		return type;
	}
	public void setType(EnumType type) {
		this.type = type;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	//methods for param to be saved
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	public List<Category> getCategory(){
		return listCategory;
	}
	public void setCategory(List<Category> listCategory) {
		this.listCategory = listCategory;
	}
		
}
