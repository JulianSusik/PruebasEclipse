package ar.edu.unlam.pb2.gestiondecriaturaselementales;

public class AscensoDelViento extends TransformacionDecorator {
	private Elementos afinidadOriginal;
	public AscensoDelViento(CriaturaElemental criatura) {
		super(criatura);
		// TODO Auto-generated constructor stub
	}
	
	 @Override
	    public Elementos getAfinidadElemental() {
	        return Elementos.AIRE;
	    }
	 
	 public Elementos getAfinidadOriginal() {
	        return this.afinidadOriginal;
	    }

}
