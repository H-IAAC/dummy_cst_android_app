package com.example.agent.codelets.perceptual;

import java.util.Arrays;
import java.util.List;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;

public class WalkingDetector extends Codelet {
    Memory sensorMicMO;
    Memory sensor3DMO;
    Memory walkingMO;

    @Override
    public void accessMemoryObjects() {
        if (sensorMicMO == null){
            sensorMicMO = this.getInput("SENSOR_MIC");
        }
        if (sensor3DMO == null){
            sensor3DMO = this.getInput("SENSOR_3D");
        }
        if (walkingMO == null){
            walkingMO = this.getOutput("WALKING");
        }
    }

    @Override
    public void calculateActivation() {

    }

    @Override
    public void proc() {

        if(sensor3DMO.getI() != null && sensorMicMO.getI() != null){
            List<Double> euler = (List<Double>)sensor3DMO.getI();
            List<Double> nullOption = Arrays.asList(0d,0d,0d);

            if(!euler.containsAll(nullOption)){
                walkingMO.setI(true);

            }
            else{
                walkingMO.setI(false);
            }
        }

    }
}
