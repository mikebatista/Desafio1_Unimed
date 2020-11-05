package com.noesis.steps;

import org.openqa.selenium.By;

public class UnimedElementMap {

	// Nesta Classe estão mapeados todos os elementos do utilizados no teste
	/**
	 * @author Michael Batista
	 * @data 04/11/2020
	 */

	protected final By menuGuiaMedico = By.xpath("//div[@id='menuPrincipalItens']//a[contains(@href,'guia-medico')]");
	protected final By menuBuscaRapida = By.id("busca_rapida");
	protected final By inputCampoPesquisa = By.id("campo_pesquisa");
	protected final By btnPesuisar = By.id("btn_pesquisar");
	protected final By unidade = By.xpath("//form[@class='form-escolher-unimed-gm']//input[1]");

	protected final By campoEstado = By.xpath("//div[contains(@class,'selecione-rede big-field pesquisa-avancada')]");
	protected final By campoCidade = By.xpath("//div[contains(@class,'selecione-plano big-field pesquisa-avancada')]");
	protected final By btnContinuar = By.xpath("//button[@class='btn btn-success']");
	
	

}
