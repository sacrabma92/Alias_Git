public class OperadoresAritmeticos {
    public static void main(String[] args) {
        int i = 5;
        int j = i + 4;
        System.out.println("i = " + i);
        System.out.println("j = " + j);
        
        i += 2; // i = i + 2;
        System.out.println("i = " + i);
        
        i += 5; // i = i + 5
        System.out.println("i = " + i);
        
        i -= 4;
        System.out.println("i = " + i);
        
        i *= 3;
        System.out.println("i = " + i);
        
        String sqlString = "SELECT * FROM clientes as c";
        sqlString += " WHERE c.nombre = 'Andres'";
        sqlString += " AND c.activo = 1";
        System.out.println("sqlString = " + sqlString);
    }
}
