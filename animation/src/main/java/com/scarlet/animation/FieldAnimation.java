package com.scarlet.animation;

public abstract class FieldAnimation<T> extends AbstractAnimation {
  public FieldAnimation(String name) {
    super(name);
  }

  @Override
  void updateCurrentTime(int currentTime) {

  }

  @Override
  void updateDirection(Direction direction) {
    this.direction = direction;
  }

  @Override
  void updateState(State newState, State oldState) {

  }
}