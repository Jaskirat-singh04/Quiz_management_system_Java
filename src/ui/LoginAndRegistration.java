
package ui;

import registrationAndVerification.DetailsNotMatchedExc;
import registrationAndVerification.UserException;
import registrationAndVerification.Login;
import registrationAndVerification.UserRegistration;

/*

-------- use -----
LoginAndRegistration.userid and LoginAndRegistration.fullname to assign to a string
 */
public class LoginAndRegistration {

    public static String store_exc = ""; //string to store exceptions
    public static String ques = "", pass = "";  //to show recovery question and password to user
    public static String ans = ""; //use this in mainUi to match the answer entered by the user
    public static String userid ="",fullname=""; //

    public static boolean registration(String username, String password, String first_name, String surname, String sec_ques, String ans) {
        UserRegistration ob = new UserRegistration(username, password, first_name, surname, sec_ques, ans);
        try {
            if ((ob.check_username() == true) && (ob.check_fullname() == true) && (ob.check_password() == true) && (ob.check_que_ans() == true)) {
                ob.registerUser(); //if everything fine,register the user---ie. save the details
                return true;
            }
        } catch (UserException e) {
            store_exc = store_exc.replace(store_exc, e.toString());
        }
        return false;
    }

    public static boolean login(String username, String password) {
        Login ob = new Login(username, password);
        try {
            if (ob.matchAndFetchUser() == true) { //after successful login,,,
                userid = userid.replace(userid,ob.getUsername());
                fullname = fullname.replace(fullname,ob.getFullname());
                return true;
            }
        } catch (UserException e) {
            store_exc = store_exc.replace(store_exc, e.toString());
        }
        return false;
    }
//!~~~~~~~~~~~~!
    public static boolean getRecoveryQues(String username) {
        Login ob = new Login(username); //instatiation
        try {
            if (ob.matchAndFetchUser() == true) {
                ques = ques.replace(ques, ob.getQuestion());  //this string stores question
                ans = ans.replace(ans, ob.getAnswer());
                pass = pass.replace(pass, ob.getPassword());
                return true;
            }
        } catch (UserException e) {
            store_exc = store_exc.replace(store_exc, e.toString());
        }
        return false;
    }

    public static boolean getRecoveryPassword(String str){
        try {
            if (ans.equals(str) == false) {
                throw new DetailsNotMatchedExc(2);
            } else {
                return true;
            }
        } catch (UserException e) {
            store_exc = store_exc.replace(store_exc, e.toString());
            return false;
        }
    }
}
