package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;


public class Main {

    public static void main(String[] args) throws Exception{


KontoBankowe a = new KontoBankowe();

    }
}
class KontoBankowe extends JFrame implements ActionListener {
    JButton wypłata;
    JButton depozyt;
    JTextField input;
    JTextArea historiaOperacji;
    JTextArea saldo;
    JLabel kwota;

    public KontoBankowe() throws Exception {

        wypłata = new JButton("wypłać");
        wypłata.setBounds(40, 30, 70, 30);
        wypłata.addActionListener(this);
        wypłata.setFont(new Font("Arial", Font.BOLD, 9));
        wypłata.setFocusable(true);


        kwota = new JLabel();
        kwota.setBounds(114, 26, 90, 40);
        kwota.setText("kwota: ");


        input = new JTextField();
        input.setBounds(156, 38, 60, 18);
        input.setEditable(true);

        depozyt = new JButton("depozyt");
        depozyt.setBounds(230, 30, 70, 30);
        depozyt.addActionListener(this);
        depozyt.setFont(new Font("Arial", Font.BOLD, 9));


        saldo = new JTextArea();
        saldo.setText("saldo: " + Konto.getSaldo());
        saldo.setBackground(Color.lightGray);
        saldo.setBounds(310, 38, 90, 40);


        historiaOperacji = new JTextArea();
        historiaOperacji.setBounds(80, 80, 200, 270);
        historiaOperacji.setBackground(Color.white);
        historiaOperacji.setEditable(false);


        this.add(historiaOperacji);
        this.add(input);
        this.add(saldo);
        this.add(kwota);
        this.add(depozyt);
        this.add(wypłata);
        this.setTitle("KontoBankowe");
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(420, 400);
        this.getContentPane().setBackground(Color.lightGray);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == wypłata) {
            try {
                String inputString = input.getText();
                if (inputString.isEmpty()) {
                    throw new Exception();
                }

                try {
                    String inputText = input.getText();
                    for (int i = 0; i < inputText.length(); i++) {
                        if (!Character.isDigit(inputText.charAt(i))) {
                            throw new Exception("Błąd!");
                        }
                    }
                    try {
                        int inputInt = Integer.parseInt(input.getText());
                        int saldoInt = Konto.getSaldo();


                        if (inputInt > saldoInt) {
                            throw new Exception("Błąd");
                        } else {
                            Konto.setSaldo(Konto.saldo - Integer.parseInt(input.getText()));
                            saldo.setText("saldo: " + Konto.getSaldo());
                            if (historiaOperacji.getLineCount() >= 0) {
                                historiaOperacji.replaceRange("\nwypłacono: " + input.getText(), 0, 0);
                            }
                        }
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(null,
                                "Chcesz wypłacić więcej niż masz!",
                                "Błąd", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null,
                            "Musisz wprowadzić dodatnią liczbę całkowitą! ",
                            "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(null,
                        "Proszę wprowadzić kwotę",
                        "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == depozyt) {
            try {
                String inputString = input.getText();
                if (inputString.isEmpty()) {
                    throw new Exception("Błąd");
                }

                try {
                    String inputText = input.getText();
                    boolean test = true;
                    for (int i = 0; i < inputText.length(); i++) {
                        if (!Character.isDigit(inputText.charAt(i))) {
                            test = false;
                            throw new Exception("Błąd");
                        }
                    }
                    if (test) {
                        Konto.setSaldo(Konto.saldo + Integer.parseInt(input.getText()));
                        saldo.setText("saldo: " + Konto.getSaldo());
                        if (historiaOperacji.getLineCount() >= 0) {
                            historiaOperacji.replaceRange("\nzdeponowano: " + input.getText(), 0, 0);
                        }
                    }
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null,
                            "Musisz wprowadzić dodatnią liczbę całkowitą! ",
                            "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(null,
                        "Proszę wprowadzić kwotę",
                        "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}

class Konto{

public static int saldo;

    public static int getSaldo() {
        return saldo;
    }

    public static void setSaldo(int saldo) {
        Konto.saldo = saldo;
    }
}

class Wyjątek extends Exception {

    Wyjątek(String message){
        super(message);
    }

}