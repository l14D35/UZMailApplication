package com.gui;

import com.mail.MailUZ;
import com.suggestions.AutoSuggestions;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MailGui extends JFrame {
    private JLabel fromLabel;
    private JLabel toLabel;
    private JLabel subjectLabel;
    private JLabel textLabel;
    private JLabel passwdLabel;
    private JTextField fromTextField;
    private JTextField toTextField;
    private JPasswordField passwdPasswordField;
    private JTextField subjectTextField;
    private JTextArea textArea;
    private JPanel southPanel;
    private JPanel northPanel;

    private boolean isPressed = false;

    public MailGui() {
        northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(5, 5));

        fromLabel = new JLabel("From: ", SwingConstants.RIGHT);
        passwdLabel = new JLabel("Password: ", SwingConstants.RIGHT);
        toLabel = new JLabel("To: ", SwingConstants.RIGHT);
        subjectLabel = new JLabel("Subject: ", SwingConstants.RIGHT);
        textLabel = new JLabel("Content: ", SwingConstants.LEFT);

        fromTextField = new JTextField();

        toTextField = new JTextField();
        passwdPasswordField = new JPasswordField();
        subjectTextField = new JTextField();
        textArea = new JTextArea();

        JButton sendButton = new JButton("Send");
        JButton colorButton = new JButton("Change color");

        textArea.setLineWrap(true);

        northPanel.add(fromLabel);
        northPanel.add(fromTextField);

        northPanel.add(passwdLabel);
        northPanel.add(passwdPasswordField);

        northPanel.add(toLabel);
        northPanel.add(toTextField);

        northPanel.add(subjectLabel);
        northPanel.add(subjectTextField);

        northPanel.add(textLabel);
        northPanel.add(textArea);

        this.add(northPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(textArea);
        this.add(scrollPane, BorderLayout.CENTER);

        southPanel = new JPanel();
        southPanel.add(sendButton);
        southPanel.add(colorButton);

        add(southPanel, BorderLayout.SOUTH);

        new AutoSuggestions(toTextField, this, null, Color.WHITE.brighter(),
                Color.BLUE, Color.RED.darker(), 0.75f) {

            protected boolean wordTyped(String typedWord) {

                //create list for dictionary
                ArrayList<String> words = new ArrayList<>();
                words.add("96125@stud.uz.zgora.pl1");
                words.add("96131@stud.uz.zgora.pl1");
                words.add("96132@stud.uz.zgora.pl1");
                words.add("96133@stud.uz.zgora.pl1");
                words.add("96124@stud.uz.zgora.pl1");
                words.add("96135@stud.uz.zgora.pl1");
                setDictionary(words);

                return super.wordTyped(typedWord);//now call super to check for any matches against newest dictionary
            }
        };


        sendButton.addActionListener(e -> {
            if (!AutoSuggestions.validate(toTextField.getText())) {
                JOptionPane.showMessageDialog(this,
                        "Wrong recipient email.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {

                //////////////////////////////////////////////////////////////////////////////////////////////////////////////
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////

                MailUZ.send(fromTextField.getText(), new String(passwdPasswordField.getPassword()), toTextField.getText(),
                        subjectTextField.getText(), textArea.getText());
                System.exit(0);
            }
        });

        colorButton.addActionListener(e -> changeColor());

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("Email");

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        this.setPreferredSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
        this.pack();
    }

    private void changeColor() {
        if (!isPressed) {
            fromTextField.setBackground(new Color(70, 70, 70));
            toTextField.setBackground(new Color(70, 70, 70));
            passwdPasswordField.setBackground(new Color(70, 70, 70));
            subjectTextField.setBackground(new Color(70, 70, 70));
            textArea.setBackground(new Color(70, 70, 70));
            northPanel.setBackground(new Color(70, 70, 70));
            southPanel.setBackground(new Color(70, 70, 70));

            fromLabel.setForeground(Color.WHITE);
            passwdLabel.setForeground(Color.WHITE);
            toLabel.setForeground(Color.WHITE);
            subjectLabel.setForeground(Color.WHITE);
            textLabel.setForeground(Color.WHITE);

            fromTextField.setForeground(Color.WHITE);
            toTextField.setForeground(Color.WHITE);
            passwdPasswordField.setForeground(Color.WHITE);
            subjectTextField.setForeground(Color.WHITE);
            textArea.setForeground(Color.WHITE);
            isPressed = true;
        } else {
            fromTextField.setBackground(Color.WHITE);
            toTextField.setBackground(Color.WHITE);
            passwdPasswordField.setBackground(Color.WHITE);
            subjectTextField.setBackground(Color.WHITE);
            textArea.setBackground(Color.WHITE);
            northPanel.setBackground(new Color(238, 238, 238));
            southPanel.setBackground(new Color(238, 238, 238));

            fromLabel.setForeground(Color.BLACK);
            passwdLabel.setForeground(Color.BLACK);
            toLabel.setForeground(Color.BLACK);
            subjectLabel.setForeground(Color.BLACK);
            textLabel.setForeground(Color.BLACK);

            fromTextField.setForeground(Color.BLACK);
            toTextField.setForeground(Color.BLACK);
            passwdPasswordField.setForeground(Color.BLACK);
            subjectTextField.setForeground(Color.BLACK);
            textArea.setForeground(Color.BLACK);

            isPressed = false;
        }

    }

}
