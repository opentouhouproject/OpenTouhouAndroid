package com.scarlet.animation;

import java.util.ArrayList;

public abstract class AnimationGroup extends AbstractAnimation {

  private ArrayList<AbstractAnimation> animations;

  public AnimationGroup(String name) {
    super(name);

    animations = new ArrayList<>();
  }

  /**
   *
   * @param animation
   */
  public void addAnimationGroup(AbstractAnimation animation) {

  }

  /**
   * Retrieves the animation at the given index.
   * @param index
   * @return
   */
  public AbstractAnimation animationAt(int index) {
    try {
      return animations.get(index);
    } catch (IndexOutOfBoundsException e) {
      return null;
    }
  }

  /**
   *
   * @return
   */
  public int animationCount() {
    return animations.size();
  }

  /**
   *
   */
  public void clear() {
    animations.clear();
  }

  public boolean event() {
    return false;
  }

  /**
   *
   * @param animation
   * @return
   */
  public int indexOf(AbstractAnimation animation) {
    for (AbstractAnimation anim : animations) {
      if (anim == animation) {
        return 0;
      }
    }

    return -1;
  }

  /**
   *
   * @param index
   * @param animation
   */
  public void insertAnimation(int index, AbstractAnimation animation) {

  }

  public void removeAnimation(AbstractAnimation animation) {

  }

  AbstractAnimation takeAnimation(int index) {
    return null;
  }
}