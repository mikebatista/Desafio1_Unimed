package com.noesis.steps;

import com.noesis.pages.UnimedPage;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Mas;
import cucumber.api.java.pt.Quando;

public class UnimedSteps {
	UnimedPage unimedPage;
	// nome do diretorio onde serão armazenados as evidencias do teste
	public static String folderEvidenceName = "";

	@Before
	public void antes(Scenario scenario) {
		folderEvidenceName = scenario.getName();
		unimedPage = new UnimedPage();
		unimedPage.abrirNavegador();
	}
	@Dado("^que eu sou um cliente$")
	public void queEuSouUmCliente() throws Throwable {
		unimedPage.acessarSiteUnimed();
	}

	@Quando("^eu pesquisar um médico especialista em \"([^\"]*)\"$")
	public void euPesquisarUmMédicoEspecialistaEm(String especialidade) throws Throwable {
		unimedPage.clicarMenuGuiaMedico()
		.clicarMenuBuscaRapida()
		.informarEspecialidade(especialidade)
		.clicarEmPesquisar();
	}

	@Quando("^informar o estado \"([^\"]*)\" e a cidade \"([^\"]*)\"$")
	public void informarOEstadoEACidade(String estado, String cidade) throws Throwable {
		unimedPage.selecionarEstado(estado)
		.selecionarCidade(cidade)
		.selecionarUnidade()
		.clicarEmContinuar()
		.listarResultado();
	}

	@Então("^serão exibidos resultados para o Rio de Janeiro$")
	public void serãoExibidosResultadosParaORioDeJaneiro() throws Throwable {
		unimedPage.validarContemEndereco("RJ");
	}

	@Mas("^nenhum resultado para os demais estados$")
	public void nenhumResultadoParaOsDemaisEstados() throws Throwable {
		unimedPage.validarNaoContemEndereco("SP");
	}

	@After
	public void depois() {
		 unimedPage.fecharNavegador();
	}

}
