package uet.oop.bomberman.entities.enemies.search;

import java.util.PriorityQueue;

public class AStar extends Search {

    private static boolean pathProcessor(
            int pi, int pj,
            int i, int j,
            Position dest,
            double fx, double gx, double hx,
            cell[][] cellDetails,
            boolean[][] closedList,
            char[][] grid,
            PriorityQueue<Position> openList
    ) {
        if (!isValid(i, j)) {
            return false;
        }
        if (isDestination(dest, i, j)) {
            cellDetails[i][j].parentX = pi;
            cellDetails[i][j].parentY = pj;
            return true;
        }

        if (!closedList[i][j] && isUnBlocked(grid, i, j)) {
            if (pi != i && pj != j) {
                gx = cellDetails[pi][pj].g + Math.sqrt(2);
            } else {
                gx = cellDetails[pi][pj].g + 1.0;
            }
            hx = calculateDistance(dest, i, j);
            fx = gx + hx;

            if (cellDetails[i][j].f == Double.MAX_VALUE || cellDetails[i][j].f > fx) {
                cellDetails[i][j].f = fx;
                cellDetails[i][j].g = gx;
                cellDetails[i][j].h = hx;
                cellDetails[i][j].parentX = pi;
                cellDetails[i][j].parentY = pj;
                openList.add(new Position(i, j));
            }
        }
        return false;
    }

    public static Position aStarSearch(char[][] grid, Position src, Position dest) {
        if (!isValid(src.getR(), src.getC()) || !isValid(dest.getR(), dest.getC())) {
            return src;
        }
        if (!isUnBlocked(grid, src.getR(), src.getC()) || !isUnBlocked(grid, dest.getR(), dest.getC())) {
            return src;
        }
        if (isDestination(dest, src.getR(), src.getC())) {
            return src;
        }

        boolean[][] closedList = new boolean[ROW][COL];
        cell[][] cellDetails = new cell[ROW][COL];

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                cellDetails[i][j] = new cell();
                cellDetails[i][j].f = Double.MAX_VALUE;
                cellDetails[i][j].g = Double.MAX_VALUE;
                cellDetails[i][j].h = Double.MAX_VALUE;
                cellDetails[i][j].parentX = -1;
                cellDetails[i][j].parentY = -1;
            }
        }

        int i = src.getR(), j = src.getC();
        cellDetails[i][j].f = 0.0;
        cellDetails[i][j].g = 0.0;
        cellDetails[i][j].h = 0.0;
        cellDetails[i][j].parentX = i;
        cellDetails[i][j].parentY = j;

        PriorityQueue<Position> openList = new PriorityQueue<>((a, b) -> {
            double fa = cellDetails[a.getR()][a.getC()].f;
            double fb = cellDetails[b.getR()][b.getC()].f;
            return Double.compare(fa, fb);
        });
        openList.add(src);

        boolean foundDest = false;
        while (!openList.isEmpty()) {
            Position p = openList.poll();
            i = p.getR();
            j = p.getC();
            closedList[i][j] = true;

            double fx = 0, gx = 0, hx = 0;
            if (pathProcessor(i, j, i - 1, j, dest, fx, gx, hx, cellDetails, closedList, grid, openList)) {
                foundDest = true;
                break;
            }
            if (pathProcessor(i, j, i + 1, j, dest, fx, gx, hx, cellDetails, closedList, grid, openList)) {
                foundDest = true;
                break;
            }
            if (pathProcessor(i, j, i, j - 1, dest, fx, gx, hx, cellDetails, closedList, grid, openList)) {
                foundDest = true;
                break;
            }
            if (pathProcessor(i, j, i, j + 1, dest, fx, gx, hx, cellDetails, closedList, grid, openList)) {
                foundDest = true;
                break;
            }
        }

        return tracePath(cellDetails, src, dest);
    }
}