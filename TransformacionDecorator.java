package ar.edu.unlam.pb2.gestiondecriaturaselementales;

public abstract class TransformacionDecorator extends CriaturaElemental {

    protected CriaturaElemental criatura;

    public TransformacionDecorator(CriaturaElemental criatura) {
        super(criatura.getNombre(),
              criatura.getAfinidadElemental(),
              criatura.getNivelEnergia(),
              criatura.getEstado());

        this.criatura = criatura;
    }
    @Override
    public Integer getNivelEnergia() {
        return criatura.getNivelEnergia();
    }

    @Override
    public Elementos getAfinidadElemental() {
        return criatura.getAfinidadElemental();
    }

    @Override
    public EstadoEmocional getEstado() {
        return criatura.getEstado();
    }

    @Override
    public void setNivelEnergia(Integer nivel) {
        criatura.setNivelEnergia(nivel);
    }

    @Override
    public void pacificar() {
        criatura.pacificar();
    }

    @Override
    public void entrenar() {
        criatura.entrenar();
    }

    @Override
    public void entrenar(Integer puntos) {
        criatura.entrenar(puntos);
    }

}
