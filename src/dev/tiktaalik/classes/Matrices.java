package dev.tiktaalik.classes;

import java.util.Scanner;

public class Matrices {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int[][] valores = new int[3][3];
        int acumTodos = 0;
        int[] sumaFilas = {0,0,0};
        int[] sumaColumnas = {0,0,0};



        for (int i=0; i < valores.length; i++){
            for (int j=0; j < valores[0].length; j++){
                System.out.printf("Ingrese un número para la posición [%d][%d]: ",i+1,j+1);
                valores[i][j] = scanner.nextInt();
            }
        }

        for(int i=0; i < valores.length; i++){
            for(int j=0; j < valores[0].length; j++){
                System.out.print(valores[i][j] + "\t");
                acumTodos += valores[i][j];
                sumaFilas[i] += valores[i][j];
                sumaColumnas[i] += valores[j][i];
            }
            System.out.println();
        }

        System.out.println("Total: " + acumTodos);
        System.out.println(sumaFilas[0] + " | " + sumaFilas[1] + " | " + sumaFilas[2]);
        System.out.println(sumaColumnas[0] + " | " + sumaColumnas[1] + " | "  + sumaColumnas[2]);

    }
}
