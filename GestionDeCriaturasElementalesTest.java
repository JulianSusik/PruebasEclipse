package ar.edu.unlam.pb2.gestiondecriaturaselementales;

import static org.junit.Assert.*;

import org.junit.Test;

public class GestionDeCriaturasElementalesTest {

	
	@Test
	public void queSePuedaCrearUnMaestroElementalSinCriaturasASuCargo() {
		MaestroElemental maestro = new MaestroElemental("Kai", 30, Elementos.FUEGO);
		assertEquals("Kai", maestro.getNombre());
		assertEquals(Integer.valueOf(30), maestro.getNivelMaestria());
		assertEquals(Elementos.FUEGO, maestro.getAfinidadPrincipal());
		assertNotNull(maestro.getCriaturas());
		assertTrue(maestro.getCriaturas().isEmpty());

	}

	@Test
	public void queSePuedaAsignarCriaturasACargoDeUnMaestro() throws CriaturaYaExistenteException {
		MaestroElemental maestro = new MaestroElemental("Kai", 30, Elementos.FUEGO);
		CriaturaSalvaje criatura = new CriaturaSalvaje("Goru", Elementos.AGUA, 190, EstadoEmocional.TRANQUILA);
		maestro.asignarCriatura(criatura);
		assertTrue(maestro.getCriaturas().containsKey("Goru"));
	}

	@Test(expected = CriaturaYaExistenteException.class)
	public void queNoSePuedaAsignarUnaCriaturaConNombreRepetido() throws CriaturaYaExistenteException {
		MaestroElemental maestro = new MaestroElemental("Kai", 30, Elementos.FUEGO);
		CriaturaSalvaje c1 = new CriaturaSalvaje("Goru", Elementos.AGUA, 100, EstadoEmocional.TRANQUILA);
		CriaturaSalvaje c2 = new CriaturaSalvaje("Goru", Elementos.TIERRA, 80, EstadoEmocional.TRANQUILA);

		maestro.asignarCriatura(c1);
		maestro.asignarCriatura(c2);

	}

	
	@Test (expected=CriaturaYaPacificaException.class)
    public void queNoSePuedaPacificarUnaCriaturaQueYaEsteTranquila() throws CriaturaYaExistenteException, CriaturaYaPacificaException, NivelDeMaestriaInsuficienteException {
        MaestroElemental maestro = new MaestroElemental("Kai", 50, Elementos.FUEGO); // creo un maestro
        CriaturaElemental criatura = new CriaturaAncestral("Goru", Elementos.AGUA, 150, EstadoEmocional.TRANQUILA); // creo una criatura
        maestro.asignarCriatura(criatura);
        maestro.pacificarCriatura(criatura);
        assertEquals(EstadoEmocional.TRANQUILA, criatura.getEstado());
    }

	@Test
	public void seCreaUnMaestroYSeGuardaUnaCriatura() throws CriaturaYaExistenteException {
		
		CriaturaElemental ance = new CriaturaAncestral("Dragon");
		MaestroElemental mae = new MaestroElemental("Juan");

		mae.asignarCriatura(ance);

		assertNotNull(mae);
		assertEquals(Integer.valueOf(1), mae.getCantidadDeCriaturas());
	}

	@Test(expected = CriaturaYaExistenteException.class)
	public void seIntentaAgregarDosCriaturasDeMismoNombre() throws CriaturaYaExistenteException {
		CriaturaElemental ance = new CriaturaAncestral("Dragon");
		MaestroElemental mae = new MaestroElemental("Juan");

		mae.asignarCriatura(ance);
		CriaturaElemental dome2 = new CriaturaDomesticada("Dragon");
		mae.asignarCriatura(dome2);
	}

	@Test
	public void seAgreganDosCriaturasDistintas() throws CriaturaYaExistenteException {
		CriaturaElemental salv = new CriaturaSalvaje("Ogro");
		CriaturaElemental ance = new CriaturaAncestral("Dragon");
		MaestroElemental mae = new MaestroElemental("Juan");

		mae.asignarCriatura(ance);
		mae.asignarCriatura(salv);

		assertEquals(Integer.valueOf(2), mae.getCantidadDeCriaturas());
	}

	@Test(expected = NivelDeMaestriaInsuficienteException.class)
	public void queElMaestroNoPuedaEntrenarUnaCriaturaFueraDelAlcanceDeSuMaestría()
			throws CriaturaYaExistenteException, NivelDeMaestriaInsuficienteException {
		MaestroElemental maestro = new MaestroElemental("Kai", 20, Elementos.FUEGO); // creo un maestro
		CriaturaSalvaje criatura = new CriaturaSalvaje("Goru", Elementos.AGUA, 150, EstadoEmocional.TRANQUILA);
		maestro.asignarCriatura(criatura);
		maestro.entrenarCriatura(criatura);
	}

	@Test
	public void queAlTransformarABendicionDelRioSeDupliqueLaEnergiaCorrectamente() throws CriaturaYaExistenteException {
		MaestroElemental maestro = new MaestroElemental("Kai", 50, Elementos.FUEGO); // creo un maestro
		CriaturaSalvaje criatura = new CriaturaSalvaje("Goru", Elementos.AGUA, 70, EstadoEmocional.INESTABLE); // creo
																												// una
																												// criatura

		maestro.asignarCriatura(criatura);
		CriaturaElemental criaturaTransformada = maestro.transformarEnBendicionDelRio(criatura);

		assertEquals(Integer.valueOf(140), criaturaTransformada.getNivelEnergia());
	}

