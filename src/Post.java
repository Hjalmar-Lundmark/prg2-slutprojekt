public class Post {
    private String Title;
    private String Content;
    private String Author;
    private int id;
    //private time-thingy createdAt;

    public Post(String title, String content, String author, int id) {
        Title = title;
        Content = content;
        Author = author;
        this.id = id;
    }

    @Override
    public String toString() {
        return getTitle() + "\n" +
                getAuthor() + "\n" +
                getContent() + "\n";
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
