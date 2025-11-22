package ar.edu.unlam.pb2.gestiondecriaturaselementales;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reportes {
	public List<CriaturaElemental> listarTodasLasCriaturas(List<MaestroElemental> maestros) {
		if (maestros == null || maestros.isEmpty()) {
			throw new IllegalArgumentException("No se puede ingresar una lista sin elementos");
		}
		List<CriaturaElemental> resultado = new ArrayList<>();

		for (MaestroElemental m : maestros) {
			resultado.addAll(m.getCriaturas().values());
		}

		return resultado;
	}

	public CriaturaElemental getCriaturaConMayorEnergia(List<MaestroElemental> maestros) {
		if (maestros == null || maestros.isEmpty()) {
			throw new IllegalArgumentException("No se puede ingresar una lista sin elementos");
		}
		CriaturaElemental maxEnergia = null;
		for (MaestroElemental m : maestros) {// recorro los maestros
			for (CriaturaElemental c : m.getCriaturas().values()) { // recorro las criaturas de los maestros
				if (maxEnergia == null || c.getNivelEnergia() > maxEnergia.getNivelEnergia()) {
					maxEnergia = c;
				}
			}

		}
		return maxEnergia;

	}

	public String mostrarMaestroConMasCriaturasTransformadas(List<MaestroElemental> maestros) {
		if (maestros.isEmpty() || maestros == null)
			throw new IllegalArgumentException("No se puede ingresar una lista sin elementos");
		String maestro = maestros.get(0).getNombre();

		for (int i = 0; i < maestros.size() - 1; i++) {
			if (maestros.get(i + 1).getCantidadDeCriaturas() > maestros.get(i).getCantidadDeCriaturas()) {
				maestro = maestros.get(i + 1).getNombre();
			}
		}
		return maestro;
	}

	public Map<Elementos, Integer> obtenerCantidadPorAfinidad(List<MaestroElemental> maestros) {
		Map<Elementos, Integer> criaturas = new HashMap<>();

		for (MaestroElemental m : maestros) {
			for (CriaturaElemental c : m.getCriaturas().values()) {
	            Elementos afinidad = c.getAfinidadElemental();
	            criaturas.put(afinidad, criaturas.getOrDefault(afinidad, 0) + 1);
			}
		}
		return criaturas;
	}
}
