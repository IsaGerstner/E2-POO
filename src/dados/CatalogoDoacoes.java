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

public class CatalogoDoacoes {
     private List<Doacao> doacoes;
     private CatalogoDoadores catalogoDoadores;

     public CatalogoDoacoes(){
        doacoes = new ArrayList<>();
        catalogoDoadores = new CatalogoDoadores();
     }

     public void LerArquivoDoacoesPereciveis() throws Exception{

        Path path = Paths.get("recursos" , "doacoespereciveis.csv"); 
        try (BufferedReader br = Files.newBufferedReader(path,
                Charset.forName("UTF8"))) {
            String linha = null;

            while ((linha = br.readLine()) != null) {
       
                Scanner sc = new Scanner(linha).useDelimiter(";");
                String descricao = sc.next();
                double valor = sc.nextDouble();
                int quantidade = sc.nextInt();
                String nome = sc.next();
                int validade = sc.nextInt();
                String email = sc.next();

                Doador doador = catalogoDoadores.buscarPorEmail(email);
                    if (doador == null) {
                        throw new Exception("2:ERRO:doador inexistente");
                    }

                TipoPerecivel tipoPerecivel;
                    if (nome.equals("ALIMENTO")) {
                        tipoPerecivel = TipoPerecivel.ALIMENTO;
                    } else if (nome.equals("MEDICAMENTO")) {
                        tipoPerecivel = TipoPerecivel.MEDICAMENTO;
                    } else {
                        throw new Exception("2:ERRO:tipo invalido");
                    }

                Perecivel p = new Perecivel(descricao, valor, quantidade, validade, tipoPerecivel, doador);
                doacoes.add(p);

            }
        } 
            catch (NumberFormatException e) {
                    throw new Exception("2:ERRO:formato invalido");
        }
            catch (IOException e) {
                   throw new Exception("Erro de E/S: %s%n", e);
        }

    }


    public List<Doacao> getDoacoes() {
        return doacoes;
    }


}

     
    


