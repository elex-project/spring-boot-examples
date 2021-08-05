package kr.pe.elex.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private JFrame window;
	//@Autowired
	//private MyPanel contentPane;

	public static void main(String[] args) {
		ConfigurableApplicationContext context
				= new SpringApplicationBuilder(Application.class)
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
