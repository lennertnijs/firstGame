package com.mygdx.game.TextureSelector;

import com.mygdx.game.Util.Direction;
import com.mygdx.game.WeekSchedule.Action;

import java.util.List;
import java.util.Objects;

public final class AnimationManager implements IAnimationManager{

    private final FrameSelectionData data;
    private final AnimationRepository repository;

    public AnimationManager(List<Action> actionList, Direction direction, double delta, AnimationRepository repository){
        this.data = new FrameSelectionData(actionList, direction, delta);
        this.repository = Objects.requireNonNull(repository, "Animation repository is null.");
    }

    @Override
    public Frame getFrame(){
        Key key = new Key(data.getActiveAction(), data.getDirection());
        return repository.get(key).getFrame(data.getDelta());
    }

    @Override
    public void setDirection(Direction direction) {
        data.setDirection(direction);
    }

    @Override
    public void pushAction(Action action) {
        data.addAction(action);
    }

    @Override
    public Action getActiveAction() {
        return data.getActiveAction();
    }

    @Override
    public void popActiveAction() {
        data.removeAction();
    }

    @Override
    public void increaseDelta(double delta){
        data.increaseDelta(delta);
    }
}
