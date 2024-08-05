package dev.tiktaalik.classes;

public class CambiarWhile {
    public static void main(String[] args) {
        // Este es el código original
        System.out.println("\nCódigo original\n---------------");
        int i = 0;
        while (i<50) {
            i+=1;
            System.out.printf("Iteración %d\n", i);
        }

        // Este es el código modificado, pero que cumple la misma función
        System.out.println("\n\nCódigo modificado\n-----------------");
        i = 0;
        do {
            i+=1;
            System.out.printf("Iteración %d\n", i);
        }
        while (i<50);
    }
}
