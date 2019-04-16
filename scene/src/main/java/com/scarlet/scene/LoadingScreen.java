package com.scarlet.scene;

public abstract class LoadingScreen extends Scene {
    public boolean finishedLoading = false;

    public LoadingScreen(String name, Stage stage) {
        super(name, stage);
    }
}