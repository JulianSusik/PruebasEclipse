package ar.edu.unlam.pb2.gestiondecriaturaselementales;

public class CriaturaDomesticada extends CriaturaElemental {
	public final Integer ENERGIA_GANADA = 10;
	
	public CriaturaDomesticada(String nombre) {
		super(nombre);
		this.nivelEnergia = generador.nextInt(151);
	}
	
    public CriaturaDomesticada(String nombre, Elementos elemento, Integer nivelEnergia, EstadoEmocional estado) {
    	
        super(nombre, elemento, nivelEnergia, estado);

       
        if (estado == EstadoEmocional.INESTABLE) {
            throw new IllegalArgumentException("Una criatura domesticada no puede ser inestable.");
        }
    }

    @Override
    public void entrenar() {
       
        if (this.nivelEnergia == this.MAXIMO_ENERGIA)
            throw new LimiteEnergiaSuperadoException(
                    "La energia esta en su nivel maximo. No se puede seguir entrenando");

        if (this.nivelEnergia + ENERGIA_GANADA > this.MAXIMO_ENERGIA) {
            nivelEnergia = this.MAXIMO_ENERGIA;
        } else {
            nivelEnergia += ENERGIA_GANADA;
        }
    }
   

    @Override
    public void entrenar(Integer puntos) throws LimiteEnergiaSuperadoException {
        if (this.nivelEnergia == this.MAXIMO_ENERGIA) {
            throw new LimiteEnergiaSuperadoException("La energía ya está en su máximo.");
        }

        if (this.nivelEnergia + puntos > this.MAXIMO_ENERGIA) {
            throw new LimiteEnergiaSuperadoException("La energía no puede superar el máximo de 200.");
        }

        this.nivelEnergia += puntos;

        
        if (this.estado == EstadoEmocional.INESTABLE) {
            this.estado = EstadoEmocional.TRANQUILA; 
        }
    }
    
    @Override
    public void pacificar() {
        if (this.estado == EstadoEmocional.TRANQUILA) throw new CriaturaYaPacificaException("No se puede pacificar una criatura que no se encuentra inestable");
        estado = EstadoEmocional.TRANQUILA;
    }
}
