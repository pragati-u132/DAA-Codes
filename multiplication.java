import java.util.Scanner;

public class multiplication {
    public String Sum(String s1, String s2) {
        String s = "";

        if (s2.length() > s1.length()) {
            String curr = s1;
            s1 = s2;
            s2 = curr;
        }
        int n1 = s1.length();
        int n2 = s2.length();

        s1 = new StringBuilder(s1).reverse().toString();
        s2 = new StringBuilder(s2).reverse().toString();

        int c = 0;
        for (int i = 0; i < n2; i++) {
            int addVar = (s1.charAt(i) - '0') + (s2.charAt(i) - '0') + c;
            s += (char) (addVar % 10 + '0');
            c = addVar / 10;
        }

        for (int i = n2; i < n1; i++) {
            int addVar = (s1.charAt(i) - '0') + c;
            s += (char) (addVar % 10 + '0');
            c = addVar / 10;
        }

        if (c > 0) {
            s += (char) (c + '0');
        }

        s = new StringBuilder(s).reverse().toString();
        return s;
    }

    public String Subtract(String s1, String s2) {
        String s = "";

        if (s2.length() > s1.length()) {
            String curr = s1;
            s1 = s2;
            s2 = curr;
        }
        int n1 = s1.length();
        int n2 = s2.length();
        int borrow = 0;

        s1 = new StringBuilder(s1).reverse().toString();
        s2 = new StringBuilder(s2).reverse().toString();

        for (int i = 0; i < n2; i++) {
            int sub = (s1.charAt(i) - '0') - (s2.charAt(i) - '0') - borrow;
            if (sub < 0) {
                s += (sub + 10);
                borrow = 1;
            } else {
                s += sub;
                borrow = 0;
            }
        }

        for (int i = n2; i < n1; i++) {
            int sub = (s1.charAt(i) - '0') - borrow;

            if (sub < 0) {
                sub += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            s += (sub);

        }
        s = new StringBuilder(s).reverse().toString();
        return s;

    }

    public String mul(String s1, String s2) {
        if (s1.length() > s2.length()) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }
        int n1 = s1.length(), n2 = s2.length();
        while (n2 > n1) {
            s1 = "0" + s1;
            ++n1;
        }

        if (n1 == 1) {
            int ans = (s1.charAt(0) - '0') * (s2.charAt(0) - '0');
            return Integer.toString(ans);
        }
        if (n1 % 2 == 1) {
            s1 = "0" + s1;
            s2 = "0" + s2;
            ++n1;
        }

        int h = n1 / 2;
        String al = s1.substring(0, h);
        String ar = s1.substring(h);
        String bl = s2.substring(0, h);
        String br = s2.substring(h);

        String a = mul(al, bl);
        String b = mul(ar, br);
        String c = Subtract(mul(Sum(al, ar), Sum(bl, br)), Sum(a, b));

        for (int i = 0; i < n1; i++) {
            a = a + "0";
        }
        for (int i = 0; i < n1 / 2; i++) {
            c = c + "0";
        }
        String res = Sum(a, Sum(c, b));
        return res.replaceFirst("^0+(?!$)", "");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        multiplication m = new multiplication();
        int ch;
        System.out.println("1.Multiplication");
        System.out.println("2.Square of number");
        System.out.println("3.Quit");
        ch = sc.nextInt();
        sc.nextLine();
        switch (ch) {
            case 1:
                System.out.print("Enter a and b: ");
                String a = sc.nextLine();
                String b = sc.nextLine();
                System.out.println("Multiplication: " + m.mul(a, b));
                break;
            case 2:
                System.out.print("Enter a : ");
                String c = sc.nextLine();
                System.out.println("Multiplication: " + m.mul(c, c));
                break;
        }
        sc.close();
    }
}