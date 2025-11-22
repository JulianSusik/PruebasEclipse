package ar.edu.unlam.pb2.gestiondecriaturaselementales;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class GestionDeCriaturasElementalesTest {
	
	
	@Test
	public void seCreaUnaCriaturaDeCualquierTipo() {
		CriaturaElemental salv = new CriaturaSalvaje("Ogro");
		
		assertNotNull(salv);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void noSePuedeCrearUnaCriaturaConNombreNull () {
		CriaturaElemental salv = new CriaturaSalvaje(null);
		
	}
	
	@Test
	public void seCreaUnMaestroYSeGuardaUnaCriatura () throws CriaturaYaExistenteException {
		CriaturaElemental salv = new CriaturaSalvaje("Ogro");
		CriaturaElemental dome = new CriaturaDomesticada("Lobo");
		CriaturaElemental ance = new CriaturaAncestral("Dragon");
		MaestroElemental mae = new MaestroElemental("Juan");
		
		mae.asignarCriatura(ance);
		
		assertNotNull(mae);
		assertEquals(Integer.valueOf(1), mae.getCantidadDeCriaturas());
	}

	@Test (expected=CriaturaYaExistenteException.class)
	public void seIntentaAgregarDosCriaturasDeMismoNombre () throws CriaturaYaExistenteException {
		CriaturaElemental ance = new CriaturaAncestral("Dragon");
		MaestroElemental mae = new MaestroElemental("Juan");
		
		mae.asignarCriatura(ance);
		CriaturaElemental dome2 = new CriaturaDomesticada("Dragon");
		mae.asignarCriatura(dome2);
	}
	
	@Test 
	public void seAgreganDosCriaturasDistintas () throws CriaturaYaExistenteException {
		CriaturaElemental salv = new CriaturaSalvaje("Ogro");
		CriaturaElemental ance = new CriaturaAncestral("Dragon");
		MaestroElemental mae = new MaestroElemental("Juan");
		
		mae.asignarCriatura(ance);
		mae.asignarCriatura(salv);
		
		assertEquals(Integer.valueOf(2), mae.getCantidadDeCriaturas());
	}
	
	@Test 
	public void seVerificanLosGetsDeMaestroElemental () {
		MaestroElemental mae1 = new MaestroElemental("Marco", 30, Elementos.AGUA);
		
		assertEquals("Marco", mae1.getNombre());
		assertEquals(Integer.valueOf(30), mae1.getNivelMaestria());
		assertEquals(Elementos.AGUA, mae1.getAfinidadElemental());
	}
	
	@Test
    public void queSePuedaAsignarCriaturasACargoDeUnMaestro() throws CriaturaYaExistenteException {
        MaestroElemental maestro = new MaestroElemental("Kai", 30, Elementos.FUEGO); // creo un maestro
        CriaturaSalvaje criatura = new CriaturaSalvaje("Goru", Elementos.AGUA, 190, EstadoEmocional.TRANQUILA); // creo una criatura
        maestro.asignarCriatura(criatura);
        assertTrue(maestro.getCriaturas().containsKey("Goru"));

    }
	
	@Test (expected=NivelDeMaestriaInsuficienteException.class)
	public void queElMaestroNoPuedaEntrenarUnaCriaturaFueraDelAlcanceDeSuMaestría() throws CriaturaYaExistenteException, NivelDeMaestriaInsuficienteException {
        MaestroElemental maestro = new MaestroElemental("Kai", 20, Elementos.FUEGO); // creo un maestro
        CriaturaSalvaje criatura = new CriaturaSalvaje("Goru", Elementos.AGUA, 150, EstadoEmocional.TRANQUILA); // creo una criatura
        maestro.asignarCriatura(criatura);
        maestro.entrenarCriatura(criatura);
	}

	@Test 
	public void queSePuedaPacificarUnaCriaturaInestable() throws CriaturaYaExistenteException, CriaturaYaPacificaException, NivelDeMaestriaInsuficienteException {
        MaestroElemental maestro = new MaestroElemental("Kai", 50, Elementos.FUEGO); // creo un maestro
        CriaturaAncestral criatura = new CriaturaAncestral("Goru", Elementos.AGUA, 150, EstadoEmocional.INESTABLE); // creo una criatura
        maestro.asignarCriatura(criatura);
        maestro.pacificarCriatura(criatura);
        assertEquals(EstadoEmocional.TRANQUILA, criatura.getEstado());
	}

	@Test (expected=NivelDeMaestriaInsuficienteException.class)
	public void queNoSePuedaPacificarUnaCriaturaInestableConMaestriaSuficiente() throws CriaturaYaExistenteException, CriaturaYaPacificaException, NivelDeMaestriaInsuficienteException {
        MaestroElemental maestro = new MaestroElemental("Kai", 5, Elementos.FUEGO); // creo un maestro
        CriaturaSalvaje criatura = new CriaturaSalvaje("Goru", Elementos.AGUA, 150, EstadoEmocional.INESTABLE); // creo una criatura
        maestro.asignarCriatura(criatura);
        maestro.pacificarCriatura(criatura);
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
	public void queAlTransformarAVinculoTerrestreElMinimoSea50() throws CriaturaYaExistenteException {
		MaestroElemental maestro = new MaestroElemental("Kai", 50, Elementos.FUEGO); // creo un maestro
        CriaturaElemental criatura = new CriaturaDomesticada("Goru", Elementos.AGUA, 15, EstadoEmocional.TRANQUILA); // creo una criatura
        maestro.asignarCriatura(criatura);
        
        
        CriaturaElemental criaturaTransformada = maestro.transformarEnVinculoTerrestre(criatura);
        

        assertEquals(Integer.valueOf(50), criaturaTransformada.getNivelEnergia());
        assertEquals(Integer.valueOf(15), criatura.getNivelEnergia());
        
	}

	@Test
	public void queAlTransformarALlamaInternaAumente30DeEnergiaSiendoDeAtributoFuegoConservandoElMaximoDe200() throws CriaturaYaExistenteException {
		MaestroElemental maestro = new MaestroElemental("Kai", 50, Elementos.FUEGO); // creo un maestro
        CriaturaElemental criatura = new CriaturaDomesticada("Goru", Elementos.FUEGO, 30, EstadoEmocional.TRANQUILA); // creo una criatura
        CriaturaElemental criatura2 = new CriaturaDomesticada("asdasd", Elementos.FUEGO, 190, EstadoEmocional.TRANQUILA); // creo una criatura
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
	public void queAlTransformarALlamaInternaSeDescontroleUnaCriaturaQueNoSeaAtributoFuego() throws CriaturaYaExistenteException {
		MaestroElemental maestro = new MaestroElemental("Kai", 50, Elementos.FUEGO); // creo un maestro
        CriaturaElemental criatura = new CriaturaDomesticada("Goru", Elementos.AGUA, 30, EstadoEmocional.TRANQUILA); // creo una criatura
        maestro.asignarCriatura(criatura);

        CriaturaElemental criaturaTransformada = maestro.transformarEnLlamaInterna(criatura);
        
        assertEquals(EstadoEmocional.INESTABLE, criaturaTransformada.getEstado());
	}
	
	@Test
    public void queAlTransformarAAscensoDelVientoSeTarsnformeElElementoAAire() throws CriaturaYaExistenteException {
        MaestroElemental maestro = new MaestroElemental("Kai", 50, Elementos.FUEGO); // creo un maestro
        CriaturaSalvaje criatura = new CriaturaSalvaje("Goru", Elementos.AGUA, 70, EstadoEmocional.INESTABLE); // creo una criatura

        maestro.asignarCriatura(criatura);
        CriaturaElemental criaturaTransformada = maestro.transformarEnAscensoDelViento(criatura);

        assertEquals(Elementos.AIRE, criaturaTransformada.getAfinidadElemental());
        assertEquals(Elementos.AGUA, criatura.getAfinidadElemental());
    }
	
	@Test
    public void queDupliqueLaEnergiaCorrectamente() throws CriaturaYaExistenteException {
        MaestroElemental maestro = new MaestroElemental("Kai", 50, Elementos.FUEGO); // creo un maestro
        CriaturaElemental criatura = new CriaturaSalvaje("Goru", Elementos.AGUA, 70, EstadoEmocional.INESTABLE); // creo una criatura

        maestro.asignarCriatura(criatura);
        CriaturaElemental criaturaTransformada = maestro.transformarEnBendicionDelRio(criatura);

        assertEquals(Integer.valueOf(140), criaturaTransformada.getNivelEnergia());
    }
	
	@Test
    public void queGanenEnergiaSiCompartenAfinidad() {
		CriaturaElemental criatura1 = new CriaturaSalvaje("Goru", Elementos.AGUA, 50, EstadoEmocional.INESTABLE); // creo una criatura
		CriaturaElemental criatura2 = new CriaturaSalvaje("Goruu", Elementos.AGUA, 70, EstadoEmocional.INESTABLE); // creo una criatura
        GestionDeCriaturas.interactuar(criatura1, criatura2);
        assertEquals(Integer.valueOf(60), criatura1.getNivelEnergia());
        assertEquals(Integer.valueOf(80), criatura2.getNivelEnergia());
    }
	//////////////////////////////////
	@Test
    public void queSiLasCriaturasSonOpuestasSeVuelvanInestables() {
		CriaturaElemental criatura1 = new CriaturaAncestral("Goru", Elementos.AGUA, 100, EstadoEmocional.TRANQUILA); // creo una criatura
		CriaturaElemental criatura2 = new CriaturaDomesticada("Kira", Elementos.FUEGO, 100, EstadoEmocional.TRANQUILA); // creo una criatura
        GestionDeCriaturas.interactuar(criatura1, criatura2);
        assertEquals(EstadoEmocional.INESTABLE, criatura1.getEstado());
        assertEquals(EstadoEmocional.INESTABLE, criatura2.getEstado());
    }

    @Test
    public void queSiUnaCriaturaEsAncestralGaneEnergiaYLaOtraPierda() {
    	CriaturaElemental criatura1 = new CriaturaAncestral("Goru", Elementos.AGUA, 100, EstadoEmocional.TRANQUILA); // creo una criatura
    	CriaturaElemental criatura2 = new CriaturaSalvaje("Kira", Elementos.FUEGO, 70, EstadoEmocional.TRANQUILA); // creo una criatura
        GestionDeCriaturas.interactuar(criatura1, criatura2);
        assertEquals(Integer.valueOf(120), criatura1.getNivelEnergia());
        assertEquals(Integer.valueOf(55), criatura2.getNivelEnergia());
    }

    @Test
    public void queLaEnergiaDeLaCriaturaAncestralNoBajeDeCero() {
    	CriaturaElemental criatura1 = new CriaturaAncestral("Goru", Elementos.AGUA, 150, EstadoEmocional.TRANQUILA); // creo una criatura
    	CriaturaElemental criatura2 = new CriaturaSalvaje("Kira", Elementos.FUEGO, 10, EstadoEmocional.TRANQUILA); // creo una criatura
        GestionDeCriaturas.interactuar(criatura1, criatura2);
        assertEquals(Integer.valueOf(170), criatura1.getNivelEnergia());
        assertEquals(Integer.valueOf(0), criatura2.getNivelEnergia());
    }

    @Test
    public void devuelveLaCantidadDeCriaturasTransformadas() throws CriaturaYaExistenteException {
        MaestroElemental maestro = new MaestroElemental("Kai", 50, Elementos.FUEGO); // creo un maestro
        CriaturaSalvaje criatura1 = new CriaturaSalvaje("Goru", Elementos.AGUA, 70, EstadoEmocional.INESTABLE); // creo una criatura
        CriaturaSalvaje criatura2 = new CriaturaSalvaje("Golu", Elementos.AGUA, 70, EstadoEmocional.INESTABLE); // creo una criatura
        CriaturaSalvaje criatura3 = new CriaturaSalvaje("Go,u", Elementos.AGUA, 70, EstadoEmocional.INESTABLE); // creo una criatura

        maestro.asignarCriatura(criatura1);
        maestro.asignarCriatura(criatura2);
        maestro.asignarCriatura(criatura3);

        CriaturaElemental criaturaTransformada1 = maestro.transformarEnAscensoDelViento(criatura1);
        CriaturaElemental criaturaTransformada2 = maestro.transformarEnAscensoDelViento(criatura2);
        CriaturaElemental criaturaTransformada3 = maestro.transformarEnAscensoDelViento(criatura3);
        
        assertEquals(Integer.valueOf(3), maestro.getCantidadDeCriaturasTransformadas());
    }

    @Test (expected=NullPointerException.class)
    public void queHayaExcepcionAlIntentarEntrenarUnaCriaturaNula() throws NivelDeMaestriaInsuficienteException {
    	
    	MaestroElemental mae = new MaestroElemental("Kai");
    	CriaturaDomesticada cria = new CriaturaDomesticada("Leo");
    	
    	mae.entrenarCriatura(null, 200);
    }

    @Test (expected=NivelDeMaestriaInsuficienteException.class)
    public void queHayaExcepcionAlIntentarEntrenarUnaCriaturaTeniendoPocaMaestria() throws NivelDeMaestriaInsuficienteException, CriaturaYaExistenteException {
    	
    	MaestroElemental mae = new MaestroElemental("Kai", 20, Elementos.AGUA);
    	CriaturaDomesticada cria = new CriaturaDomesticada("Leo", Elementos.AIRE, 160, EstadoEmocional.TRANQUILA);
    	
    	mae.asignarCriatura(cria);
    	
    	mae.entrenarCriatura(cria, 10);
    }
    
    
}
