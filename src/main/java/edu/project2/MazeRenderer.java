package edu.project2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MazeRenderer implements Renderer{
    @Override
    public String render(Maze maze) {
        StringBuilder result = new StringBuilder();

        for (int row = 0; row < maze.getHeight(); row++) {
            for (int col = 0; col < maze.getWidth(); col++) {
                Cell cell = maze.getGrid()[row][col];
                if (cell.type() == Cell.Type.WALL) {
                    result.append("W"); // Используем символ "#" для стены
                } else {
                    result.append("P"); // Используем пробел для прохода
                }
            }
            result.append('\n');
        }

        return result.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder result = new StringBuilder();

        // Создаем множество для быстрого поиска координат пути
        Set<Coordinate> pathCoordinates = new HashSet<>(path);

        for (int row = 0; row < maze.getHeight(); row++) {
            for (int col = 0; col < maze.getWidth(); col++) {
                Coordinate currentCoordinate = new Coordinate(row, col);
                if (pathCoordinates.contains(currentCoordinate)) {
                    result.append("*"); // Используем "*" для обозначения пути
                } else {
                    Cell cell = maze.getGrid()[row][col];
                    if (cell.type() == Cell.Type.WALL) {
                        result.append("W"); // Используем символ "#" для стены
                    } else {
                        result.append("P"); // Используем пробел для прохода
                    }
                }
            }
            result.append('\n');
        }

        return result.toString();
    }
}
