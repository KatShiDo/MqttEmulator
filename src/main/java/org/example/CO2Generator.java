package org.example;

import java.util.Random;

public class CO2Generator implements Runnable {

    private Random random = new Random();
    private MqttHandler mqttHandler;
    private int startValue = 1000;

    public CO2Generator(MqttHandler mqttHandler) {
        this.mqttHandler = mqttHandler;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
                int change = random.nextInt(10) - 3;
                startValue += change;
                String publishData = String.valueOf(startValue);
                publishData = "{co2:" + publishData + "}";
                mqttHandler.publish("CO2", String.valueOf(startValue));
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
