package informare.livrare.beans;

import com.google.maps.model.LatLng;

public class Client {

	private LatLng coords;
	private String nume;

	public LatLng getCoords() {
		return coords;
	}

	public void setCoords(LatLng coords) {
		this.coords = coords;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	@Override
	public String toString() {
		return "Client [coords=" + coords + ", nume=" + nume + "]";
	}
	
	

}
