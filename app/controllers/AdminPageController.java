package controllers;

import models.Approval;
import models.Post;
import models.User;
import org.apache.commons.mail.HtmlEmail;
import play.libs.Mail;
import play.mvc.Controller;
import repository.ApprovalRepository;
import repository.Impl.ApprovalTestingRepositoryImpl;
import repository.Impl.PostTestingImpl;
import repository.PostRepository;

import java.io.File;
import java.util.Date;
import java.util.List;


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

        //For logging only
        try{
        HtmlEmail email = new HtmlEmail();
        email.setFrom("me@hello.com");
        email.addTo("me@hello.com");
        email.setSubject("subject");
        //This is the default text for clients that do not support HTML Message
        email.setTextMsg("Not null  " + title + "   ");
        email.setHtmlMsg("Not null  " + title + "   "+photo.getName());
        //Play.libs.Mail
        Mail.send(email);}
        catch (Exception e){

        }
        //For logging only

        //System.out.println("Not null  " + title + "   ");
        System.out.println("Not null  " + photo==null+ "   ");
        //Logger.debug("Not null  " + title);
        renderJSON("{\"error\":false,\"path\":\"http://www.mydomain.com/myimage.jpg\"}");// or {"error":"filetype"} or {"error":"unknown"}
    }
}
