import java.util.*;

class Job {
    char id;
    int dead;
    int profit;

    public Job(char id, int dead, int profit) {
        this.id = id;
        this.dead = dead;
        this.profit = profit;
    }
}

public class JobSequencing {
    public void printJobSeq(Job arr[], int n) {
        Arrays.sort(arr, new Comparator<Job>() {
            public int compare(Job a, Job b) {
                return Integer.compare(b.profit, a.profit);
            }
        });
        int result[] = new int[n];
        boolean slot[] = new boolean[n];
        int totalProfit = 0;
        for (int i = 0; i < n; i++) {
            slot[i] = false;
        }

        for (int i = 0; i < n; i++) {
            for (int j = Math.min(n, arr[i].dead) - 1; j >= 0; --j) {
                if (slot[j] == false) {
                    result[j] = i;
                    slot[j] = true;
                    totalProfit += arr[i].profit;
                    break;
                }
            }
        }
        System.out.println("The jobs added are : ");
        for (int i = 0; i < n; i++) {
            if (slot[i])
                System.out.print(arr[result[i]].id + " ");
        }
        System.out.println();
        System.out.println("Maximum profit: " + totalProfit);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("No. of jobs: ");
        int n = sc.nextInt();
        Job arr[] = new Job[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter job id,deadline and profit fro each job resp. :  ");
            String input = sc.next();
            char id = input.charAt(0);
            int dead = sc.nextInt();
            int pro = sc.nextInt();
            arr[i] = new Job(id, dead, pro);
        }
        JobSequencing j = new JobSequencing();
        j.printJobSeq(arr, n);
        sc.close();
    }
}