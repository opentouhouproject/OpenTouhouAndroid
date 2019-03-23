package opentouhou.com.opentouhouandroid.scene.loader;

import android.util.Log;

public class CreateVAORunnable implements Runnable {
    private CreateVAOTask task;

    /*
     * Constructor(s).
     */
    public CreateVAORunnable(CreateVAOTask task) {
        this.task = task;
    }

    /*
     * Implement Runnable.
     */
    @Override
    public void run() {
        Log.d("VAO CREATE", "Starting VAO creation.");
        task.createVAO();

        Log.d("VAO CREATE", "VAO created.");
        task.done = true;
    }
}