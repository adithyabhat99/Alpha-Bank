
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Database {
    public static Connection getConnection() throws Exception {
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost/bank";
            String username = "root";
            String passw = "123654654";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, passw);
            System.out.println("DB Connected");
            return conn;
        } catch (Exception e) {
            System.out.println("CONNECTION: " + e);
            return null;
        }
    }
    public static boolean createTable() throws Exception {
        try {
            Connection conn = getConnection();
            String query = "CREATE TABLE IF NOT EXISTS account(\n" + "fname varchar(20) not null,\n"
                    + "lname varchar(20) not null,\n" + "acno int  not null auto_increment,\n" + "dob date not null,\n"
                    + "balance int not null,\n" + "actype varchar(10) not null,\n" + "passw varchar(20) not null,\n"
                    + "primary key (acno))";
            PreparedStatement create = conn.prepareStatement(query);
            create.executeUpdate();
            System.out.println("Table created");
            return true;
        } catch (Exception e) {
            System.out.println("CONNECTION: " + e);
            return false;
        }
    }

    public static boolean createAccount(ArrayList<String> details) throws Exception {
        try {
            createTable();
            Connection conn = getConnection();
            String query = "insert into account(fname,lname,dob,balance,actype,passw)\n" + "values('" + details.get(0)
                    + "','" + details.get(1) + "','" + details.get(2) + "','" + details.get(3) + "','" + details.get(4) + "','"
                    + details.get(5) + "')";
            PreparedStatement create = conn.prepareStatement(query);
            create.executeUpdate();
            System.out.println("Account created");
            return true;
        } catch (Exception e) {
            System.out.println("CREATE ACC: " + e);
            return false;
        }
    }

    public static double withdraw(String acno, String passw, double amt) {
        try {
            Connection conn = getConnection();
            String query = "select balance from account where acno='" + acno + "'" + " and passw='" + passw + "'";
            PreparedStatement create = conn.prepareStatement(query);
            ResultSet result = create.executeQuery();
            String balS = "";
            if (result.next())
                balS = result.getString("balance");
            double balance = Double.parseDouble(balS);
            if (balance < amt) {
                return -1;
            } else
                balance -= amt;
            query = "update account set balance='" + balance + "'" + " where acno='" + acno + "'" + " and passw='"
                    + passw + "'";
            create = conn.prepareStatement(query);
            create.executeUpdate();
            System.out.println("Withdrawal successfull");
            return balance;
        } catch (Exception e) {
            System.out.println("WITHDRAW: " + e);
            return -1;
        }
    }

    public static boolean deposit(String acno, String passw, double amt) {
        try {
            Connection conn = getConnection();
            String query = "select balance from account where acno='" + acno + "'" + " and passw='" + passw + "'";
            PreparedStatement create = conn.prepareStatement(query);
            ResultSet result = create.executeQuery();
            String balS = "";
            if (result.next())
                balS = result.getString("balance");
            double balance = Double.parseDouble(balS);
            balance += amt;
            query = "update account set balance='" + balance + "'" + " where acno='" + acno + "'" + " and passw='"
                    + passw + "'";
            create = conn.prepareStatement(query);
            create.executeUpdate();
            System.out.println("Deposit successfull");
            return true;
        } catch (Exception e) {
            System.out.println("DEPOSIT: " + e);
            return false;
        }
    }

    public static ArrayList<String> getDetails(String acno,String passw) throws Exception {
        try {
            Connection conn =  getConnection();
            String query = "select * from account where acno='" + acno + "' and passw='"+passw+"'";
            PreparedStatement create = conn.prepareStatement(query);
            ResultSet result = create.executeQuery();
            ArrayList<String> details = new ArrayList<String>();
            if (result.next()) {
                details.add(result.getString("fname"));
                details.add(result.getString("lname"));
                details.add(result.getString("acno"));
                details.add(result.getString("dob"));
                details.add(result.getString("balance"));
                details.add(result.getString("actype"));
            }
            System.out.println("Get details succesfull");
            return details;
        } catch (Exception e) {
            System.out.println("GET_DETAILS: " + e);
            return null;
        }
    }

    public static boolean transfer(String ac1, String ac1passw, String ac2, double amt) {
        try {
            double b1 = withdraw(ac1, ac1passw, amt);
            if (b1 < amt) {
                deposit(ac1, ac1passw, b1);
                return false;
            }
            Connection conn = getConnection();
            String query = "select balance from account where acno='" + ac2 + "'";
            PreparedStatement create = conn.prepareStatement(query);
            ResultSet result = create.executeQuery();
            String balS = "";
            if (result.next())
                balS = result.getString("balance");
            double balance = Double.parseDouble(balS);
            balance += amt;
            query = "update account set balance='" + balance + "'" + " where acno='" + ac2 + "'";
            create = conn.prepareStatement(query);
            create.executeUpdate();
            System.out.println("Transaction succesfull");
            return true;
        } catch (Exception e) {
            System.out.println("TRANSFER: " + e);
            return false;
        }
    }
    public static String findMyAccountNo(String fname,String lname){
        try{
            Connection conn = getConnection();
            String query="select acno from account where fname='"+fname+"' and lname='"+lname+"'";
            PreparedStatement create = conn.prepareStatement(query);
            ResultSet result = create.executeQuery();
            String accno="";
            if(result.next()){
                accno=result.getString("acno");
            }
            System.out.println("FindMyAccountNo successfull");
            return accno;
        }
        catch(Exception e){
            System.out.println("findMyAccountNo: "+e);
            return "";
        }
    }
    public static boolean login(String acno,String passw){
        try{
            createTable();
            Connection conn = getConnection();
            String query="select fname from account where acno='"+acno+"' and passw='"+passw+"'";
            PreparedStatement create=conn.prepareStatement(query);
            ResultSet result=create.executeQuery();
            if(result.next()){
                System.out.println("Login successfull");
                return true;
            }
            return false;
        }
        catch(Exception e){
            System.out.println("LOGIN: "+e);
            return false;
        }
    }

    public static void main(String args[]) throws Exception {
        /*
         * createTable(); 
         * ArrayList<String> ar=new ArrayList<String>();
         * ar.add("adi"); ar.add("bhat"); ar.add("1999/10/13"); ar.add("1000"); ar.add("savings"); ar.add("1234");
         * createAccount(ar);
         * withdraw("1","1234",500); 
         * deposit("1","1234",500); 
         * ArrayList<String> details=getDetails("1","1234"); 
         * details.forEach(System.out::println);
         * ArrayList<String> arr=new ArrayList<String>();
         * arr.add("adithya"); arr.add("bhat"); arr.add("1999/10/13"); arr.add("100"); arr.add("savings"); arr.add("123");
         * createAccount(arr); 
         * transfer("1","1234","2",300);
         * System.out.println(findMyAccountNo("adithya","bhat"));
         * login("1","1234");
         */
    }
}