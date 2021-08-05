/*
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttPublisher {

	/**
	 * MQTT 메시지는 여기서 발행한다.
	 *
	 * @param topic
	 * @param payload
	 */
	void publish(@Header(MqttHeaders.TOPIC) String topic, String payload);

}
