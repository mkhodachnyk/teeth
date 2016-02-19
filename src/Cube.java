import javafx.geometry.Point3D;
import java.util.ArrayList;

public class Cube {
    int nx, ny, nz;
    float x, y, z;
    public ArrayList<Point3D> global = new ArrayList<>();
    public ArrayList<Point3D> local = new ArrayList<>();
    public ArrayList<Point3D> paintCube = new ArrayList<>();

    public Cube(int nx, int ny, int nz, float x, float y, float z) {
        this.nx = nx;
        this.ny = ny;
        this.nz = nz;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void paintPoints() {
        for (float i = x; i >= -x; i -= x * 2 / nx) {
            for (float j = y; j >= -y; j -= y * 2 / ny)
                for (float k = z; k >= -z; k -= z * 2 / nz) {
                    paintCube.add(new Point3D(i, j, k));
                }
        }
    }
}
