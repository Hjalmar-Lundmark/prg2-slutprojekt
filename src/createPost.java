import javax.swing.*;

public class createPost {
    private JPanel root;
    private JTextField textField1;
    private JTextArea textArea1;
    private JButton sendButton;
    private JTextArea errorArea;

    public JPanel getRoot() {
        return root;
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public String getTextField1() {
        return textField1.getText();
    }

    public String getTextArea1() {
        return textArea1.getText();
    }

    public JTextArea getErrorArea() {
        return errorArea;
    }

    public void emptyPage() {
        textArea1.setText("");
        textField1.setText("");
    }

    public void insertError(String e) {
        errorArea.setText("ERROR: \n" + e);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Create new Post");
        frame.setContentPane(new createPost().root);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
