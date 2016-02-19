import javafx.geometry.Point3D;

/**
 * Created by Mariana on 18.02.2016.
 */
public class Test {
    public static void main(String[] args) {
        float x = 4, y = 4, z = 4;
        float nx = 1, ny = 1, nz = 3;
        x /= 2;
        int count = 0;
        for (float i = x; i >= -x; i -= x * 2 / nx) {
            for (float j = y; j >= -y; j -= y * 2 / ny)
                for (float k = z; k >= -z; k -= z * 2 / nz) {
                    count++;
                }
        }
        System.out.println(count);
    }
}
