package controllers;

import models.Post;
import models.User;
import play.Play;
import play.modules.paginate.ValuePaginator;
import play.mvc.Before;
import play.mvc.Controller;
import repository.ApprovalRepository;
import repository.CommentRepository;
import repository.PostRepository;
import repository.Impl.ApprovalTestingRepositoryImpl;
import repository.Impl.CommentRepositoryImpl;
import repository.Impl.PostRepositoryImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static controllers.AdminPageController.postRepository;

public class Application extends Controller {
	
	static ApprovalRepository repo=new ApprovalTestingRepositoryImpl();
    static PostRepository postRepository = new PostRepositoryImpl();
    static CommentRepository commentRepository = new CommentRepositoryImpl();
    
    private static String curCategory = null;
    
    @Before
    static void addDefaults() {
        renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
        renderArgs.put("blogLead", Play.configuration.getProperty("blog.lead"));
        //curCategory = null;
    }

    public static void index() {
        //PostRepository postRepository = new PostTestingImpl();
        //int number_of_likes, Date date, String body, String category, String user_nickname
    	
    	List<Post> allPosts = postRepository.getAllPosts();
        List<Post> listposts;
        String category = curCategory;
        
        if (category == null || category.isEmpty())
        	listposts = allPosts;
        else {
        	listposts = new ArrayList();
        	
        	for (Post post: allPosts)
        		if ( post.getCategory().equals( category ) )
        			listposts.add( post );
        }
        
        System.out.println( "Number of posts in " + category + " category is " + listposts.size() );
        //int totalposts=listposts.size();
        List<String> listCat = postRepository.getAllCategories();
        //listposts.add(0,featuredPost);
        ValuePaginator paginator = new ValuePaginator(listposts);
        paginator.setPageSize(5);
        
        User user = Security.getConnectedUser();
        render(paginator, listCat, user, category);
    }
    
    public static void setCategory(String cat) {
    	curCategory = cat;
    	System.out.println(curCategory);
    	index();
    }
    
    public static void postComment(int postId, String author, String content) {
    	if (author != null && !author.isEmpty() && content != null && !content.isEmpty())
    		System.out.println(postId + " " + author + " " + content);
        //commentRepository.createComment( (new Date()).toString(), content, postId, author);
        index();
    }
}