package com.example.agent.codelets.behavioral;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryContainer;

public class ShowSteps extends Codelet {
    Memory walkingMO;
    MemoryContainer displayMO;
    int steps = 0;

    @Override
    public void accessMemoryObjects() {
        if (walkingMO == null){
            walkingMO = this.getInput("WALKING");
        }

        if (displayMO == null){
            displayMO = (MemoryContainer)this.getOutput("DISPLAY");
        }

    }

    @Override
    public void calculateActivation() {

    }

    @Override
    public void proc() {
        if(walkingMO.getI() != null ){
            boolean displaySteps = (boolean) walkingMO.getI();
            if(displaySteps){
                steps++;
                displayMO.setI(steps, 0.9, "STEPS");
            }
            else{
                displayMO.setI(null, 0.0, "STEPS");
            }
            //&&
        }
    }
}
