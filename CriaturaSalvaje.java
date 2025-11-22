package ar.edu.unlam.pb2.gestiondecriaturaselementales;

public class CriaturaSalvaje extends CriaturaElemental {
	private final Integer AUMENTO_ALEATORIO_MAXIMO = 51;
	
	
	public CriaturaSalvaje(String nombre) {
		super(nombre);
		this.nivelEnergia = generador.nextInt(151);
	}
	
	public CriaturaSalvaje(String nombre, Elementos elemento, Integer nivelEnergia, EstadoEmocional estado) {
		super(nombre, elemento, nivelEnergia, estado);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void entrenar() throws LimiteEnergiaSuperadoException {
		Integer valorAAumentar = generador.nextInt(AUMENTO_ALEATORIO_MAXIMO);
		Integer auxiliar = nivelEnergia;
		if (this.nivelEnergia == this.MAXIMO_ENERGIA)
			throw new LimiteEnergiaSuperadoException("La energia esta en su nivel maximo. No se puede seguir entrenando");
		if ((auxiliar+valorAAumentar)> this.MAXIMO_ENERGIA) {
			throw new LimiteEnergiaSuperadoException("No se puede superar los 200 puntos de energia.");
		}
		this.nivelEnergia += valorAAumentar;
		
	}

	@Override
	public void entrenar(Integer energia) throws LimiteEnergiaSuperadoException {
		Integer auxiliar = nivelEnergia;
		if (this.nivelEnergia == this.MAXIMO_ENERGIA)
			throw new LimiteEnergiaSuperadoException("La energia esta en su nivel maximo. No se puede seguir entrenando");
		if ((auxiliar+energia)> this.MAXIMO_ENERGIA) {
			throw new LimiteEnergiaSuperadoException("No se puede superar los 200 puntos de energia.");
		}
		this.nivelEnergia += energia;
	}

	
	@Override
    public void pacificar() {
        if (this.estado == EstadoEmocional.TRANQUILA)
            throw new CriaturaYaPacificaException("No se puede pacificar una criatura que no se encuentra inestable");

        if (generador.nextInt(4) != 3)
            estado = EstadoEmocional.TRANQUILA;
    }
}