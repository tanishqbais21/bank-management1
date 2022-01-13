import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
public class main extends query
{
    public static void main(String[] args) throws Exception
    {
        int ch = 0,ch0;
        Random ran= new Random();
        Scanner s = new Scanner(System.in);
        query q=new query();
        do {


            System.out.println("*******BANK MANAGEMENT SYSTEM******");
            System.out.println("-----------------------------------");
            System.out.println("1.CREATE NEW ACCOUNT");
            System.out.println("2.LOGIN");
            System.out.println("3.EXIT");
            System.out.println("-----------------------------------");
            System.out.println("ENTER YOUR CHOICE");
            ch = s.nextInt();
            if (ch == 1) {
                String nam, add, Email, last;
                long ad_no, mo_no;
                System.out.print("ENTER YOUR FIRST NAME:-");
                nam = s.next();
                System.out.print("ENTER LAST NAME:-");
                last = s.next();
                System.out.print("ENTER ADDRESS:-");
                add = s.next();
                System.out.print("ENTER EMAIL:-");
                Email = s.next();
                System.out.print("ENTER AADHAAR NUMBER:-");
                ad_no = s.nextLong();
                System.out.print("ENTER MOBILE NUMBER:-");
                mo_no = s.nextLong();
                q.q2(nam, last, add, Email, ad_no, mo_no);


            } else if (ch == 2) {
                int acc_no;
                String pas, nam;
                System.out.println("********BANK LOGIN MENU*********");
                System.out.print("ENTER YOUR ACCOUNT NUMBER:-");
                acc_no = s.nextInt();
                System.out.print("ENTER YOUR 8 DIGIT PIN:-");
                pas = s.next();
                nam = q3(acc_no, pas);
                int cho;
                System.out.println("WELCOME " + nam);
                System.out.println("1.GET ACCOUNT INFO");
                System.out.println("2.CHECK BALANCE");
                System.out.println("3.TRANSFER FUNDS");
                System.out.println("4.ADD MONEY INTO ACCOUNT");
                System.out.println("5.DEBIT FROM ACCOUNT");
                System.out.println("6.GET ACCOUNT TRANSACTION HISTORY");
                System.out.println("7.EXIT");
                System.out.println("ENTER YOUR CHOICE");
                cho = s.nextInt();
                switch (cho) {
                    case 1:
                        q4(acc_no, pas);
                        break;
                    case 2:
                        q5(acc_no, pas);
                        break;
                    case 3:
                        q6(acc_no, pas);
                        break;
                    case 4:
                        System.out.println("CREDIT MENU");
                        System.out.println("---------------------------------");
                        long money = q5(acc_no, pas);
                        System.out.println("---------------------------------");
                        System.out.print("ENTER THE AMOUNT TO BE ADDED:- ");
                        long add = s.nextLong();
                        long fina = money + add;
                        q7(acc_no, pas, fina);
                        break;
                    case 5:
                        System.out.println("DEBIT MENU");
                        System.out.println("---------------------------------");
                        money = q5(acc_no, pas);
                        System.out.println("---------------------------------");
                        System.out.print("ENTER THE AMOUNT TO BE DEBITED:- ");
                        add = s.nextLong();
                        fina = money - add;
                        q7(acc_no, pas, fina);
                        break;

                    case 6:
                        System.out.println("HISTORY");
                        break;
                    case 7:
                        System.exit(0);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + cho);
                }

            }
            if (ch == 3) {
                System.exit(0);
            }
            System.out.println("");
            System.out.println("----------------------------");
            System.out.println("DO YOU WANT TO CONTINUE?");
            System.out.println("1.YES");
            System.out.println("2.NO");
            System.out.println("----------------------------");
            ch0 = s.nextInt();
        }
            while (ch0 == 1) ;

    }
}
