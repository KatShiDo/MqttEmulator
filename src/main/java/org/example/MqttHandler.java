package org.example;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttHandler {

    private MqttClient client;

    public void connect(String brokerUrl, String clientId) {
        try {
            //var persistence = new MemoryPersistence();
            client = new MqttClient(brokerUrl, clientId);
            client.connect();
        }
        catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            client.disconnect();
        }
        catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(String topic, String message) {
        try {
            var mqttMessage = new MqttMessage(message.getBytes());
            client.publish(topic, mqttMessage);
        }
        catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
