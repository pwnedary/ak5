/**
 * 
 */
package org.gamelib.graphics;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.gamelib.backend.Backend;
import org.gamelib.util.Disposable;

/** @author pwnedary */
public class FrameBufferObject implements Disposable {
	private Backend backend;
	private GL20 gl;
	private int framebuffer;
	private Texture texture;

	public FrameBufferObject(Backend backend, GL10 gl, Texture texture) {
		this.backend = backend;
		this.gl = (GL20) gl;

		IntBuffer buffer = ByteBuffer.allocateDirect(4).asIntBuffer();
		this.gl.glGenFramebuffers(1, buffer);
		framebuffer = buffer.get(0);

		(this.texture = texture).bind();
		bind();
		this.gl.glFramebufferTexture2D(GL20.GL_FRAMEBUFFER, GL20.GL_COLOR_ATTACHMENT0, texture.getTarget(), texture.getTexture(), 0);
		unbind();

		int status = this.gl.glCheckFramebufferStatus(GL20.GL_FRAMEBUFFER);
		if (status != GL20.GL_FRAMEBUFFER_COMPLETE) {
			dispose();
			switch (status) {
			case GL20.GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT:
				throw new RuntimeException("FrameBuffer: " + framebuffer + ", has caused a GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT exception");
			case GL20.GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT:
				throw new RuntimeException("FrameBuffer: " + framebuffer + ", has caused a GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT exception");
			case GL20.GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS:
				throw new RuntimeException("FrameBuffer: " + framebuffer + ", has caused a GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS exception");
			case GL20.GL_FRAMEBUFFER_UNSUPPORTED:
				throw new RuntimeException("FrameBuffer: " + framebuffer + ", has caused a GL_FRAMEBUFFER_UNSUPPORTED exception");
			default:
				throw new RuntimeException("Unexpected reply from glCheckFramebufferStatus: " + status);
			}
		}
	}

	public void bind() {
		gl.glBindFramebuffer(GL20.GL_FRAMEBUFFER, framebuffer);
		gl.glViewport(0, 0, texture.getWidth(), texture.getHeight());
	}

	public void unbind() {
		gl.glBindFramebuffer(GL20.GL_FRAMEBUFFER, 0);
		gl.glViewport(0, 0, backend.getWidth(), backend.getHeight());
	}

	@Override
	public void dispose() {
		unbind();
		IntBuffer buffer = ByteBuffer.allocateDirect(4).asIntBuffer();
		buffer.put(framebuffer);
		gl.glDeleteFramebuffers(1, buffer);
	}
}
