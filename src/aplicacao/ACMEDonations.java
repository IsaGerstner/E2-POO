package aplicacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;

import dados.CatalogoDoadores;
import dados.CatalogoDoacoes;
import dados.Doador;
import dados.Perecivel;

public class ACMEDonations {

     private Scanner entrada = new Scanner(System.in);  
    private PrintStream saidaPadrao = System.out;   
    private final String nomeArquivoEntrada = "dadosentrada.txt";  
    private final String nomeArquivoSaida = "relatorio.txt";  

    private CatalogoDoadores catalogoDoadores;
    private CatalogoDoacoes catalogoDoacoes;

    public ACMEDonations(){
        catalogoDoadores = new CatalogoDoadores();
        redirecionaEntrada();
        redirecionaSaida();

    }

    private void redirecionaEntrada() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader("dadosentrada.txt"));
            entrada = new Scanner(streamEntrada);
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);
        entrada.useLocale(Locale.ENGLISH);
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
       

    }

    private void passo1(){
        try{

            catalogoDoadores.lerArquivoDoadores();

            for (Doador d : catalogoDoadores.getDoadores()) {
                System.out.println("1:" + d.getNome() + "," + d.getEmail());
            }

        } catch(Exception e){
            System.out.println(e.getMessage());
        
        }
        
    }

    private void passo2(){
        try{
            catalogoDoacoes.LerArquivoDoacoesPereciveis();

            for (Perecivel p : catalogoDoacoes.getDoacoes()){
                System.out.println("2:" + p.getDescricao() + "," + p.getValor() + "," + p.getQuantidade() + "," + p.getTipoPerecivel());
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}


    
    

