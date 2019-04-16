package com.scarlet.ui;

public class ExitActivityListener implements OnEventListener {
    @Override
    public void onEvent() {
        // Exit current activity on the stack.
        System.exit(0);
    }
}