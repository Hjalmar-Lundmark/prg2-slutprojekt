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

    public void addPost(Post in) {
        textArea1.append(in.toString() + "\n");

        // Does not work as intended, changes the font of all text inside the textArea
        /*
        textArea1.setFont(new Font("Arial", Font.BOLD, 18));
        textArea1.append(in.getTitle() + "\n");
        textArea1.setFont(new Font("Arial", Font.ITALIC, 16));
        textArea1.append("Made by: " + in.getAuthor() + "\n");
        textArea1.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea1.append(in.getContent() + "\n");
        textArea1.setFont(new Font("Arial", Font.BOLD, 14));
        textArea1.append("Created at " + in.getCreatedAt() + "\n\n");
        */
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
