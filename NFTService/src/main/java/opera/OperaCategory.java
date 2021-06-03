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
	
	
	@Id @Column(name="Id_Opera")
	private String idOpera;
	@Id @Column(name="Id_Cat")
	private int idCategory;
	
	
	public String getIdOpera() {
		return idOpera;
	}
	public void setIdOpera(String idOpera) {
		this.idOpera = idOpera;
	}
	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	
}
