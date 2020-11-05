package com.noesis.pages;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.noesis.steps.UnimedElementMap;
import com.noesis.steps.UnimedSteps;

public class UnimedPage extends UnimedElementMap {

	public static WebDriver webDriver;
	WebDriverWait wait;

	public UnimedPage abrirNavegador() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");// tela maximizada
		options.addArguments("--incognito");// navegar em janela anonima
		options.addArguments("--disable-notifications");// desativar notificacoes do navegador
		webDriver = new ChromeDriver(options);
		wait = new WebDriverWait(webDriver, 20);
		return this;
	}

	public UnimedPage fecharNavegador() {
		webDriver.quit();
		return this;
	}

	public UnimedPage acessarSiteUnimed() {
		webDriver.navigate().to("https://www.unimed.coop.br/");
		screenShot();
		return this;
	}

	public UnimedPage clicarMenuGuiaMedico() {
		webDriver.findElement(menuGuiaMedico).click();
		screenShot();
		return this;
	}

	public UnimedPage clicarMenuBuscaRapida() {
		webDriver.findElement(menuBuscaRapida).click();
		return this;
	}

	public UnimedPage informarEspecialidade(String especialidade) {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(inputCampoPesquisa));
		webDriver.findElement(inputCampoPesquisa).sendKeys(especialidade);
		screenShot();
		return this;
	}

	public UnimedPage clicarEmPesquisar() {
		webDriver.findElement(By.id("btn_pesquisar")).click();
		return this;
	}

	public UnimedPage selecionarEstado(String estado) throws InterruptedException {
		webDriver.findElement(campoEstado).click();
		webDriver.findElement(By.xpath("//*[contains(text(),'" + estado + "')]")).click();
		return this;
	}

	public UnimedPage selecionarCidade(String cidade) throws InterruptedException {
		webDriver.findElement(campoCidade).click();
		Thread.sleep(3000);
		webDriver.findElement(By.xpath("//*[contains(text(),'" + cidade + "')]")).click();
		return this;
	}

	public UnimedPage selecionarUnidade() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(unidade));
		webDriver.findElement(unidade).click();
		screenShot();
		return this;
	}

	public UnimedPage clicarEmContinuar() {
		webDriver.findElement(btnContinuar).click();
		return this;
	}
	// Este map armazena a lista de resultados encontrados para a pesquisar
	public static Map<String, String> mapEnderecos = new HashMap<String, String>();

	public UnimedPage listarResultado() {
		try {
			// Mapeamentos
			// OBS: Os mapeamentos desse metodo não foram colocados na classe de
			// mapeamentos, pois ele sofrentem alteração durante a execução
			String medico;
			String enderecoMedico;
			String enderecoResultadoXpath;
			String listaMedicosXpath = "//div[@class='resultado-resumido padding relative']/h4";

			// aguarde aparecer a lista de resultados
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(listaMedicosXpath)));
			List<WebElement> listOfElements = webDriver.findElements(By.xpath(listaMedicosXpath));
			System.out.println(listOfElements.size() + " Resultados encontrados!");

			// percorre a lista e mostrar o nome do medico e endereço
			for (int linha = 1; linha < listOfElements.size(); linha++) {
				listaMedicosXpath = "(//div[@class='resultado-resumido padding relative']/h4)[" + linha + "]";
				medico = webDriver.findElement(By.xpath(listaMedicosXpath)).getText();
				System.out.println(medico);

				enderecoResultadoXpath = "(//div[@class='resultado-resumido padding relative']//span[@id='txt_endereco']/p[1])["
						+ linha + "]";
				enderecoMedico = webDriver.findElement(By.xpath(enderecoResultadoXpath)).getText();
				mapEnderecos.put("R" + linha, enderecoMedico);
				System.out.println(enderecoMedico + "\n");

			}
			screenShot();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public UnimedPage validarContemEndereco(String estado) {
		// percorre o map verifica se todos os endereços pertencem ao Rio de Janeiro
		for (String key : mapEnderecos.keySet()) {
			String value = mapEnderecos.get(key);
			assertTrue(value.contains(estado));
		}
		return this;
	}
	public UnimedPage validarNaoContemEndereco(String estado) {
		// percorre o map verifica se apresentou algum endereço de São Paulo
		for (String key : mapEnderecos.keySet()) {
			String value = mapEnderecos.get(key);
			assertFalse(value.contains(estado));
		}
		return this;
	}
	
	/**
	 * Faz uma captura de tela e armazena na pasta evidências
	 */
	public UnimedPage screenShot() {
		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HHmmss");
		String horaFormatada = formatterHora.format(agora);
		File imagem = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\evidencias\\"
		+UnimedSteps.folderEvidenceName+"\\imagem"+horaFormatada+".png";
		try {
			FileUtils.copyFile(imagem, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}
}
