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

     public CatalogoDoacoes(CatalogoDoadores catalogoDoadores){
        doacoes = new ArrayList<>();
        this.catalogoDoadores = catalogoDoadores;
     }

     public void LerArquivoDoacoesPereciveis() throws Exception{

        Path path = Paths.get("recursos" , "doacoespereciveis.csv"); 
        try (BufferedReader br = Files.newBufferedReader(path,
                Charset.forName("UTF8"))) {
            String linha = null;
            br.readLine();
            

            Scanner sc = null;

            while ((linha = br.readLine()) != null) {

               sc = new Scanner(linha).useDelimiter(";");
              
                String descricao = sc.next();
                String valor = sc.next();
                double doubleValor = Double.parseDouble(valor);
                String quantidade = sc.next();
                int intQuantidade = Integer.parseInt(quantidade);
                String email = sc.next();
                String nome = sc.next();
                String validade = sc.next();
                int intValidade = Integer.parseInt(validade);
                

                Doador doador = catalogoDoadores.buscarPorEmail(email);
                    if (doador == null) {
                        sc.close();
                        throw new Exception("2:ERRO:doador inexistente.");
                    } 

                TipoPerecivel tipoPerecivel;
                    if (nome.equals("ALIMENTO")) {
                        tipoPerecivel = TipoPerecivel.ALIMENTO;
                    } else if (nome.equals("MEDICAMENTO")) {
                        tipoPerecivel = TipoPerecivel.MEDICAMENTO;
                    } else {
                        sc.close();
                        throw new Exception("2:ERRO:tipo invalido.");
                    }

                Perecivel p = new Perecivel(descricao, doubleValor, intQuantidade, intValidade, tipoPerecivel, doador);
                doacoes.add(p);


            }
            sc.close();
        } 
            catch (NumberFormatException e) {
                    throw new Exception("2:ERRO:formato invalido.");
        }
            catch (IOException e) {
                   throw new Exception("Erro de E/S: %s%n", e);
        }
        

    }


    public List<Doacao> getDoacoes() {
        return doacoes;
    }


}

     
    


