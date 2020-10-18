package xyz.nedderhoff.bankstatementanalyser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class BankStatementAnalyserApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankStatementAnalyserApplication.class, args);
	}

}
