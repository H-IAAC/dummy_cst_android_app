package com.example.agent.codelets.motor;

import android.widget.TextView;

import com.example.agent.mind.AgentMind;
import com.example.test_cst_android_2.R;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.MemoryContainer;

public class DisplayAction extends Codelet {
    MemoryContainer displayMO;
    TextView textView;
    private AgentMind.OnTextChangeListener listener;

    public DisplayAction(AgentMind.OnTextChangeListener listener){
        super();
        this.listener = listener;

    }

    @Override
    public void accessMemoryObjects() {
        if (displayMO == null){
            displayMO = (MemoryContainer) this.getInput("DISPLAY");
        }
    }

    @Override
    public void calculateActivation() {

    }

    @Override
    public void proc() {
        Object action = displayMO.getI();

        if(action instanceof Integer){
            // show steps count
            notifyTextChange(action + " steps");
        }
        else if(action instanceof String) {
            // show quiet face
            System.out.println("here");
            notifyTextChange(":|");
        }

    }

    public void notifyTextChange(String text){
        this.listener.onTextChanged(text);
    }
}
