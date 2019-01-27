package com.gui;
import com.mail.MailUZ;

import javax.swing.*;
import java.awt.*;

public class MailGui extends JFrame {
    public MailGui() {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(5, 5));

        JLabel fromLabel = new JLabel("From: ", SwingConstants.RIGHT);
        JLabel passwdLabel = new JLabel("Password: ", SwingConstants.RIGHT);
        JLabel toLabel = new JLabel("To: ", SwingConstants.RIGHT);
        JLabel subjectLabel = new JLabel("Subject: ", SwingConstants.RIGHT);
        JLabel textLabel = new JLabel("Content: ", SwingConstants.LEFT);

        JTextField fromTextField = new JTextField();

        JTextField toTextField = new JTextField();
        JPasswordField passwdPasswordField = new JPasswordField();
        JTextField subjectTextField = new JTextField();
        JTextArea textArea = new JTextArea(8, 30);

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

        sendButton.addActionListener(e -> {
            MailUZ.send(fromTextField.getText(), new String(passwdPasswordField.getPassword()), toTextField.getText(),
                    subjectTextField.getText(), textArea.getText());
            System.exit(0);
        });

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("Email");

        this.pack();
    }
}
