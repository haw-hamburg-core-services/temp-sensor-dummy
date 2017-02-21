package de.haw_hamburg.informatik.core.temp_sensor_dummy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * Created by TimoHäckel on 21.02.2017.
 */
public class TempSensorDummy implements Runnable{
    private static final Logger log = LoggerFactory.getLogger(TempSensorDummy.class);

    private static final String URI = "http://localhost:8091/";
    private static final String PATH = "tempdata/insert";

    private final String sensorID;

    public TempSensorDummy(String sensorID) {
        this.sensorID = sensorID;
    }

    @Override
    public void run() {

        while (!Thread.interrupted()){
            double temperature = (double)(Math.random() * 30);
            String query = URI + PATH +
                    "?srcid=" + sensorID +
                    "&temperature=" + temperature;
            System.out.println(query);
            RestTemplate restTemplate = new RestTemplate();
            String ret = restTemplate.getForObject(query, String.class);
            System.out.println(ret);
            try {
                Thread.sleep((int)(Math.random()*5000));
            } catch (InterruptedException e) {
                break;
            }
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
