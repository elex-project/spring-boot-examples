/*
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import com.samskivert.mustache.Mustache;
import org.springframework.boot.autoconfigure.mustache.MustacheEnvironmentCollector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class Config {

	@Bean
	public Mustache.Compiler mustacheCompiler(
			Mustache.TemplateLoader templateLoader,
			Environment environment) {

		MustacheEnvironmentCollector collector
				= new MustacheEnvironmentCollector();
		collector.setEnvironment(environment);

		return Mustache.compiler()
				.withLoader(templateLoader)
				.withCollector(collector);
	}

}
