package informare.livrare.beans;

public class DateMasina {

	private double vitezaMedie;
	private String tipMasina;

	public double getVitezaMedie() {
		return vitezaMedie;
	}

	public void setVitezaMedie(double vitezaMedie) {
		this.vitezaMedie = vitezaMedie;
	}

	public String getTipMasina() {
		return tipMasina;
	}

	public void setTipMasina(String tipMasina) {
		this.tipMasina = tipMasina;
	}

	@Override
	public String toString() {
		return "DateMasina [vitezaMedie=" + vitezaMedie + ", tipMasina=" + tipMasina + "]";
	}

}
