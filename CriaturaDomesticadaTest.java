package ar.edu.unlam.pb2.gestiondecriaturaselementales;

import static org.junit.Assert.*;

import org.junit.Test;

public class CriaturaDomesticadaTest {

	@Test
	public void queSePuedaCrearUnaCriaturaDomesticadaCorrectamente() {
		CriaturaElemental dom = new CriaturaDomesticada("Felix", Elementos.AIRE, 120, EstadoEmocional.TRANQUILA);

		assertEquals("Felix", dom.getNombre());
		assertEquals(Elementos.AIRE, dom.getAfinidadElemental());
		assertEquals(Integer.valueOf(120), dom.getNivelEnergia());
		assertEquals(EstadoEmocional.TRANQUILA, dom.getEstado());
	}

	@Test
	public void queSePuedaEntrenarUnaCriaturaDomesticadaDeFormaEstable() throws LimiteEnergiaSuperadoException {
		CriaturaElemental dom = new CriaturaDomesticada("Felix", Elementos.AIRE, 100, EstadoEmocional.TRANQUILA);

		dom.entrenar(30);

		assertEquals(Integer.valueOf(130), dom.getNivelEnergia());
	}

	@Test(expected = LimiteEnergiaSuperadoException.class)
	public void queNoSePuedaSuperarElLimiteDeEnergia() throws LimiteEnergiaSuperadoException {
		CriaturaElemental dom = new CriaturaDomesticada("Felix", Elementos.AIRE, 190, EstadoEmocional.TRANQUILA);

		dom.entrenar(20); 
	}

	@Test
	public void queNuncaSeVuelvaInestable() {
		CriaturaElemental dom = new CriaturaDomesticada("Felix", Elementos.AIRE, 160, EstadoEmocional.TRANQUILA);

		
		assertNotEquals(EstadoEmocional.INESTABLE, dom.getEstado());
	}

	@Test(expected = IllegalArgumentException.class)
	public void queNoSePuedaCrearDomesticadaConEstadoInestable() {
		new CriaturaDomesticada("Felix", Elementos.FUEGO, 140, EstadoEmocional.INESTABLE // → NO PERMITIDO
		);
	}
	
	

}
