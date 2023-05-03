package data;

import java.io.Serializable;

public enum ColorEye implements Serializable {
    GREEN("GREEN"),
    YELLOW("YELLOW"),
    BROWN("BROWN"),
    DEFAULT_COLOR("default color");
    private final String color;

    ColorEye(String color) {
        this.color = color;
    }

    public static String getList() {
        StringBuilder types = new StringBuilder();
        for (ColorEye type : values()) {
            if(!type.equals(DEFAULT_COLOR)){
            types.append(type.name()+" - "+type.ordinal()).append("; ");}
        }
        return types.substring(0, types.length() - 2);
    }
}
