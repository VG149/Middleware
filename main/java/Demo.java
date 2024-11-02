/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Classe de demonstração. Reúne todos os componentes do sistema.
 * 
 * @author FATEC ZONA LESTE
 */
import com.mycompany.middleware.*;
import server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Classe Demo que demonstra o funcionamento do sistema de autenticação.
 */
public class Demo {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // Leitor para entrada do usuário
    private static Server server; // Instância do servidor

    /**
     * Método que inicializa o servidor e registra usuários com credenciais.
     * Também configura a cadeia de middleware que será utilizada para autenticação.
     */
    private static void init() {
        server = new Server(); // Cria uma nova instância do servidor
        server.register("admin@example.com", "admin_pass"); // Registra um usuário administrador
        server.register("user@example.com", "user_pass"); // Registra um usuário comum

        // Todos os checadores são ligados em uma cadeia. O cliente pode construir
        // várias cadeias usando os mesmos componentes.
        Middleware middleware = Middleware.link(
            new ThrottlingMiddleware(2), // Limita a 2 requisições por minuto
            new UserExistsMiddleware(server), // Verifica se o usuário existe
            new RoleCheckMiddleware() // Verifica o papel do usuário
        );

        // O servidor recebe a cadeia de middleware do código do cliente.
        server.setMiddleware(middleware);
    }

    /**
     * Método principal que inicia a aplicação.
     * Solicita ao usuário que insira credenciais até que a autenticação seja bem-sucedida.
     */
    public static void main(String[] args) throws IOException {
        init(); // Inicializa o servidor e a configuração do middleware

        boolean success; // Variável para controlar o sucesso da autenticação
        do {
            System.out.print("Enter email: "); // Solicita o email do usuário
            String email = reader.readLine(); // Lê o email
            System.out.print("Input password: "); // Solicita a senha do usuário
            String password = reader.readLine(); // Lê a senha
            success = server.logIn(email, password); // Tenta autenticar o usuário
        } while (!success); // Repete até que a autenticação seja bem-sucedida
    }
}
