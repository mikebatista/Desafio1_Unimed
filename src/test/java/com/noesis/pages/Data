package com.everis.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.everis.utils.Global;

/**
 * Esta classe contém o hashMap que armazenam os dados da planilha de teste
 *
 * @author Michael Batista
 *
 */
public class Dados {

	public static Map<String, String> mapTestCase = new HashMap<String, String>();
	public static Map<String, String> mapCotador = new HashMap<String, String>();
	public static List<Object[]> listReturn = new ArrayList<Object[]>();
	public static int linhaLeitura = 1;
	public static Planilha excel = new Planilha();

	/**
	 * Lê os valores da linha na planilha e atribui ao HashMap
	 */
	public static String lerDadosLinha(int linha) {
		String message = "";
		try {
			mapTestCase = new HashMap<String, String>();
			excel.ds_startExcel(Global.spreadsheetFolder + Global.planilha, Global.folha);
			int coluna = 0;
			while (!excel.getValue(0, coluna).isEmpty()) {
				mapTestCase.put(excel.getValue(0, coluna), excel.getValue(linha, coluna));
				coluna++;
			}
			excel.ds_closeData();
		} catch (Exception e) {
			message = "Falha ao ler Planilha";
		}
		return message;

	}

	/**
	 * Lê a planilha e capturar todos testes marcados como sim
	 */
	public static Object[] verificarTestes() {
		excel.ds_startExcel(Global.spreadsheetFolder + Global.planilha, Global.folha);
		String executar;
		do {
			// leia a coluna executar
			executar = excel.getValue(linhaLeitura, "Executar?");
			// se a coluna executar estiver com o valor "Sim"
			if (executar.equals("Sim")) {
				// carrega todos os dados da linha no map

				listReturn.add(new Object[] { linhaLeitura, excel.getValue(linhaLeitura, "ID") });

			}
			// proxima linha
			linhaLeitura++;
			// repete o ciclo enquanto a coluna executar for diferente de vazio
		} while (!executar.isEmpty());

		excel.ds_closeData();
		Object[] objectReturn = new Object[listReturn.size()];
		return listReturn.toArray(objectReturn);

	}

	public static String getValue(String colummName) {
		try {
			String value = mapTestCase.get(colummName);
			if (value.equals(null)) {
				return "";
			} else {
				return value;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Map não inicializado";
		}

	}

}
