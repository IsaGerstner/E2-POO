package dados;

public enum TipoDuravel {
    ROUPA ("ROUPA"),
    BRINQUEDO("BRINQUEDO"),
    ELETRODOMESTICO("ELETRODOMESTICO"),
    MOVEL("MOVEL");

    private final String tipo;

    private TipoDuravel(String tipo){
        this.tipo = tipo;
    } 

    public String getTipo(){
        return this.tipo;
    }
    
}
