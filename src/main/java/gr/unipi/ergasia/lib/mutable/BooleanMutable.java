package gr.unipi.ergasia.lib.mutable;

/**
 *
 * @author siggouroglou@gmail.com
 */
public class BooleanMutable implements Comparable<BooleanMutable> {

    private boolean value;

    public BooleanMutable(boolean value) {
        this.value = value;
    }

    public void set(boolean value) {
        this.value = value;
    }

    public boolean booleanValue() {
        return value;
    }

    @Override
    public int compareTo(BooleanMutable o) {
        return compare(this.value, o.value);
    }

    public static int compare(boolean x, boolean y) {
        return (x == y) ? 0 : (x ? 1 : -1);
    }
}
