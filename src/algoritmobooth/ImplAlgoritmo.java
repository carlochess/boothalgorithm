package algoritmobooth;

import java.util.ArrayList;

// El siguiente código es una mejora de
// http://javaengineeringprograms.blogspot.com/2013/08/booth-algorithm-coa.html

public class ImplAlgoritmo {
    /*------------------------------------------*/
    // Atributos
    /*------------------------------------------*/
    int nVeces;
    
    /*------------------------------------------*/
    // Métodos
    /*------------------------------------------*/
    public ImplAlgoritmo(){
        nVeces = 0;
    }
    /* Función que permite mostrar el resultado*/
    private String display(int a[], int q[], int Q1, int m[], String codOP) {
        String g="";
        for (int i = 0; i < nVeces; i++) {
            g+=a[i];
        }
        g+="       ";
        for (int i = 0; i < nVeces; i++) {
            g+=(q[i]);
        }
        g+=("       ");
        g+=(Q1);
        g+=("       ");
        for (int i = 0; i < nVeces; i++) {
            g+=(m[i]);
        }
        g+=("       ");
        g+=codOP;
        return g;
    }
        
    /** Función que convierte de decimal a binario  **/
    private int[] decABin(int n, int longitud) {
        int[] bin = new int[longitud];
        int ctr = longitud-1;
        int num = n;

        /** Para los números negativos **/
        if (n < 0) {
            int num2pow =  (int)Math.pow(2,longitud);
            num = num2pow + n;
        }
        while (num != 0) {
            bin[ctr--] = num % 2;
            num /= 2;
        }

        return bin;
    }
    /*Función que halla el número de dígitos necesarios para representar
     un número en complemento a dos*/
    private int rango(int n)
    {
        n = (n>=0)? n+1 : n;
        return (int)(Math.ceil(Math.log10(Math.abs(n))/Math.log10(2))+1);
    }
    /*Realiza el desplazamiento aritmetico entre A y Q*/
    private void desplazamientoAritmetico(int a[], int q[]) {
        int ultimoIndice = a.length-1;
        int b = a[ultimoIndice];
        for (int i = ultimoIndice-1; i >= 0; i--) {
            a[i + 1] = a[i];
            q[i + 1] = q[i];
        }
        q[0] = b;
    }
    
    /*Función que realiza la suma entre dos números binarios*/
    private int[] sumar(int a[], int m1[]) {
        int carry = 0;
        int sum[] = new int[a.length];
        for (int i = a.length-1; i >= 0; i--) {
            sum[i] = (a[i] + m1[i] + carry) % 2;
            carry = (a[i] + m1[i] + carry) / 2;
        }
        return sum;
    }
    /* Función que saca el complemento de un número binario */
    private int[] complemento(int m1[]) {
        int z[] = decABin(1, nVeces);
        for (int i = 0; i < nVeces; i++) {
            m1[i] =(m1[i] == 0)? 1:0;
        }
        return sumar(m1, z);
    }          
    /*------------------------------------------*/
    // Función que efectua el algotimo de booth
    private ArrayList<String> boothAlgorith(int[] m, int[] q) {
        int a[] = decABin(0, nVeces);
        int Q1 = 0;
        int count = nVeces;
        int m1[] = new int[nVeces];
        ArrayList<String> arr = new ArrayList<String>();
        
        arr.add("A       Q       Q-1       M       operación");
        arr.add("---------------------------");
        arr.add(display(a,q,Q1,m,"Inicial"));
        
        int nVecesMenosUna = nVeces-1;
        
        for (int i = 0; i < count; i++) {
            arr.add(i+"---------------------------");
            System.arraycopy(m, 0, m1, 0, nVeces);
            
            if (q[nVecesMenosUna] == 0 && Q1 == 1) {
                a = sumar(a, m1);
                arr.add(display(a,q,Q1,m," A<~A+M"));
            }
            else if (q[nVecesMenosUna] == 1 && Q1 == 0) {
                int c[] = complemento(m1);
                a = sumar(a, c);
                arr.add(display(a,q,Q1,m," A<~A-M"));
            }
            
            Q1 = q[nVecesMenosUna];
            desplazamientoAritmetico(a, q);
            arr.add(display(a,q,Q1,m," desplazamiento"));         
        }        
        return arr;
    }
    /*------------------------------------------*/
    // Función inicial
    /*------------------------------------------*/
    public ArrayList<String> init(int x,int y)
    {
        nVeces = (rango(x)>rango(y))?rango(x):rango(y);
        int[] x1 = decABin(x, nVeces);
        int[] y1 = decABin(y, nVeces);
                
        return boothAlgorith(x1,y1);
    }
    
}