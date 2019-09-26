package com.opentouhou.opentouhouandroid.scene.scenes.game;

import com.scarlet.scene.State;

import org.jetbrains.annotations.NotNull;

/*
 * Holds all possible states.
 */
class States {
  static final State<GameScene30> INITIAL_STATE = new StateInitial();

  private States() { }

  @Override @NotNull
  public String toString() {
    return "Game States.";
  }
}