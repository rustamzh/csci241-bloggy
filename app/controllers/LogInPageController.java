package controllers;

import java.sql.Connection;

import database.Database;
import database.DatabaseJDBC;
import models.User;
import play.mvc.Controller;
import repository.UserRepository;
import repository.Impl.UserRepositoryImpl;

public class LogInPageController extends Controller {
	
	private Database database = DatabaseJDBC.getInstance();
	private Connection conn = database.getConnection();
	
	public static void loginPage() {
		if ( Security.isConnected() )
			Application.index();
		
    	String error = flash.get("error");
        render(error);
    }
	
	public static void login(String nickname, String password) {
    	
		if (nickname == null || password == null) {
    		flash.put("error", "The error occured. Try again!!!");
    		loginPage();
    	}
    	
    	if ( !Security.authenticate(nickname, password) ) {
    		flash.put("error", "The nickname or password is incorrect!!!");
    		loginPage();
    	}
    	
    	Application.index();
    }
}
