package com.example.agent.mind;

import com.example.agent.codelets.behavioral.BeQuiet;
import com.example.agent.codelets.behavioral.ShowSteps;
import com.example.agent.codelets.motor.DisplayAction;
import com.example.agent.codelets.perceptual.ChatingDetector;
import com.example.agent.codelets.perceptual.RestingDetector;
import com.example.agent.codelets.perceptual.WalkingDetector;
import com.example.agent.codelets.sensory.FakeMic;
import com.example.agent.codelets.sensory.FakeOscilloscope;

import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryContainer;
import br.unicamp.cst.core.entities.Mind;

public class AgentMind extends Mind {
    private OnTextChangeListener listener;


    public AgentMind(OnTextChangeListener listener){
        super();
        this.listener = listener;
        mindMount();

    }



    private void mindMount(){
        // Declare Memory Objects
        Memory micMO = createMemoryObject("SENSOR_MIC");
        Memory pos3DMO = createMemoryObject("SENSOR_3D");
        Memory chattingMO = createMemoryObject("CHATTING");
        Memory walkingMO = createMemoryObject("WALKING");
        Memory restingMO = createMemoryObject("RESTING");
        Memory quiet = createMemoryObject("QUIET");
        Memory steps = createMemoryObject("STEPS");
        MemoryContainer displayContainer = createMemoryContainer("DISPLAY");

        displayContainer.add(quiet);
        displayContainer.add(steps);

        // Declare and insert Sensory Codelets
        FakeMic mic = new FakeMic();
        mic.addOutput(micMO);
        insertCodelet(mic);

        FakeOscilloscope pos3D = new FakeOscilloscope();
        pos3D.addOutput(pos3DMO);
        insertCodelet(pos3D);

        // Declare and insert Perceptual Codelets
        ChatingDetector chatingDetector = new ChatingDetector();
        chatingDetector.addInput(micMO);
        chatingDetector.addInput(pos3DMO);
        chatingDetector.addOutput(chattingMO);
        insertCodelet(chatingDetector);

        RestingDetector restingDetector = new RestingDetector();
        restingDetector.addInput(micMO);
        restingDetector.addInput(pos3DMO);
        restingDetector.addOutput(restingMO);
        insertCodelet(restingDetector);

        WalkingDetector walkingDetector = new WalkingDetector();
        walkingDetector.addInput(micMO);
        walkingDetector.addInput(pos3DMO);
        walkingDetector.addOutput(walkingMO);
        insertCodelet(walkingDetector);


        // Declare and insert Perceptual Codelets
        BeQuiet beQuiet = new BeQuiet();
        beQuiet.addInput(chattingMO);
        beQuiet.addInput(restingMO);
        beQuiet.addOutput(displayContainer);
        insertCodelet(beQuiet);

        ShowSteps showSteps = new ShowSteps();
        showSteps.addInput(walkingMO);
        showSteps.addOutput(displayContainer);
        insertCodelet(showSteps);

        // Declare and insert Motor Codelets
        System.out.println(listener);
        DisplayAction displayAction = new DisplayAction(this.listener);
        displayAction.addInput(displayContainer);
        insertCodelet(displayAction);


        // Start Cognitive Cycle
        start();
    }

    public interface OnTextChangeListener{
        void onTextChanged(String text);
    }

}


