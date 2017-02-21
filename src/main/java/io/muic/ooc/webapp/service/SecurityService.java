/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.webapp.service;

import com.ja.security.PasswordHash;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;


public class SecurityService {
    
//    private Map<String, String> userCredentials = new HashMap<String, String>() {{
//        put("admin", "123456");
//        put("muic", "1111");
//    }};

//    private DatabaseService databaseService;

    public boolean isAuthorized(HttpServletRequest request) {

        Map<String, String> userCredentials = new DatabaseService().readData();

        String username = (String) request.getSession()
                .getAttribute("username");
        // do checking
       return (username != null && userCredentials.containsKey(username));
    }

    public String hashPassword(String password){

        try {
            String passwordHash = new PasswordHash().createHash(password);
//            System.out.println(new PasswordHash().validatePassword("boww",passwordHash));
            return passwordHash;
        } catch (Exception e) {
            return null;
        }

    }

    public boolean isCorrect(String pw, String hash_pw){
        try {
            return new PasswordHash().validatePassword(pw,hash_pw);
//            return StringUtils.equals(pw, hash_pw);
        } catch (Exception e){
            return false;
        }
    }


    public boolean authenticate(String username, String password, HttpServletRequest request) {

        Map<String, String> userCredentials = new DatabaseService().readData();

//        String hashedPw = hashPassword(password);
        String passwordInDB = userCredentials.get(username);

//        boolean isMatched = StringUtils.equals(password, passwordInDB);
//        boolean isMatched = isCorrect(password,hashedPw);

        System.out.println(isCorrect(password,passwordInDB));
        System.out.println(passwordInDB);
        System.out.println(password);
        System.out.println(username);

        if (isCorrect(password,passwordInDB)) {
            request.getSession().setAttribute("username", username);
            return true;
        } else {
            return false;
        }
    }

    public boolean allowAdd(String username) {

        Map<String, String> userCredentials = new DatabaseService().readData();

        ArrayList<String> allUsers = new ArrayList<>();
        for (Map.Entry<String, String> entry: userCredentials.entrySet()){
            allUsers.add(entry.getKey());
        }

        return !(allUsers.contains(username));
    }
    
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    public static void main(String[] args) {
        SecurityService se = new SecurityService();
        String temp = "20000:4a2dbee0b6a94779ef2a1d4801405660f83a06effe0a8dbd:4b54da3d5af75011a12f3065cc5510214978f4f936515f72";
//        System.out.println(se.authenticate("boww",temp));
    }
}
