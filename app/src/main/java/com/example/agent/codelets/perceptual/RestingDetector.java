package com.example.agent.codelets.perceptual;

import java.util.Arrays;
import java.util.List;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;

public class RestingDetector extends Codelet {
    Memory sensorMicMO;
    Memory sensor3DMO;
    Memory restingMO;

    @Override
    public void accessMemoryObjects() {
        if (sensorMicMO == null){
            sensorMicMO = this.getInput("SENSOR_MIC");
        }
        if (sensor3DMO == null){
            sensor3DMO = this.getInput("SENSOR_3D");
        }
        if (restingMO == null){
            restingMO = this.getOutput("RESTING");
        }
    }

    @Override
    public void calculateActivation() {

    }

    @Override
    public void proc() {

        if(sensor3DMO.getI() != null && sensorMicMO.getI() != null){
            List<Double> euler = (List<Double>)sensor3DMO.getI();
            Double mic = (Double)sensorMicMO.getI();
            List<Double> nullOption = Arrays.asList(0d,0d,0d);

            if(mic <= 1.5 && euler.containsAll(nullOption)){
                restingMO.setI(true);
            }
            else{
                restingMO.setI(false);
            }
        }
    }
}
