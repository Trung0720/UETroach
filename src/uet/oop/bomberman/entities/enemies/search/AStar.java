package uet.oop.bomberman.entities.enemies.search;

import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar extends Search {

    private static boolean pathProcessor(
            int pi, int pj,
            int i, int j,
            Position dest,
//            double fx, double gx, double hx,
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
            double gx = cellDetails[pi][pj].g + 1.0;
            double hx = calculateDistance(dest, i, j);
            double fx = gx + hx;

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
        if (!isValid(src.getRow(), src.getCol())) {
            return src;
        }
        if (!isValid(dest.getRow(), dest.getCol())) {
            return src;
        }
        if (!isUnBlocked(grid, src.getRow(), src.getCol()) || !isUnBlocked(grid, dest.getRow(), dest.getCol())) {
            return src;
        }
        if (isDestination(dest, src.getRow(), src.getCol())) {
            return src;
        }

        boolean[][] closedList = new boolean[ROW][COL];
        cell[][] cellDetails = new cell[ROW][COL];

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                cellDetails[i][j] = new cell();
            }
        }

        int i = src.getRow(), j = src.getCol();
        cellDetails[i][j].f = 0.0;
        cellDetails[i][j].g = 0.0;
        cellDetails[i][j].h = 0.0;
        cellDetails[i][j].parentX = i;
        cellDetails[i][j].parentY = j;

        PriorityQueue<Position> openList = new PriorityQueue<>(
                Comparator.comparingDouble(p -> cellDetails[p.getRow()][p.getCol()].f)
        );
        openList.add(src);

        while (!openList.isEmpty()) {
            Position p = openList.poll();
            i = p.getRow();
            j = p.getCol();
            closedList[i][j] = true;

            if (pathProcessor(i, j, i - 1, j, dest, cellDetails, closedList, grid, openList)) {
                break;
            }
            if (pathProcessor(i, j, i + 1, j, dest, cellDetails, closedList, grid, openList)) {
                break;
            }
            if (pathProcessor(i, j, i, j - 1, dest, cellDetails, closedList, grid, openList)) {
                break;
            }
            if (pathProcessor(i, j, i, j + 1, dest, cellDetails, closedList, grid, openList)) {
                break;
            }
        }

        return tracePath(cellDetails, src, dest);
    }
}