package transaction;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "transaction")
@IdClass(TransactionIdKey.class)
public class Transaction {
	
	@Id @Column(name="id_hash")
	private String idHash;
	@Column(name="buyer")
	private int userBuyer;
	@Column(name="seller")
	private int userSeller;
	@Transient
	private String walletBuyer;
	@Transient
	private String walletSeller;
	@Transient
	private BigInteger tokenId;
	@Column(name="price")
	private String price;
	@Id @Column(name="timestamp")
	private String timestamp;
	
	//getter-setter methods ---------------------------------------------------------------------
	public String getIdHash() {
		return idHash;
	}
	public void setIdHash(String idHash) {
		this.idHash = idHash;
	}
	public int getUserBuyer() {
		return userBuyer;
	}
	public void setUserBuyer(int userBuyer) {
		this.userBuyer = userBuyer;
	}
	public int getUserSeller() {
		return userSeller;
	}
	public void setUserSeller(int userSeller) {
		this.userSeller = userSeller;
	}
	public String getWalletBuyer() {
		return walletBuyer;
	}
	public void setWalletBuyer(String walletBuyer) {
		this.walletBuyer = walletBuyer;
	}
	public String getWalletSeller() {
		return walletSeller;
	}
	public void setWalletSeller(String walletSeller) {
		this.walletSeller = walletSeller;
	}
	public BigInteger getTokenId() {
		return tokenId;
	}
	public void setTokenId(BigInteger tokenId) {
		this.tokenId = tokenId;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
}
