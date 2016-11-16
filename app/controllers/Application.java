package controllers;

import models.Post;
import models.User;
import play.Play;
import play.modules.paginate.ValuePaginator;
import play.mvc.Before;
import play.mvc.Controller;

import java.util.List;

import static controllers.AdminPageController.postRepository;

public class Application extends Controller {



    @Before
    static void addDefaults() {
        renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
        renderArgs.put("blogLead", Play.configuration.getProperty("blog.lead"));
    }

    public static void index() {
        //PostRepository postRepository = new PostTestingImpl();
        //int number_of_likes, Date date, String body, String category, String user_nickname

        List<Post> listposts = postRepository.getAllPosts();
        int totalposts=listposts.size();
        List<String> listCat = postRepository.getAllCategories();
        //listposts.add(0,featuredPost);
        ValuePaginator paginator = new ValuePaginator(listposts);
        paginator.setPageSize(5);
        
        User user = Security.getConnectedUser();
        render(paginator, listCat, user);
    }
}