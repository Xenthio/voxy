package me.cortex.voxy.client.core.rendering.post;

import com.mojang.blaze3d.systems.RenderSystem;
import me.cortex.voxy.client.core.gl.shader.Shader;
import me.cortex.voxy.client.core.gl.shader.ShaderType;

import static org.lwjgl.opengl.GL11C.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11C.glDrawArrays;
import static org.lwjgl.opengl.GL20C.*;
import static org.lwjgl.opengl.GL30C.glBindVertexArray;
import static org.lwjgl.opengl.GL45C.glCreateVertexArrays;

public class FullscreenBlit {
    private static final int EMPTY_VAO = glCreateVertexArrays();

    private final Shader shader;
    public FullscreenBlit(String fragId) {
        this.shader = Shader.make()
                .add(ShaderType.VERTEX, "voxy:post/fullscreen.vert")
                .add(ShaderType.FRAGMENT, fragId)
                .compile();
    }

    public void bind() {
        this.shader.bind();
    }

    public void blit() {
        glBindVertexArray(EMPTY_VAO);
        this.shader.bind();
        int fogStart = glGetUniformLocation(this.shader.id(), "FogStart");
        int fogEnd = glGetUniformLocation(this.shader.id(), "FogEnd");
        int fogColour = glGetUniformLocation(this.shader.id(), "FogColour");
        glUniform1f(fogStart, RenderSystem.getShaderFogStart());
        glUniform1f(fogEnd,RenderSystem.getShaderFogEnd());
        glUniform4f(fogColour,RenderSystem.getShaderFogColor()[0],RenderSystem.getShaderFogColor()[1],RenderSystem.getShaderFogColor()[2],RenderSystem.getShaderFogColor()[3]);
        glDrawArrays(GL_TRIANGLES, 0, 6);
        glBindVertexArray(0);
    }

    public void delete() {
        this.shader.free();
    }
}
