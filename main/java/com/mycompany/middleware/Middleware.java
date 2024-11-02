/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.middleware;

/**
 * Classe abstrata que representa a estrutura de Middleware.
 * Esta classe permite a construção de uma cadeia de objetos Middleware.
 * 
 * @author FATEC ZONA LESTE
 */
public abstract class Middleware {
    private Middleware next; // Referência ao próximo Middleware na cadeia

    /**
     * Método estático para construir a cadeia de Middlewares.
     * Recebe o primeiro Middleware e um número variável de Middlewares para encadear.
     */
    public static Middleware link(Middleware first, Middleware... chain) {
        Middleware head = first; // Inicia o cabeçalho da cadeia
        for (Middleware nextInChain: chain) { // Itera sobre cada Middleware na cadeia
            head.next = nextInChain; // Define o próximo Middleware
            head = nextInChain; // Avança para o próximo Middleware na cadeia
        }
        return first; // Retorna o primeiro Middleware da cadeia
    }

    /**
     * Método abstrato que deve ser implementado por subclasses
     * para realizar checagens concretas (ex: validação de email e senha).
     */
    public abstract boolean check(String email, String password);

    /**
     * Método protegido que executa a checagem no próximo objeto na cadeia.
     * Se não houver próximo, retorna true (finaliza a verificação).
     */
    protected boolean checkNext(String email, String password) {
        if (next == null) { // Se não houver próximo Middleware
            return true; // Retorna verdadeiro (fim da cadeia)
        }
        return next.check(email, password); // Chama o método check do próximo Middleware
    }
}
