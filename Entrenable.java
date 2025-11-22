package ar.edu.unlam.pb2.gestiondecriaturaselementales;

public interface Entrenable {
	public void entrenar() throws LimiteEnergiaSuperadoException;

	void entrenar(Integer puntos) throws LimiteEnergiaSuperadoException; 


}
