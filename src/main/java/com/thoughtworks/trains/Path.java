
package com.thoughtworks.trains;

/**
 * Created by sijieliu on 3/31/18.
 */
public class Path {
    private final int length;
    private final String dest;

    public Path(int length, String dest) {
        this.length = length;
        this.dest = dest;
    }

    public int getLength() {
        return length;
    }

    public String getDest() {
        return dest;
    }
}
