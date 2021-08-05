package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Slf4j
@Component
public class MyPanel extends JPanel {
	//@Autowired
	//private JFrame window;
	@Autowired
	private MyService service;

	public MyPanel() {
		super();

		this.setLayout(new BorderLayout());
		JButton btn = new JButton("Button");
		this.add(new JLabel("Hello"), BorderLayout.CENTER);
		this.add(btn, BorderLayout.SOUTH);

		btn.addActionListener(e -> {
			//System.out.println(window.getTitle());
			System.out.println(service.getText());
		});
	}
}
