/*
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

@Slf4j
public class MqttMessageHandler implements MessageHandler {

	/**
	 * 구독 중인 MQTT 메시지는 여기서 받는다.
	 *
	 * @param message
	 * @throws MessagingException
	 */
	@Override
	public void handleMessage(Message<?> message) throws MessagingException {
		String topic = (String) message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC);
		log.info("MQTT Rx: {} {}", topic, message.getPayload());

	}
}
