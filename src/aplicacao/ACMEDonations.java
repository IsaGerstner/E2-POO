package aplicacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;

import dados.CatalogoDoadores;

public class ACMEDonations {

     private Scanner entrada = new Scanner(System.in);  
    private PrintStream saidaPadrao = System.out;   
    private final String nomeArquivoEntrada = "dadosentrada.txt";  
    private final String nomeArquivoSaida = "relatorio.txt";  

    private CatalogoDoadores catalogoDoadores;

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
        passo1(null, null);
       

    }

    private void passo1(String nome, String email){
        try{
            catalogoDoadores.lerArquivoDoadores();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("DEBUG: "+ nome + "-" + email); // o que seria esse debug?? printa no terminal null-null

    }

    private void passo2(){

    }


    
    
}
