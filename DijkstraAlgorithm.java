import java.util.Scanner;

public class DijkstraAlgorithm {

    public int minDist(int dist[], boolean shSet[]) {
        int minIndex = -1;
        int min = Integer.MAX_VALUE;
        int v = dist.length;
        for (int i = 0; i < v; i++) {
            if (!shSet[i] && dist[i] < min) {
                minIndex = i;
                min = dist[i];
            }
        }
        return minIndex;
    }

    public void dijk(int mat[][], int src, int v) {
        int dist[] = new int[v];
        boolean shSet[] = new boolean[v];
        // checking each point whether it is infinity if infinity the false
        for (int i = 0; i < v; i++) {
            dist[i] = Integer.MAX_VALUE;
            shSet[i] = false;
        }
        dist[src] = 0;

        for (int count = 0; count < v - 1; count++) {
            int u = minDist(dist, shSet);
            shSet[u] = true;
            for (int i = 0; i < v; i++) {
                if (!shSet[i] && mat[u][i] != Integer.MAX_VALUE && dist[u] != Integer.MAX_VALUE &&
                        dist[i] > dist[u] + mat[u][i]) {
                    dist[i] = dist[u] + mat[u][i];
                }
            }
        }
        System.out.println();
        printMatrix(dist);
    }

    public void printMatrix(int dist[]) {
        int v = dist.length;
        System.out.println("The shortest distances from source to the vertices are  : ");
        for (int i = 0; i < v; i++) {
            if (dist[i] != Integer.MAX_VALUE) {
                System.out.println((i + 1) + " --> " + dist[i]);
            } else {
                System.out.println((i + 1) + " --> " + "INF");
            }
        }
        System.out.println();
    }
}

class TestA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int n = sc.nextInt();
        int mat[][] = new int[n][n];
        // storing initial value 0 in matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = 0;
            }
        }
        System.out.println("Enter -1 as the distance for infinity");
        for (int i = 0; i < n; i++) {
            // asking values to user and storing it
            System.out.print("Enter the distance of vertices from vertex " + (i + 1) + " : ");
            for (int j = 0; j < n; j++) {
                int input = sc.nextInt();
                if (input >= 0)
                    mat[i][j] = input;
                else
                    mat[i][j] = Integer.MAX_VALUE; // for storing infinity

            }
        }
        System.out.print("Enter the source vertex: ");
        int src = sc.nextInt();

        DijkstraAlgorithm d = new DijkstraAlgorithm();
        d.dijk(mat, src - 1, n);// *************** */
        sc.close();
    }
}