//Imports.
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Calendar;

public class Main {
    //Cores.
    private static final String BLUE = "\u001B[34m";
    private static final String GREEN = "\u001B[32m";
    private static final String BOLD = "\u001B[1m";
    private static final String RESET = "\u001B[0m";

    /*
    Esse programa simples vai receber dois números: um que representa o mês, e outro que representa o ano.
    Com isso, ele vai construir um calendário com a formatação correta dos dias do mês
    (Por exemplo: mostrar como o mês 11/2024 tem seu primeiro dia em uma sexta-feira.).
    */

    public static void main(String[] args) {
        //Variáveis e inicialização do scanner.
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        Calendar chronos = Calendar.getInstance();
        boolean val = false, exit = false;
        String answer = "sair";
        int semana = 0, totalDiasMes = 0;
        int mes = 0, ano = 0;
        int i, d = 0;

        System.out.println(GREEN + "Bem-vindo. Este programa irá receber um " + BOLD + "mês" + RESET + GREEN + " e " + BOLD + GREEN + "ano" + RESET + GREEN + ", e retornará a formatação com espaçamento e número de dias adequado." + RESET);

        do {
            val = false;
            do {
                try {
                    //Coleta de dados.
                    d=0;
                    System.out.println(" ");
                    System.out.print("Digite o mês que pretende ver: ");
                    mes = scanner.nextInt() - 1;
                    System.out.print("Digite o ano que pretende ver: ");
                    ano = scanner.nextInt();

                    //Validação de dados
                    val = true;
                    if (((mes < 0) || (mes > 11)) && ((ano < 1) || (ano > 9999))) {
                        System.out.println("Mês e ano inválidos.");
                        val = false;
                    } else {
                        if ((mes < 0) || (mes > 11)) {
                            System.out.println("Mês inválido.");
                            val = false;
                        }
                        if ((ano < 1) || (ano > 9999)) {
                            System.out.println("Ano inválido.");
                            val = false;
                        }
                    }
                } catch (InputMismatchException e) {
                    //Tratamento de excessão.
                    System.out.println("Input inválido. Certifique-se de usar apenas números.");
                    mes = 0;
                    ano = 0;
                    scanner.next();
                }
            } while (!val);

            //Conversão de input de usuário para data real.
            System.out.println(" ");
            chronos.set(Calendar.MONTH, mes);
            chronos.set(Calendar.YEAR, ano);
            chronos.set(Calendar.DAY_OF_MONTH, 1);
            semana = chronos.get(Calendar.DAY_OF_WEEK) - 1;

            YearMonth yearMonth = YearMonth.of(ano, mes + 1);
            totalDiasMes = yearMonth.lengthOfMonth();
            System.out.println("Mês: " + sdf.format(chronos.getTime()));
            System.out.println("---------------------------------");
            System.out.println("DOM  SEG  TER  QUA  QUI  SEX  SAB");

            //Espaços antes do dia 1 para formatação adequada
            for (i = 0; i < semana; i++) {
                System.out.print("     ");
            }

            //Retorno dos dias do mês
            for (i = 1; i <= totalDiasMes; i++) {
                d++;
                if (d < 10) {
                    System.out.print(i + "    ");
                } else {
                    System.out.print(i + "   ");
                }
                if ((i + semana) % 7 == 0) {
                    System.out.println();
                }
            }

            //Este if checa se o mês tem seu último dia da semana em um sábado,
            //um detalhe que garante que ele sempre deixe um espaço uniforme abaixo
            if(((totalDiasMes-(7-semana))!=28)&&(mes!=1)){
                System.out.println();
            }
            System.out.println();

            //Opção de encerramento.
            System.out.println(GREEN + "Digite " + BOLD + BLUE + "sair" + RESET + GREEN + " para encerrar o programa, ou qualquer outro input para reiniciar" + RESET);
            answer = scanner.nextLine();
            answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("sair"))
            {
                exit=true;
            }
        } while(!exit);
        //Feito por Caetano Córdova.
    }
}