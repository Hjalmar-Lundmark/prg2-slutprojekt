import javax.swing.*;
import java.util.ArrayList;

public class Controller {
    View theView;
    Model theModel;

    public Controller(View theView, Model theModel) {
        this.theView = theView;
        this.theModel = theModel;

        JFrame frame = new JFrame("Forum");
        frame.setContentPane(theView.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);



        theModel.connect();
        ArrayList<Post> posts = theModel.getPosts();
        for (int i = 0; i<posts.toArray().length; i++) {
            theView.addPost(posts.get(i).toString());
        }




    }

    public static void main(String[] args) {
        View theView = new View();
        Model theModel = new Model();
        Controller theController = new Controller(theView,theModel);
    }
}
