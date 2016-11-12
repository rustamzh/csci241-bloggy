package controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import models.Approval;
import models.Post;
import models.User;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Router;
import repository.ApprovalRepository;
import repository.Impl.ApprovalTestingRepositoryImpl;
import repository.Impl.PostTestingImpl;
import repository.PostRepository;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class AdminPageController extends Controller{
    static ApprovalRepository repo=new ApprovalTestingRepositoryImpl();
    static PostRepository postRepository = new PostTestingImpl();


    public static void adminPage(Post postedit){
        List<Approval> listappr = repo.getAllApprovalsforTable();
        List<Post> postList = postRepository.getAllPosts();
        String error = flash.get("error");
        render(listappr, postList, error, postedit);
    }

    public static void approveAll(){

        repo.deleteAllApprovals();
        AdminPageController.adminPage(null);
    }

    public static void deleteAll(){
        postRepository.deleteAllPosts();
        AdminPageController.adminPage(null);
    }
    public static void delete(int postid){
        postRepository.deletePost(postid);
        AdminPageController.adminPage(null);
    }

    public static void save(String title, String content, int postid){
        //Date date,String title, String body, String category, User user
        if(title==null||title.isEmpty() || content==null || content.isEmpty()){
            flash.put("error", "Title and body cannot be empty");

        }
        if(new Integer(postid)!=null){
            Post post = postRepository.getPost(postid);
            post.setTitle(title);
            post.setBody(content);
            post.setDate(new Date());
            postRepository.updatePost(postid, post);
        }
        else {
            postRepository.createPost(new Date(), title, content, "Uncategorized", new User("head_admin", "123", "admin", "Rustam Zhumagambetov"));
        }
        AdminPageController.adminPage(null);
    }

    public static void edithelp(String title, String content){
        //Date date,String title, String body, String category, User user
        String param = flash.get("editID");

        Post post=postRepository.getPost(Integer.parseInt(param));
        save(title,content,post.getPostId());
    }
    public static void edit(int postid){
        //Date date,String title, String body, String category, User user
        Post postedit;
        postedit=postRepository.getPost(postid);
        AdminPageController.adminPage(postedit);
    }
    public static void uploadPhoto(File file) {
        Cloudinary cloudinary = new Cloudinary("cloudinary://297275137474391:2Dh-cCwMIig131jx9Vb-2r6sSMI@ruszh");
        if(file==null){
            System.out.println("NULLLLLLLLLLLl");
            renderJSON("{\"error\":false,\"path\":\""+/*uploadURL+*/"\"}");// or {"error":"filetype"} or {"error":"unknown"}
        }
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());

        } catch (IOException e) {
            e.printStackTrace();
        }
        String uploadURL= (String)uploadResult.get("secure_url");
        System.out.println(uploadURL);
        renderJSON("{\"error\":false,\"path\":\""+uploadURL+"\"}");// or {"error":"filetype"} or {"error":"unknown"}
    }
}
