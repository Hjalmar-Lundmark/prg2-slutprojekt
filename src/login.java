import javax.swing.*;

public class login {
    private JPanel root;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;

    public JPanel getRoot() {
        return root;
    }

    public String getTextField1() {
        return textField1.getText();
    }

    public String getPasswordField1() {
        return passwordField1.getText();
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public void emptyPage() {
        textField1.setText("");
        passwordField1.setText("");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new login().root);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
