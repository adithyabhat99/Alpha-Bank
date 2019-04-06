
import java.util.ArrayList;
interface Account{
    public boolean createAccount(ArrayList<String> details);
    public boolean deposit(String acno,String passw,double amount);
    public double withdraw(String acno,String passw,double amount);
    public ArrayList<String> getDetails(String acno,String passw);
    public boolean transfer(String ac1,String ac1passw,String ac2,double amt);
    public String findMyAccountNo(String fname,String lname);
    public boolean login(String acno,String passw);
}