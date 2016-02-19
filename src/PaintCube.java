import com.jogamp.opengl.*;
import com.jogamp.opengl.util.gl2.GLUT;
import javafx.geometry.Point3D;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL3;
import javax.media.opengl.glu.GLU;
import java.util.Iterator;


public class PaintCube {
    protected static void setup( GL2 gl, int width, int height ) {

        GLU glu = new GLU();
        if (height == 0) height = 1;   // prevent divide by zero
        float aspect = (float)width / height;

        // Set the view port (display area) to cover the entire window
        gl.glViewport(0, 0, width, height);

        // Setup perspective projection, with aspect ratio matches viewport
        gl.glMatrixMode(gl.GL_PROJECTION);  // choose projection matrix
        gl.glLoadIdentity();             // reset projection matrix
        //glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar
        double fH, fW;
        fH = Math.tan(45.0 / 180 * Math.PI) * 0.1 / 2;
        fW = fH * aspect;
        gl.glFrustum(-fW, fW, -fH, fH, 0.1, 100.0);

        // Enable the model-view transform
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity(); // reset


    }


    protected static void render( GL2 gl, int width, int height, float x, float y, float z, int nx, int ny, int nz, float scale, double rotateX, double rotateY ) {

        GLU glu = new GLU();
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT); // clear color and depth buffers
        gl.glLoadIdentity();  // reset the model-view matrix
        gl.glPushMatrix();
        glu.gluLookAt(0, 0.5, -z, 0, 0, 0, 0, 1, 0);
        gl.glRotated(rotateX, 1, 0, 0);
        gl.glRotated(rotateY, 0, 1, 0);
        gl.glScaled(scale, scale, scale);

        x = x/2;
        y = y/2;
        z = z/2;
        gl.glBegin(gl.GL_LINES);

//front
        gl.glVertex3f(-x, y, z);
        gl.glVertex3f(x, y, z);

        gl.glVertex3f(x, y, z);
        gl.glVertex3f(x, -y, z);

        gl.glVertex3f(x, -y, z);
        gl.glVertex3f(-x, -y, z);

        gl.glVertex3f(-x, -y, z);
        gl.glVertex3f(-x, y, z);

//right
        gl.glVertex3f(x, y, z);
        gl.glVertex3f(x, y, -z);

        gl.glVertex3f(x, y, -z);
        gl.glVertex3f(x, -y, -z);

        gl.glVertex3f(x, -y, -z);
        gl.glVertex3f(x, -y, z);

//back
        gl.glVertex3f(x, y, -z);
        gl.glVertex3f(-x, y, -z);

        gl.glVertex3f(-x, -y, -z);
        gl.glVertex3f(x, -y, -z);

        gl.glVertex3f(-x, -y, -z);
        gl.glVertex3f(-x, y, -z);

//left
        gl.glVertex3f(-x, y, -z);
        gl.glVertex3f(-x, y, z);

        gl.glVertex3f(-x, -y, z);
        gl.glVertex3f(-x, -y, -z);
        gl.glEnd();

        {
//        gl.glBegin(gl.GL_LINES);
//
////front
//        gl.glVertex3f(x, 0, z);
//        gl.glVertex3f(x, 0, 0);
//
//        gl.glVertex3f(x, 0, 0);
//        gl.glVertex3f(x, y, 0);
//
//        gl.glVertex3f(x, y, 0);
//        gl.glVertex3f(x, y, z);
//
//        gl.glVertex3f(x, y, z);
//        gl.glVertex3f(x, 0, z);
//
////left
//        gl.glVertex3f(x, 0, z);
//        gl.glVertex3f(0, 0, z);
//
//        gl.glVertex3f(0, 0, z);
//        gl.glVertex3f(0, 0, 0);
//
//        gl.glVertex3f(0, 0, 0);
//        gl.glVertex3f(x, 0, 0);
//
////back
//        gl.glVertex3f(0, 0, z);
//        gl.glVertex3f(0, y, z);
//
//        gl.glVertex3f(0, y, z);
//        gl.glVertex3f(0, y, 0);
//
//        gl.glVertex3f(0, y, 0);
//        gl.glVertex3f(0, 0, 0);
//
////left
//        gl.glVertex3f(x, y, z);
//        gl.glVertex3f(0, y, z);
//
//        gl.glVertex3f(0, y, 0);
//        gl.glVertex3f(x, y, 0);
//        gl.glEnd();
        }
        if(nx > 0 && ny > 0 && nz > 0) {
            gl.glEnable(gl.GL_POINT_SMOOTH);
            gl.glEnable(gl.GL_BLEND);
            gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_ONE_MINUS_SRC_ALPHA);
            gl.glPointSize(5);
            Cube cube = new Cube(nx, ny, nz, x, y, z);
            cube.paintPoints();
            gl.glBegin(gl.GL_POINTS);
            for (Point3D p : cube.paintCube) {
                //gl.glVertex3d(p.getX(), p.getY(), p.getZ());
                
            }
            gl.glEnd();
        }

        gl.glPopMatrix();

    }
}