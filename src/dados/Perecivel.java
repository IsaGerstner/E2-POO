package dados;

public class Perecivel extends Doacao {

    private int validade;
    private TipoPerecivel tipoPerecivel;

    public Perecivel(String descricao, double valor, int quantidade, int validade, TipoPerecivel tipoPerecivel) {
        super(descricao, valor, quantidade);
        this.validade = validade;
        this.tipoPerecivel = tipoPerecivel;
        
    }

    public TipoPerecivel getTipoPerecivel(){
        return this.tipoPerecivel;
    }

    @Override
    public String geraResumo(){
        return getDescricao() + "," + getValor() + "," + getQuantidade() + "," + this.validade + "," + this.tipoPerecivel + ",";
    }
    
}
