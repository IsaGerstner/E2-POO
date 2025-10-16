package dados;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CatalagoDoacoes {
     private List<Doacao> doacoes;

     public CatalagoDoacoes(){
        doacoes = new ArrayList<>();
     }

     public void LerArquivoDoacoesPereciveis() throws Exception{
        Path path = Paths.get("recursos" , "doadores.csv"); 
        try (BufferedReader br = Files.newBufferedReader(path,
                Charset.forName("UTF8"))) {
            String linha = null;
            while ((linha = br.readLine()) != null) {
                // separador: ;
                Scanner sc = new Scanner(linha).useDelimiter(";");
                String descricao;
                double valor;
                int quantidade;
                Doador doador;
                descricao = sc.next();
                valor = sc.nextDouble();
                quantidade = sc.nextInt();
                doador = sc.nextDoador(); //????????

                Doacao d = new Doacao (descricao, valor, quantidade, doador);

                if (Perecivel.getTipoPerecivel() =! TipoPerecivel.getNome()) {
                throw new Exception("2 : ERRO : tipo inválido");
            }
                else if (doador == null){ 
                    throw new Exception("2 : ERRO : doador inexistente");
                }

                doacoes.add(d);
                System.out.println("2 : " + descricao + " , " + valor + "," + quantidade + "," + doador);

            }
        }
        catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);
        }

    }


}

     
    


