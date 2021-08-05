/*
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * @see "https://docs.spring.io/spring-integration/reference/html/mqtt.html#mqtt"
 */
@Configuration
public class MqttConfig {
	private static final String HOST = "tcp://localhost:1883";
	private static final String USERNAME = "elex";
	private static final String PASSWORD = "test";
	private static final String CLIENT_ID = "i-am-a-server";

	private static final String TOPIC_FILTER = "#";
	private static final String TOPIC_FILTER_2 = "$SYS/#";

	private MqttConnectOptions connectOptions() {
		MqttConnectOptions options = new MqttConnectOptions();
		options.setCleanSession(true);
		options.setServerURIs(new String[]{HOST});
		options.setKeepAliveInterval(10);
		options.setAutomaticReconnect(true);
		options.setUserName(USERNAME);
		options.setPassword(PASSWORD.toCharArray());
		return options;
	}

	@Bean
	public DefaultMqttPahoClientFactory defaultMqttPahoClientFactory() {
		DefaultMqttPahoClientFactory clientFactory = new DefaultMqttPahoClientFactory();
		clientFactory.setConnectionOptions(connectOptions());
		return clientFactory;
	}

	@Bean
	public MessageChannel mqttInputChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageProducer inboundChannel(DefaultMqttPahoClientFactory clientFactory) {
		MqttPahoMessageDrivenChannelAdapter adapter =
				new MqttPahoMessageDrivenChannelAdapter(HOST, CLIENT_ID, clientFactory,
						TOPIC_FILTER, TOPIC_FILTER_2);
		adapter.setCompletionTimeout(5000);
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setQos(1);
		adapter.setOutputChannel(mqttInputChannel());
		return adapter;
	}

	@Bean
	@ServiceActivator(inputChannel = "mqttInputChannel")
	public MessageHandler messageHandler() {
		return new MqttMessageHandler();
	}

	@Bean
	public MessageChannel mqttOutboundChannel() {
		return new DirectChannel();
	}

	@Bean
	@ServiceActivator(inputChannel = "mqttOutboundChannel")
	public MessageHandler mqttOutbound(DefaultMqttPahoClientFactory clientFactory) {
		MqttPahoMessageHandler messageHandler =
				new MqttPahoMessageHandler(CLIENT_ID + MqttAsyncClient.generateClientId(),
						clientFactory);
		messageHandler.setAsync(true);
		messageHandler.setDefaultQos(1);
		return messageHandler;
	}

}
