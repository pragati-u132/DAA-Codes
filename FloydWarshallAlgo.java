import java.util.Scanner;

public class FloydWarshallAlgo {
    public void warshall(int mat[][]) {
        int n = mat.length;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][k] != Integer.MAX_VALUE && mat[k][j] != Integer.MAX_VALUE)
                        mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                }
            }
        }
        printmatrix(mat);
    }

    public void printmatrix(int mat[][]) {
        int n = mat.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == Integer.MAX_VALUE)
                    System.out.print("INF" + "");
                else
                    System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int n = sc.nextInt();
        int mat[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print("Enter the distance of vertices from vertex " + (i + 1) + " : ");
            for (int j = 0; j < n; j++) {
                int input = sc.nextInt();
                if (input >= 0)
                    mat[i][j] = input;
                else
                    mat[i][j] = Integer.MAX_VALUE;

            }
        }
        FloydWarshallAlgo f = new FloydWarshallAlgo();
        System.out.println("The given matrix is : ");
        f.printmatrix(mat);
        System.out.println("The matrix after applying Floyd Warshall Algorithm: ");
        f.warshall(mat);

        sc.close();
    }

}