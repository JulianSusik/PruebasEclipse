package ar.edu.unlam.pb2.gestiondecriaturaselementales;

import static org.junit.Assert.*;


import org.junit.Test;

public class CriaturaAncestralTest {

	
    @Test
    public void sePuedeCrearUnaCriaturaAncestralYVerSusParametros() {
        CriaturaElemental ance = new CriaturaAncestral("Dragon", Elementos.FUEGO, 180, EstadoEmocional.INESTABLE);

        assertEquals("Dragon", ance.getNombre());
        assertEquals(Elementos.FUEGO, ance.getAfinidadElemental());
        assertEquals(Integer.valueOf(180), ance.getNivelEnergia());
        assertEquals(EstadoEmocional.INESTABLE, ance.getEstado());
    }


    @Test
    public void sePuedeEntrenarUnaCriaturaAncestral () throws CriaturaYaExistenteException {
        CriaturaElemental ance = new CriaturaAncestral("Dragon", Elementos.FUEGO, 100, EstadoEmocional.INESTABLE);
        ance.entrenar(50);
        assertEquals(Integer.valueOf(150), ance.getNivelEnergia());
    }

    @Test (expected=LimiteEnergiaSuperadoException.class)
    public void noSePuedeEntrenarMasAllaDelNivel200DeEnergia () throws LimiteEnergiaSuperadoException {
        CriaturaElemental ance = new CriaturaAncestral("Dragon", Elementos.FUEGO, 150, EstadoEmocional.INESTABLE);
        ance.entrenar(51);
    }

    @Test (expected=IllegalArgumentException.class)
    public void laEnergiaDeLaCriaturaAncestralNoPuedeSerMenorA100 (){
        CriaturaElemental ance = new CriaturaAncestral("Dragon", Elementos.FUEGO, 90, EstadoEmocional.INESTABLE);
        assertEquals(Integer.valueOf(20), ance.getNivelEnergia());
    }
}


