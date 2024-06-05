public class Persona {
    // Atributos (Estado/Caracteristicas de un objeto)
    String nombre;
    String apellido;
    int edad;

    // Métodos (Comportamiento de un objeto)
    public String darNombreCompleto(){
        return apellido + ", " + nombre;
    }

    public String EnviarSaludo(String saludado)
    {
        return "Hola, ¿Como estas, " + saludado + "?";
    }
}
