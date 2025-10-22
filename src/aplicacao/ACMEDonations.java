package aplicacao;

import java.io.File;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;

import dados.CatalogoDoadores;
import dados.CatalogoDoacoes;

public class ACMEDonations {

    private PrintStream saidaPadrao = System.out;

    private CatalogoDoadores catalogoDoadores;
    private CatalogoDoacoes catalogoDoacoes;

    public ACMEDonations() {
        catalogoDoadores = new CatalogoDoadores();
        catalogoDoacoes = new CatalogoDoacoes(catalogoDoadores);
        redirecionaSaida();

    }

    private void redirecionaSaida() {
        try {
            saidaPadrao = new PrintStream(new File("recursos", "relatorio.txt"), Charset.forName("UTF-8"));
            System.setOut(saidaPadrao);
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);
    }

    public void executar() {
        passo1();
        passo2();
        passo3();
        passo4();
        passo5();
        passo6();
        passo7();
        passo8();
        passo9();
        passo10();
    }

    private void passo1() {
        for (String s : catalogoDoadores.lerArquivoDoadores()) {
            System.out.println(s);
        }
    }

    private void passo2() {
        for (String s : catalogoDoacoes.lerArquivoDoacoesPereciveis()) {
            System.out.println(s);

        }
    }

    private void passo3() {
        for (String s : catalogoDoacoes.lerArquivoDoacoesDuraveis()) {
            System.out.println(s);
        }
    }

    private void passo4() {
        for (String s : catalogoDoadores.mostrarDoadorPorEmail()) {
            System.out.println(s);
        }
    }

    private void passo5() {
        for (String s : catalogoDoacoes.mostrarDoacoes()) {
            System.out.println(s);
        }
    }

    private void passo6() {
        for (String s : catalogoDoadores.mostrarTodosDoadores(catalogoDoacoes.getDoacoes())) {
            System.out.println(s);
        }
    }

    private void passo7() {
        for (String s : catalogoDoacoes.mostrarDoacoesPorNome()) {
            System.out.println(s);
        }
    }

    private void passo8() {
        for (String s : catalogoDoacoes.mostarDoacoesDuraveisPorTipo()) {
            System.out.println(s);
        }
    }

    private void passo9() {
        for (String s : catalogoDoacoes.mostrarPerecivelMaiorQuantidade()) {
            System.out.println(s);
        }
    }

    private void passo10() {
        for (String s : catalogoDoadores.mostrarDoadorComMaiorSomatorioDeValoresDoados(catalogoDoacoes.getDoacoes())) {
            System.out.println(s);
        }
    }

}
