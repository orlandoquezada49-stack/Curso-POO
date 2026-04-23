package Calculadora;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        Imc calculadoraIMC = new Imc();
        Scanner teclado = new Scanner(System.in);

        try {
            System.out.println("Ingresa tu Peso");
            calculadoraIMC.setPeso(teclado.nextDouble());
        } catch (Exception e){
            System.out.println("Error, revisa tu entrada");

        }

        try {
            System.out.println("Ingresa tu Altura");
            calculadoraIMC.setAltura(teclado.nextDouble());
        } catch (Exception e){
            System.out.println("Error, revisa tu entrada");

        }

       calculadoraIMC.calcularImc();
        System.out.println("Su IMC es : " + calculadoraIMC.getImc());
    }


}