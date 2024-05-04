package Right_Triangles_3128;

import java.util.ArrayList;
import java.util.List;

public class RightTriangles{

    public static void main(String[] args) {

        int [][] grid = new int[][]{
                {0,1,0},
                {0,1,1},
                {0,1,0}
        };

        long result = numberOfRightTriangles(grid);

        System.out.println(result);
    }

    public static long result = 0L;
    public static List<ResultSet> rowComb = new ArrayList<>();
    public static List<ResultSet> colComb = new ArrayList<>();

    static class Point {
        int row;
        int col;

        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public boolean equals(Point other) {
            if (this.row == other.row && this.col == other.col)
                return true;
            return false;
        }
    }

    static class ResultSet {
        Point left;
        Point right;

        ResultSet(Point left, Point right) {
            this.left = left;
            this.right = right;
        }

        public boolean isOverLap(ResultSet other) {
            // 하나라도 겹치면 OK
            if (left.equals(other.left)) {
                return true;
            }

            if (left.equals(other.right)) {
                return true;
            }

            if (right.equals(other.right)) {
                return true;
            }

            if (right.equals(other.left)) {
                return true;
            }

            return false;
        }
    }


    /**
     * Idea : Row 별로 1 1 이 되는 쌍을 전부 저장
     * Col 별로 1 1 (세로) 가 되는 쌍을 전부 저장
     * 돌면서 겹치는 Point 가 있는 케이스들을 세면 된다.
     * @param grid
     * @return
     */
    public static long numberOfRightTriangles(int[][] grid) {

        for (int i=0;i<grid.length;i++) {
            // Row 0 부터 끝까지
            for (int j=0;j<grid[0].length-1;j++) {
                // 오른쪽만 바라보고 만들면 되므로, 가장 오른쪽은 X
                if (grid[i][j] == 1) {
                    for (int k=j+1;k<grid[0].length;k++) {
                        // j가 1이면, 그 다음부터 검사
                        if (grid[i][k] == 1) {
                            rowComb.add(new ResultSet(new Point(i,j), new Point(i,k)));
                        }
                    }
                }
            }
        }

        for (int i=0;i<grid[0].length;i++) {
            // Col 0 부터 끝까지
            for (int j=0;j<grid.length-1;j++) {
                // 아래만 바라보고 만들면 되므로, 가장 아래는 고려 X
                if (grid[j][i] == 1) { // 위랑 반대로 idx 넣어줘야한다.
                    for (int k=j+1;k<grid.length;k++) {
                        // j가 1이면, 그 다음부터 검사
                        if (grid[k][i] == 1) {
                            colComb.add(new ResultSet(new Point(j,i), new Point(k,i)));
                        }
                    }
                }
            }
        }

        for (int i=0;i<rowComb.size();i++) {
            for (int j=0;j<colComb.size();j++) {
                if (rowComb.get(i).isOverLap(colComb.get(j))) {
                    result++;
                }
            }
        }

        return result;
    }
}
