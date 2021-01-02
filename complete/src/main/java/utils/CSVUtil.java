package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 * Codigo fonte para executar a gravacao dos dados em arquivo no formato csv.
 * 
 * Projeto de Graduacao - Engenharia de Software - Poli/UFRJ
 * 
 * @author Wallace de Souza Espindola
 *
 */
public class CSVUtil {

	public static void salvarCSV(String nomeArquivo, int numFibo, int contChamada, long tInicioChamadas, long tChamadaAtual, long inicioProcesso,
			long fimProcesso, long resultFibo) throws IOException {

		String csvFile = "D:\\Desktop\\" + nomeArquivo + ".csv";

		FileWriter writer = new FileWriter(csvFile, true);// true = modo append

		CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL.withDelimiter(';'));

		BigDecimal mil = new BigDecimal(1000);
		mil = mil.setScale(2, BigDecimal.ROUND_HALF_UP);

		long duracaoChamadasEmMilis = tChamadaAtual - tInicioChamadas;
		BigDecimal decimalDuracaoChamadaEmMilis = new BigDecimal(duracaoChamadasEmMilis);
		decimalDuracaoChamadaEmMilis = decimalDuracaoChamadaEmMilis.setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal decimalDuracaoChamadaEmSeg = decimalDuracaoChamadaEmMilis.divide(mil, BigDecimal.ROUND_HALF_UP);

		long duracaoProcessoEmMilis = (fimProcesso - inicioProcesso) < 0 ? 0 : (fimProcesso - inicioProcesso);
		BigDecimal decimalDuracaoProcessoEmMilis = new BigDecimal(duracaoProcessoEmMilis);
		decimalDuracaoProcessoEmMilis = decimalDuracaoProcessoEmMilis.setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal decimalDuracaoProcessoEmSeg = decimalDuracaoProcessoEmMilis.divide(mil, BigDecimal.ROUND_HALF_UP);

		csvPrinter.printRecord(numFibo, contChamada, tInicioChamadas, tChamadaAtual, duracaoChamadasEmMilis, decimalDuracaoChamadaEmSeg,
				inicioProcesso, fimProcesso, duracaoProcessoEmMilis, decimalDuracaoProcessoEmSeg, resultFibo);

		System.out.println(
				String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s", numFibo, contChamada, tInicioChamadas, tChamadaAtual, duracaoChamadasEmMilis,
						decimalDuracaoChamadaEmSeg, inicioProcesso, fimProcesso, duracaoProcessoEmMilis, decimalDuracaoProcessoEmSeg, resultFibo));

		csvPrinter.flush();
		csvPrinter.close();
	}
}
