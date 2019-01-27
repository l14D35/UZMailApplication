package com.test;

import com.gui.MailGui;

import javax.swing.*;

public class MailTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MailGui::new);
    }
}
