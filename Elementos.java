package ar.edu.unlam.pb2.gestiondecriaturaselementales;

public enum Elementos {
	AGUA("Agua", 0), FUEGO("Fuego", 1), AIRE("Aire", 0), TIERRA("Tierra", 1);

    private String nombre ="";
   private Integer afinidad;

    Elementos(String nombre, Integer afinidad) {
        this.nombre = nombre;
    }

    public String toString() {
        return nombre;
    }

	public Integer getAfinidad() {
		// TODO Auto-generated method stub
		return afinidad;
	}
}
