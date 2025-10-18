package dados;

import java.util.ArrayList;
import java.util.List;
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
}
