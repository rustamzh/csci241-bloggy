package controllers;

import models.Post;
import models.User;
import play.Play;
import play.modules.paginate.ValuePaginator;
import play.mvc.Before;
import play.mvc.Controller;
import repository.Impl.PostTestingImpl;
import repository.PostRepository;

import java.util.Calendar;
import java.util.List;

public class Application extends Controller {



    @Before
    static void addDefaults() {
        renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
        renderArgs.put("blogLead", Play.configuration.getProperty("blog.lead"));
    }
    public static void index() {
        PostRepository postRepository = new PostTestingImpl();
        //int number_of_likes, Date date, String body, String category, String user_nickname

        Post featuredPost = new Post(5,new Calendar.Builder().setDate(2016,10,8).build().getTime() ,"First post" ,"<p>This is the first post in this blog</p>","Uncategorized",new User("head_admin", "123","admin", "Rustam Zhumagambetov"));
        List<Post> listposts= postRepository.getAllPosts();
        int totalposts=listposts.size();
        List<String> listCat = postRepository.getAllCategories();
        listposts.add(0,featuredPost);
        ValuePaginator paginator = new ValuePaginator(listposts);
        paginator.setPageSize(4);
        render(paginator, listCat);
    }

}