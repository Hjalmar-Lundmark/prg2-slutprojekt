import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller {
    View theView;
    Model theModel;
    login loginPage;
    register registerPage;
    createPost createPostPage;

    public Controller(View theView, Model theModel, login l, register r, createPost p) {
        this.theView = theView;
        this.theModel = theModel;

        //sub pages
        loginPage = l;
        registerPage = r;
        createPostPage = p;

        JFrame frame = new JFrame("Forum");
        frame.setContentPane(theView.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        JFrame loginFrame = new JFrame("Login");
        loginFrame.setContentPane(loginPage.getRoot());
        loginFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);

        JFrame registerFrame = new JFrame("Register account");
        registerFrame.setContentPane(registerPage.getRoot());
        registerFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        registerFrame.pack();
        registerFrame.setLocationRelativeTo(null);

        JFrame createPostFrame = new JFrame("Create new post");
        createPostFrame.setContentPane(createPostPage.getRoot());
        createPostFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        createPostFrame.pack();
        createPostFrame.setLocationRelativeTo(null);


        theModel.connect();
        ArrayList<Post> posts = theModel.getPosts();
        /*for (int i = 0; i<posts.toArray().length; i++) {
            theView.addPost(posts.get(i).toString());
        }*/
        for (Post i : posts) { //cleaner code, or something
            theView.addPost(i.toString());
        }

        // Opens other views
        theView.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.setVisible(true);
            }
        });
        theView.getRegisterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerFrame.setVisible(true);
            }
        });
        theView.getCreatePostButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPostFrame.setVisible(true);
            }
        });

        loginPage.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theModel.connect();
                // code for login


                // if success, change stuff


                loginFrame.setVisible(false);
            }
        });


    }

    public static void main(String[] args) {
        View theView = new View();
        Model theModel = new Model();
        login l = new login();
        register r = new register();
        createPost p = new createPost();
        Controller theController = new Controller(theView,theModel, l, r, p);
    }
}
