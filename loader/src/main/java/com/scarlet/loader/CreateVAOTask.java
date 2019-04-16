package com.scarlet.loader;

import com.scarlet.opengles30.mesh.Mesh30;

public class CreateVAOTask {
    // The scene to load/setup.
    private Mesh30 mesh;
    private int handle;

    public volatile boolean done = false;

    /*
     * Constructor(s).
     */
    public CreateVAOTask(Mesh30 mesh, int handle) {
        this.mesh = mesh;
        this.handle = handle;
    }

    /*
     * Creates a new runnable.
     */
    public CreateVAORunnable getRunnable() {
        return new CreateVAORunnable(this);
    }

    /*
     * Creates the VAO.
     */
    public void createVAO() {
        mesh.createVAO(handle);
    }
}