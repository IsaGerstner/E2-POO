package dados;

public enum TipoPerecivel {
    ALIMENTO ("ALIMENTO"),
	MEDICAMENTO ("MEDICAMENTO");

    private final String tipo;

    private TipoPerecivel(String tipo){
        this.tipo = tipo;
    }

    public String getTipo(){
        return this.tipo;
    }

    
}
