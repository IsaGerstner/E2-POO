package dados;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.*;

public class CatalogoDoadores {
    private List<Doador> doadores;

    public CatalogoDoadores() {
        doadores = new ArrayList<>();
    }

    public List<Doador> getDoadores() {
        return doadores;
    }

    public List<String> lerArquivoDoadores() {

        List<String> mensagens = new ArrayList<>();

        Path path = Paths.get("recursos", "doadores.csv");

        try (BufferedReader br = Files.newBufferedReader(path,
                Charset.forName("UTF8"))) {

            String linha = null;
            br.readLine();

            while ((linha = br.readLine()) != null) {
                try {
                    Scanner sc = new Scanner(linha).useDelimiter(";");
                    String nome;
                    String email;
                    nome = sc.next();
                    email = sc.next();

                    if (buscarPorEmail(email) != null) {
                        mensagens.add("1:ERRO:doador repetido.");
                        continue;

                    }

                    Doador d = new Doador(nome, email);
                    doadores.add(d);

                    mensagens.add("1:" + d.getNome() + "," + d.getEmail());

                } catch (NoSuchElementException e) {
                    mensagens.add("1:ERRO:formato invalido.");
                    continue;

                }
            }
        } catch (IOException e) {
            mensagens.add("Erro de E/S: %s%n" + e);
        }
        return mensagens;

    }

    public Doador buscarPorEmail(String email) {
        for (Doador d : doadores) {
            if (d.getEmail().equalsIgnoreCase(email)) {
                return d;
            }
        }
        return null;
    }

    public List<String> mostrarDoadorPorEmail() {

        List<String> mensagens = new ArrayList<>();

        Path path = Paths.get("recursos", "dadosentrada.txt");

        try (BufferedReader br = Files.newBufferedReader(path,
                Charset.forName("UTF8"))) {
            String email = br.readLine();

            Doador d = buscarPorEmail(email);

            if (d == null) {
                mensagens.add("4:ERRO:e-mail inexistente.");
            } else {
                mensagens.add("4:" + d.getNome() + "," + d.getEmail());
            }
        } catch (IOException e) {
            mensagens.add("Erro de E/S: %s%n" + e);
        }
        return mensagens;
    }

    public List<String> mostrarTodosDoadores(List<Doacao> doacoes) {

        List<String> mensagens = new ArrayList<>();

        Path path = Paths.get("recursos", "dadosentrada.txt");

        try (BufferedReader br = Files.newBufferedReader(path,
                Charset.forName("UTF8"))) {

            if (doadores.isEmpty()) {
                mensagens.add("6:nenhum doador encontrado.");
                return mensagens;
            }

            for (Doador d : doadores) {
                int qntDoacoes = 0;
                for (Doacao doa : doacoes) {
                    if (doa.getDoador().equals(d)) {
                        qntDoacoes++;
                    }
                }
                mensagens.add("6:" + d.getNome() + "," + d.getEmail() + "," + qntDoacoes);
            }

        } catch (IOException e) {
            mensagens.add("Erro de E/S: %s%n" + e);
        }
        return mensagens;

    }

    public List<String> mostrarDoadorComMaiorSomatorioDeValoresDoados(List<Doacao> doacoes) {

        List<String> mensagens = new ArrayList<>();

        Path path = Paths.get("recursos", "dadosentrada.txt");

        try (BufferedReader br = Files.newBufferedReader(path,
                Charset.forName("UTF8"))) {

            if (doadores.isEmpty()) {
                mensagens.add("10:nenhum doador encontrado.");
                return mensagens;
            }

            Doador maiorDoador = null;
            double maiorMontante = 0.0;

            for (Doador d : doadores) {
                double soma = 0.0;

                for (Doacao doa : doacoes) {
                    if (doa.getDoador().equals(d)) {
                        soma += doa.getValor();
                    }
                }

                if (soma >= maiorMontante) {
                    maiorMontante = soma;
                    maiorDoador = d;
                }
            }

            if (maiorDoador == null) {
                mensagens.add("10:ERRO:nenhum doador localizado.");
            } else {
                String montanteFormatado = String.format(Locale.ENGLISH, "%.2f", maiorMontante);
                mensagens.add("10:" + maiorDoador.getNome() + "," + maiorDoador.getEmail() + "," + montanteFormatado);
            }

        } catch (IOException e) {
            mensagens.add("Erro de E/S: %s%n" + e);
        }
        return mensagens;
    }

}
