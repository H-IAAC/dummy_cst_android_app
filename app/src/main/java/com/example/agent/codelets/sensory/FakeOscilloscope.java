package com.example.agent.codelets.sensory;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;

public class FakeOscilloscope extends Codelet {
    Memory sensor3DMO;
    Random random = new Random(42);

    @Override
    public void accessMemoryObjects() {
        if (sensor3DMO == null){
            sensor3DMO = this.getOutput("SENSOR_3D");
        }
    }

    @Override
    public void calculateActivation() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void proc() {
        List<Double> fakeReadings = getEuler();

        sensor3DMO.setI(fakeReadings);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List getEuler(){
        double theta = new Random().doubles(-Math.PI, Math.PI).limit(1).findFirst().getAsDouble();
        double phi = new Random().doubles(-Math.PI, Math.PI).limit(1).findFirst().getAsDouble();
        double azimutal = new Random().doubles(-Math.PI/2, Math.PI/2).limit(1).findFirst().getAsDouble();

        if(!random.nextBoolean()){
            return Arrays.asList(0d,0d,0d);
        }
        return Arrays.asList(theta, phi, azimutal);
    }
}
