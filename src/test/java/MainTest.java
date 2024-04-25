import org.example.Main;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    public void obtenerCapacidadTest() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("100\n".getBytes());
        System.setIn(in);
        assertEquals(100, Main.obtenerCapacidad());
        System.setIn(sysInBackup);
    }

    @Test
    public void obtenerPaquetesTest() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("3\n50\n60\n70\n".getBytes());
        System.setIn(in);
        float[] expected = {50, 60, 70};
        assertArrayEquals(expected, Main.obtenerPaquetes());
        System.setIn(sysInBackup);
    }

    @Test
    public void ordenacionTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        float[] paquetes = {50, 30, 70};
        Main.ordenacion(paquetes);
        assertEquals("[30.0, 50.0, 70.0]", Arrays.toString(paquetes));
        System.setOut(System.out); // restore System.out
    }

    @Test
    public void factibilidadTest() {
        assertTrue(Main.factibilidad(80, 20, 100));
        assertFalse(Main.factibilidad(90, 20, 80));
    }

    @Test
    public void solucionTest() {
        assertFalse(Main.solucion(80, 100));
        assertTrue(Main.solucion(100, 100));
    }
}