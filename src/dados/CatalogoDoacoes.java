package dados;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CatalogoDoacoes {
    private List<Doacao> doacoes;
    private CatalogoDoadores catalogoDoadores;

    public CatalogoDoacoes(CatalogoDoadores catalogoDoadores) {
        doacoes = new ArrayList<>();
        this.catalogoDoadores = catalogoDoadores;
    }

      public List<Doacao> getDoacoes() {
        return doacoes;
    }

    public List<String> LerArquivoDoacoesPereciveis(){

        List<String> mensagens = new ArrayList<>();

        Path path = Paths.get("recursos", "doacoespereciveis.csv");

        try (BufferedReader br = Files.newBufferedReader(path,
                Charset.forName("UTF8"))) {

            String linha = null;
            br.readLine();

            while ((linha = br.readLine()) != null) {

                try{
                Scanner sc = new Scanner(linha).useDelimiter(";");
                String descricao = sc.next();
                String valor = sc.next();
                double doubleValor = Double.parseDouble(valor);
                String quantidade = sc.next();
                int intQuantidade = Integer.parseInt(quantidade);
                String email = sc.next();
                String tipo = sc.next();
                String validade = sc.next();
                int intValidade = Integer.parseInt(validade);
               

                Doador doador = catalogoDoadores.buscarPorEmail(email);
                if (doador == null) {
                    mensagens.add("2:ERRO:doador inexistente.");
                    continue;
                }
                TipoPerecivel tipoPerecivel;

                if (tipo.equals("ALIMENTO")) {
                    tipoPerecivel = TipoPerecivel.ALIMENTO;
                } else if (tipo.equals("MEDICAMENTO")) {
                    tipoPerecivel = TipoPerecivel.MEDICAMENTO;
                } else {
                    mensagens.add("2:ERRO:tipo invalido.");
                    continue;
                }

                Perecivel p = new Perecivel(descricao, doubleValor, intQuantidade, intValidade, tipoPerecivel, doador);
                doacoes.add(p);

                mensagens.add("2:" + p.getDescricao() + "," + p.getValor() + "," + p.getQuantidade() + "," + p.getTipoPerecivel().getNome() + "," + p.getValidade());

                } catch (NoSuchElementException e) {
                    mensagens.add("2:ERRO:formato invalido.");
                }
           
            } 
        } catch (IOException e) {
            mensagens.add("Erro de E/S: %s%n" + e);
        }
        return mensagens;

    }

}

