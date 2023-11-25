package edu.project2;

import java.util.List;

public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Cell[][] getGrid() {
        return grid;
    }

    public Cell getGrid(int row, int col) {
        if (isWithinBounds(row, col)) {
            return grid[row][col];
        } else return null;
    }

    private boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Maze(int height, int width, Cell[][] grid) {
        this.height = height;
        this.width = width;
        this.grid = grid;
    }
    public Maze(){
        height = 20;
        width = 20;
        grid = new Cell[height][width];
    }


    public void start() {
        MazeGenerator mazeGenerator = new MazeGenerator();
        Maze maze = mazeGenerator.generate(height, width);

        MazeSolver mazeSolver = new MazeSolver();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(height - 1, width - 1);
        List<Coordinate> path = mazeSolver.solve(maze, start, end);

        MazeRenderer mazeRenderer = new MazeRenderer();
        String render = mazeRenderer.render(maze);
        String renderSolution = mazeRenderer.render(maze, path);

        System.out.println("Maze:");
        System.out.println(render);

        System.out.println();

        System.out.println("Solution:");
        System.out.println(renderSolution);

    }

}
