/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import com.mycompany.middleware.Middleware; // Importa a classe Middleware

import java.util.HashMap;
import java.util.Map;

/**
 * Classe que representa o servidor.
 * Gerencia usuários e autenticação através de um sistema de middleware.
 * 
 * @author FATEC ZONA LESTE
 */
public class Server {
    private Map<String, String> users = new HashMap<>(); // Mapa que armazena emails e senhas
    private Middleware middleware; // Referência ao middleware para autenticação

    /**
     * Permite que o cliente passe uma cadeia de objetos Middleware para o servidor.
     * Isso melhora a flexibilidade e torna os testes da classe Server mais fáceis.
     * 
     * @param middleware A cadeia de middleware a ser usada para autenticação.
     */
    public void setMiddleware(Middleware middleware) {
        this.middleware = middleware; // Inicializa o middleware
    }

    /**
     * Método para autenticar o usuário com email e senha.
     * O servidor envia a solicitação de autorização para a cadeia de middleware.
     * 
     * @param email O email do usuário.
     * @param password A senha do usuário.
     * @return true se a autorização for bem-sucedida; caso contrário, false.
     */
    public boolean logIn(String email, String password) {
        if (middleware.check(email, password)) { // Chama o método check do middleware
            System.out.println("Authorization have been successful!"); // Mensagem de sucesso

            // Aqui você pode implementar ações úteis para usuários autorizados.

            return true; // Retorna verdadeiro se a autorização for bem-sucedida
        }
        return false; // Retorna falso se a autorização falhar
    }

    /**
     * Método para registrar um novo usuário.
     * 
     * @param email O email do novo usuário.
     * @param password A senha do novo usuário.
     */
    public void register(String email, String password) {
        users.put(email, password); // Adiciona o usuário ao mapa
    }

    /**
     * Verifica se um email está registrado no sistema.
     * 
     * @param email O email a ser verificado.
     * @return true se o email estiver registrado; caso contrário, false.
     */
    public boolean hasEmail(String email) {
        return users.containsKey(email); // Retorna verdadeiro se o email existir
    }

    /**
     * Verifica se a senha fornecida é válida para o email especificado.
     * 
     * @param email O email do usuário.
     * @param password A senha a ser verificada.
     * @return true se a senha for válida; caso contrário, false.
     */
    public boolean isValidPassword(String email, String password) {
        return users.get(email).equals(password); // Compara a senha armazenada com a senha fornecida
    }
}
