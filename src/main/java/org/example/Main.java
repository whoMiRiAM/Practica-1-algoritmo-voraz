package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Main{

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int capacidad = obtenerCapacidad();
        float[] paquetes = obtenerPaquetes();

        voraz(paquetes,capacidad);
    } //FIN MAIN


    public static int obtenerCapacidad(){
        System.out.println("Capacidad maxima?");
        return scanner.nextInt();
    }

    public static float[] obtenerPaquetes(){
        float[] paquetes;
        System.out.println("Numero total de paquetes?");
        paquetes = new float[scanner.nextInt()];
        for(int i = 0; i < paquetes.length; i++){
            System.out.println("Peso: ");
            paquetes[i] = scanner.nextInt();
        }
        System.out.println("Entrada: ");
        System.out.println(Arrays.toString(paquetes));

        return paquetes;
    }

    public static void voraz(float[] paquetes, int capacidad){
        int i;
        float pesoTotal;
        float paquete;
        boolean factible, elegidos[] = new boolean[paquetes.length];
        ordenacion(paquetes);
        i = 0;
        pesoTotal = 0;
        factible = true;
        while(i < paquetes.length && !solucion(pesoTotal,capacidad) && factible){ //la preferencia la tiene quien no sea factible
            //acabar los paquetes, llenar el camión, encontrar un paquete que ya no quepa
            paquete = seleccion(paquetes, i); //seleccion devuelve el siguiente según ordenación
            if(factibilidad(pesoTotal, paquete, capacidad)){
                elegidos[i] = true;
                pesoTotal += paquete;
                i++;
            }else{
                factible = false;
            }
        }
        mostrarSolucion(paquetes, elegidos);
    }

    public static void ordenacion(float[] paquetes){
        Arrays.sort(paquetes);
        System.out.println("Paquetes ordenados creciente por peso: ");
        System.out.println(Arrays.toString(paquetes));
    }

    public static float seleccion(float[] paquetes, int i){
        return paquetes[i];
    }

    public static boolean factibilidad(float pesoTotal, float paquete, int capacidad){
        return pesoTotal + paquete <= capacidad;
    }

    public static boolean solucion(float pesoTotal, int capacidad){
        return pesoTotal == capacidad;
    }

    public static void mostrarSolucion(float[] paquete, boolean[] elegido){
        int i; //cantidad de paquetes que nos hemos llevado
        float factura = 0, pesoT = 0;
        System.out.println("Paquetes transportados");
        i = 0;
        while(i < paquete.length && elegido[i]){
            System.out.println(paquete[i]);
            System.out.println("[" + paquete[i] + "]");
            factura += 40;
            pesoT += paquete[i];
            i++;
        }
        System.out.println("Cantidad de paquetes que nos hemos llevado: " + i);
        System.out.println("Factura: " + factura);
        System.out.println("Peso total: " + pesoT);
    }

}
