//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class HolaMundo {
    public static void main(String[] args) {
        String saludar = "Hola Mundo desde Java";
        System.out.println(saludar);
        System.out.println("salusar.toUpperCase() = " + saludar.toUpperCase());

        int numero = 10;

        boolean valor = true;
        int numero2 = 5;

        if(valor){
            System.out.println("numero = " + numero);
        }

        System.out.println("numero2 = " + numero2);

        var numero3 = "15";

        String nombre;
        nombre = "Andrés";

        if(numero > 10){
            nombre = "Carlos";
        }

        System.out.println("nombre = " + nombre);
    }
}