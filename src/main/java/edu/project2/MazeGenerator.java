package edu.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;


    public class MazeGenerator implements Generator {
        @Override
        public Maze generate(int height, int width) {
            Cell[][] grid = new Cell[height][width];

            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    grid[row][col] = new Cell(row, col, Cell.Type.WALL);
                }
            }

            Random random = new Random();
            int startRow = 1;
            int startCol = 1;

            grid[startRow][startCol].setType(Cell.Type.PASSAGE);

            Stack<Coordinate> stack = new Stack<>();
            stack.push(new Coordinate(startRow, startCol));

            while (!stack.isEmpty()) {
                Coordinate current = stack.peek();
                int row = current.row();
                int col = current.col();

                List<Coordinate> neighbors = getUnvisitedNeighbors(grid, row, col);

                if (!neighbors.isEmpty()) {
                    Coordinate neighbor = neighbors.get(random.nextInt(neighbors.size()));
                    int nRow = neighbor.row();
                    int nCol = neighbor.col();

                    grid[nRow][nCol].setType(Cell.Type.PASSAGE);
                    int wallRow = (row + nRow) / 2;
                    int wallCol = (col + nCol) / 2;
                    grid[wallRow][wallCol].setType(Cell.Type.PASSAGE);

                    stack.push(neighbor);
                } else {
                    stack.pop();
                }
            }

            // Установим начальную и конечную точки
            grid[1][0].setType(Cell.Type.PASSAGE);
            grid[height - 2][width - 1].setType(Cell.Type.PASSAGE);

            return new Maze(height, width, grid);
        }

        private List<Coordinate> getUnvisitedNeighbors(Cell[][] grid, int row, int col) {
            List<Coordinate> neighbors = new ArrayList<>();
            int height = grid.length;
            int width = grid[0].length;

            int[][] directions = {{2, 0}, {-2, 0}, {0, 2}, {0, -2}};

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow > 0 && newRow < height && newCol > 0 && newCol < width && grid[newRow][newCol].type() == Cell.Type.WALL) {
                    neighbors.add(new Coordinate(newRow, newCol));
                }
            }

            return neighbors;
        }
    }
