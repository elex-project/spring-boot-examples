package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

@Slf4j
@Component
public class MyPanel extends JPanel {

	@Autowired
	private MyService service;
	@Autowired
	private MyToolbar toolbar;

	@PostConstruct
	private void init(){
		this.setLayout(new BorderLayout());
		JButton btn = new JButton("Button");
		this.add(new JLabel("Hello"), BorderLayout.CENTER);
		this.add(btn, BorderLayout.SOUTH);
		this.add(toolbar, BorderLayout.NORTH);

		btn.addActionListener(e -> {
			System.out.println(service.getSomething());
		});
	}

}
