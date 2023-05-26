import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

public class Controller {
    View theView;
    Model theModel;
    login loginPage;
    register registerPage;
    createPost createPostPage;
    JFrame frame;
    JFrame loginFrame;
    JFrame registerFrame;
    JFrame createPostFrame;

    public Controller(View theView, Model theModel, login l, register r, createPost p) {
        this.theView = theView;
        this.theModel = theModel;

        //sub pages
        loginPage = l;
        registerPage = r;
        createPostPage = p;

        frame = new JFrame("Forum");
        frame.setContentPane(theView.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        loginFrame = new JFrame("Login");
        loginFrame.setContentPane(loginPage.getRoot());
        loginFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        loginFrame.setSize(400, 400);
        loginFrame.setLocationRelativeTo(null);

        registerFrame = new JFrame("Register account");
        registerFrame.setContentPane(registerPage.getRoot());
        registerFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        registerFrame.setSize(400, 400);
        registerFrame.setLocationRelativeTo(null);

        createPostFrame = new JFrame("Create new post");
        createPostFrame.setContentPane(createPostPage.getRoot());
        createPostFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        createPostFrame.setSize(400, 400);
        createPostFrame.setLocationRelativeTo(null);


        theModel.connect();
        updateFeed(theModel.getPosts());

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
                login(); // method further down here in controller
            }
        });
        registerPage.getRegisterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });
        createPostPage.getSendButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPost();
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

        // Kb listener for send / login buttons
        loginPage.getLoginButton().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    login();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {

            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        registerPage.getRegisterButton().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    register();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {

            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        createPostPage.getSendButton().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    createPost();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {

            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        // mouse listener for changing color of logout button on hover
        theView.getLogoutButton().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
            @Override
            public void mousePressed(MouseEvent e) {

            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                theView.getLogoutButton().setBackground(new Color(255, 97, 97));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                theView.getLogoutButton().setBackground(UIManager.getColor("control"));
            }
        });




    }

    public void updateFeed(ArrayList<Post> posts) {
        theView.emptyFeed();
        /*for (int i = 0; i<posts.toArray().length; i++) {
            theView.addPost(posts.get(i).toString());
        }*/
        for (Post i : posts) { //cleaner code, or something
            theView.addPost(i.toString());
        }
    }

    public void login() {
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

            loginPage.getErrorArea().setVisible(false);
        } else {
            System.out.println("Login failed - cont");
            if (!Objects.equals(theModel.getError(), "")) {
                loginPage.getErrorArea().setVisible(true);
                loginPage.insertError(theModel.getError());
            }
        }
    }

    public void register() {
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

            registerPage.getErrorArea().setVisible(false);
        } else {
            System.out.println("Reg failed - cont");
            if (!Objects.equals(theModel.getError(), "")) {
                registerPage.getErrorArea().setVisible(true);
                registerPage.insertError(theModel.getError());
            }
        }
    }

    public void createPost() {
        theModel.connect();
        boolean success = theModel.createNewPost(createPostPage.getTextField1(), createPostPage.getTextArea1());
        if (success) { // if a post is created this runs, else keeps the window opened
            createPostFrame.setVisible(false);
            createPostPage.emptyPage();

            //refresh posts
            theModel.connect(); // do I need this here ???
            updateFeed(theModel.getPosts());
            theView.scrollToTop();

            createPostPage.getErrorArea().setVisible(false);
        } else {
            System.out.println("Posting failed - cont");
            if (!Objects.equals(theModel.getError(), "")) {
                createPostPage.getErrorArea().setVisible(true);
                createPostPage.insertError(theModel.getError());
            }
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
