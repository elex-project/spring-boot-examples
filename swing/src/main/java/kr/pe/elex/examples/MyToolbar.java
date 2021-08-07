package kr.pe.elex.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;

@Component
public class MyToolbar extends JToolBar {
	@Autowired
	private MyService service;

	@PostConstruct
	private void init(){

		this.add(new JLabel(service.getSomething()));

		service.setSomething("changed by Toolbar");

	}
}
