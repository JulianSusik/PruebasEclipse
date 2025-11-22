package ar.edu.unlam.pb2.gestiondecriaturaselementales;

public class GestionDeCriaturas {
	public static void interactuar(CriaturaElemental criatura1, CriaturaElemental criatura2) {

	    if (criatura1 == null || criatura2 == null)
	        throw new IllegalArgumentException("No se pueden ingresar criaturas nulas");

	    
	    if (criatura1.esAncestral() && !criatura2.esAncestral()) {
	        criatura1.setNivelEnergia(criatura1.getNivelEnergia() + 20);
	        criatura2.setNivelEnergia(Math.max(0, criatura2.getNivelEnergia() - 15));
	        
	    }else if (criatura2.esAncestral() && !criatura1.esAncestral()) {
	        criatura2.setNivelEnergia(criatura2.getNivelEnergia() + 20);
	        criatura1.setNivelEnergia(Math.max(0, criatura1.getNivelEnergia() - 15));
	     
	    }

	   
	    if (criatura1.getAfinidadElemental() == criatura2.getAfinidadElemental()) {
	        criatura1.setNivelEnergia(criatura1.getNivelEnergia() + 10);
	        criatura2.setNivelEnergia(criatura2.getNivelEnergia() + 10);
	   
	    }

	    
	    if (sonOpuestas(criatura1.getAfinidadElemental(), criatura2.getAfinidadElemental())) {
	        criatura1.setEstado(EstadoEmocional.INESTABLE);
	        criatura2.setEstado(EstadoEmocional.INESTABLE);
	    }
	}
	
	private static boolean sonOpuestas(Elementos a, Elementos b) {
	    return (a == Elementos.AGUA && b == Elementos.FUEGO) ||
	           (a == Elementos.FUEGO && b == Elementos.AGUA) ||
	           (a == Elementos.AIRE && b == Elementos.TIERRA) ||
	           (a == Elementos.TIERRA && b == Elementos.AIRE);
	}
}
