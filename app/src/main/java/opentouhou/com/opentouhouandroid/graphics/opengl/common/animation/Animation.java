package opentouhou.com.opentouhouandroid.graphics.opengl.common.animation;

/*
 * Represents a generic sequence animation.
 */

public abstract class Animation<T> {
    private String name;

    protected int count = 0;
    protected int currentFrame = 0;
    protected int frameCount = 0;
    protected T[] sequence;

    /*
     * Constructor(s).
     */
    public Animation(String name) {
        this.name = name;
    }

    /*
     * Getter(s).
     */
    public String getName() { return name; }

    /*
     * Retrieve the current sequence item.
     */
    public T currentFrame() {
        return sequence[currentFrame];
    }

    /*
     * Move to the next sequence item.
     */
    public void nextFrame() {
        if (currentFrame < (frameCount - 1)) {
            if (count < 4) {
                count++;
            }
            else {
                currentFrame++;
                count = 0;
            }
        }
        else {
            if (count < 4) {
                count++;
            }
            else {
                count = 0;
                currentFrame = 0;
            }
        }
    }

    abstract void setSequence(T[] sequence);

    @Override
    public String toString() {
        return "Animation: " + name;
    }
}