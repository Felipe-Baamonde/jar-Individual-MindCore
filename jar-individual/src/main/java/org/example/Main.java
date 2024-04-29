package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;

import java.util.Scanner;
import java.util.Timer;

public class Main {
    public static void main(String[] args) {
        Looca looca = new Looca();
        Scanner leitor = new Scanner(System.in);

        Sistema sistema = looca.getSistema();
        System.out.println(sistema);
        Integer opcao = 1;
        while(opcao.equals(1) == true){
            Temperatura temperatura = looca.getTemperatura();
            System.out.println(temperatura+"°C");
            System.out.println("Temperaturas acima de 70°C são prejudiciais aos componentes!");
            if(temperatura.getTemperatura() < 70.0){
                System.out.println("Temperatura da CPU dentro do padrão ;)");
            }else{
                System.out.println("Temperatura acima do recomendado!!!");
                System.out.println("""
                    Possíveis soluções:
                    -Limpeza;
                    -Pasta térmica ressecada;
                    -Problemas com o cooler;
                    -Baixa circulação de ar ;
                    """);
        }
            System.out.println("""
                    [0] Sair
                    [1] Nova leitura
                    """);
            opcao = leitor.nextInt();

        }





    }
}