package org.example;

import com.google.gson.JsonObject;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Obtener las tasas de cambio desde la API
            String jsonResponse = ApiClient.fetchRates("USD");
            JsonObject jsonObject = JsonParserHelper.parseRates(jsonResponse);
            JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

            while (true) {
                System.out.println("   Sea bienvenido/a al Conversor de Moneda =]");
                System.out.println("**************************************************");
                System.out.println("1) Dólar  >> Peso argentino");
                System.out.println("2) Peso argentino >> Dólar");
                System.out.println("3) Dólar  >> Real brasileño");
                System.out.println("4) Real brasileño >> Dólar");
                System.out.println("5) Dólar  >> Peso colombiano");
                System.out.println("6) Peso colombiano >> Dólar");
                System.out.println("7) Salir");
                System.out.println("**************************************************");
                System.out.print("Elija una opción válida: ");

                int option = scanner.nextInt();

                // Salir del programa
                if (option == 7) {
                    System.out.println("Gracias por usar el conversor de monedas. ¡Hasta luego!");
                    break;
                }

                // Solicitar la cantidad a convertir
                System.out.print("Ingrese la cantidad a convertir: ");
                double amount = scanner.nextDouble();

                double convertedAmount = 0.0;
                String targetCurrency = "";

                // Realizar la conversión basada en la opción seleccionada
                switch (option) {
                    case 1: // Dólar a Peso argentino
                        targetCurrency = "ARS";
                        convertedAmount = CurrencyConverter.convertCurrency(amount, conversionRates.get(targetCurrency).getAsDouble());
                        System.out.printf("%.2f USD son %.2f ARS\n", amount, convertedAmount);
                        break;
                    case 2: // Peso argentino a Dólar
                        targetCurrency = "ARS";
                        convertedAmount = CurrencyConverter.convertCurrency(amount, 1 / conversionRates.get(targetCurrency).getAsDouble());
                        System.out.printf("%.2f ARS son %.2f USD\n", amount, convertedAmount);
                        break;
                    case 3: // Dólar a Real brasileño
                        targetCurrency = "BRL";
                        convertedAmount = CurrencyConverter.convertCurrency(amount, conversionRates.get(targetCurrency).getAsDouble());
                        System.out.printf("%.2f USD son %.2f BRL\n", amount, convertedAmount);
                        break;
                    case 4: // Real brasileño a Dólar
                        targetCurrency = "BRL";
                        convertedAmount = CurrencyConverter.convertCurrency(amount, 1 / conversionRates.get(targetCurrency).getAsDouble());
                        System.out.printf("%.2f BRL son %.2f USD\n", amount, convertedAmount);
                        break;
                    case 5: // Dólar a Peso colombiano
                        targetCurrency = "COP";
                        convertedAmount = CurrencyConverter.convertCurrency(amount, conversionRates.get(targetCurrency).getAsDouble());
                        System.out.printf("%.2f USD son %.2f COP\n", amount, convertedAmount);
                        break;
                    case 6: // Peso colombiano a Dólar
                        targetCurrency = "COP";
                        convertedAmount = CurrencyConverter.convertCurrency(amount, 1 / conversionRates.get(targetCurrency).getAsDouble());
                        System.out.printf("%.2f COP son %.2f USD\n", amount, convertedAmount);
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, intente de nuevo.");
                }

                System.out.println("**************************************************");
            }
        } catch (Exception e) {
            System.out.println("Se produjo un error al procesar su solicitud.");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
