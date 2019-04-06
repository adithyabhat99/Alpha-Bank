import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DepositUI extends JFrame implements ActionListener{
    JPasswordField p;
    JTextField amt;
    JLabel l1,l2,l3;
    JButton b1;
    String accno,passW,amount;
    public DepositUI(String acno,String passw){
        super("Alpha Bank-Deposit");
        setSize(700,700);
        JPanel panel = new JPanel();

        accno=acno;
        passW=passw;

        l1=new JLabel("Amount");
        l2=new JLabel("Password");
        l3=new JLabel("");
        amt=new JTextField();
        p=new JPasswordField();
        b1=new JButton("Submit");

        l1.setBounds(200,200,100,30);
        l2.setBounds(200,250,100,30);
        amt.setBounds(350,200,100,30);
        p.setBounds(350,250,100,30);
        b1.setBounds(350,350,200,50);
        l3.setBounds(300,450,200,30);

        panel.add(l1);
        panel.add(l2);
        panel.add(l3);
        panel.add(amt);
        panel.add(p);
        panel.add(b1);

        add(l1);
        add(l2);
        add(l3);
        add(amt);
        add(p);
        add(b1);

        b1.addActionListener(this);

        this.getContentPane().add(panel);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
               public void windowClosing(WindowEvent e) {
                dispose();
                new DashboardUI(acno, passw);
            }
        });

        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        amount=amt.getText();
        char[] pw=p.getPassword();
        passW=new String(pw);
        if(e.getSource()==b1){
            Savings s=new Savings();
            boolean w=s.deposit(accno, passW, Double.parseDouble(amount));
            if(!w){
                l3.setText("Deposit unsuccesfull");
            }
            else{
                l3.setText("Deposit succesfull!");
            }
        }
    }
}