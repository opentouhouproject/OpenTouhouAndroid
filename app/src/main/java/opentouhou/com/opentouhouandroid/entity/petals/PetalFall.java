package opentouhou.com.opentouhouandroid.entity.petals;

import opentouhou.com.opentouhouandroid.entity.GameEntity;
import com.scarlet.graphics.opengl.GraphicsOptions;
import com.scarlet.graphics.opengl.Renderer;

/*
 * Simulates falling petals.
 */
public class PetalFall extends GameEntity {
    private PetalDrawable30 drawable;

    private Petal[] petalList;

    private int numberOfPetals = 50;

    /*
     * Constructor(s).
     */
    public PetalFall(Renderer renderer) {
        // Generate a drawable petal.
        drawable = createDrawable(renderer);

        // Generate new petals.
        petalList = new Petal[numberOfPetals];

        for (int i = 0; i < numberOfPetals; i++) {
            petalList[i] = new Petal();
        }
    }

    /*
     * Creates a new drawable.
     */
    private PetalDrawable30 createDrawable(Renderer renderer) {
        GraphicsOptions opt = new GraphicsOptions(true, false);
        drawable = new PetalDrawable30(opt, renderer);

        return drawable;
    }

    /*
     * Implement update method.
     */
    public void update() {
        for (Petal petal : petalList) {
            petal.update();
        }
    }

    /*
     * Implement draw method.
     */
    public void draw(Renderer renderer) {
        for (Petal petal : petalList) {
            if (petal.isAnimated()) {
                drawable.setModelMatrix(petal.getModel());
                drawable.draw(renderer, petal);
            }
        }
    }
}