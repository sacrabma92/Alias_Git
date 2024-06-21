public class WrapperInteger {
    public static void main(String[] args) {
        
        int intPrimitivo = 4231;
        Integer intObjeto = Integer.valueOf(intPrimitivo); 
        Integer intObjeto2 = 3231;
        System.out.println("intObjeto = " + intObjeto);
        
        int num = intObjeto;
        int num2 = intObjeto.intValue();
        System.out.println("num2 = " + num2);
        
        String valorTvLcd = "67000";
        Integer valor = Integer.valueOf(valorTvLcd);
        System.out.println("valor = " + valor);
    }
}
