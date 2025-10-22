package dados;

public enum TipoPerecivel {
    ALIMENTO("ALIMENTO"),
    MEDICAMENTO("MEDICAMENTO");

    private final String nome;

    private TipoPerecivel(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

}
