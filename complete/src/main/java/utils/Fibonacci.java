package utils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Codigo fonte para executar o teste com o algoritmo de fibonacci.
 * 
 * Projeto de Graduacao - Engenharia de Software - Poli/UFRJ
 * 
 * @author Wallace de Souza Espindola
 *
 */
public class Fibonacci {

	public static void main(String[] args) {

		int duracaoFibo = 60;

		BigDecimal mil = new BigDecimal(1000);
		mil = mil.setScale(2, BigDecimal.ROUND_HALF_UP);

		// Teste do programa, imprime os X primeiros termos.
		for (int i = 1; i <= duracaoFibo; i++) {
			long inicio = System.currentTimeMillis();
			System.out.println("---------------------------------------------------");
			System.out.println("Inicio fibo de [" + i + "] [" + new Date() + "]");
			System.out.println("fibo(" + i + "):" + fibo(i));
			System.out.println("Fim fibo de [" + i + "] [" + new Date() + "]");
			long fim = System.currentTimeMillis();

			long duracaoMillis = fim - inicio;

			BigDecimal decimalDuracaoEmMilis = new BigDecimal(duracaoMillis);
			decimalDuracaoEmMilis = decimalDuracaoEmMilis.setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal decimalDuracaoEmSeg = decimalDuracaoEmMilis.divide(mil, BigDecimal.ROUND_HALF_UP);

			System.out.println("Duracao fibonacci de [" + i + "] em segundos: " + decimalDuracaoEmSeg);
		}
	}

	public static long fibo(int n) {
		return (n < 2) ? n : fibo(n - 1) + fibo(n - 2);
	}

}
