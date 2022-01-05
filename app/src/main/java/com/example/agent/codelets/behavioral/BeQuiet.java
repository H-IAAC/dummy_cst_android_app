package com.example.agent.codelets.behavioral;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryContainer;

public class BeQuiet extends Codelet {
    Memory chattingMO;
    Memory restingMO;
    MemoryContainer displayMO;

    @Override
    public void accessMemoryObjects() {
        if (chattingMO == null){
            chattingMO = this.getInput("CHATTING");
        }
        if (restingMO == null){
            restingMO = this.getInput("RESTING");
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
        if (chattingMO. getI() != null && restingMO.getI() != null){
            boolean shouldBeQuiet = (boolean) chattingMO.getI() || (boolean) restingMO.getI();
            if(shouldBeQuiet){
                displayMO.setI("resting_face", 0.9, "QUIET");
            }
            else{
                displayMO.setI(null, 0.0, "QUIET");
            }
            //&&
        }



    }
}
