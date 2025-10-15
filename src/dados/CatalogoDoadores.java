package dados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.*;


public class CatalogoDoadores {
    private List<Doador> doadores;

    public CatalogoDoadores(){
            doadores = new ArrayList<>();
        }

            
    public void lerArquivo(){
        Path path = Paths.get("recursos" , "doadores.csv"); 
        try (BufferedReader br = Files.newBufferedReader(path,
                Charset.forName("UTF8"))) {
            String linha = null;
            while ((linha = br.readLine()) != null) {
                // separador: ;
                Scanner sc = new Scanner(linha).useDelimiter(";");
                String nome;
                String email;
                nome = sc.next();
                email = sc.next();
               

            }
        }
        catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);
        }

    }

}
    

