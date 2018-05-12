package org.lehmenkuehler.calculator.Mechanics;


import org.lehmenkuehler.calculator.Enums.Component;

public class Element {

    private Component component = Component.VOID;
    private int id = 0;
    private int answerId = 0;
    private OpticFeatureScript opticFeatureScript = OpticFeatureScript.NORMAL;
    private OpticFeatureSize opticFeatureSize = OpticFeatureSize.NORMAL;

    public enum OpticFeatureScript {NORMAL, SUPERSCRIPT_START, SUPERSCRIPT_STOP, SUBSCRIPT_START, SUBSCRIPT_STOP}
    public enum OpticFeatureSize {NORMAL, SMALL_START, SMALL_STOP}

    public Element(Component component, int id) {
        this.component = component;
        this.id = id;
    }
    public Element(Component component, int id, OpticFeatureScript opticFeatureScript, OpticFeatureSize opticFeatureSize) {
        this.component = component;
        this.id = id;
        this.opticFeatureScript = opticFeatureScript;
        this.opticFeatureSize = opticFeatureSize;
    }

    public Component getComponent() {
        return component;
    }

    public int getId() {
        return id;
    }

    public OpticFeatureScript getOpticFeatureScript() {
        return opticFeatureScript;
    }

    public OpticFeatureSize getOpticFeatureSize() {
        return opticFeatureSize;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getAnswerId() {
        return answerId;
    }
}
