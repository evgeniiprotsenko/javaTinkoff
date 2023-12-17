package edu.hw10.Task1;

public class MyClass {

    private final String name;
    private final int id;

    public MyClass(@NotNull String name, @Min(5) @Max(10) int id) {

        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
