public class PrimitivosCaracteres {
    public static void main(String[] args) {
        char unicode = '\u0040';
        char decimal = 64;
        System.out.println("unicode = " + unicode);
        System.out.println("decimal = " + decimal);
        System.out.println("¿ Decimal == Unidode ? : " + (decimal == unicode));

        System.out.println("######################");
        
        char simbol = '@';
        System.out.println("simbol = " + simbol);
        System.out.println("¿ Decimal == Unidode ? : " + (simbol == unicode));

        System.out.println("######################");

        char espacio = '\u0020';
        char retroceso = '\b';
        char tabulador = '\t';

        System.out.println("Char corresponde en byte:" + espacio + Character.BYTES);
        System.out.println("Char corresponde en Bits = " + Character.SIZE);
        System.out.println("Character.MAX_VALUE = " + Character.MAX_VALUE);
        System.out.println("Character.MIN_VALUE = " + Character.MIN_VALUE);

        System.out.println("######################");
    }
}
