/**
 * Box class
 *
 * @author cameron
 * <p>
 * Simple box class length, width and height fields
 * with a setter and getter for each field.
 * </p>
 */
public class Box {

    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    void setLength(String length) {
        this.length = Double.parseDouble(length);
    }

    void setWidth(String width) {
        this.width = Double.parseDouble(width);
    }

    void setHeight(String height) {
        this.height = Double.parseDouble(height);
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    /**
     * Calculate the volume when provided with a box object
     *
     * @return The calculated volume as a String.
     */
    String calculateVolume() {
        return Double.toString(length * width * height);
    }

    /**
     * Calculate the surface area when provided with a box object
     *
     * @return The calculated surface area as a String.
     */
    String calculateSurfaceArea() {
        return Double.toString((2 * length * width) + (2 * length * height) + (2 * width * height));
    }
}
