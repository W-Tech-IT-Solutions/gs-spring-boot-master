package app;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import utils.CSVUtil;
import utils.ProcessaFiboThread;

/**
 * Controller para testes de Long Polling.
 * 
 * Projeto de Graduacao - Engenharia de Software - Poli/UFRJ
 * 
 * @author Wallace de Souza Espindola
 *
 */
@RestController
public class LongPollingController {

	private final String NOME_TESTE = "Long-polling";
	private final int MAX_TESTE = 50;
	private int contChamada = 1;
	private int numFibo = 0;
	private long tInicioChamadas = 0;
	private long tChamadaAtual = 0;
	private boolean fimTeste = false;
	private ProcessaFiboThread fiboThread = new ProcessaFiboThread();

	@RequestMapping("/longpolling")
	public String longPolling() throws IOException {

		tChamadaAtual = System.currentTimeMillis();

		if (fiboThread.isProcessoFinalizado() & !fimTeste) {

			CSVUtil.salvarCSV(NOME_TESTE, numFibo, contChamada, tInicioChamadas, tChamadaAtual, fiboThread.getInicioProcesso(), fiboThread.getFimProcesso(), fiboThread.getResultado());

			numFibo++;
			if (numFibo > MAX_TESTE) {
				fimTeste = true;
			}
			if (!fimTeste) {
				reiniciar();
			} else {
				reset();
				System.out.println("##### Fim do teste. #####");
			}
		}

		if (!fimTeste) {
			CSVUtil.salvarCSV(NOME_TESTE, numFibo, contChamada, tInicioChamadas, tChamadaAtual, fiboThread.getInicioProcesso(), fiboThread.getFimProcesso(), fiboThread.getResultado());
		}

		String resultado;
		if (!fimTeste) {
			resultado = NOME_TESTE + " - fibo: " + numFibo + " - chamada: " + contChamada++ + " - resultFibo: " + fiboThread.getResultado();
		} else {
			resultado = "Fim do teste.";
		}
		System.out.println(resultado);
		return resultado;
	}

	private void reiniciar() {
		fiboThread.interrupt();
		fiboThread = new ProcessaFiboThread();
		contChamada = 1; // reinicia contagem
		tInicioChamadas = tChamadaAtual;
		fiboThread.setNumRodadas(numFibo);
		fiboThread.start();
	}

	private void reset() {
		fiboThread.interrupt();
		contChamada = 0;
		tInicioChamadas = 0;
		tChamadaAtual = 0;
		fiboThread.setNumRodadas(0);
	}
}
