package ifpr.pgua.eic.listatelefonica.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListaTelefonica {
    
    private ArrayList<Contato> contatos;

    public ListaTelefonica(){
        contatos = new ArrayList<>();
        criaFakes();
    }

    private void criaFakes(){
        adicionarContato("Zé", "111-1111", "ze@teste.com");
        adicionarContato("Zé Leoncio", "111-2222", "zeleoncio@teste.com");
        adicionarContato("Zéro Berto", "111-3333", "zero@teste.com");
        adicionarContato("Maria", "111-4444", "maria@teste.com");
        adicionarContato("Chico", "111-5555", "chico@teste.com");
        
        
    }

    /**
     * Adiciona um novo contato na lista, caso não existe nenhum contato prévio com o
     * mesmo e-mail.
     *      
     *  
     * @param nome Nome do contato
     * @param telefone Telefone do contato
     * @param email Email do contato
     * @return true se conseguiu adicionar, false caso contrário
     */
    public boolean adicionarContato(String nome, String telefone, String email){
        
        
        if(buscar(email) == null){
            Contato contato = new Contato(nome, email, telefone);
            contatos.add(contato);
            return true;
        }
        return false;
    }

    /**
     * Busca um contato por e-mail.
     * 
     * @param email O e-mail a ser buscado.
     * @return Contato ou null, caso não encontrar.
     */

    public Contato buscar(String email){
        return contatos.stream().filter((contato)->contato.getEmail().equals(email)).findFirst().orElse(null);
    }

    /**
     * Acesso a lista de contatos;
     *  
     * @return Lista de contatos
     */
    public List<Contato> getContatos(){
        return contatos;
    }

    /**
     * Filtra a lista, buscando contatos que iniciam pela string
     * passada por parâmetro.
     * 
     * @param inicio String com o inicio do nome a ser buscado
     * @return Lista com contatos que possuem o inicio passado por parâmetro.
     */
    public List<Contato> buscaPorNome(String inicio){
        return contatos.stream().filter((contato)->contato.getNome().startsWith(inicio)).collect(Collectors.toList());
    }

}
