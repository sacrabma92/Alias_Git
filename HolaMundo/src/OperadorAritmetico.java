import javax.swing.*;

public class OperadorAritmetico {
    public static void main(String[] args) {
        int i = 5, j = 4;
        int suma = i + j;
        System.out.println("suma = " + suma);

        System.out.println("i + j = " + (i+j));

        int resta = i - j;
        System.out.println("resta = " + resta);
        System.out.println("(i - j) = " + (i - j));
        
        int multi = i * j;
        System.out.println("multiplicacion = " + multi);
        
        float div = (float)i / j;
        System.out.println("div = " + div);

        int rest = i % j;
        System.out.println("resto = " + rest);

        int numero = Integer.parseInt(JOptionPane.showInputDialog("ingrese un numero"));
        if(numero % 2 == 0){
            System.out.println("Numero par = " + numero);
        }else{
            System.out.println("numero impar = " + numero);
        }
    }
}
