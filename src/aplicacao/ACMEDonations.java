package aplicacao;

import java.io.PrintStream;
import java.util.Scanner;

import dados.CatalogoDoadores;

public class ACMEDonations {

     private Scanner entrada = new Scanner(System.in);  // Atributo para entrada padrao (teclado)
    private PrintStream saidaPadrao = System.out;   // Guarda a saida padrao - tela (console)
    private final String nomeArquivoEntrada = "dadosentrada.txt";  // Nome do arquivo de entrada de dados
    private final String nomeArquivoSaida = "relatorio.txt";  // Nome do arquivo de saida de dados

    private CatalogoDoadores catalogoDoadores;

    public ACMEDonations(){
        catalogoDoadores = new CatalogoDoadores();

    }

    public void executar(){
        passo1(null, null);
       

    }

    private void passo1(String nome, String email){
        try{
            catalogoDoadores.lerArquivo();
        } catch(Exception e){
            System.out.println(e);
        }
        System.out.println("DEBUG: "+ nome + "-" + email);

    }

    private void passo2(){

    }


    
    
}
