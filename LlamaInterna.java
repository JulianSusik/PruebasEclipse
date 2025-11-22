package ar.edu.unlam.pb2.gestiondecriaturaselementales;

public class LlamaInterna extends TransformacionDecorator {

    public LlamaInterna(CriaturaElemental criatura) {
        super(criatura);
    }

    
    
    @Override
    public Integer getNivelEnergia() {
        if (criatura.getAfinidadElemental() == Elementos.FUEGO) {

            Integer nuevaEnergia = criatura.getNivelEnergia() + 30;

            if (nuevaEnergia > MAXIMO_ENERGIA) {
               nuevaEnergia = MAXIMO_ENERGIA;
            }

           return nuevaEnergia;

        } else if(estado == EstadoEmocional.TRANQUILA){
            criatura.setEstado(EstadoEmocional.INESTABLE);
        }
		return criatura.getNivelEnergia();
    }
}
