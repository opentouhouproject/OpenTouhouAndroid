package com.opentouhou.opentouhouandroid.scene.scenes.startmenu;

import com.scarlet.scene.State;

import org.jetbrains.annotations.NotNull;

class States {
  // States
  static final State<StartMenuScreen30> INITIAL_STATE = new StateInitial();

  /*
   * Constructor(s).
   */
  private States() { }

  @Override @NotNull
  public String toString() {
    return "Start Menu States.";
  }
}