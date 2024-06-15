public class PrimitivosEnteros {
    public static void main(String[] args) {
        byte numeroByte = 127;
        System.out.println("numeroByte = " + numeroByte);
        System.out.println("Tipo byte corresponde en byte a " + Byte.BYTES );
        System.out.println("Tipo byte corresponde en bites a " + Byte.SIZE );
        System.out.println("Valor máximo en byte " + Byte.MAX_VALUE );
        System.out.println("Valor minimo en byte " + Byte.MIN_VALUE );

        System.out.println("######################");

        short numeroShort = 32767;
        System.out.println("numeroShort = " + numeroShort);
        System.out.println("Tipo short corresponde en byte a " + Short.BYTES );
        System.out.println("Tipo short corresponde en bites a " + Short.SIZE );
        System.out.println("Valor máximo en byte " + Short.MAX_VALUE );
        System.out.println("Valor minimo en byte " + Short.MIN_VALUE );

        System.out.println("######################");

        int numeroInt = 2147483647;
        System.out.println("numeroInt = " + numeroInt);
        System.out.println("Tipo int corresponde en byte a " + Integer.BYTES );
        System.out.println("Tipo int corresponde en bites a " + Integer.SIZE );
        System.out.println("Valor máximo en byte " + Integer.MAX_VALUE );
        System.out.println("Valor minimo en byte " + Integer.MIN_VALUE );

        System.out.println("######################");

        long numeroLong = 9223372036854775807L;
        System.out.println("numeroLong = " + numeroLong);
        System.out.println("Tipo Long corresponde en byte a " + Long.BYTES );
        System.out.println("Tipo Long corresponde en bites a " + Long.SIZE );
        System.out.println("Valor máximo en byte " + Long.MAX_VALUE );
        System.out.println("Valor minimo en byte " + Long.MIN_VALUE );

        System.out.println("######################");

        float numeroFloat = 9223372036854775807F;
        System.out.println("numeroFloat = " + numeroFloat);
        System.out.println("Tipo Float corresponde en byte a " + Float.BYTES );
        System.out.println("Tipo Float corresponde en bites a " + Float.SIZE );
        System.out.println("Valor máximo en byte " + Float.MAX_VALUE );
        System.out.println("Valor minimo en byte " + Float.MIN_VALUE );
    }
}
