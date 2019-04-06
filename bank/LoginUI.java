import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class LoginUI extends JFrame implements ActionListener{
    JTextField t1;
    JPasswordField t2;
    JLabel l1,l2;
    JButton b1,b2;
    public LoginUI(){
        super("Alpha Bank-Login");
        setSize(700,600);
        JPanel panel = new JPanel();
        t1=new JTextField();
        t2=new JPasswordField();
        l1=new JLabel("Account Number");
        l2=new JLabel("Password");
        b1=new JButton("Submit");
        b2=new JButton("Clear");
        l1.setBounds(150,150,200,50);
        l2.setBounds(150,220,200,50);
        t1.setBounds(350,150,200,50);
        t2.setBounds(350,220,200,50);
        b1.setBounds(150,300,100,50);
        b2.setBounds(350,300,100,50);
        add(b1);
        add(b2);
        add(t1);
        add(t2);
        add(l1);
        add(l2);
        this.getContentPane().add(panel);
        setLocationRelativeTo(null);

        b1.addActionListener(this);
        b2.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                new Login();
            }
        });
        setVisible(true);  
    }
    public void actionPerformed(ActionEvent e){
        String acno=t1.getText();
        char[] passwC=t2.getPassword();
        String passw=new String(passwC);
        if(e.getSource()==b1){
            Savings s=new Savings();
            if(s.login(acno,passw)){
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
        new LoginUI();
    }
}