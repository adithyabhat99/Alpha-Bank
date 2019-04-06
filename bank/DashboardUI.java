import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.Border;
import java.awt.Color;
import java.util.ArrayList;

public class DashboardUI extends JFrame implements ActionListener{
    JLabel lfname,llname,lacno,ldob,lbalance,lactype;
    Border border;
    JButton b1,b2,b3;
    ArrayList<String> details;
    String passw1;
    DashboardUI(String acno,String passw){
        super("Alpha Bank-Dashboard");
        setSize(700,700);
        
        Savings s=new Savings();
        details=s.getDetails(acno,passw);
        passw1=passw;
        //System.out.println(details.get(0));
        
        JPanel panel = new JPanel();
        lfname=new JLabel("First Name: "+details.get(0));
        llname=new JLabel("Second name: "+details.get(1));
        lacno=new JLabel("Account number: "+details.get(2));
        ldob=new JLabel("DOB: "+details.get(3));
        lbalance=new JLabel("Balance: "+details.get(4));
        lactype=new JLabel("Account type: "+details.get(5));
        border = BorderFactory.createLineBorder(Color.GREEN, 2);
        b1=new JButton("Deposit");
        b2=new JButton("Withdraw");
        b3=new JButton("Transfer");

        lfname.setBounds(100,50,200,50);
        llname.setBounds(100,120,200,50);
        lacno.setBounds(100,190,200,50);
        ldob.setBounds(100,260,200,50);
        lbalance.setBounds(100,330,200,50);
        lactype.setBounds(100,400,200,50);
        b1.setBounds(400,200,200,50);
        b2.setBounds(400,300,200,50);
        b3.setBounds(400,400,200,50);

        lfname.setBorder(border);
        llname.setBorder(border);
        lacno.setBorder(border);
        ldob.setBorder(border);
        lbalance.setBorder(border);
        lactype.setBorder(border);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        
        panel.add(lfname);
        panel.add(llname);
        panel.add(lacno);
        panel.add(ldob);
        panel.add(lbalance);
        panel.add(lactype);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);

        add(lfname);
        add(llname);
        add(lacno);
        add(ldob);
        add(lbalance);
        add(lactype);
        add(b1);
        add(b2);
        add(b3);
        
        this.getContentPane().add(panel);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==b1){
            dispose();
            new DepositUI(details.get(2),passw1);
        }
        if(e.getSource()==b2){
            dispose();
            new WithdrawUI(details.get(2),passw1);
        }
        if(e.getSource()==b3){
            dispose();
            new TransferUI(details.get(2),passw1);
        }
    }

    public static void main(String args[]){
        DashboardUI d=new DashboardUI("2","1234");
    }
}