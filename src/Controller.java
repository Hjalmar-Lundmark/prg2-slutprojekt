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
        loginFrame.setSize(400, 400);
        loginFrame.setLocationRelativeTo(null);

        JFrame registerFrame = new JFrame("Register account");
        registerFrame.setContentPane(registerPage.getRoot());
        registerFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        registerFrame.setSize(400, 400);
        registerFrame.setLocationRelativeTo(null);

        JFrame createPostFrame = new JFrame("Create new post");
        createPostFrame.setContentPane(createPostPage.getRoot());
        createPostFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        createPostFrame.setSize(400, 400);
        createPostFrame.setLocationRelativeTo(null);


        theModel.connect();
        getPosts(theModel.getPosts());

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

        // actions inside sub-windows
        loginPage.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theModel.connect();
                theModel.login(loginPage.getTextField1(), loginPage.getPasswordField1());
                if (theModel.isLoggedIn()) {
                    System.out.println("Login successful - cont");
                    theView.changeUserLabel(theModel.getUsername());
                    loginFrame.setVisible(false);
                    loginPage.emptyPage();

                    // Changes button layout
                    theView.getLoginButton().setVisible(false);
                    theView.getRegisterButton().setVisible(false);
                    theView.getCreatePostButton().setVisible(true);
                    theView.getLogoutButton().setVisible(true);
                } else {
                    System.out.println("Login failed - cont");
                }
            }
        });
        registerPage.getRegisterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theModel.connect();
                theModel.register(registerPage.getTextField1(), registerPage.getPasswordField1(), registerPage.getPasswordField2());
                if (theModel.isLoggedIn()) {
                    System.out.println("reg successful - cont");
                    theView.changeUserLabel(theModel.getUsername());
                    registerFrame.setVisible(false);
                    registerPage.emptyPage();

                    // Changes button layout
                    theView.getLoginButton().setVisible(false);
                    theView.getRegisterButton().setVisible(false);
                    theView.getCreatePostButton().setVisible(true);
                    theView.getLogoutButton().setVisible(true);
                } else {
                    System.out.println("Reg failed - cont");
                }
            }
        });
        createPostPage.getSendButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theModel.connect();
                boolean success = theModel.createNewPost(createPostPage.getTextField1(), createPostPage.getTextArea1());
                if (success) { // if a post is created this runs, else keeps the window opened
                    createPostFrame.setVisible(false);
                    createPostPage.emptyPage();

                    //refresh posts
                    theModel.connect(); // do I need this here ???
                    getPosts(theModel.getPosts());
                }
            }
        });

        // logout
        theView.getLogoutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theModel.setUsername(null);
                theModel.setUserId(0);
                theModel.setLoggedIn(false);

                theView.changeUserLabel("Logged out");

                theView.getLogoutButton().setVisible(false);
                theView.getCreatePostButton().setVisible(false);
                theView.getLoginButton().setVisible(true);
                theView.getRegisterButton().setVisible(true);
            }
        });

    }

    public void getPosts(ArrayList<Post> posts) {
        theView.emptyFeed();
        /*for (int i = 0; i<posts.toArray().length; i++) {
            theView.addPost(posts.get(i).toString());
        }*/
        for (Post i : posts) { //cleaner code, or something
            theView.addPost(i.toString());
        }
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
