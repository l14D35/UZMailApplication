package com.gui;

import com.mail.MailUZ;
import com.suggestions.AutoSuggestions;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MailGui extends JFrame {
    private JTextField fromTextField;
    private JTextField toTextField;
    private JPasswordField passwdPasswordField;
    private JTextField subjectTextField;
    private JTextArea textArea;

    public MailGui() {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(5, 5));

        JLabel fromLabel = new JLabel("From: ", SwingConstants.RIGHT);
        JLabel passwdLabel = new JLabel("Password: ", SwingConstants.RIGHT);
        JLabel toLabel = new JLabel("To: ", SwingConstants.RIGHT);
        JLabel subjectLabel = new JLabel("Subject: ", SwingConstants.RIGHT);
        JLabel textLabel = new JLabel("Content: ", SwingConstants.LEFT);

        fromTextField = new JTextField();

        toTextField = new JTextField();
        passwdPasswordField = new JPasswordField();
        subjectTextField = new JTextField();
        textArea = new JTextArea();

        JButton sendButton = new JButton("Send");

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

        JPanel southPanel = new JPanel();
        southPanel.add(sendButton);

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
                MailUZ.send(fromTextField.getText(), new String(passwdPasswordField.getPassword()), toTextField.getText(),
                        subjectTextField.getText(), textArea.getText());
                System.exit(0);
            }
        });

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("Email");

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        this.setPreferredSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
        this.pack();
    }
}
