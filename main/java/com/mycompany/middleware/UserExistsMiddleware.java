/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.middleware;

import server.Server; // Importa a classe Server para verificar credenciais

/**
 * Classe concreta que verifica se um usuário com as credenciais fornecidas existe.
 * Extende a classe Middleware para realizar a verificação de autenticação.
 * 
 * @author FATEC ZONA LESTE
 */
public class UserExistsMiddleware extends Middleware {
    private Server server; // Instância do servidor para verificar usuários

    /**
     * Construtor que recebe uma instância de Server.
     * 
     * @param server O servidor que contém os dados dos usuários.
     */
    public UserExistsMiddleware(Server server) {
        this.server = server; // Inicializa o servidor
    }

    /**
     * Método que verifica se o email está registrado e se a senha é válida.
     * 
     * @param email O email do usuário a ser verificado.
     * @param password A senha do usuário a ser verificada.
     * @return true se o usuário existir e a senha for correta; caso contrário, false.
     */
    public boolean check(String email, String password) {
        // Verifica se o email está registrado no servidor
        if (!server.hasEmail(email)) {
            System.out.println("This email is not registered!"); // Mensagem de erro para email não registrado
            return false; // Retorna falso se o email não existir
        }
        // Verifica se a senha é válida para o email fornecido
        if (!server.isValidPassword(email, password)) {
            System.out.println("Wrong password!"); // Mensagem de erro para senha incorreta
            return false; // Retorna falso se a senha estiver incorreta
        }
        return checkNext(email, password); // Chama o próximo Middleware na cadeia
    }
}
