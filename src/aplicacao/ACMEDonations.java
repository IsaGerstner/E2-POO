package aplicacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;

import dados.CatalogoDoadores;
import dados.Doacao;
import dados.CatalogoDoacoes;
import dados.Doador;
import dados.Perecivel;


public class ACMEDonations {

     private Scanner entrada = new Scanner(System.in);  
    private PrintStream saidaPadrao = System.out;    
    private final String nomeArquivoSaida = "relatorio.txt";  

    private CatalogoDoadores catalogoDoadores;
    private CatalogoDoacoes catalogoDoacoes;

    public ACMEDonations(){
        catalogoDoadores = new CatalogoDoadores();
        catalogoDoacoes = new CatalogoDoacoes(catalogoDoadores);
        redirecionaSaida();

    }

    private void redirecionaSaida() {
        try {
            saidaPadrao = new PrintStream(new File("relatorio.txt"), Charset.forName("UTF-8"));
            System.setOut(saidaPadrao);
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);
    }

    public void executar(){
        passo1();
        passo2();
    }

    private void passo1(){
        for (String s : catalogoDoadores.lerArquivoDoadores()) {
            System.out.println(s);
            }
    }

    private void passo2(){
        for (String m : catalogoDoacoes.LerArquivoDoacoesPereciveis()){
            System.out.println(m);

        }
    }
}


    
    

