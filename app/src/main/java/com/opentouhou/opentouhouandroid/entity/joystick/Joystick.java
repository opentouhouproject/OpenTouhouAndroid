package com.opentouhou.opentouhouandroid.entity.joystick;

import com.scarlet.io.event.EventQueue;
import com.scarlet.math.Vector3f;

/**
 * A virtual joystick for moving the player sprite.
 */
public class Joystick {
  private Vector3f position;
  private EventQueue eventQueue;

  public Joystick(EventQueue eventQueue) {
    position = new Vector3f(0f, 0f, 0f);
  }

  private void queueCommand() {
    eventQueue.enqueue(new JoystickEvent());
  }
}