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
                    String valorStr = sc.next();
                    String quantidadeStr = sc.next();
                    String email = sc.next();
                    String tipo = sc.next();
                    String validadeStr = sc.next();
              

                    // conversões numéricas com verificação
                    double valor;
                    int quantidade, validade;
                    try {
                        valor = Double.parseDouble(valorStr);
                        quantidade = Integer.parseInt(quantidadeStr);
                        validade = Integer.parseInt(validadeStr);
                    } catch (NumberFormatException e) {
                        mensagens.add("2:ERRO:formato invalido.");
                        continue;
                    }

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

                Perecivel p = new Perecivel(descricao, valor, quantidade, validade, tipoPerecivel, doador);
                doacoes.add(p);

                mensagens.add("2:" + p.getDescricao() + "," + p.getValor() + "," + p.getQuantidade() + "," + p.getTipoPerecivel().getTipo() + "," + p.getValidade());

                } catch (NoSuchElementException e) {
                    mensagens.add("2:ERRO:formato invalido.");
                    continue;
                }
           
            } 
        } catch (IOException e) {
            mensagens.add("Erro de E/S: %s%n" + e);
        }
        return mensagens;

    }


     public List<String> LerArquivoDoacoesDuraveis(){

        List<String> mensagensP = new ArrayList<>();

        Path path = Paths.get("recursos", "doacoesduraveis.csv");

        try (BufferedReader br = Files.newBufferedReader(path,
                Charset.forName("UTF8"))) {

            String linha = null;
            br.readLine();

            while ((linha = br.readLine()) != null) {
                try{
                    Scanner sc = new Scanner(linha).useDelimiter(";");
                    String descricao = sc.next();
                    String valorStr = sc.next();
                    String quantidadeStr = sc.next();
                    String email = sc.next();
                    String tipo = sc.next();
              
                    double valor;
                    int quantidade;
                    try {
                        valor = Double.parseDouble(valorStr);
                        quantidade = Integer.parseInt(quantidadeStr);
                    } catch (NumberFormatException e) {
                        mensagensP.add("3:ERRO:formato invalido.");
                        continue;
                    }

                Doador doador = catalogoDoadores.buscarPorEmail(email);
                if (doador == null) {
                    mensagensP.add("3:ERRO:doador inexistente.");
                    continue;
                }
                TipoDuravel tipoDuravel;

                if (tipo.equals("ROUPA")) {
                    tipoDuravel = TipoDuravel.ROUPA;
                } else if (tipo.equals("BRINQUEDO")) {
                    tipoDuravel = TipoDuravel.BRINQUEDO;
                } else if (tipo.equals("ELETRODOMESTICO")) {
                    tipoDuravel = TipoDuravel.ELETRODOMESTICO;
                } else if (tipo.equals("MOVEL")) {
                    tipoDuravel = TipoDuravel.MOVEL;
                } else {
                    mensagensP.add("3:ERRO:tipo invalido.");
                    continue;
                }

                Duravel d = new Duravel(descricao, valor, quantidade, tipoDuravel, doador);
                doacoes.add(d);

                mensagensP.add("3:" + d.getDescricao() + "," + d.getValor() + "," + d.getQuantidade() + "," + d.getTipoDuravel().getTipo());

                } catch (NoSuchElementException e) {
                    mensagensP.add("3:ERRO:formato invalido.");
                    continue;
                }
           
            } 
        } catch (IOException e) {
            mensagensP.add("Erro de E/S: %s%n" + e);
        }
        return mensagensP;

    }

    public List<String> MostrarDoacoes(){

        List<String> mensagens = new ArrayList<>();

        Path path = Paths.get("recursos", "dadosentrada.txt");

        try (BufferedReader br = Files.newBufferedReader(path,
                Charset.forName("UTF8"))) {

            String linha = null;

            if(doacoes.isEmpty()){
                mensagens.add("5:nenhuma doacao encontrada.");
                return mensagens;
            }

            for (Doacao d : doacoes ){
                mensagens.add("5:" + d.getDescricao() + "," + d.getValor() + "," + d.getQuantidade() + "," + d.getDoador().getNome() + "," + d.getDoador().getEmail() );
            }

        } catch (IOException e) {
            mensagens.add("Erro de E/S: %s%n" + e);
        }
        return mensagens;
        
    }

    }



