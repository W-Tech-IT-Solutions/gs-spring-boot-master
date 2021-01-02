package utils;

/**
 * Codigo fonte para executar o teste com polling numa thread rodando fibonacci.
 * 
 * Projeto de Graduacao - Engenharia de Software - Poli/UFRJ
 * 
 * @author Wallace de Souza Espindola
 *
 */
public class ProcessaFiboThread extends Thread {

	private boolean processoFinalizado = true;
	private int numRodadas = 0;
	private long inicioProcesso = 0;
	private long fimProcesso = 0;
	private long resultado = 0;

	public void run() {
		System.out.println("ProcessaFiboThread iniciando - rodadas fibo: " + this.numRodadas);
		inicioProcesso = System.currentTimeMillis();
		processoFinalizado = false;
		resultado = Fibonacci.fibo(this.numRodadas); // processo alvo com tempo de execucao x
		processoFinalizado = true;
		fimProcesso = System.currentTimeMillis();
		System.out.println("ProcessaFiboThread fim processamento");
	}

	public void setNumRodadas(int numRodadas) {
		this.numRodadas = numRodadas;
	}

	public boolean isProcessoFinalizado() {
		return processoFinalizado;
	}

	public long getInicioProcesso() {
		return inicioProcesso;
	}

	public long getFimProcesso() {
		return fimProcesso;
	}

	public long getResultado() {
		return processoFinalizado ? resultado : 0;
	}
}
