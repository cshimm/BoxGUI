import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoxTest {
    @Test
    void test_BoxInitializedProperly(){
        Box box = new Box(1., 2., 3.);
        assertNotNull(box);
        assertEquals(box.getLength(), 1.);
        assertEquals(box.getWidth(), 2.);
        assertEquals(box.getHeight(), 3.);
    }
    @Test
    void test_CalculateVolume() {
        Box box = new Box(2., 2., 2.);
        assertEquals(box.calculateVolume(), "8.0");
    }

    @Test
    void test_CalculateSurfaceArea() {
        Box box = new Box(2., 2., 2.);
        assertEquals(box.calculateSurfaceArea(), "24.0");
    }

    @Test
    void test_DimensionsSetProperly() {
        Box box = new Box(0., 0., 0.);
        box.setLength("5");
        box.setWidth("3");
        box.setHeight("4");

        assertEquals(box.getLength(), 5.);
        assertEquals(box.getWidth(), 3.);
        assertEquals(box.getHeight(), 4.);

    }
}
