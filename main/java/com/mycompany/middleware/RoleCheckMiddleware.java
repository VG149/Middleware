/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.middleware;

/**
 * Classe que verifica o papel do usuário (RoleCheck).
 * Estende a classe Middleware e implementa a checagem de autenticação.
 * 
 * @author FATEC ZONA LESTE
 */
public class RoleCheckMiddleware extends Middleware {
    
    /**
     * Implementa o método check para verificar se o email corresponde a um administrador.
     * 
     * @param email O email do usuário a ser verificado.
     * @param password A senha do usuário (não utilizada nesta checagem).
     * @return true se o usuário for um administrador, ou chama o próximo Middleware na cadeia.
     */
    public boolean check(String email, String password) {
        // Verifica se o email é do administrador
        if (email.equals("admin@example.com")) {
            System.out.println("Hello, admin!"); // Mensagem para o administrador
            return true; // Retorna verdadeiro se for administrador
        }
        
        System.out.println("Hello, user!"); // Mensagem para usuários comuns
        return checkNext(email, password); // Chama o próximo Middleware na cadeia
    }
}
