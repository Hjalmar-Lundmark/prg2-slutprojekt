import javax.swing.*;
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

        JFrame loginFrame = new JFrame("Login");
        loginFrame.setContentPane(loginPage.getRoot());
        loginFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        loginFrame.pack();

        JFrame registerFrame = new JFrame("Register account");
        registerFrame.setContentPane(registerPage.getRoot());
        registerFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        registerFrame.pack();

        JFrame postFrame = new JFrame("Create new post");
        postFrame.setContentPane(createPostPage.getRoot());
        postFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        postFrame.pack();


        theModel.connect();
        ArrayList<Post> posts = theModel.getPosts();
        for (int i = 0; i<posts.toArray().length; i++) {
            theView.addPost(posts.get(i).toString());
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
