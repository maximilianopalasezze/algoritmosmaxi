import java.util.Scanner;

public class Exp2_s4_Maximiliano_Palasezze {
public static void main(String[] args) throws InterruptedException {
                    try (Scanner scanner = new Scanner(System.in)) {
                    boolean salir = false;
                    int totalAcumulado = 0; // Acumula el total de todas las compras
            
                        System.out.println("Bienvenido al Teatro Moro");
                        System.out.println("abriendo menu de compra un monento por favor");
                        Thread.sleep(1*1000);
             
                    do {
                        System.out.println("\n   MENU PRINCIPAL    ");
                        System.out.println("1. Comprar entrada");
                        System.out.println("2. Salir");
                        System.out.print("Seleccione una opción para continuar: ");
                
                        String opcion = scanner.nextLine(); // Lee la opción del usuario
                
                   switch (opcion) {
                    case "1" -> {
                        System.out.println("\n--- Plano del Teatro ---");
                        System.out.println("VIP         -_- -_- -_- -_- -_- -_- ");
                        System.out.println("Platea baja -_- -_- -_- -_- -_- -_-");
                        System.out.println("Platea alta -_- -_- -_- -_- -_- -_-");
                        System.out.println("Palcos      -_- -_- -_- -_- -_- -_-");
                        System.out.println("-------------------------------");
                        System.out.println("Zonas disponibles: 1. VIP, 2. Platea baja, 3. Plate alta, 4. Palcos\n");

                        //  tipos de entradas 
                        System.out.println("\n    Tipos de asientos   :");
                        System.out.println("1. VIP ($20000)");
                        System.out.println("2. Platea Baja ($10000)");
                        System.out.println("3. Platea Alta ($9000)");
                        System.out.println("4. Palcos ($6500)");
                        
                        String seleccion; 
                    boolean EntradaValida; 
                    int precioBase = 0; 
                        String tipoEntrada = "";
                        
                    do {
                        System.out.print("seleccione su asiento (1, 2, 3, 4): ");
                        seleccion = scanner.nextLine().trim();
                        EntradaValida = true;  
                      // Solicitar tipo de entrada
                    switch (seleccion) {
                    case "1" -> {
                        precioBase = 20000;
                        tipoEntrada = "VIP";
                                }
                    case "2" -> {
                        precioBase = 10000;
                        tipoEntrada = "Platea alta";
                                }
                    case "3" -> {
                        precioBase = 9000;
                        tipoEntrada = "platea baja";
                                }
                    case "4" -> {
                        precioBase = 6500;
                        tipoEntrada = "Palcos";
                                }
                    default -> {
                        System.out.println("Entrada no válida. Intente nuevamente.");
                        EntradaValida = false;
                                }
                            }
                  } while (!EntradaValida);
                        // solicitar edad al usuario
                    int edad;
                    do {
                        System.out.print("Ingrese su edad: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Caracter no válido. Intente nuevamente.");
                        scanner.next();
                        System.out.print("Ingrese su edad: ");
                            }
                        edad = scanner.nextInt();
                        scanner.nextLine(); 
                  } while (edad <= 0);
                         // Calcula el descuento según la edad
                    double descuento = 0;
                    if (edad <= 25) {
                        descuento = 0.10;
                  } else if (edad >= 60) {
                        descuento = 0.15;
                        }
                        
                        System.out.println("calculando descuentos aguarde por favor");
                        Thread.sleep(1*1000);
                    double precioFinal = precioBase - (precioBase * descuento);
                    int precioFinalInt = (int) precioFinal; // Convertir a entero para sumar
                        totalAcumulado += precioFinalInt; // Sumar al total acumulado
                        
                        System.out.println("\n   Resumen de compra    ");
                        System.out.println("Tipo de entrada: " + tipoEntrada);
                        System.out.println("Precio base: $" + (int) precioBase);
                        System.out.println("Descuento aplicado: " + (int) (descuento * 100) + "%");
                        System.out.println("Precio final a pagar: $" + (int) precioFinal);
                        
                         String respuesta = "";
                    boolean respuestaValida = false;
                    do {
                        System.out.print("¿Desea realizar otra compra? (si/no): ");
                        respuesta = scanner.nextLine().trim().toLowerCase();

                    if (respuesta.equals("si")) {
                        respuestaValida = true;
                        salir = false;
                  } else if (respuesta.equals("no")) {
                        respuestaValida = true;
                        salir = true;
                            
                  } else {
                        System.out.println("Respuesta no válida. escribe 'si'  o 'no' para continuar.");
                        }
                  } while (!respuestaValida);

                    break;
                    } 
                    case "2" -> salir = true;
                    default -> System.out.println("Opción no válida. Intentelo nuevamente.");
                }
                  } while (!salir);
                       System.out.println("calculando total de compra un momento por favor ");
                       Thread.sleep(1*1000);
            
                       System.out.println("Total acumulado de todas las compras: $" + totalAcumulado);
            
                       System.out.println("Gracias por su compra. ¡Disfrute la funcion!");
        }
    }
}
