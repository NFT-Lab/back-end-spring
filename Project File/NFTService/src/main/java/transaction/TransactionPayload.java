package transaction;

import java.math.BigInteger;

public class TransactionPayload {
	private int idOwner;
	private String price;
	private BigInteger tokenId;
	private String idHash;
	
	public int getIdOwner() {
		return idOwner;
	}
	public void setIdOwner(int idOwner) {
		this.idOwner = idOwner;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public BigInteger getTokenId() {
		return tokenId;
	}
	public void setTokenId(BigInteger tokenId) {
		this.tokenId = tokenId;
	}
	public String getIdHash() {
		return idHash;
	}
	public void setIdHash(String idHash) {
		this.idHash = idHash;
	}
}
