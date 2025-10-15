package aplicacao;

import dados.CatalogoDoadores;

public class ACMEDonations {
    private CatalogoDoadores catalogoDoadores;

    public ACMEDonations(){
        catalogoDoadores = new CatalogoDoadores();

    }

    public void executar(){
        passo1(null, null);
       

    }

    private void passo1(String nome, String email){
        catalogoDoadores.lerArquivo();
        System.out.println("DEBUG: "+ nome + "-" + email);

    }

    private void passo2(){

    }


    
    
}
