package ar.edu.unlam.pb2.gestiondecriaturaselementales;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class ReportesParaElConsejoTest {


	@Test
	public void queSeListenTodasLasCriaturasDeTodosLosMaestros() throws CriaturaYaExistenteException {
		MaestroElemental maestro1 = new MaestroElemental("Kai", 50, Elementos.FUEGO); // creo un maestro
		MaestroElemental maestro2 = new MaestroElemental("Luna", 50, Elementos.TIERRA); // creo un maestro
		
		CriaturaSalvaje criatura1 = new CriaturaSalvaje("Goru", Elementos.AGUA, 50, EstadoEmocional.TRANQUILA); // creo una criatura
        CriaturaSalvaje criatura2 = new CriaturaSalvaje("Kira", Elementos.FUEGO, 70, EstadoEmocional.TRANQUILA); // creo una criatura
        
        maestro1.asignarCriatura(criatura1);
        maestro2.asignarCriatura(criatura2);

        Reportes reportes = new Reportes();
        
        List<CriaturaElemental> lista = reportes.listarTodasLasCriaturas(Arrays.asList(maestro1, maestro2));
        assertTrue(lista.contains(criatura1));
        assertTrue(lista.contains(criatura2));
        
        
	}
	
	@Test
	public void queDevuelvaLaCriaturaConMayorEnergia() throws CriaturaYaExistenteException {
		MaestroElemental maestro1 = new MaestroElemental("Kai", 50, Elementos.FUEGO); // creo un maestro
		MaestroElemental maestro2 = new MaestroElemental("Luna", 50, Elementos.TIERRA); // creo un maestro
		
		CriaturaSalvaje criatura1 = new CriaturaSalvaje("Goru", Elementos.AGUA, 50, EstadoEmocional.TRANQUILA); // creo una criatura
        CriaturaSalvaje criatura2 = new CriaturaSalvaje("Kira", Elementos.FUEGO, 70, EstadoEmocional.TRANQUILA); // creo una criatura
        
        maestro1.asignarCriatura(criatura1);
        maestro2.asignarCriatura(criatura2);

        Reportes reportes = new Reportes();
        
        CriaturaElemental maxEnergia = reportes.getCriaturaConMayorEnergia(Arrays.asList(maestro1, maestro2));
        assertEquals(criatura2, maxEnergia);

        
	}
	
	@Test
    public void queSeMuestreElMaestroConMasCriaturasTransformadas() throws CriaturaYaExistenteException {
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
        MaestroElemental maestro2 = new MaestroElemental("Kira", 50, Elementos.FUEGO); // creo un maestro
        CriaturaSalvaje criatura4 = new CriaturaSalvaje("Ra", Elementos.AGUA, 70, EstadoEmocional.INESTABLE); // creo una criatura
        CriaturaSalvaje criatura5 = new CriaturaSalvaje("Re", Elementos.AGUA, 70, EstadoEmocional.INESTABLE); // creo una criatura
        CriaturaSalvaje criatura6 = new CriaturaSalvaje("Ru", Elementos.AGUA, 70, EstadoEmocional.INESTABLE); // creo una criatura
        CriaturaSalvaje criatura7 = new CriaturaSalvaje("Ro", Elementos.AGUA, 70, EstadoEmocional.INESTABLE); // creo una criatura
        maestro2.asignarCriatura(criatura4);
        maestro2.asignarCriatura(criatura5);
        maestro2.asignarCriatura(criatura6);
        maestro2.asignarCriatura(criatura7);
        CriaturaElemental criaturaTransformada4 = maestro2.transformarEnAscensoDelViento(criatura4);
        CriaturaElemental criaturaTransformada5 = maestro2.transformarEnAscensoDelViento(criatura5);
        CriaturaElemental criaturaTransformada6 = maestro2.transformarEnAscensoDelViento(criatura6);
        CriaturaElemental criaturaTransformada7 = maestro2.transformarEnAscensoDelViento(criatura7);


        Reportes reporte = new Reportes();
        String maestroConMasTransformadas = reporte.mostrarMaestroConMasCriaturasTransformadas(Arrays.asList(maestro, maestro2));



        assertEquals("Kira", maestroConMasTransformadas);
    }
	
	@Test
	public void queSePuedaContarLaCantidadDeCriaturasPorAfinidad() throws CriaturaYaExistenteException {
		MaestroElemental maestro1 = new MaestroElemental("Kai", 50, Elementos.FUEGO); // creo un maestro
		MaestroElemental maestro2 = new MaestroElemental("Luna", 50, Elementos.TIERRA); // creo un maestro
		
		CriaturaSalvaje criatura1 = new CriaturaSalvaje("Goru", Elementos.AGUA, 50, EstadoEmocional.TRANQUILA); // creo una criatura
        CriaturaSalvaje criatura2 = new CriaturaSalvaje("Kira", Elementos.FUEGO, 70, EstadoEmocional.TRANQUILA); // creo una criatura
        CriaturaSalvaje criatura3 = new CriaturaSalvaje("Milo", Elementos.FUEGO, 70, EstadoEmocional.TRANQUILA); // creo una criatura
        
        maestro1.asignarCriatura(criatura1);
        maestro2.asignarCriatura(criatura2);
        maestro2.asignarCriatura(criatura3);
        
        Reportes reporte = new Reportes();
        Map<Elementos, Integer> criaturas = reporte.obtenerCantidadPorAfinidad(Arrays.asList(maestro1, maestro2));
        assertEquals(Integer.valueOf(1), criaturas.get(Elementos.AGUA));
        assertEquals(Integer.valueOf(2), criaturas.get(Elementos.FUEGO));

        
	
	}


	
	
}
