package informare.livrare.beans;

import java.io.Serializable;

public class Articol implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nume;
	private String cantitate;
	private String um;

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getCantitate() {
		return cantitate;
	}

	public void setCantitate(String cantitate) {
		this.cantitate = cantitate;
	}

	public String getUm() {
		return um;
	}

	public void setUm(String um) {
		this.um = um;
	}

	@Override
	public String toString() {
		return "Articol [nume=" + nume + ", cantitate=" + cantitate + ", um=" + um + "]";
	}

}
