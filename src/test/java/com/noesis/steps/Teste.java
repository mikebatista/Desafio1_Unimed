package com.noesis.steps;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Teste extends UnimedElementMap{
	
	

	public static void main(String[] args) {
		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HHmmss");
		String horaFormatada = formatterHora.format(agora);
		
		System.out.println(horaFormatada);

	}

	public static WebDriver webDriver;
	public static WebDriverWait wait;
	public static void antes() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");// tela maximizada
		options.addArguments("--incognito");// navegar em janela anonima
		options.addArguments("--disable-notifications");// desativar notificacoes do navegador
		webDriver = new ChromeDriver(options);
		wait = new WebDriverWait(webDriver, 20);
	}

	public static void teste() {

		try {
			String estado = "Rio de Janeiro";
			String cidade = "Nova Iguaçu";
			String especialidade = "Otorrino";
			webDriver.navigate().to("https://www.unimed.coop.br/");

			// clicar no menu guia médico
			//webDriver.findElement(By.xpath("//div[@id='menuPrincipalItens']//a[contains(@href,'guia-medico')]"))
					//.click();
			webDriver.findElement(menuGuiaMedico).click();
			// clicar no menu busca rapida
			webDriver.findElement(By.id("busca_rapida")).click();
			// informar especialidade
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("campo_pesquisa")));
			webDriver.findElement(By.id("campo_pesquisa")).sendKeys(especialidade);
			// clicar em pesquisar
			webDriver.findElement(By.id("btn_pesquisar")).click();
			// selecionar estado
			webDriver.findElement(By.xpath("//div[contains(@class,'selecione-rede big-field pesquisa-avancada')]"))
					.click();
			Thread.sleep(3000);
			webDriver.findElement(By.xpath("//*[contains(text(),'"+estado+"')]")).click();
			Thread.sleep(3000);
			// selecionar cidade
			webDriver.findElement(By.xpath("//div[contains(@class,'selecione-plano big-field pesquisa-avancada')]"))
					.click();
			Thread.sleep(2000);
			webDriver.findElement(By.xpath("//*[contains(text(),'"+cidade+"')]")).click();

			String unidade = "//form[@class='form-escolher-unimed-gm']//input[1]";
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(unidade)));
			// selecionar unidade
			webDriver.findElement(By.xpath(unidade)).click();
			// clicar no botão continuar
			webDriver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
			teste2();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void teste2() {
		
		try {
			
			String medico;
			String enderecoMedico;
			
			String listaMedicos ="//div[@class='resultado-resumido padding relative']/h4";
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(listaMedicos)));
			String estadoResultado = "(//div[@class='resultado-resumido padding relative']//span[@id='txt_endereco']/p[1])[1]";
			List<WebElement> listOfElements = webDriver.findElements(By.xpath(listaMedicos));
			System.out.println(listOfElements.size()+" Resultados encontrados!");
			
			for (int linha = 1; linha < listOfElements.size(); linha++) {
				listaMedicos ="(//div[@class='resultado-resumido padding relative']/h4)["+linha+"]";
				medico = webDriver.findElement(By.xpath(listaMedicos)).getText();
				System.out.println(medico);
				
				estadoResultado = "(//div[@class='resultado-resumido padding relative']//span[@id='txt_endereco']/p[1])["+linha+"]";
				enderecoMedico = webDriver.findElement(By.xpath(estadoResultado)).getText();
				System.out.println(enderecoMedico+"\n");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void validarResultado() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
