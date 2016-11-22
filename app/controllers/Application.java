package controllers;

import models.Comment;
import models.Post;
import models.User;
import models.UserComment;
import play.Play;
import play.modules.paginate.ValuePaginator;
import play.mvc.Before;
import play.mvc.Controller;
import repository.ApprovalRepository;
import repository.CommentRepository;
import repository.PostRepository;
import repository.UserRepository;
import repository.Impl.ApprovalTestingRepositoryImpl;
import repository.Impl.CommentRepositoryImpl;
import repository.Impl.PostRepositoryImpl;
import repository.Impl.UserRepositoryImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static controllers.AdminPageController.postRepository;

public class Application extends Controller {
	
	static ApprovalRepository repo=new ApprovalTestingRepositoryImpl();
    static PostRepository postRepository = new PostRepositoryImpl();
    static CommentRepository commentRepository = new CommentRepositoryImpl();
    static UserRepository userRepository = new UserRepositoryImpl();
    
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
        
        String error = flash.get("error");
        flash.remove("error");
        
        render(paginator, listCat, user, category, error);
    }
    
    public static void articlePage(int postId) {
    	
    	Post post = postRepository.getPost(postId);
    	String category = curCategory;
    	List<String> listCat = postRepository.getAllCategories();
    	User user = Security.getConnectedUser();
    	String error = flash.get("error");
        flash.remove("error");
    	
        List<Comment> commentList = commentRepository.getAllComments(postId);
        List<UserComment> userCommentList = new ArrayList();
        
        for ( Comment comment: commentList ) {
        	User commentUser = userRepository.getUser( comment.getUser_nickname() );
        	UserComment userComment = new UserComment(commentUser.getName(), commentUser.getNickname(), commentUser.getAvatar(), comment);
        	userCommentList.add( userComment );
        }
        
        render(post, listCat, user, category, error, userCommentList);
    }
    
    public static void setCategory(String cat) {
    	curCategory = cat;
    	System.out.println(curCategory);
    	index();
    }
    
    public static void postComment(int postId, String content) {
    	if (content != null && !content.isEmpty()) {
    		String userNickname = Security.getConnectedUser().getNickname();
    		
    		System.out.println(postId + " " + userNickname + " " + content);
    		
    		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    		LocalDateTime now = LocalDateTime.now();
    		
    		if ( !commentRepository.createComment( dtf.format(now).toString(), content, postId, userNickname ) )
    			flash.put("error", "The error uploading comment occured");
        } else {
        	flash.put("error", "Some error with fields occured");
        }
        articlePage(postId);
    }

    public static void Search(String s){
        List<Post> listposts = postRepository.seacrhPost(s);
        ValuePaginator paginator = new ValuePaginator(listposts);
        paginator.setPageSize(5);
        List<String> listCat = postRepository.getAllCategories();
        render(paginator, listCat, s);
    }
}