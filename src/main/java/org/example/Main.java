package org.example;

import org.eclipse.paho.client.mqttv3.MqttClient;

public class Main {
    private static final String BROKER_URL = "tcp://127.0.0.1:1883";

    public static void main(String[] args) {
        var mqttT = new MqttHandler();
        mqttT.connect(BROKER_URL, MqttClient.generateClientId());
        var mqttCO2 = new MqttHandler();
        mqttCO2.connect(BROKER_URL, MqttClient.generateClientId());

        Thread co2 = new Thread(new CO2Generator(mqttCO2));
        Thread temperature = new Thread(new TemperatureGenerator(mqttT));
        co2.start();
        temperature.start();
    }
}