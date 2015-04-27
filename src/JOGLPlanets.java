import java.awt.*;
import java.awt.event.*;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL2ES1;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.fixedfunc.*;
import com.jogamp.opengl.fixedfunc.GLLightingFunc;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.gl2.GLUT;

public class JOGLPlanets implements GLEventListener {
    static final GLCanvas canvas = new GLCanvas();
    private float rotateT = 0.0f;
    private float sunRotate = 0.0f;
    private float moon1Rotate = 0.0f;
    private float moon2Rotate = 0.0f;
    private float submoon1Rotate = 0.0f;
    private float subsubmoon1Rotate = 0.0f;
    private float subsubmoon2Rotate = 0.0f;

    @Override
    public void display(GLAutoDrawable gLDrawable) {
        GLU glu = new GLU();
        GL2 gl = gLDrawable.getGL().getGL2();
        GLUT glut = new GLUT();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, -12.0f, -45.0f);

        // rotate about the three axises
        gl.glRotatef(rotateT, 0.0f, 1.0f, 0.0f);

        float[] ambientYellow = {1f, .8f, 0f, 1.0f};
        float[] diffuse = {1.0f, 1.0f, 1.0f, 1.0f };
        float[] specular ={ 1.0f, 1.0f, 1.0f, 1.0f };
        float[] position = { 200.0f, 300.0f, 100.0f, 0.0f };

        gl.glLightfv(gl.GL_LIGHT0, gl.GL_AMBIENT, ambientYellow, 0);
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_POSITION, position, 0);
        gl.glEnable(gl.GL_LIGHT0);
        gl.glEnable(gl.GL_LIGHTING);

        /*
         * Draw the sun
         */
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, 3.0f, 0.0f);
        gl.glRotatef(sunRotate, 0.0f, 1.0f, 0.0f);   // rotate on axis
        glut.glutSolidSphere(3.0, 20, 20);
        gl.glPopMatrix();

        /*
         * Draw moon M1
         */
        gl.glPushMatrix();
        float[] ambientBlue = {0.3f, 0.3f, 0.7f};
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_AMBIENT, ambientBlue, 0);

        gl.glTranslatef(-20.0f, 3.0f, 0.0f);
        gl.glRotatef(moon1Rotate, 0.0f, 1.0f, 0.0f);   // rotate on axis
        glut.glutSolidSphere(1.4f, 20, 20);
        gl.glPopMatrix();

        /*
         *   Draw sub-moon M11
         */
        gl.glPushMatrix();
        float[] ambientGray = {0.3f, 0.3f, 0.3f};
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_AMBIENT, ambientGray, 0);

        gl.glTranslatef(-20.0f, 3.0f, 0.0f);
        gl.glRotatef(submoon1Rotate, 0.0f, 1.0f, 0.0f);   // rotate around sub moon

        gl.glTranslatef(6.5f, 0.0f, 0.0f);
        gl.glRotatef(1.0f, 0.0f, 1.0f, 0.0f); // rotate on axis

        glut.glutSolidSphere(.70f, 20, 20);
        gl.glPopMatrix();

        /*
            Draw sub-sub moon M111
         */
        gl.glPushMatrix();

        gl.glTranslatef(-20.0f, 3.0f, 0.0f);
        gl.glRotatef(submoon1Rotate, 0.0f, 1.0f, 0.0f);   // rotate around moon

        gl.glTranslatef(6.5f, 0.0f, 0.0f);
        gl.glRotatef(subsubmoon1Rotate, 0.0f, 1.0f, 0.0f); // rotate around sub-moon

        gl.glTranslatef(-1.5f, 0.0f, 0.0f);
        gl.glRotatef(1.0f, 0.0f, 1.0f, 0.0f); // rotate around axis

        glut.glutSolidSphere(.45f, 20, 20);
        gl.glPopMatrix();

        /*
            Draw sub-sub moon M112
         */
        gl.glPushMatrix();

        gl.glTranslatef(-20.0f, 3.0f, 0.0f);
        gl.glRotatef(submoon1Rotate, 0.0f, 1.0f, 0.0f);   // rotate around moon

        gl.glTranslatef(6.5f, 0.0f, 0.0f);
        gl.glRotatef(subsubmoon2Rotate, 0.0f, 1.0f, 0.0f); // rotate around sub-moon

        gl.glTranslatef(3.1f, 0.0f, 0.0f);
        gl.glRotatef(1.0f, 0.0f, 1.0f, 0.0f); // rotate around axis

        glut.glutSolidSphere(.30f, 20, 20);
        gl.glPopMatrix();

        /*
         * Draw moon M2
         */
        gl.glPushMatrix();
        float[] ambientGreen = {0.3f, 0.6f, 0.3f};
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_AMBIENT, ambientGreen, 0);

        gl.glTranslatef(6.0f, 3.0f, -5.0f);
        gl.glRotatef(moon2Rotate, 0.0f, 1.0f, 0.0f);   //Y
        glut.glutSolidSphere(1.3f, 20, 20);
        gl.glPopMatrix();

        // increasing rotation for the next iteration
        rotateT += 0.3f;

        sunRotate += 0.5f;
        moon1Rotate += 0.6f;
        moon2Rotate += 0.7f;

        submoon1Rotate += .8f;
        subsubmoon1Rotate += .8f;
        subsubmoon2Rotate += .85f;
    }

    @Override
    public void init(GLAutoDrawable glDrawable) {
        GL2 gl = glDrawable.getGL().getGL2();
        gl.glShadeModel(GLLightingFunc.GL_SMOOTH);
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LEQUAL);
        gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
    }

    @Override
    public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height) {
        GL2 gl = gLDrawable.getGL().getGL2();
        final float aspect = (float) width / (float) height;
        gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        gl.glLoadIdentity();
        final float fh = 0.5f;
        final float fw = fh * aspect;
        gl.glFrustumf(-fw, fw, -fh, fh, 1.0f, 1000.0f);
        gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void dispose(GLAutoDrawable gLDrawable) {
        gLDrawable.destroy();
    }

    public static void main(String[] args) {
        final Frame frame = new Frame("Jogl Quad drawing");
        final Animator animator = new Animator(canvas);
        canvas.addGLEventListener(new JOGLPlanets());
        frame.add(canvas);
        frame.setSize(800, 520);
        frame.setResizable(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                animator.stop();
                frame.dispose();
                System.exit(0);
            }
        });

        canvas.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (Character.isSpaceChar(e.getKeyChar())) {
                    if (animator.isPaused()) {
                        animator.resume();
                    } else {
                        animator.pause();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        frame.setVisible(true);
        animator.start();
        canvas.requestFocus();
    }
}