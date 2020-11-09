package com.noesis.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/resource/features/buscarMedico.feature",
		glue = "com.noesis.steps",
		plugin = { "pretty", 
				"html:evidencias/cucumber-reports",
				"junit:evidencias/cucumber-reports/Cucumber.xml"},
		monochrome = true,
		snippets = SnippetType.CAMELCASE,
		strict = false
		)
public class UnimedTest {
	
}
