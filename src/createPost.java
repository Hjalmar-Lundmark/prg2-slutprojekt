import javax.swing.*;

public class createPost {
    private JPanel root;
    private JTextField textField1;
    private JTextArea textArea1;
    private JButton sendButton;

    public JPanel getRoot() {
        return root;
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextArea getTextArea1() {
        return textArea1;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Create new Post");
        frame.setContentPane(new createPost().root);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
