package com.rpg.game;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputManager {
    
    static Scanner scanner = new Scanner(System.in);

    //Métodos
    public static int lerInteiro(String mensagem, int min, int max){
        while (true){

            System.out.print(mensagem);
            String input = null;

            try{
                input = scanner.nextLine().trim();
                if (input.isEmpty()){
                    System.out.println("Entrada vazia, digite um numero entre " + min + " e " + max + ".");
                    continue;
                }

                int valor = Integer.parseInt(input);
                if (valor < min || valor > max){
                    System.out.println("Numero fora do intervalo. Digite um numero entre " + min + " e " + max + ".");
                    continue;
                }

                return valor;
            } catch (NumberFormatException e){
                System.out.println("Valor invalido. Digite um numero inteiro.");
            } catch (NoSuchElementException e) {
                throw new RuntimeException("Entrada nao disponivel", e);
            }
        }
    }

    public static String lerString(String mensagem){
        String input = null;

        while (true){      
            try{
                System.out.print(mensagem);
                input = scanner.nextLine().trim();

                if (input.isEmpty()){
                    System.out.println("Entrada vazia, digite uma linha valida");
                    continue;
                }

                for (char c : input.toCharArray()){
                    if (Character.isDigit(c))
                        throw new IllegalArgumentException("A linha não deve conter numeros!");
                }
                break;

            } catch (IllegalArgumentException e){
                System.out.println("Erro: " + e.getMessage());
            }

        }
        
        return input;
    }

    public static boolean lerSimNao(String mensagem){
        while (true){
            System.out.print(mensagem);
            String input = null;

            try{
                input = scanner.nextLine();

                input = input.toLowerCase();

                if (input.equals("s"))
                    return true;
                else if (input.equals("n"))
                    return false;
                else{
                    System.out.println("Entrada invalida. Responda com s ou n.");
                }
            } catch (NoSuchElementException e) {
                throw new RuntimeException("Entrada nao disponivel.", e);
            } catch (IllegalStateException e) {
                System.out.println("Scanner foi fechado.");
            }
        }
    }

    public static void esperarEnter(String mensagem){
        System.out.print(mensagem);
        scanner.nextLine();
    }

    public static void fecharScanner(){
        scanner.close();
    }
}