	@Test
	public void queSePuedaPacificarUnaCriaturaInestable()
			throws CriaturaYaExistenteException, CriaturaYaPacificaException, NivelDeMaestriaInsuficienteException {
		MaestroElemental maestro = new MaestroElemental("Kai", 50, Elementos.FUEGO); // creo un maestro
		CriaturaAncestral criatura = new CriaturaAncestral("Goru", Elementos.AGUA, 150, EstadoEmocional.INESTABLE); // creo
																												// una
																												// criatura
		maestro.asignarCriatura(criatura);
		maestro.pacificarCriatura(criatura);
		assertEquals(EstadoEmocional.TRANQUILA, criatura.getEstado());
	}

	@Test
	public void queAlTransformarALlamaInternaAumente30DeEnergiaSiendoDeAtributoFuegoConservandoElMaximoDe200()
			throws CriaturaYaExistenteException {
		MaestroElemental maestro = new MaestroElemental("Kai", 50, Elementos.FUEGO); // creo un maestro
		CriaturaElemental criatura = new CriaturaDomesticada("Goru", Elementos.FUEGO, 30, EstadoEmocional.TRANQUILA); // creo
																														// una
																														// criatura
		CriaturaElemental criatura2 = new CriaturaDomesticada("asdasd", Elementos.FUEGO, 190,
				EstadoEmocional.TRANQUILA); // creo una criatura
		maestro.asignarCriatura(criatura);
		maestro.asignarCriatura(criatura2);

		CriaturaElemental criaturaTransformada = maestro.transformarEnLlamaInterna(criatura);
		CriaturaElemental criaturaTransformada2 = maestro.transformarEnLlamaInterna(criatura2);

		assertEquals(Integer.valueOf(60), criaturaTransformada.getNivelEnergia());
		assertEquals(Integer.valueOf(30), criatura.getNivelEnergia());
		assertEquals(Integer.valueOf(200), criaturaTransformada2.getNivelEnergia());
		assertEquals(Integer.valueOf(190), criatura2.getNivelEnergia());

	}

	@Test
	public void queAlTransformarAAscensoDelVientoSeTarsnformeElElementoAAire() throws CriaturaYaExistenteException {
		MaestroElemental maestro = new MaestroElemental("Kai", 50, Elementos.FUEGO); // creo un maestro
		CriaturaSalvaje criatura = new CriaturaSalvaje("Goru", Elementos.AGUA, 70, EstadoEmocional.INESTABLE); // creo
																												// una
																												// criatura

		maestro.asignarCriatura(criatura);
		CriaturaElemental criaturaTransformada = maestro.transformarEnAscensoDelViento(criatura);

		assertEquals(Elementos.AIRE, criaturaTransformada.getAfinidadElemental());
		assertEquals(Elementos.AGUA, criatura.getAfinidadElemental());
	}

	@Test
	public void queGanenEnergiaSiCompartenAfinidad() {
		CriaturaSalvaje criatura1 = new CriaturaSalvaje("Goru", Elementos.AGUA, 50, EstadoEmocional.INESTABLE); // creo
																												// una
																												// criatura
		CriaturaSalvaje criatura2 = new CriaturaSalvaje("Kira", Elementos.AGUA, 70, EstadoEmocional.INESTABLE); // creo
																												// una
																												// criatura
		GestionDeCriaturas.interactuar(criatura1, criatura2);
		assertEquals(Integer.valueOf(60), criatura1.getNivelEnergia());
		assertEquals(Integer.valueOf(80), criatura2.getNivelEnergia());
	}

	@Test
	public void queSiLasCriaturasSonOpuestasSeVuelvanInestables() {
		CriaturaAncestral criatura1 = new CriaturaAncestral("Goru", Elementos.AGUA, 100, EstadoEmocional.TRANQUILA); // creo
																														// una
																														// criatura
		CriaturaSalvaje criatura2 = new CriaturaSalvaje("Kira", Elementos.FUEGO, 180, EstadoEmocional.TRANQUILA); // creo
																													// una
																													// criatura
		GestionDeCriaturas.interactuar(criatura1, criatura2);
		assertEquals(EstadoEmocional.INESTABLE, criatura1.getEstado());
		assertEquals(EstadoEmocional.INESTABLE, criatura2.getEstado());
	}

	@Test
	public void queSiUnaCriaturaEsAncestralGaneEnergiaYLaOtraPierda() {
		CriaturaAncestral criatura1 = new CriaturaAncestral("Goru", Elementos.AGUA, 100, EstadoEmocional.TRANQUILA); // creo
																														// una
																														// criatura
		CriaturaSalvaje criatura2 = new CriaturaSalvaje("Kira", Elementos.TIERRA, 70, EstadoEmocional.TRANQUILA); // creo
																													// una
																													// criatura
		GestionDeCriaturas.interactuar(criatura1, criatura2);
		assertEquals(Integer.valueOf(120), criatura1.getNivelEnergia());
		assertEquals(Integer.valueOf(55), criatura2.getNivelEnergia());
	}

	@Test
	public void queLaEnergiaDeLaCriaturaAncestralNoBajeDeCero() {
		CriaturaAncestral criatura1 = new CriaturaAncestral("Goru", Elementos.AGUA, 100, EstadoEmocional.TRANQUILA); // creo
																														// una
																														// criatura
		CriaturaSalvaje criatura2 = new CriaturaSalvaje("Kira", Elementos.TIERRA, 10, EstadoEmocional.TRANQUILA); // creo
																													// una
																													// criatura
		GestionDeCriaturas.interactuar(criatura1, criatura2);
		assertEquals(Integer.valueOf(120), criatura1.getNivelEnergia());
		assertEquals(Integer.valueOf(0), criatura2.getNivelEnergia());
	}

}
