package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Looca looca = new Looca();
        Scanner leitor = new Scanner(System.in);

        Sistema sistema = looca.getSistema();
        Processador processador = looca.getProcessador();
        System.out.println(sistema);
        System.out.println(processador);
        Integer opcao = 1;
        while (opcao.equals(1)) {
            Temperatura temperatura = looca.getTemperatura();
            String temperaturaMsg = temperatura + "°C";
            System.out.println(temperaturaMsg);

            String statusMsg;
            if (temperatura.getTemperatura() < 70.0) {
                statusMsg = "Temperatura da CPU dentro do padrão ;)";
                System.out.println(statusMsg);
            } else {
                statusMsg = "Temperatura acima do recomendado!!!";
                System.out.println(statusMsg);
                System.out.println("""
                        Possíveis soluções:
                        -Limpeza;
                        -Trocar pasta térmica;
                        -Verificar Coolers;
                        """);
            }

            logTemperatura(temperaturaMsg, statusMsg);

            System.out.println("""
                    [0] Sair
                    [1] Nova leitura
                    """);
            opcao = leitor.nextInt();

            if (opcao == 0) {
                System.out.println("Saindo...");
                break; // Sai do loop quando a opção é 0
            }
        }
    }

    private static void logTemperatura(String temperaturaMsg, String statusMsg) {
        File logDir = new File("Logs");
        if (!logDir.exists()) {
            logDir.mkdirs();
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String now = dtf.format(LocalDateTime.now());
        try (PrintWriter writer = new PrintWriter(new FileWriter("Logs/log.txt", true))) {
            writer.println("------------Registro--------------");
            writer.println("Data e hora: " + now);
            writer.println(temperaturaMsg);
            writer.println(statusMsg);
            writer.println("----------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}