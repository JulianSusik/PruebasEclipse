package ar.edu.unlam.pb2.gestiondecriaturaselementales;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MaestroElemental {
	private String nombre;
	private Integer nivelMaestria;
	private Elementos afinidadPrincipal;
	private Map<String, CriaturaElemental> criaturasBajoControl = new HashMap<>();
	private Map<CriaturaElemental, Elementos> criaturasOrganizadasPorElementos = new HashMap<>();

	protected final Random generador = new Random();

	public MaestroElemental(String nombre) {
		this.nombre = nombre;
		this.nivelMaestria = generador.nextInt(50) + 1;
		this.afinidadPrincipal = Elementos.values()[generador.nextInt(Elementos.values().length)];
	}

	public MaestroElemental(String nombre, Integer nivelMaestria, Elementos afinidadPrincipal) {
		this.nombre = nombre;
		this.nivelMaestria = nivelMaestria;
		this.afinidadPrincipal = afinidadPrincipal;
	}

	public String getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}

	public Integer getNivelMaestria() {
		// TODO Auto-generated method stub
		return nivelMaestria;
	}

	public Integer getCantidadDeCriaturas() {
		return criaturasBajoControl.size();
	}

	public void asignarCriatura(CriaturaElemental nuevaCriatura) throws CriaturaYaExistenteException {
		if (nuevaCriatura == null)
			throw new IllegalArgumentException("No se deben ingresar argumentos nulos.");
		if (criaturasBajoControl.containsKey(nuevaCriatura.getNombre()))
			throw new CriaturaYaExistenteException(
					"Ya existe una criatura con ese nombre bajo control. No puede repetirse el nombre.");
		criaturasBajoControl.put(nuevaCriatura.getNombre(), nuevaCriatura);
		criaturasOrganizadasPorElementos.put(nuevaCriatura, nuevaCriatura.getAfinidadElemental());
	}

	public void entrenarCriatura(CriaturaElemental criaturaAEntrenar) throws NivelDeMaestriaInsuficienteException {
		if (criaturaAEntrenar == null)
			throw new NullPointerException("No se pueden entrenar criaturas nulas.");

		if (!criaturasBajoControl.containsKey(criaturaAEntrenar.getNombre()))
			throw new IllegalArgumentException(
					"La criatura que se intenta entrenar NO pertenece al Maestro Elemental.");

		if (criaturaAEntrenar.getNivelEnergia() >= (this.nivelMaestria * 4)) {
			throw new NivelDeMaestriaInsuficienteException("El maestro no tiene suficiente maestria");
		}

		criaturaAEntrenar.entrenar();
	}

	public void entrenarCriatura(CriaturaElemental criaturaAEntrenar, Integer valor)
			throws NivelDeMaestriaInsuficienteException {
		if (criaturaAEntrenar == null)
			throw new NullPointerException("No se pueden entrenar criaturas nulas.");

		if (!criaturasBajoControl.containsKey(criaturaAEntrenar.getNombre()))
			throw new IllegalArgumentException(
					"La criatura que se intenta entrenar NO pertenece al Maestro Elemental.");

		if (criaturaAEntrenar.getNivelEnergia() >= (this.nivelMaestria * 4)) {
			throw new NivelDeMaestriaInsuficienteException("El maestro no tiene suficiente maestria");
		}

		criaturaAEntrenar.entrenar(valor);
	}

	public void pacificarCriatura(CriaturaElemental criatura)
			throws NivelDeMaestriaInsuficienteException, CriaturaYaPacificaException {
		if (criatura == null)
			throw new NullPointerException("No se pueden pacificar criaturas nulas.");

		if (!criaturasBajoControl.containsKey(criatura.getNombre()))
			throw new IllegalArgumentException(
					"La criatura que se intenta pacificar NO pertenece al Maestro Elemental.");

		if (criatura.getNivelEnergia() >= (this.nivelMaestria * 4))
			throw new NivelDeMaestriaInsuficienteException("El maestro no tiene suficiente maestría");

		criatura.pacificar();
	}

	public CriaturaElemental transformarEnBendicionDelRio(CriaturaElemental criatura) {
		if (!criaturasBajoControl.containsKey(criatura.getNombre()))
			throw new IllegalArgumentException("La criatura no está bajo su control.");
		CriaturaElemental bendicion = new BendicionDelRio(criatura);
		criaturasBajoControl.put(criatura.getNombre(), bendicion);
		criaturasOrganizadasPorElementos.remove(criatura);
		criaturasOrganizadasPorElementos.put(bendicion, bendicion.getAfinidadElemental());

		return bendicion;
	}

	public CriaturaElemental transformarEnVinculoTerrestre(CriaturaElemental criatura) {
		if (!criaturasBajoControl.containsKey(criatura.getNombre()))
			throw new IllegalArgumentException("La criatura no está bajo su control.");

		CriaturaElemental terrestre = new VinculoTerrestre(criatura);

		criaturasBajoControl.put(criatura.getNombre(), terrestre);
		criaturasOrganizadasPorElementos.remove(criatura);

		criaturasOrganizadasPorElementos.put(terrestre, terrestre.getAfinidadElemental());

		return terrestre;

	}

	public CriaturaElemental transformarEnAscensoDelViento(CriaturaElemental criatura) {
		if (!criaturasBajoControl.containsKey(criatura.getNombre()))
			throw new IllegalArgumentException("La criatura no está bajo su control.");

		CriaturaElemental viento = new AscensoDelViento(criatura);

		criaturasBajoControl.put(criatura.getNombre(), viento);
		criaturasOrganizadasPorElementos.remove(criatura);

		criaturasOrganizadasPorElementos.put(viento, viento.getAfinidadElemental());

		return viento;

	}

	public Elementos getAfinidadPrincipal() {
		return afinidadPrincipal;
	}

	public Map<String, CriaturaElemental> getCriaturas() {
		return criaturasBajoControl;
	}

	public Map<CriaturaElemental, Elementos> getCriaturasOrganizadasPorElementos() {
		return criaturasOrganizadasPorElementos;
	}

	public CriaturaElemental transformarEnLlamaInterna(CriaturaElemental criatura) {
        if (!criaturasBajoControl.containsKey(criatura.getNombre()))
            throw new IllegalArgumentException("La criatura no está bajo su control.");

        CriaturaElemental llama = new LlamaInterna(criatura);

        criaturasBajoControl.put(criatura.getNombre(), llama);

        return llama;
    }
	public Integer getCantidadDeCriaturasTransformadas() {
        Integer contador = 0;

        for(CriaturaElemental c : criaturasBajoControl.values()) {
            if (c.getClase().equals(LlamaInterna.class.getSimpleName())
                    || c.getClase().equals(VinculoTerrestre.class.getSimpleName())
                   ||  c.getClase().equals(BendicionDelRio.class.getSimpleName())
                    || c.getClase().equals(AscensoDelViento.class.getSimpleName())) {
                contador++;
            }
        }
        return contador;
    }

}
