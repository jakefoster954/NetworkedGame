package graphics;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL;

import graphics.gamestate.GameStateManager;

import org.lwjgl.glfw.GLFWVidMode;

public class Main {
	private GameStateManager gsm;
	
	private boolean running;
	
	private final long MAX_FPS = 60;

	private final int WIDTH = 1080, HEIGHT = 720;
	
	public Main() {
		
		//check the LWJGL jars have been imported
		if (!glfwInit()) {
			System.err.println("GLFW Failed to Initialize");
			System.exit(1);
		}
	
		//create window
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		long window = glfwCreateWindow(WIDTH, HEIGHT, "My LWJGL Program", 0, 0);
		if (window == 0) {
			System.err.println("Failed to Create Window");
			System.exit(1);
		}
		
		//set window position on computer screen
		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (videoMode.width() - WIDTH) / 2, (videoMode.height() - HEIGHT) / 2);
	
		glfwShowWindow(window);
		
		glfwMakeContextCurrent(window);
		
		GL.createCapabilities();
		
		glEnable(GL_TEXTURE_2D);	//Textures must be below the context creation (create capabilities)
		
		gsm = GameStateManager.getInstance();
		
		running = true;
		long start; // nanoseconds
		long elapsed; // nanoseconds
		long wait; // milliseconds
		long target = 1_000 / MAX_FPS; // milliseconds
		int fps;

		while (!glfwWindowShouldClose(window) && running) {
			start = System.nanoTime();
			input(window);
			
			glfwPollEvents();
			
			glClear(GL_COLOR_BUFFER_BIT);
				
			update();
			render();
			
			glfwSwapBuffers(window);
			
			elapsed = System.nanoTime() - start;

			if (elapsed / 1_000_000 < target) {
				wait = target - elapsed / 1_000_000;
				try {
					Thread.sleep(wait);
					elapsed = System.nanoTime() - start;
				} catch (InterruptedException e) {
					System.err.println("Thread.sleep failed");
				}
			}

			fps = (int) (1_000_000_000 / elapsed);
			if (fps < 60) {System.out.println("FPS: " + fps);}


		}
		
		glfwTerminate();
	}
	
	public void input(long window) {
		if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GL_TRUE) {
			glfwSetWindowShouldClose(window, true);
		}
	}
	
	public void update() {
		gsm.update();
	}
	
	public void render() {
		gsm.render();
	}
	
	
	public static void main(String[] args) {
		new Main();
	}
}
