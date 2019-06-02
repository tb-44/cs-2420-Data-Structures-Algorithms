
/******************************************************************
 * 
 * Implementation of a vector of real numbers.
 * 
 * This is a modified version of Sedgewick's class Vector
 * http://introcs.cs.princeton.edu/java/33design/Vector.java.html
 * 
 * The class is implemented to be immutable. Immutability is a very desirable
 * feature of a data type. Once the client program initializes a Vector, it can
 * no longer change any of its fields neither directly nor indirectly.
 * 
 ****************************************************************/

public class Vector {

    final int length; // length of the vector
    private double[] data; // components of the vector

    // create the zero vector of length N
    public Vector(int N) {
        this.length = N;
        this.data = new double[N];
    }

    /**
     * Creates a vector based on the data provided It creates a defensive copy so
     * that the clien't won't be able to alter the components of the vector
     * 
     * @param data an array that includes the components of the vector
     */
    public Vector(double[] data) {
        length = data.length;

        // defensive copy so that client can't alter the data
        this.data = new double[length];
        for (int i = 0; i < length; i++)
            this.data[i] = data[i];
    }

    /**
     * Returns the length of the vector.
     * 
     * @return length of the vector
     */
    public int length() {
        return length;
    }

    /**
     * Multiplies this vector by another vector Returns the inner product dot
     * product of this vector and the other vector.
     * 
     * @param other The other vector to calculate the inner product
     * @return inner product (dot product)
     */
    public double dot(Vector other) {
        if (this.length != other.length)
            throw new RuntimeException("Dimensions don't agree");

        double sum = 0.0;
        for (int i = 0; i < length; i++)
            sum = sum + (this.data[i] * other.data[i]);
        return sum;
    }

    /**
     * Returns the magnitude (Eucliden norm) of a vector
     * 
     * @return magnitude of the vector
     */
    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }

    /**
     * Returns the component (coordinate) at index i
     * 
     * @param i index
     * @return corresponding coordinate (component)
     */
    public double componentAt(int i) {
        return data[i];
    }

    /**
     * Adds two vectors Returns the sum of this vector and the other vector
     * 
     * @param other vector to add to this vector
     * @return this + other
     */
    public Vector plus(Vector other) {
        if (this.length != other.length)
            throw new RuntimeException("Dimensions don't agree");

        Vector c = new Vector(length);
        for (int i = 0; i < length; i++)
            c.data[i] = this.data[i] + other.data[i];
        return c;
    }

    /**
     * Subtracts two vectors Returns the difference between this vector and the
     * other vector
     * 
     * @param other vector to add to this vector
     * @return this - other
     */
    public Vector minus(Vector other) {
        if (this.length != other.length)
            throw new RuntimeException("Dimensions don't agree");

        Vector c = new Vector(length);
        for (int i = 0; i < length; i++)
            c.data[i] = this.data[i] - other.data[i];
        return c;
    }

    /**
     * Multiplies this vector by a scalar Creates and returns a new object that is
     * the product of this vector and the factor
     * 
     * @param factor number to multiply the vector with
     * @return create and return a new object whose value is (this * factor)
     */
    public Vector times(double factor) {
        Vector c = new Vector(length);
        for (int i = 0; i < length; i++)
            c.data[i] = factor * data[i];
        return c;
    }

    /**
     * Returns the Euclidean distance between this and the other vector.
     * 
     * @param other the other vector
     * @return Euclidean distance
     */
    public double distanceTo(Vector other) {
        if (this.length != other.length)
            throw new RuntimeException("Dimensions don't agree");

        return this.minus(other).magnitude();
    }

    /**
     * String that lists all components of the vector
     * 
     * @return a string representation of the vector
     */
    public String toString() {
        String s = "(";
        for (int i = 0; i < length; i++) {
            s += data[i];
            if (i < length - 1)
                s += ", ";
        }
        return s + ")";
    }

    // test client
    public static void main(String[] args) {
        double[] data1 = { 1.0, 2.0, 3.0, 4.0 };
        double[] data2 = { 5.0, 2.0, 4.0, 1.0 };

        Vector v1 = new Vector(data1);
        Vector v2 = new Vector(data2);
        System.out.println("v1 =  " + v1);
        System.out.println("v2 =  " + v2);
        System.out.println();
        System.out.println("v1.componentAt(0)  =  " + v1.componentAt(0));
        System.out.println("v1.componentAt(2)  =  " + v1.componentAt(2));
        System.out.println("v1.plus(v2)        =  " + v1.plus(v2));
        System.out.println("v1.minus(v2)       =  " + v1.minus(v2));
        System.out.println("v1.times(10)       =  " + v1.times(10.0));
        System.out.println("v1.distanceTo(v2)  =  " + v1.distanceTo(v2));
        System.out.println("v2.distanceTo(v1)  =  " + v2.distanceTo(v1));

    }
}
