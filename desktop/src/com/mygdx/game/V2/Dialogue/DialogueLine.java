package com.mygdx.game.V2.Dialogue;

import java.util.Objects;

public final class DialogueLine implements Line{

    private final String text;

    private DialogueLine(String text){
        this.text = text;
    }

    public static DialogueLine create(String text){
        Objects.requireNonNull(text, "Cannot create a DialogueLine with a null text.");
        return new DialogueLine(text);
    }

    public String getText(){
        return text;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof DialogueLine))
            return false;
        DialogueLine line = (DialogueLine) other;
        return text.equals(line.text);
    }

    @Override
    public int hashCode(){
        return text.hashCode();
    }

    @Override
    public String toString(){
        return String.format("DialogueLine[Text=%s]", text);
    }
}
