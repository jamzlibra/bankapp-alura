import java.util.Scanner;

public class CuentaBancaria {
    private String titular;
    private double saldo;
    private String tipoCuenta;

    public CuentaBancaria(String titular, double saldoInicial, String tipoCuenta) {
        this.titular = titular;
        this.saldo = saldoInicial;
        this.tipoCuenta = tipoCuenta;
    }

    public void depositar(double cantidad) {
        saldo += cantidad;
        System.out.println("Se ha depositado $" + cantidad + " en la cuenta.");
    }

    public void retirar(double cantidad) {
        if (saldo >= cantidad) {
            saldo -= cantidad;
            System.out.println("Se ha retirado $" + cantidad + " de la cuenta.");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public void mostrarSaldo() {
        System.out.println("El saldo actual es: $" + saldo);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("¡Bienvenido al BankApp de Alura!");

        int intentosFallidos = 0;
        boolean accesoConcedido = false;
        while (!accesoConcedido && intentosFallidos < 3) {
            System.out.print("Por favor, introduzca su nombre de usuario: ");
            String usuario = scanner.nextLine();

            System.out.print("Por favor, introduzca su contraseña: ");
            int contraseña = scanner.nextInt();
            scanner.nextLine();

            if (usuario.equals("Tony Stark") && contraseña == 1234) {
                System.out.println("\n¡Bienvenido, Tony!\n");
                accesoConcedido = true;

                // Información del cliente
                String nombreCliente = "Tony Stark";
                String tipoCuenta = "Cuenta Corriente";
                double saldoDisponible = 1599.99;
                System.out.println("**********************************");
                System.out.println("Nombre del cliente: " + nombreCliente);
                System.out.println("Tipo de cuenta: " + tipoCuenta);
                System.out.println("Saldo disponible: $" + saldoDisponible);
                System.out.println("**********************************");

                CuentaBancaria cuenta = new CuentaBancaria(nombreCliente, saldoDisponible, tipoCuenta);

                String comando;
                do {
                    System.out.println("\n--- MENÚ ---");
                    System.out.println("1. Saldo");
                    System.out.println("2. Retirar");
                    System.out.println("3. Depositar");
                    System.out.println("9. Salir");
                    System.out.print("Seleccione una opción o ingrese un comando: ");
                    comando = scanner.nextLine();

                    switch (comando.toLowerCase()) {
                        case "saldo":
                        case "1":
                            cuenta.mostrarSaldo();
                            preguntarOtraTransaccion(scanner);
                            break;
                        case "retirar":
                        case "2":
                            System.out.print("Introduzca la cantidad a retirar: $");
                            double cantidadRetiro = scanner.nextDouble();
                            scanner.nextLine();
                            cuenta.retirar(cantidadRetiro);
                            preguntarOtraTransaccion(scanner);
                            break;
                        case "depositar":
                        case "3":
                            System.out.print("Introduzca la cantidad a depositar: $");
                            double cantidadDeposito = scanner.nextDouble();
                            scanner.nextLine();
                            cuenta.depositar(cantidadDeposito);
                            preguntarOtraTransaccion(scanner);
                            break;
                        case "9":
                            System.out.println("Muchas gracias por usar nuestros servicios.");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Comando inválido. Inténtelo de nuevo.");
                            break;
                    }
                } while (!comando.equals("9"));
            } else {
                intentosFallidos++;
                if (intentosFallidos < 3) {
                    int intentosRestantes = 3 - intentosFallidos;
                    System.out.println("Usuario o contraseña incorrectos. Acceso denegado. Te quedan " + intentosRestantes + " intentos.");
                } else {
                    System.out.println("Usuario o contraseña incorrectos. Acceso denegado.");
                    System.out.println("Su BankApp ha sido bloqueada. Llame al servicio al cliente de BankAlura.");
                }
            }
        }

        scanner.close();
    }

    public static void preguntarOtraTransaccion(Scanner scanner) {
        System.out.println("\n¿Desea realizar otra transacción?");
        System.out.println("1. Sí");
        System.out.println("2. No");
        System.out.print("Seleccione una opción: ");
        String respuesta = scanner.next().toLowerCase(); // Convertir a minúsculas
        if (respuesta.equals("si") || respuesta.equals("1")) {
            scanner.nextLine();
            return; // Continuar con el bucle
        } else if (respuesta.equals("no") || respuesta.equals("2")) {
            System.out.println("Gracias por utilizar nuestros servicios.");
            System.exit(0); // Terminar el programa
        } else {
            System.out.println("Respuesta inválida. Por favor, seleccione 'Sí' o 'No'.");
            preguntarOtraTransaccion(scanner); // Preguntar de nuevo
        }
    }
}
