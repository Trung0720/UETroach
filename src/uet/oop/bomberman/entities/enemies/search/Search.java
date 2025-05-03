package uet.oop.bomberman.entities.enemies.search;

import java.util.Stack;

public abstract class Search {
    public static final int ROW = 13;
    public static final int COL = 31;

    public static class Position {
        private int r;
        private int c;

        public Position(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public int getR() {
            return r;
        }

        public int getC() {
            return c;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Position)) return false;
            Position p = (Position) obj;
            return r == p.r && c == p.c;
        }

        @Override
        public String toString() {
            return "(" + r + "," + c + ")";
        }
    }

    public static class cell {
        public int parentX = -1, parentY = -1;
        public double f, g, h;

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
        return r == dest.getR() && c == dest.getC();
    }

    public static double calculateDistance(Position dest, int r, int c) {
        return Math.sqrt(Math.pow(dest.getR() - r, 2) + Math.pow(dest.getC() - c, 2));
    }

    public static Position tracePath(AStar.cell[][] cellDetails, Position src, Position dest) {
        int r = dest.getR();
        int c = dest.getC();

        Stack<Position> path = new Stack<>();
        try {
            while (cellDetails[r][c].parentX != src.getR() || cellDetails[r][c].parentY != src.getC()) {
                path.push(new Position(r, c));
                int tmpR = cellDetails[r][c].parentX;
                int tmpC = cellDetails[r][c].parentY;
                r = tmpR;
                c = tmpC;
            }
//            path.push(new Position(r, c));
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