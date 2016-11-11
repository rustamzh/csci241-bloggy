package controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import models.Approval;
import models.Post;
import models.User;
import play.mvc.Controller;
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


    public static void adminPage(){
        List<Approval> listappr = repo.getAllApprovalsforTable();
        List<Post> postList = postRepository.getAllPosts();
        render(listappr, postList);
    }
    public static void approveAll(){

        repo.deleteAllApprovals();
        AdminPageController.adminPage();
    }

    public static void save(String title, String content){
        //Date date,String title, String body, String category, User user
        /*Logger.debug("A log message "+content);
        System.out.println("A log message "+content);
*/
        postRepository.createPost(new Date(), title, content, "Uncategorized", new User("head_admin", "123","admin", "Rustam Zhumagambetov"));
        AdminPageController.adminPage();
    }
    public static void uploadPhoto(String title, File photo) {
        Cloudinary cloudinary = new Cloudinary("CLOUDINARY_URL=cloudinary://297275137474391:2Dh-cCwMIig131jx9Vb-2r6sSMI@ruszh");
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(photo, ObjectUtils.emptyMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String uploadURL= (String)uploadResult.get("url");
        renderJSON("{\"error\":false,\"path\":\""+uploadURL+"\"}");// or {"error":"filetype"} or {"error":"unknown"}
    }
}
