public class App {
    public static void main(String[] args) throws Exception {
        Persona persona1 = new Persona();

        persona1.nombre = "Carlos";
        persona1.apellido = "Ramirez";
        persona1.edad = 32;

        System.out.println(persona1.darNombreCompleto() + " tiene "+ persona1.edad + " años");
        System.out.println(persona1.EnviarSaludo(persona1.nombre));
    }
}
