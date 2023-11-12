package org.example;

import java.util.Random;

public class TemperatureGenerator implements Runnable {

    private Random random = new Random();
    private MqttHandler mqttHandler;
    private float startValue = 20;

    public TemperatureGenerator(MqttHandler mqttHandler) {
        this.mqttHandler = mqttHandler;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
                float change = random.nextFloat() - 0.3f;
                startValue += change;
                String publishData = String.format("%.1f", startValue).replace(",", ".");
                publishData = "{temperature:" + publishData + "}";
                mqttHandler.publish("Temperature", String.format("%.1f", startValue).replace(",", "."));
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}