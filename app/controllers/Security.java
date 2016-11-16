package controllers;
 
import models.*;
import repository.UserRepository;
import repository.Impl.UserRepositoryImpl;
 
public class Security extends Secure.Security {
	
    static boolean authenticate(String nickname, String password) {
    	
    	if (nickname == null || password == null)
    		return false;
    	
    	UserRepository ur = new UserRepositoryImpl();
    	User user = ur.getUser(nickname);
    	
    	if ( user == null || !user.getPassword().equals(password) ) {
    		return false;
    	} else {
    		session.put( "nickname", user.getNickname() );
    		return true;
    	}
    }
    
    static User getConnectedUser() {
    	if ( session.get( "nickname" ) == null )
    		return null;
    	
    	UserRepository ur = new UserRepositoryImpl();
    	return ur.getUser( session.get("nickname") );
    }
    
    static boolean isConnected() {
    	return session.contains( "nickname" );
    }
    
    static void logout() {
    	session.clear();
    	onDisconnected();
    }
    
    static void onDisconnected() {
        Application.index();
    }
}	