package opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.postprocessing;

import android.opengl.GLES30;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.ShaderProgram;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.Renderer30;

public class PostProcessing {
    private PostProcessing() { }

    public static void GaussianBlur(Renderer30 renderer) {
        ShaderProgram program = renderer.getShaderManager().getShaderProgram("GaussianBlur");

        boolean isHorizontal = true;
        boolean isFirstIteration = true;
        int amount = 4;

        for (int i = 0; i < amount; i++) {
            if (isHorizontal) {
                //Log.d("Post Processing", "BIND PP 1");
                renderer.postProcessingBuffer1.bind();
            } else {
                renderer.postProcessingBuffer2.bind();
            }

            renderer.renderQuad.setShader(program);
            renderer.renderQuad.useShader();

            int handle = GLES30.glGetUniformLocation(program.getHandle(), "isHorizontal");
            float val = 1.0f;
            if (isHorizontal) {
                val = 0.0f;
            }
            GLES30.glUniform1f(handle, val);

            if (isFirstIteration) {
                //Log.d("Post Processing", "TEXTURE FB 01");
                renderer.renderQuad.setupTexture(renderer.frameBuffer.getTexture(1), GLES30.GL_TEXTURE0, "image");
            } else {
                if (isHorizontal) {
                    //Log.d("Post Processing", "TEXTURE pp2 00");
                    renderer.renderQuad.setupTexture(renderer.postProcessingBuffer2.getTexture(0), GLES30.GL_TEXTURE0, "image");
                } else {
                    //Log.d("Post Processing", "TEXTURE pp1 00");
                    renderer.renderQuad.setupTexture(renderer.postProcessingBuffer1.getTexture(0), GLES30.GL_TEXTURE0, "image");
                }
            }

            renderer.renderQuad.draw(renderer.stage.getCurrentScene());

            if (isHorizontal) {
                //Log.d("Post Processing", "BIND PP 1");
                renderer.postProcessingBuffer1.unbind();
            } else {
                renderer.postProcessingBuffer2.unbind();
            }

            isHorizontal = !isHorizontal;

            if (isFirstIteration) {
                isFirstIteration = false;
            }
        }
    }
}