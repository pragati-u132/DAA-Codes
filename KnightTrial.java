import java.util.Scanner;

public class KnightTrial {
    public void printMat(int mat[][]) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++)
                System.out.print(mat[i][j] + " ");
            System.out.println();
        }
    }

    public void ktour(int x, int y, int n) {
        int ans[][] = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                ans[i][j] = -1;

        int xdir[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int ydir[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
        ans[x][y] = 0;
        if (!kTourHelp(x, y, 1, ans, xdir, ydir))
            System.out.println("No");
        else
            printMat(ans);
    }

    public boolean kTourHelp(int x, int y, int move, int ans[][], int xdir[], int ydir[]) {
        int n = ans.length;
        int k, nextX, nextY;
        if (move == (n * n))
            return true;

        for (k = 0; k < 8; k++) {
            nextX = x + xdir[k];
            nextY = y + ydir[k];
            if (isSafe(nextX, nextY, ans)) {
                ans[nextX][nextY] = move;
                if (kTourHelp(nextX, nextY, move + 1, ans, xdir, ydir))
                    return true;
                else
                    ans[nextX][nextY] = -1;
            }
        }
        return false;
    }

    public boolean isSafe(int x, int y, int ans[][]) {
        int n = ans.length;
        return (x >= 0 && x < n && y >= 0 && y < n && ans[x][y] == -1);
    }
}

class testKnight {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of chessboard : ");
        int n = sc.nextInt();
        KnightTrial kn = new KnightTrial();
        System.out.print("Enter the starting position of knight: ");
        int x = sc.nextInt();
        int y = sc.nextInt();
        System.out.println();
        System.out.println("The tour of knight across the chessboard : ");
        kn.ktour(x, y, n);
        sc.close();
    }
}
