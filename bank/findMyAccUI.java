import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class findMyAccUI extends JFrame implements ActionListener{
    JTextField t1,t2;
    JLabel l1,l2,l;
    JButton b1,b2;
    public findMyAccUI(){
        super("Alpha Bank-FindMyAcc");
        setSize(700,600);
        JPanel panel = new JPanel();
        t1=new JTextField();
        t2=new JTextField();
        l1=new JLabel("First name");
        l2=new JLabel("Last name");
        l=new JLabel();
        b1=new JButton("Submit");
        b2=new JButton("Clear");
        l1.setBounds(150,150,200,50);
        l2.setBounds(150,220,200,50);
        t1.setBounds(350,150,200,50);
        t2.setBounds(350,220,200,50);
        b1.setBounds(150,300,100,50);
        b2.setBounds(350,300,100,50);
        l.setBounds(200,400,200,50);
        add(b1);
        add(b2);
        add(t1);
        add(t2);
        add(l1);
        add(l2);
        add(l);
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
        String fname=t1.getText();
        String lname=t2.getText();
        if(e.getSource()==b1){
            Savings s=new Savings();
            String acno=s.findMyAccountNo(fname,lname);
            if(acno!="")
                l.setText("Your acount number is "+acno);
            else
                l.setText("Not found!");
        }
    }
    public static void main(String args[]){
        new findMyAccUI();
    }
}