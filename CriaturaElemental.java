package ar.edu.unlam.pb2.gestiondecriaturaselementales;

import java.util.Random;

abstract class CriaturaElemental implements Entrenable{
	protected String nombre;
	protected Integer nivelEnergia;
	protected Elementos afinidad;
	protected EstadoEmocional estado;
	protected final Integer MAXIMO_ENERGIA = 200;
	protected final Integer MINIMO_ENERGIA = 1;

	protected final Random generador = new Random();

	public CriaturaElemental(String nombre) {
		this.nombre = nombre;
		if(nombre == null) throw new IllegalArgumentException("El nombre de la criatura no puede ser nulo");
		this.afinidad = Elementos.values()[generador.nextInt(Elementos.values().length)];
		this.estado = EstadoEmocional.values()[generador.nextInt(EstadoEmocional.values().length)];
		this.nivelEnergia = generador.nextInt(MAXIMO_ENERGIA) + 1;
	}

	public CriaturaElemental(String nombre, Elementos elemento, Integer nivelEnergia, EstadoEmocional estado) {
		this.nombre = nombre;
		this.afinidad = elemento;
		this.nivelEnergia = nivelEnergia;
		this.estado = estado;
		if (nivelEnergia < MINIMO_ENERGIA || nivelEnergia > MAXIMO_ENERGIA) {
			throw new IllegalArgumentException("No se puede supera los 200 punto de energia");
		}
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getNivelEnergia() {
		return nivelEnergia;
	}

	public Elementos getAfinidadElemental() {
		return afinidad;
	}

	public EstadoEmocional getEstado() {
		return estado;
	}

	public abstract void entrenar() throws LimiteEnergiaSuperadoException;

	public void setNivelEnergia(Integer nivelEnergia) {
		if (this.nivelEnergia < MINIMO_ENERGIA || this.nivelEnergia > MAXIMO_ENERGIA) 
			throw new IllegalArgumentException("Una criatura no puede tener menos de 0 de energia ni mas de 200");
		
		this.nivelEnergia = nivelEnergia;
	}
	
	public abstract void pacificar();

	public void setEstado(EstadoEmocional estado) {
		this.estado = estado;
	}

	public boolean esAncestral() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String getClase() {
        return this.getClass().getSimpleName();
    }
	
	
}