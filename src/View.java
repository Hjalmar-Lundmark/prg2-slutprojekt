import javax.swing.*;
import java.awt.*;

public class View {
    private JPanel root;
    private JTextArea textArea1;
    private JButton registerButton;
    private JButton loginButton;
    private JButton createPostButton;
    private JLabel labelLoggedIn;
    private JButton logoutButton;
    private JScrollPane scrollBar;

    public JPanel getPanel() {
        return root;
    }

    public void addPost(String in) {
        textArea1.append(in + "\n");
    }

    public void emptyFeed() {
        textArea1.setText(null);
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getCreatePostButton() {
        return createPostButton;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public void changeUserLabel(String in) {
        labelLoggedIn.setText(in);
    }

    public void scrollToTop() {
        //scrollBar.getVerticalScrollBar().setValue(0);
        textArea1.select(0,0);
    }
}
