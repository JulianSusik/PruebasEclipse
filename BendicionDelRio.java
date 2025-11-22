package ar.edu.unlam.pb2.gestiondecriaturaselementales;

public class BendicionDelRio extends TransformacionDecorator {

    public BendicionDelRio(CriaturaElemental criatura) {
        super(criatura);
    }

    
    public Integer getNivelEnergia() {
        Integer energia = criatura.getNivelEnergia() * 2;
        if (energia > 180) energia = 180;
       
		return energia;
    }
}
