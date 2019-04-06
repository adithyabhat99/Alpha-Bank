import java.util.ArrayList;
/*createAccount arguments
1.First name
2.Last name
5.Date of birth
6.Balance(Send 0 while creating account)
5.Account type
6.Password
*/

public class Savings implements Account{
    public  boolean createAccount(ArrayList<String> details) {
        try{
            return Database.createAccount(details);
        }
        catch(Exception e){
            System.out.println("Exception1");
            return false;
        }
    }
    public  double withdraw(String acno, String passw, double amt){
        try{
            return Database.withdraw(acno,passw,amt);
        }
        catch(Exception e){
            System.out.println("Exception2");
            return -1;
        }
    }
    public  boolean deposit(String acno, String passw, double amt){
        try{
            return Database.deposit(acno,passw,amt);
        }
        catch(Exception e){
            System.out.println("Exception3");
            return false;
        }
    }
    public  ArrayList<String> getDetails(String acno,String passw){
        try{
            return Database.getDetails(acno,passw);
        }
        catch(Exception e){
            System.out.println("Exception4");
            return new ArrayList<String>();
        }
    }
    public  boolean transfer(String ac1, String ac1passw, String ac2, double amt){
        try{
            return Database.transfer(ac1,ac1passw,ac2,amt);
        }
        catch(Exception e){
            System.out.println("Exception3");
            return false;
        }
    }
    public  String findMyAccountNo(String fname,String lname){
        return Database.findMyAccountNo(fname,lname);
    }
    public boolean login(String acno,String passw){
        return Database.login(acno,passw);
    }
}