package com.scarlet.animation;

/**
 * The base class of all animations.
 */
public abstract class AbstractAnimation {
  private String name;

  private State state;
  protected Direction direction;
  private int currentLoop;
  private int loopCount;
  private int time;
  private int duration;

  /**
   * Constructor.
   * @param name - The name of the animation.
   */
  public AbstractAnimation(String name) {
    this.name = name;
    state = State.STOPPED;
    direction = Direction.FORWARD;
    currentLoop = 0;
    loopCount = 0;
    time = 0;
    duration = 0;
  }

  /**
   * Returns the name of the animation.
   * @return String - The animation name.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the current state of the animation.
   * @return State - The animation state.
   */
  public State state() {
    return state;
  }

  /**
   * Transitions the animation state from STOPPED to RUNNING.
   */
  public void start() {
    if (state == State.STOPPED) {
      state = State.RUNNING;
    }
  }

  /**
   * Transitions the animation state to STOPPED.
   */
  public void stop() {
    state = State.STOPPED;
  }

  /**
   * Transitions the animation state from RUNNING to PAUSED.
   */
  public void pause() {
    if (state == State.RUNNING) {
      state = State.PAUSED;
    }
  }

  /**
   * Transitions the animation state from PAUSED to RUNNING.
   */
  public void resume() {
    if (state == State.PAUSED) {
      state = State.RUNNING;
    }
  }

  /**
   * Retrieves the current elapsed time of the animation.
   * @return int - The current elapsed time.
   */
  public int currentLoopTime() {
    return time;
  }

  /**
   * Retrieves the duration of the animation.
   * @return int - The duration of the animation.
   */
  public int duration() {
    return duration;

  }

  /**
   * Sets the time of the animation.
   * @param time
   */
  public void setCurrentTime(int time) {
    this.time = time;
  }

  abstract void updateCurrentTime(int currentTime);
  abstract void updateDirection(Direction direction);
  abstract void updateState(State newState, State oldState);

  @Override
  public String toString() {
    return "Animation: " + name;
  }
}