import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/*createAccount arguments
1.First name
2.Last name
5.Date of birth
6.Balance (Send 0 while creating account)
5.Account type
6.Password
*/

public class createUI extends JFrame implements ActionListener{
    JLabel lfname,llname,ldob,lactype,lpassw,conditions1,conditions2,conditions3;
    JButton b1,b2;
    JTextField jfname,jlname,jdob,jactype;
    JPasswordField jpassw;
    public createUI(){
        super("Alpha Bank-CreateAccount");
        JPanel panel = new JPanel();
        lfname=new JLabel("First name");
        llname=new JLabel("Last name");
        ldob=new JLabel("DOB");
        lactype=new JLabel("Account type");
        lpassw=new JLabel("Password");
        conditions1=new JLabel("DOB in YYYT/MM/DD");
        conditions2=new JLabel("Account type savings only as of now");
        conditions3=new JLabel("You will be redirected to this page if login or create account error accurs");
        jfname=new JTextField();
        jlname=new JTextField();
        jdob=new JTextField();
        jactype=new JTextField("savings");
        jpassw=new JPasswordField();
        b1=new JButton("Submit");
        b2=new JButton("Clear");


        setSize(1366,768);
        lfname.setBounds(150,50,200,50);
        jfname.setBounds(250,50,200,50);

        llname.setBounds(150,130,200,50);
        jlname.setBounds(250,130,200,50);
        
        ldob.setBounds(150,210,200,50);
        jdob.setBounds(250,210,200,50);
        
        lactype.setBounds(150,290,200,50);
        jactype.setBounds(250,290,200,50);
        
        lpassw.setBounds(150,370,200,50);
        jpassw.setBounds(250,370,200,50);

        b1.setBounds(200,450,100,30);
        b2.setBounds(350,450,100,30);


        conditions1.setBounds(150,570,500,20);
        conditions2.setBounds(150,600,500,20);
        conditions3.setBounds(150,630,500,20);

        panel.add(lfname);
        panel.add(llname);
        panel.add(ldob);
        panel.add(lactype);
        panel.add(lpassw);
        panel.add(conditions1);
        panel.add(conditions2);
        panel.add(conditions3);
        panel.add(jfname);
        panel.add(jlname);
        panel.add(jdob);
        panel.add(jactype);
        panel.add(jpassw);        
        panel.add(b1);
        panel.add(b2);

        add(lfname);
        add(llname);
        add(ldob);
        add(lactype);
        add(lpassw);
        add(conditions1);
        add(conditions2);
        add(conditions3);
        add(jfname);
        add(jlname);
        add(jdob);
        add(jactype);
        add(jpassw);        
        add(b1);
        add(b2);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        this.getContentPane().add(panel);
        setLocationRelativeTo(null);


        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                new Login();
            }
        });

        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        String fname=jfname.getText();
        String lname=jlname.getText();
        String dob=jdob.getText();
        String balance="0";
        String actype=jactype.getText();
        char[] passwC=jpassw.getPassword();
        String passw=new String(passwC);
        
        ArrayList<String> details=new ArrayList<String>();
        details.add(fname);
        details.add(lname);
        details.add(dob);
        details.add(balance);
        details.add(actype);
        details.add(passw);

        if(e.getSource()==b1){
            Savings s=new Savings();
            if(s.createAccount(details)){
                System.out.println("UI Create account success");
                String acno=s.findMyAccountNo(fname,lname);
                dispose();
                new DashboardUI(acno,passw);
            }
            else{
                dispose();
                new Login();
            }
        }
    }
    public static void main(String args[]){
        new createUI();
    }
}