public class PrimitivosFloat {

    static float varFlotante = 45.36F;

    public static void main(String[] args) {
        float real = 2350F;
        System.out.println("numeroFloat= " + real);
        System.out.println("Tipo Float corresponde en byte a " + Float.BYTES );
        System.out.println("Tipo Float corresponde en bites a " + Float.SIZE );
        System.out.println("Valor máximo en byte " + Float.MAX_VALUE );
        System.out.println("Valor minimo en byte " + Float.MIN_VALUE );

        System.out.println("######################");

        double realDoubble = 3.4028235E38D;
        System.out.println("numeroDouble= " + realDoubble);
        System.out.println("Tipo Double corresponde en byte a " + Double.BYTES );
        System.out.println("Tipo Double corresponde en bites a " + Double.SIZE );
        System.out.println("Valor máximo en byte " + Double.MAX_VALUE );
        System.out.println("Valor minimo en byte " + Double.MIN_VALUE );

        System.out.println("######################");

        // float varFlotante = 3.1416F;
        System.out.println("varFlotante = " + varFlotante);
    }
}
