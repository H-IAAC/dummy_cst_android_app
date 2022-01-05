package com.example.agent.codelets.sensory;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Random;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;

public class FakeMic extends Codelet {
    Memory sensorMicMO;
    Random random = new Random(42);

    @Override
    public void accessMemoryObjects() {
        if (sensorMicMO == null){
            sensorMicMO = this.getOutput("SENSOR_MIC");
        }
    }

    @Override
    public void calculateActivation() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void proc() {
        double level = voice();
        Double fakeNoise = random.nextDouble();

        //System.out.println(level);
        sensorMicMO.setI(level + fakeNoise);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    double voice(){
         double level = new Random().doubles(-Math.PI, Math.PI).limit(1).findFirst().getAsDouble();

         if(!random.nextBoolean()){
             return 0;
         }

         return 10*Math.sin(level);
    }
}
