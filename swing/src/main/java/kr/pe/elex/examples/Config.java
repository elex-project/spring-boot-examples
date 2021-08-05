package kr.pe.elex.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;

@Configuration
public class Config {
	@Autowired
	private MyPanel contentPane;

	@Bean
	public JFrame window(){
		final JFrame jFrame =  new JFrame();
		jFrame.setTitle("Test");
		jFrame.setSize(800, 600);
		jFrame.setContentPane(contentPane);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return jFrame;
	}
}
