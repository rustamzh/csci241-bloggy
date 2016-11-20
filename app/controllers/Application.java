package controllers;

import models.Post;
import models.User;
import play.Play;
import play.modules.paginate.ValuePaginator;
import play.mvc.Before;
import play.mvc.Controller;
import repository.ApprovalRepository;
import repository.PostRepository;
import repository.Impl.ApprovalTestingRepositoryImpl;
import repository.Impl.PostRepositoryImpl;

import java.util.List;

import static controllers.AdminPageController.postRepository;

public class Application extends Controller {
	
	static ApprovalRepository repo=new ApprovalTestingRepositoryImpl();
    static PostRepository postRepository = new PostRepositoryImpl();
    
    private static String curCategory;
    
    @Before
    static void addDefaults() {
        renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
        renderArgs.put("blogLead", Play.configuration.getProperty("blog.lead"));
        curCategory = "Uncategorized";
    }

    public static void index() {
        //PostRepository postRepository = new PostTestingImpl();
        //int number_of_likes, Date date, String body, String category, String user_nickname

        List<Post> listposts;
        
        if (curCategory == null || curCategory.isEmpty() || curCategory.equals("Uncategorized"))
        	listposts = postRepository.getAllPosts();
        else
        	listposts = postRepository.getPostsbyCatgory(curCategory);
        
        //int totalposts=listposts.size();
        List<String> listCat = postRepository.getAllCategories();
        //listposts.add(0,featuredPost);
        ValuePaginator paginator = new ValuePaginator(listposts);
        paginator.setPageSize(5);
        
        User user = Security.getConnectedUser();
        render(paginator, listCat, user, curCategory);
    }
    
    public static void setCategory(String category) {
    	curCategory = category;
    	index();
    }
}