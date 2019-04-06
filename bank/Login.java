import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
public class Login extends JFrame implements ActionListener{
    JButton b1,b2,b3;
    JLabel l1;
    public Login(){
        super("Alpha Bank");
        JPanel panel = new JPanel();
        setSize(700,700);
        b1=new JButton("Login");
        b2=new JButton("Create Account");
        b3=new JButton("Find my account");
        l1=new JLabel("Hi,Welcome to Alpha Bank!");
        b1.setBounds(200,250,300,30);
        b2.setBounds(200,350,300,30);
        b3.setBounds(200,450,300,30);
        l1.setBounds(200,150,300,40);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(l1);
        add(b1);
        add(b2);
        add(b3);
        add(l1);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        this.getContentPane().add(panel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);  
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==b1){
            dispose();
            new LoginUI();
        }
        if(e.getSource()==b2){
            dispose();
            new createUI();
        }
        if(e.getSource()==b3){
            dispose();
            new findMyAccUI();
        }
    }
    public static void main(String args[]){
        new Login();
    }
}