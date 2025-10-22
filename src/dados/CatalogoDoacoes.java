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

    public List<String> lerArquivoDoacoesPereciveis() {

        List<String> mensagens = new ArrayList<>();

        Path path = Paths.get("recursos", "doacoespereciveis.csv");

        try (BufferedReader br = Files.newBufferedReader(path,
                Charset.forName("UTF8"))) {

            String linha = null;
            br.readLine();

            while ((linha = br.readLine()) != null) {
                try {
                    Scanner sc = new Scanner(linha).useDelimiter(";");
                    String descricao = sc.next();
                    String valorStr = sc.next();
                    String quantidadeStr = sc.next();
                    String email = sc.next();
                    String nome = sc.next();
                    String validadeStr = sc.next();

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

                    if (nome.equals("ALIMENTO")) {
                        tipoPerecivel = TipoPerecivel.ALIMENTO;
                    } else if (nome.equals("MEDICAMENTO")) {
                        tipoPerecivel = TipoPerecivel.MEDICAMENTO;
                    } else {
                        mensagens.add("2:ERRO:tipo invalido.");
                        continue;
                    }

                    Perecivel p = new Perecivel(descricao, valor, quantidade, validade, tipoPerecivel, doador);
                    doacoes.add(p);

                    mensagens.add("2:" + p.geraResumo());

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

    public List<String> lerArquivoDoacoesDuraveis() {

        List<String> mensagens = new ArrayList<>();

        Path path = Paths.get("recursos", "doacoesduraveis.csv");

        try (BufferedReader br = Files.newBufferedReader(path,
                Charset.forName("UTF8"))) {

            String linha = null;
            br.readLine();

            while ((linha = br.readLine()) != null) {
                try {
                    Scanner sc = new Scanner(linha).useDelimiter(";");
                    String descricao = sc.next();
                    String valorStr = sc.next();
                    String quantidadeStr = sc.next();
                    String email = sc.next();
                    String nome = sc.next();

                    double valor;
                    int quantidade;
                    try {
                        valor = Double.parseDouble(valorStr);
                        quantidade = Integer.parseInt(quantidadeStr);
                    } catch (NumberFormatException e) {
                        mensagens.add("3:ERRO:formato invalido.");
                        continue;
                    }

                    Doador doador = catalogoDoadores.buscarPorEmail(email);
                    if (doador == null) {
                        mensagens.add("3:ERRO:doador inexistente.");
                        continue;
                    }
                    TipoDuravel tipoDuravel;

                    if (nome.equals("ROUPA")) {
                        tipoDuravel = TipoDuravel.ROUPA;
                    } else if (nome.equals("BRINQUEDO")) {
                        tipoDuravel = TipoDuravel.BRINQUEDO;
                    } else if (nome.equals("ELETRODOMESTICO")) {
                        tipoDuravel = TipoDuravel.ELETRODOMESTICO;
                    } else if (nome.equals("MOVEL")) {
                        tipoDuravel = TipoDuravel.MOVEL;
                    } else {
                        mensagens.add("3:ERRO:tipo invalido.");
                        continue;
                    }

                    Duravel dur = new Duravel(descricao, valor, quantidade, tipoDuravel, doador);
                    doacoes.add(dur);

                    mensagens.add("3:" + dur.geraResumo());

                } catch (NoSuchElementException e) {
                    mensagens.add("3:ERRO:formato invalido.");
                    continue;
                }

            }
        } catch (IOException e) {
            mensagens.add("Erro de E/S: %s%n" + e);
        }
        return mensagens;

    }

    public List<String> mostrarDoacoes() {

        List<String> mensagens = new ArrayList<>();

        Path path = Paths.get("recursos", "dadosentrada.txt");

        try (BufferedReader br = Files.newBufferedReader(path,
                Charset.forName("UTF8"))) {

            if (doacoes.isEmpty()) {
                mensagens.add("5:nenhuma doacao encontrada.");
                return mensagens;
            }

            for (Doacao doa : doacoes) {
                if (doa instanceof Perecivel) {
                    Perecivel p = (Perecivel) doa;
                    mensagens.add("5:" + p.geraResumo() + ","
                            + p.getDoador().getNome() + "," + p.getDoador().getEmail());
                }
                if (doa instanceof Duravel) {
                    Duravel dur = (Duravel) doa;
                    mensagens.add("5:" + dur.geraResumo() + ","
                            + dur.getDoador().getNome() + "," + dur.getDoador().getEmail());
                }
            }

        } catch (IOException e) {
            mensagens.add("Erro de E/S: %s%n" + e);
        }
        return mensagens;

    }

    public List<String> mostrarDoacoesPorNome() {

        List<String> mensagens = new ArrayList<>();

        Path path = Paths.get("recursos", "dadosentrada.txt");

        try (BufferedReader br = Files.newBufferedReader(path,
                Charset.forName("UTF8"))) {
            br.readLine();
            String nome = br.readLine();

            for (Doacao doa : doacoes) {
                if (doa.getDoador().getNome().equals(nome)) {
                    mensagens.add("7:" + doa.getDescricao() + "," + doa.getValor() + "," + doa.getQuantidade() + ","
                            + doa.getDoador().getEmail());
                }
            }

            if (mensagens.size() == 0) {
                mensagens.add("7:ERRO:nenhuma doacao localizada.");
            }

        } catch (IOException e) {
            mensagens.add("Erro de E/S: %s%n" + e);
        }
        return mensagens;

    }

    public List<String> mostarDoacoesDuraveisPorTipo() {

        List<String> mensagens = new ArrayList<>();

        Path path = Paths.get("recursos", "dadosentrada.txt");

        try (BufferedReader br = Files.newBufferedReader(path,
                Charset.forName("UTF8"))) {
            br.readLine();
            br.readLine();
            String tipo = br.readLine();

            if (!tipo.equals("ROUPA") && !tipo.equals("BRINQUEDO") && !tipo.equals("ELETRODOMESTICO")
                    && !tipo.equals("MOVEL")) {
                mensagens.add("8:ERRO:tipo invalido.");
                return mensagens;
            }

            for (Doacao doa : doacoes) {
                if (doa instanceof Duravel) {
                    Duravel dur = (Duravel) doa;
                    if (dur.getTipoDuravel().getNome().equals(tipo)) {
                        mensagens.add("8:" + dur.geraResumo() + "," + dur.getDoador().getNome() + ","
                                + dur.getDoador().getEmail());

                    }

                }

            }
            if (mensagens.size() == 0) {
                mensagens.add("8:ERRO:nenhuma doacao localizada.");
            }
        } catch (IOException e) {
            mensagens.add("Erro de E/S: %s%n" + e);
        }
        return mensagens;

    }

    public List<String> mostrarPerecivelMaiorQuantidade() {

        List<String> mensagens = new ArrayList<>();
        TipoPerecivel tipoPerecivel = null;

        Path path = Paths.get("recursos", "dadosentrada.txt");

        try (BufferedReader br = Files.newBufferedReader(path,
                Charset.forName("UTF8"))) {

            br.readLine();
            br.readLine();
            br.readLine();
            String tipo = br.readLine();

            if (tipo.equals("ALIMENTO")) {
                tipoPerecivel = TipoPerecivel.ALIMENTO;
            } else if (tipo.equals("MEDICAMENTO")) {
                tipoPerecivel = TipoPerecivel.MEDICAMENTO;
            } else {
                mensagens.add("9:ERRO:tipo invalido.");
                return mensagens;
            }

            Perecivel maior = null;
            for (Doacao doa : doacoes) {
                if (doa instanceof Perecivel) {
                    Perecivel p = (Perecivel) doa;
                    if (p.getTipoPerecivel() == tipoPerecivel) {
                        if (maior == null || p.getQuantidade() > maior.getQuantidade()) {
                            maior = p;
                        }
                    }
                }
            }

            if (maior == null) {
                mensagens.add("9:ERRO:nenhuma doacao localizada.");
            } else {
                mensagens.add("9:" + maior.getDescricao() + "," + maior.getValor() + "," + maior.getQuantidade() + "," +
                        maior.getDoador().getNome() + "," + maior.getDoador().getEmail());
            }

        } catch (IOException e) {
            mensagens.add("Erro de E/S: %s%n" + e);
        }
        return mensagens;
    }

}
