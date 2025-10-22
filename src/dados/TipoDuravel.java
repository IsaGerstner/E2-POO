package dados;

public enum TipoDuravel {
    ROUPA("ROUPA"),
    BRINQUEDO("BRINQUEDO"),
    ELETRODOMESTICO("ELETRODOMESTICO"),
    MOVEL("MOVEL");

    private final String nome;

    private TipoDuravel(String tipo) {
        this.nome = tipo;
    }

    public String getNome() {
        return this.nome;
    }

}
