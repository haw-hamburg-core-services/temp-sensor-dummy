package de.haw_hamburg.informatik.core.temp_sensor_dummy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;


/**
 * Created by TimoHäckel on 30.01.2017.
 */
public class TempSensorDummy implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(RainSensorDummy.class);

    private static final String URI = "http://localhost:8090/";
    private static final String PATH = "raindata/insert";

    private final String sensorID;

    public TempSensorDummy(String sensorID) {
        this.sensorID = sensorID;
    }

    @Override
    public void run() {

        while (!Thread.interrupted()){
            //Fill what todo!
        }

    }
    public static void main(String args[]) throws InterruptedException {
        int NUMBER_OF_DUMMYS = 10;
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i<NUMBER_OF_DUMMYS; i++){
            TempSensorDummy rsd = new TempSensorDummy("TempSensorDummy:" + (int)(Math.random()*1000));
            Thread thread = new Thread(rsd);

            threads.add(thread);

            thread.start();
        }

        Thread.sleep(20000);

        for(Thread thread : threads) {
            thread.interrupt();
        }


    }
}
