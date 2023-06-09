import javax.swing.*;

public class register {
    private JPanel root;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton registerButton;
    private JTextArea errorArea;

    public JPanel getRoot() {
        return root;
    }

    public String getTextField1() {
        return textField1.getText();
    }

    public String getPasswordField1() {
        return passwordField1.getText();
    }

    public String getPasswordField2() {
        return passwordField2.getText();
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JTextArea getErrorArea() {
        return errorArea;
    }

    public void emptyPage() {
        textField1.setText("");
        passwordField1.setText("");
        passwordField2.setText("");
    }

    public void insertError(String e) {
        errorArea.setText("ERROR: \n" + e);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Register account");
        frame.setContentPane(new register().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
