package com.noesis.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/resource/features/buscarMedico.feature",
		glue = "com.noesis.steps",
		tags = "@teste1",
		plugin = "pretty",
		monochrome = true,
		snippets = SnippetType.CAMELCASE,
		strict = false
		)
public class UnimedTest {
	
}
