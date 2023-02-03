package com.everis.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.everis.excel.Dados;
import com.everis.excel.Planilha;
import com.github.javafaker.Faker;

public class Tools extends Dados {

	/**
	 * nome pasta de evidencia, o nome é composto por por dd/mm/aa hh:mm:ss
	 */
	public static String folderDateExecution = "";
	/**
	 * dados utilizando durante o cadastro nome, email, cpf, telefone
	 */
	public static String logCadastro = "";
	private Faker faker = new Faker();
	private Random random = new Random();
	String name = "";

	private static Date mv_sceneTime;
	private static Date sceneTimeCurrent;

	public Tools() {
		name = faker.name().fullName();
	}

	/**
	 * Atribui o nome do diretório que ira armazenar as evidências. O nome é
	 * composto por dd/MM/yyyy HH:mm:ss. Ou seja, o horário de início da execução
	 */
	public static void setNameFolderEvidence() {
		String folderName = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
		folderName = folderName.replace("/", "-");
		folderName = folderName.replace(":", "-");
		folderName = folderName.replace(" ", "_");
		folderDateExecution = folderName;
	}

	/**
	 * Gera um paragrafo de texto aleatorio
	 * 
	 * @return
	 */
	public String getTextRandom() {
		String text = faker.lorem().paragraph();
		return text;
	}

	/**
	 * Gera um nome completo aleatório
	 * 
	 * @return
	 */
	public String getNameRandom() {
		return name;
	}

	/**
	 * Gera um email aleatório
	 * 
	 * @return
	 */
	public String getEmailRandom() {
		String email = name.replace(" ", "").toLowerCase() + "@gmail.com";
		return email;
	}

	/**
	 * Gera um numero de celular aleatório
	 * 
	 * @return
	 */
	public String getPhoneNumberRandom() {

		int n1 = random.nextInt(9999);
		int n2 = random.nextInt(9999);

		String cellphoneNumber = "219" + n1 + n2;
		return cellphoneNumber;
	}

	/**
	 * Escolhe sim ou não
	 * 
	 * @return
	 */
	public boolean choose() {
		boolean choose = random.nextBoolean();
		return choose;
	}

	/**
	 * Cria um bloco de notas na pasta "Data" com o texto passado por parametro
	 * 
	 * @param name = nome do arquivo
	 * @param text = texto a ser gravado
	 */
	public static void writeInCSV(String text) {
		try {
			String path = Global.evidenceFolder + "Cadastro.csv";
			FileWriter escreve = new FileWriter(path, true);
			escreve.append(text);
			escreve.close();
		} catch (Exception e) {
		} finally {
			System.out.println("OS dados do usuário cadastrado foram gravados no arquivo Cadastro.CSV");
		}
	}

	public static String getDate() {
		Date dataHoraAtual = new Date();
		String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
		return data;
	}

	/**
	 * Retorna a hora de inicio da execução
	 */
	public static Date tempoInicioExecucao() {
		mv_sceneTime = new Date();
		mv_sceneTime.setTime(System.currentTimeMillis());
		return mv_sceneTime;
	}

	/**
	 * Retorna a hora do fim da execução
	 */
	public static Date tempoFimExecucao() {
		sceneTimeCurrent = new Date();
		sceneTimeCurrent.setTime(System.currentTimeMillis());
		return sceneTimeCurrent;
	}

	public static String tempoExecucao() {
		try {

			if (sceneTimeCurrent.getTime() - mv_sceneTime.getTime() < 0) {
				Calendar c = Calendar.getInstance();
				c.setTime(sceneTimeCurrent);
				c.add(Calendar.DATE, 1);
				sceneTimeCurrent = c.getTime();
			}
			long ms = sceneTimeCurrent.getTime() - mv_sceneTime.getTime();
			long diffSeconds = ms / 1000 % 60;
			long diffMinutes = ms / (60 * 1000) % 60;
			long diffHours = ms / (60 * 60 * 1000);
			String hh = String.valueOf(diffHours);
			String mm = String.valueOf(diffMinutes);
			String ss = String.valueOf(diffSeconds);
			if (String.valueOf(diffHours).length() == 1)
				hh = "0" + hh;
			if (String.valueOf(diffMinutes).length() == 1)
				mm = "0" + mm;
			if (String.valueOf(diffSeconds).length() == 1)
				ss = "0" + ss;
			return hh + ":" + mm + ":" + ss;
		} catch (Exception e) {
			return "Time error";
		}

	}

	public static void rename(String oldName, String newName) throws IOException {
		File oldFile = new File(oldName);
		oldFile.createNewFile();

		File newFile = new File(newName);
		oldFile.renameTo(newFile);
	}

	/**
	 * Finaliza os drivers
	 */
	public static void encerraExecucao() {
		if (getValue("Plataforma").equals("Web")) {
			DriverFactory.killWebDriver();
		} else {
			DriverFactory.killMobileDriver();
		}
	}

	/**
	 * Criar um back da planilha
	 */
	public static void excelBackup() {
		FileSystem system = FileSystems.getDefault();
		Path original = system.getPath(Global.spreadsheetFolder + Global.planilha);
		Path target = system.getPath(Global.backupSpreadsheetFolder + Global.planilha);
		try {
			Files.copy(original, target, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ex) {
			System.out.println("ERROR");
		}
	}

	/**
	 * Escreve na planilha o resultado da execução
	 * 
	 * @param message
	 * @throws IOException
	 */
	public static void resultado(String message, int linha) {
		String result;
		try {
			Planilha excel = new Planilha();
			excel.ds_startExcel(Global.spreadsheetFolder + Global.planilha, Global.folha);

			// se o resultado esperando for igual ao resultado obtido, o teste passou
			if (getValue("Resultado Esperado").equalsIgnoreCase(message)) {
				result = "Passed";
				System.out.println("Teste finalizado com sucesso!");
				excel.ds_writeData("Não", linha, "Executar?");
			} else {
				result = "Failed";
				System.out.println("Mensagem Esperada[" + getValue("Resultado Esperado")+"]");
				System.out.println("Mensagem Recebida[" + message+"]");
				renameFolderToFailed();

			}
			System.out.println("Result: " + result);

			excel.ds_writeData(message, linha, "Resultado Recebido");
			excel.ds_writeData(result, linha, "Status");
			excel.ds_writeData(getDate(), linha, "Data");
			excel.ds_writeData(tempoExecucao(), linha, "Tempo");

			// Salvando resultado da execução na planilha e encerrando drivers
			excel.ds_saveData();
			excel.ds_closeData();
		} catch (Exception e) {
			System.err.println("Falha ao salvar resultado da execução na planilha");
		}
	}

	public static void renameFolderToFailed() {
		String path = Global.evidenceFolder + folderDateExecution + "\\" + mapTestCase.get("ID");
		try {
			rename(path, path + "_Falhou");
		} catch (IOException e) {
			System.out.println("Falha ao renomear diretório");
			e.printStackTrace();
		}
	}
}
