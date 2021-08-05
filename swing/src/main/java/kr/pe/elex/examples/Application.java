package kr.pe.elex.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private JFrame window;

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class)
				.web(WebApplicationType.NONE)
				.headless(false)
				.run(args);
		//SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		SwingUtilities.invokeLater(() -> {
			window.setVisible(true);
		});
	}

}
