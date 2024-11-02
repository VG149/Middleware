/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.middleware;

/**
 * Classe que implementa o controle de taxa de requisições (ThrottlingMiddleware).
 * Estende a classe Middleware e limita o número de requisições permitidas por minuto.
 * 
 * @author FATEC ZONA LESTE
 */
public class ThrottlingMiddleware extends Middleware {
    private int requestPerMinute; // Limite de requisições por minuto
    private int request; // Contador de requisições atuais
    private long currentTime; // Timestamp do último reset de requisições

    /**
     * Construtor que inicializa o limite de requisições por minuto.
     * 
     * @param requestPerMinute O número máximo de requisições permitidas por minuto.
     */
    public ThrottlingMiddleware(int requestPerMinute) {
        this.requestPerMinute = requestPerMinute; // Define o limite
        this.currentTime = System.currentTimeMillis(); // Captura o tempo atual
    }

    /**
     * Método que verifica se a requisição pode ser realizada.
     * O checkNext() pode ser chamado tanto no início quanto no final deste método,
     * proporcionando flexibilidade na ordem das checagens.
     *
     * @param email O email do usuário (não utilizado nesta checagem).
     * @param password A senha do usuário (não utilizada nesta checagem).
     * @return true se o limite de requisições não foi excedido, chama o próximo Middleware na cadeia.
     */
    public boolean check(String email, String password) {
        // Reseta o contador se um minuto se passou desde o último reset
        if (System.currentTimeMillis() > currentTime + 60_000) {
            request = 0; // Reseta o contador de requisições
            currentTime = System.currentTimeMillis(); // Atualiza o timestamp
        }

        request++; // Incrementa o contador de requisições
        
        // Verifica se o número de requisições ultrapassou o limite
        if (request > requestPerMinute) {
            System.out.println("Request limit exceeded!"); // Mensagem de erro
            Thread.currentThread().stop(); // Para o thread atual (não recomendado, usar exceção seria melhor)
        }
        
        return checkNext(email, password); // Chama o próximo Middleware na cadeia
    }
}
