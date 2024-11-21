import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ClubAssignment {

    static class Node implements Comparable<Node> {
        int[] studentAssignedToClub;
        boolean[] clubAssigned;
        int cost;
        int level;

        public Node(int[] studentAssignedToClub, boolean[] clubAssigned, int cost, int level) {
            this.studentAssignedToClub = Arrays.copyOf(studentAssignedToClub, studentAssignedToClub.length);
            this.clubAssigned = Arrays.copyOf(clubAssigned, clubAssigned.length);
            this.cost = cost;
            this.level = level;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    private static int solveClubAssignment(int[][] costMatrix) {
        int n = costMatrix.length;
        int[] initialAssignment = new int[n];
        boolean[] clubAssigned = new boolean[n];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Node root = new Node(initialAssignment, clubAssigned, 0, 0);
        pq.add(root);
        int minCost = Integer.MAX_VALUE;

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            if (currentNode.level == n) {
                minCost = Math.min(minCost, currentNode.cost);
            } else {
                for (int club = 0; club < n; club++) {
                    if (!currentNode.clubAssigned[club]) {
                        int[] newAssignment = Arrays.copyOf(currentNode.studentAssignedToClub, n);
                        boolean[] newClubAssigned = Arrays.copyOf(currentNode.clubAssigned, n);
                        newAssignment[currentNode.level] = club;
                        newClubAssigned[club] = true;
                        int newCost = currentNode.cost + costMatrix[currentNode.level][club];
                        Node newNode = new Node(newAssignment, newClubAssigned, newCost, currentNode.level + 1);
                        pq.add(newNode);
                    }
                }
            }
        }

        return minCost;
    }

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of students/clubs: ");
        int n = sc.nextInt();
        int[][] costMatrix = new int[n][n];

        System.out.println("Enter the cost matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                costMatrix[i][j] = sc.nextInt();
            }
        }

        int minCost = solveClubAssignment(costMatrix);
        System.out.println("Minimum cost of assignment: " + minCost);
    }
}
