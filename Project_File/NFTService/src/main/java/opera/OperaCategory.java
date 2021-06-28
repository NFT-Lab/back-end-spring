package opera;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="opecat")
@IdClass(OpCatId.class)
public class OperaCategory {
	
	
	@Id @Column(name="opera")
	private String opera;
	@Id @Column(name="cat")
	private int cat;
	
	public OperaCategory() {}
	
	public OperaCategory(String idOpera, int idCategory) {
		this.opera = idOpera;
		this.cat = idCategory;
	}
	
	public String getIdOpera() {
		return opera;
	}
	public void setIdOpera(String idOpera) {
		this.opera = idOpera;
	}
	public int getIdCategory() {
		return cat;
	}
	public void setIdCategory(int idCategory) {
		this.cat = idCategory;
	}
	
}
