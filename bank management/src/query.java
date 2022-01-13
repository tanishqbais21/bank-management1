import javax.lang.model.element.Name;
import java.sql.*;
import java.util.Scanner;

public class query
{

    public static void q1( )throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver"); //load mysql driver
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");//get connection
        Statement s = con.createStatement();//create object of statement class
        ResultSet res = s.executeQuery("select * from  info where `Account Number`=1");//query
        while (res.next()) {
            System.out.println("Name "+res.getString("Name")+" Account NUMBER "+res.getInt("Account Number"));//traverse
        }

        s.close();
        res.close();
    }
    public static void q2(String nam,String last, String add,String Email,Long ad_no,long mo_no )throws Exception // TO CREATE NEW ACCOUNT AND GET THE ACCOUNT NUMBER
{
    Class.forName("com.mysql.jdbc.Driver"); //load mysql driver
    String name,addr,E_mail,las,pass;
    String nu;
    long ad_num,mo_num;
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");//get connection
    Statement s = con.createStatement();//create object of statement class
    nu="null";
    name=nam;
    las=last;
    addr=add;
    E_mail=Email;
    ad_num=ad_no;
    mo_num=mo_no;
    PreparedStatement p = con.prepareStatement("insert into info (Name,Surname,Address,`Aadhar Number`,`Mobile Number`,`Email ID`) value(?,?,?,?,?,?)");// TO INSERT INTO RELATION

    p.setString(1,nam);
    p.setString(2,las);
    p.setString(3,addr);
    p.setString(4, String.valueOf(ad_num));
    p.setString(5,String.valueOf(mo_num));
    p.setString(6,E_mail);
    p.executeUpdate();
    pass=generate();
    PreparedStatement q =con.prepareStatement("insert into security (pin) value(?)");
    q.setString(1,pass);
    q.executeUpdate();
    System.out.println("*********  YOU HAVE SUCCESSFULLY CREATED AN ACCOUNT HERE  ************");
    System.out.println("---------------------------------------------------------------------------");
    PreparedStatement v =con.prepareStatement("select  `Account Number` from info where `Aadhar Number`=?"); // TO FETCH ACCOUNT NUMBER FROM DATABASE
    v.setString(1, String.valueOf(ad_num));
    ResultSet srs = v.executeQuery();
    while(srs.next())
    {
        int a;
        a= srs.getInt("Account Number");
        System.out.println("YOUR GENERATED ACCOUNT NUMBER IS:- "+a+"  YOUR 8 DIGIT PIN IS:-  "+pass);
        System.out.println("");
        System.out.println("WARNING:- REMEMBER THESE DETAILS IT WILL BE REQUIRED IN FUTURE TRANSACTIONS");

    }
    System.out.println("---------------------------------------------------------------------------");
    System.out.println("YOUR ACCOUNT HAS BEEN CREDITED WITH MINIMUM BALANCE OF 100 RS");
    System.out.println("GO TO LOGIN TO ADD MORE");
    System.out.println("---------------------------------------------------------------------------");







}

    public static String q3(int a, String b) throws Exception  // For login
    {
        int ac_num = a;
        String pin = b;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
        PreparedStatement q = con.prepareStatement("select`Account Number`,Name,Surname,Address,`Aadhar Number`,`Mobile Number`from info,security where `Account Number`=? and pin =?;");
        q.setString(1, String.valueOf(ac_num));
        q.setString(2, pin);
        ResultSet res = q.executeQuery();
        String name = null;
        while (res.next())
        {

            name = res.getString("Name");

        }
        return name;
    }
    public static void q4(int a , String b) throws Exception //get info
    {
        int ac_num = a;
        String pin = b;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
        PreparedStatement q = con.prepareStatement("select`Account Number`,Name,Surname,Address,`Aadhar Number`,`Mobile Number`from info,security where `Account Number`=? and pin =? and `Account Number`=Acc_id ;");
        q.setString(1, String.valueOf(ac_num));
        q.setString(2, pin);
        ResultSet res = q.executeQuery();
        String name = null;
        while (res.next()) {
            String addr;
            String E_mail;
            String las;
            String pass;
            String nu;
            long ad_no;
            long mo_no;
            name = res.getString("Name");
            addr = res.getString("Address");
            las = res.getString("Surname");
            nu = res.getString("Account Number");
            ad_no=res.getLong("Aadhar Number");
            mo_no=res.getLong("Mobile Number");
            System.out.println("YOUR DETAILS ARE:-");
            System.out.println("-----------------------------------");
            System.out.println("ACCOUNT NUMBER:- " + nu);
            System.out.println("-----------------------------------");
            System.out.println("NAME:- " + name + " LAST NAME:- " + las);
            System.out.println("-----------------------------------");
            System.out.println("ADDRESS:- " + addr);
            System.out.println("-----------------------------------");
            System.out.println("AADHAR NUMBER:- "+ad_no);
            System.out.println("-----------------------------------");
            System.out.println("MOBILE NUMBER:- "+mo_no);
        }

    }

    public static long q5(int a, String b) throws Exception //check balance
    {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
        PreparedStatement p = con.prepareStatement("select info.Name,info.Surname,funds.fund from funds,info where info.`Account Number`=funds.acc_Id and acc_Id=?;");
        p.setString(1, String.valueOf(a));
        ResultSet res = p.executeQuery();
        long fund = 0;
        while (res.next()) {
            String name = res.getString("Name");
            String last = res.getString("Surname");
            fund = res.getLong("fund");
            System.out.println("NAME:- " + name + " " + last);
            System.out.println("---------------------------------");
            System.out.println("AVAILABLE BALANCE:- " + fund);
        }
        return fund;
    }
    private static String generate() //TO GET PIN FROM USER
    {
        String pas;
        System.out.println("ENTER YOUR NEW 8 DIGIT PIN (remember it for future use)");
        Scanner in= new Scanner(System.in);
        pas=in.next();
        return pas;
    }


    public static void q6(int a, String b) //transfer funds
    {
        int acc_no=a;
        String pin=b;
        int t_acc_no;
       System.out.println("****TRANSFER MENU****");
       System.out.println("----------------------------");
       System.out.println("ENTER THE ACCOUNT NUMBER IN WHICH YOU WANT TO TRANSFER");
       Scanner in = new Scanner(System.in);
       t_acc_no=in.nextInt();

    }
    public static void q7(int a,String b,long c) throws Exception
    {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
        Statement smt=con.createStatement();
        String sql=String.format("update funds set fund=%d where Acc_ID=%d",c,a);
        PreparedStatement p= con.prepareStatement(sql);
        p.executeUpdate();
        System.out.println("AMOUNT UPDATED SUCCESSFULLY");
        System.out.println("----------------------------");
        System.out.print("NEW BALANCE IS:- "+c);
    }
}
