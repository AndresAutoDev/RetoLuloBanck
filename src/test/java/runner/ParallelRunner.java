package runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepDefinitions",
        plugin = {"pretty", "html:target/cucumber-reports"},
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "@parallel",
        monochrome = true
)
public class ParallelRunner {
    @AfterClass
    public static void generateReport() {
        File reportOutputDirectory = new File("target/cucumber-html-reports");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber-json/cucumber.json");
        Configuration configuration = new Configuration(reportOutputDirectory, "Reto-LuloBanck");
        configuration.addClassifications("Environment", "Test");
        configuration.addClassifications("Browser", "Chrome");
        configuration.addClassifications("Branch", "main");
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}