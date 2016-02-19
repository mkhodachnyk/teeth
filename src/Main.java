import javax.media.opengl.*;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.glu.GLU;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Main {
    static   float x, y, z;
    static int nx, ny, nz;
    private static float scale = 1;
    static  double rotate_y = 0;
    static double rotate_x = 0;
    public static void main( String [] args ) {


        GLProfile glprofile = GLProfile.getDefault();
        GLCapabilities glcapabilities = new GLCapabilities( glprofile );
        GLJPanel gljpanel = new GLJPanel( glcapabilities );



        JPanel menu = new JPanel();
        BorderLayout borderLayout = new BorderLayout();

        menu.setLayout(new FlowLayout());


        JLabel xLabel = new JLabel("X size");
        JTextField xTextField = new JTextField(10);
        JLabel yLabel = new JLabel("Y size");
        JTextField yTextField = new JTextField(10);
        JLabel zLabel = new JLabel("Z size");
        JTextField zTextField = new JTextField(10);


        JLabel nxLabel = new JLabel("NX     ");
        JTextField nxTextField = new JTextField(10);
        JLabel nyLabel = new JLabel("NY     ");
        JTextField nyTextField = new JTextField(10);
        JLabel nzLabel = new JLabel("NZ     ");
        JTextField nzTextField = new JTextField(10);

        JButton meshButtom = new JButton("Mesh");


        menu.add(xLabel);
        menu.add(xTextField);
        menu.add(yLabel);
        menu.add(yTextField);
        menu.add(zLabel);
        menu.add(zTextField);

        menu.add(nxLabel);
        menu.add(nxTextField);
        menu.add(nyLabel);
        menu.add(nyTextField);
        menu.add(nzLabel);
        menu.add(nzTextField);
        menu.add(meshButtom);

        gljpanel.setLayout(borderLayout);


        gljpanel.setPreferredSize(new Dimension(720, 700));
        menu.setPreferredSize(new Dimension(90, 700));

        gljpanel.addGLEventListener(new GLEventListener() {

            @Override
            public void reshape(GLAutoDrawable glautodrawable, int x, int y, int width, int height) {
                PaintCube.setup(glautodrawable.getGL().getGL2(), width, height);
            }

            @Override
            public void init(GLAutoDrawable glautodrawable) {
                GL2 gl = glautodrawable.getGL().getGL2();      // get the OpenGL graphics context
                GLU glu = new GLU();                         // get GL Utilities
                gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
                gl.glClearDepth(1.0f);      // set clear depth value to farthest
                gl.glEnable(gl.GL_DEPTH_TEST); // enables depth testing
                gl.glDepthFunc(gl.GL_LEQUAL);  // the type of depth test to do
                gl.glHint(gl.GL_PERSPECTIVE_CORRECTION_HINT, gl.GL_NICEST); // best perspective correction
                gl.glShadeModel(gl.GL_SMOOTH); // blends colors nicely, and smoothes out lighting
            }

            @Override
            public void dispose(GLAutoDrawable glautodrawable) {
            }

            @Override
            public void display(GLAutoDrawable glautodrawable) {
                PaintCube.render(glautodrawable.getGL().getGL2(), glautodrawable.getWidth(), glautodrawable.getHeight(), x, y, z, nx, ny, nz, scale, rotate_x, rotate_y);
            }
        });

        final JFrame jframe = new JFrame( "Cube" );
        jframe.setResizable(false);
        jframe.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowevent) {
                jframe.dispose();
                System.exit(0);
            }
        });
        meshButtom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sX = xTextField.getText();
                String sY = zTextField.getText();
                String sZ = yTextField.getText();
                String snx = nxTextField.getText();
                String sny = nyTextField.getText();
                String snz = nzTextField.getText();

                x = Float.parseFloat(sX);
                y = Float.parseFloat(sY);
                z = Float.parseFloat(sZ);
                nx = Integer.parseInt(snx);
                ny = Integer.parseInt(sny);
                nz = Integer.parseInt(snz);

                //System.out.println(x + " " + y + " " + z + " " + nx + " " + ny + " " + nz);

                jframe.getContentPane().add(gljpanel, BorderLayout.EAST);
                jframe.revalidate();
                jframe.repaint();


            }
        });


        gljpanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                rotate_x = e.getXOnScreen();
                rotate_y = e.getYOnScreen();
                gljpanel.revalidate();
                gljpanel.repaint();
            }
        });
        gljpanel.addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
                double delta = 0.05f * e.getPreciseWheelRotation();
                scale += delta;
                gljpanel.revalidate();
                gljpanel.repaint();
            }
        });
        jframe.getContentPane().add(gljpanel, borderLayout.EAST);
        jframe.getContentPane().add(menu);
        jframe.setSize( 900, 700 );
        jframe.setVisible( true );
    }
}

