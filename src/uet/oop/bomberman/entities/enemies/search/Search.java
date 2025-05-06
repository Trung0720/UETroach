package uet.oop.bomberman.entities.enemies.search;

import java.util.Stack;

public abstract class Search {
    public static final int ROW = 13;
    public static final int COL = 31;

    public static class Position {
        private int row;
        private int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        @Override
        public int hashCode() {
            return 31 * row + col;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Position other = (Position) obj;
            return row == other.row && col == other.col;
        }

        @Override
        public String toString() {
            return "(" + row + "," + col + ")";
        }
    }

    public static class cell {
        public int parentX, parentY;
        public double f, g, h;

        public cell() {
            this.parentX = -1;
            this.parentY = -1;
            this.f = Double.MAX_VALUE;
            this.g = Double.MAX_VALUE;
            this.h = Double.MAX_VALUE;
        }

        @Override
        public String toString() {
            return "cell{" +
                    "parentX=" + parentX +
                    ", parentY=" + parentY +
                    ", f=" + f +
                    ", g=" + g +
                    ", h=" + h +
                    '}';
        }
    }

    public static boolean isValid(int r, int c) {
        return r >= 0 && r < ROW && c >= 0 && c < COL;
    }

    public static boolean isUnBlocked(char grid[][], int r, int c) {
        return grid[r][c] != '#' && grid[r][c] != '*';
    }

    public static boolean isDestination(Position dest, int r, int c) {
        return r == dest.getRow() && c == dest.getCol();
    }

    public static double calculateDistance(Position dest, int r, int c) {
        return Math.sqrt(Math.pow(dest.getRow() - r, 2) + Math.pow(dest.getCol() - c, 2));
    }

    public static Position tracePath(AStar.cell[][] cellDetails, Position src, Position dest) {
        int r = dest.getRow();
        int c = dest.getCol();

        Stack<Position> path = new Stack<>();
        try {
            while (cellDetails[r][c].parentX != src.getRow() || cellDetails[r][c].parentY != src.getCol()) {
                path.add(new Position(r, c));
                int tmpR = cellDetails[r][c].parentX;
                int tmpC = cellDetails[r][c].parentY;
                r = tmpR;
                c = tmpC;
            }
            path.push(new Position(r, c));
            if (!path.isEmpty()) {
                return path.pop();
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("blocked");
            return src;
        }
        return src;
    }
}
