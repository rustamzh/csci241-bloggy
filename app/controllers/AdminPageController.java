package controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import models.Approval;
import models.Post;
import play.mvc.Controller;
import repository.ApprovalRepository;
import repository.CommentRepository;
import repository.Impl.ApprovalRepositoryImpl;
import repository.Impl.PostRepositoryImpl;
import repository.PostRepository;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static controllers.Application.approvalRepository;
import static controllers.Application.commentRepository;
import static controllers.Application.postRepository;

public class AdminPageController extends Controller{



    public static void adminPage(Post postedit){
    	if ( !Security.isConnected() || (!Security.getConnectedUser().getType().equals("admin") && !Security.getConnectedUser().getType().equals("editor")) )
    		Application.index();

        List<Approval> listappr = approvalRepository.getAllApprovalsforTable();
        List<Post> postList = postRepository.getAllPosts();
        List<String> listCat = postRepository.getAllCategories();
        String error = flash.get("error");
        String editor = "editor";
        if(Security.isConnected() && Security.getConnectedUser().getType().equals("editor")){
            postList = postRepository.getPostbyUser(Security.getConnectedUser().getNickname());
            render(listappr, postList, error, postedit, listCat, editor);
        }
        render(listappr, postList, error, postedit, listCat);
    }

    public static void approveAll(){

        approvalRepository.deleteAllApprovals();
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

    public static void save(String title, String content, String categories, Integer postid){
        //Date date,String title, String body, String category, User user

        if(title==null||title.isEmpty() || content==null || content.isEmpty()){
            flash.put("error", "Title and body cannot be empty");
            adminPage(null);

        }
        if(postid!=null){
            Post post = postRepository.getPost(postid);
            post.setTitle(title);
            post.setBody(content);
            post.setCategory(categories);
            post.setDate(new Date());
            postRepository.updatePost(postid, post);
        }
        else {
            System.out.println(title);
            System.out.println(content);
            System.out.println(categories);
            System.out.println(Security.getConnectedUser().getNickname());
            postRepository.createPost(new Date(), title, content, categories, Security.getConnectedUser().getNickname());
        }
        AdminPageController.adminPage(null);
    }

    public static void edithelp(String title, String content, String cat){
        //Date date,String title, String body, String category, User user
        String param = session.get("editID");
        System.out.println(param);
        Post post=postRepository.getPost(Integer.parseInt(param));
        save(title,content,cat,post.getPostId());
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
    
    public static void approveSelected(List<String> selectedTerms) {
        if(selectedTerms!=null && !selectedTerms.isEmpty()){
            selectedTerms.forEach((String s) -> approvalRepository.deleteApproval(Integer.parseInt(s)));
        }
        AdminPageController.adminPage(null);
    }
    public static void deleteApproval(Integer approvalid, Integer commentId){
        approvalRepository.deleteApproval(approvalid);
        commentRepository.deleteComment(commentId);
        AdminPageController.adminPage(null);
    }
}
