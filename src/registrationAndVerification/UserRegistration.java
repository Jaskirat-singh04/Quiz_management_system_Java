/* class to provide facilities of registering a user within the program.
    this class handles everything related to registering a user---
    only have to instatiate it using its constructor.
 */
package registrationAndVerification;

import java.io.*;
import ui.DynamicDirLocator;

public class UserRegistration {

    private String username, fullname, password;         //fullname = first_name surname;
    private String ques, ans; //for password recovery

    public UserRegistration(String user, String pass, String first_name, String surname, String q, String a) {
        username = user;
        password = pass;
        fullname = first_name + " " + surname;
        ques = q;
        ans = a;
    }
    //checking whether username,password,fullname,question and answer fulfill the required conditions

    /*     for username : 20 words limit,no numerical or special chars except @,no spaces
	   for password : must be of 4-6 numerical or alphabetical chars only.
	   for fullname : max 20 alphabetical chars,with a space after first_name.
           for question : underscore not allowed.
           for answer   : underscore not allowed.
     */
//--------------------------------PUBLIC METHODS-----------------------------------------------	
    public boolean check_username() throws InvalidWordLimitExc, InvalidCharExc, UsernameExistsExc {
        if (username.length() > 20) {
            throw new InvalidWordLimitExc(0); //wordLimit
        }        //check for characters
        for (int i = 0; i < username.length(); i++) {
            if (!isChar(username.charAt(i))) { //if not alphabetical char
                if (username.charAt(i) != '@') { //if not @
                    throw new InvalidCharExc(0); //invalid character combination
                }
            }
        }
        if (existing_username() == true) {
            throw new UsernameExistsExc(0); //existing user found
        }
        return true; //valid
    }

    public boolean check_password() throws InvalidWordLimitExc, InvalidCharExc {
        if ((password.length() < 4) || (password.length() > 6)) {
            throw new InvalidWordLimitExc(1); //charcter limit
        }        //check for characters
        for (int i = 0; i < password.length(); i++) {  //alphabetical/numerical chars only
            if ((isNum(password.charAt(i)) == false) && (isChar(password.charAt(i))) == false) {
                throw new InvalidCharExc(1); //neither char nor numerical
            }
        }
        return true; //valid
    }

    public boolean check_fullname() throws InvalidWordLimitExc, InvalidCharExc {
        if (fullname.length() > 20) {
            throw new InvalidWordLimitExc(3); //char limit
        }        //check for characters
        int spaceCount = -1; //to check only one space
        for (int i = 0; i < fullname.length(); i++) {
            if(fullname.charAt(i) == ' ') spaceCount++;
            if (isChar(fullname.charAt(i)) == false) { //if not character
                if (fullname.charAt(i) != ' ') throw new InvalidCharExc(2); //if not space
                if ( (fullname.charAt(i) == ' ') && (spaceCount != 0)) throw new InvalidCharExc(2); //not the only space
            }
        }
        return true; //valid
    }
    
    public boolean check_que_ans() throws UnderScore_Exc{
        for(int i=0;i<ques.length();i++){
            if(ques.charAt(i) == '_') throw new UnderScore_Exc(0);
        }
        for(int i=0;i<ans.length();i++){
            if(ans.charAt(i) == '_') throw new UnderScore_Exc(1);
        }
        return true;
    }
    //~~~~~~~~~~~~~~ONLY CALLED IN THE THREE METHODS ABOVE~~~~~~~~~~~~~~~~~~~

    private boolean isNum(char x) {
        String num = "0123456789";
        for (int i = 0; i < 10; i++) {
            if (x == num.charAt(i)) {
                return true;   //IS NUMBER
            }
        }
        return false;
    }

    private boolean isChar(char x) {
        String s_char = "abcdefghijklmnopqrstuvwxyz";
        String l_char = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < 26; i++) {
            if ((x == s_char.charAt(i)) || (x == l_char.charAt(i))) {
                return true; //IS ALPHABETICAL CHARACTER
            }
        }
        return false;
    }

    private boolean existing_username() {  //true = already exists
        DynamicDirLocator ob = new DynamicDirLocator();
        String read_username = "";
        boolean store = true;   //false => underscore after a dot encounterd.  true => encountering a dot.
        FileInputStream open = null;
        try {
            open = new FileInputStream(ob.getFullPath("user_details_file", 1)); //opening the file-FileNotFoundException
            int i; //to store characters  
            do {
                i = open.read(); //reading characters (integral values)
                if ((char) i == '_') {
                    store = false;
                    if (read_username.equals(username)) {
                        return true;   //terminate----found----------------
                    }
                    read_username = read_username.replace(read_username,"");    //to reset the string
                } else if ((char) i == '.') {
                    store = true;
                } else if (store == true) {
                    read_username = read_username + (char) i;  //storing the username
                }
            } while (i != -1); //end of file
        }//try
        catch (FileNotFoundException e) {
            System.out.println("ERROR : FILE WITH USER DETAILS NOT FOUND(method: existing_username)!!! ");
            System.exit(2001);
        } catch (IOException e) {
            System.out.println("ERROR : UNABLE TO READ THE FILE(method: existing_username)!!!");
            System.exit(2001);
        } finally {
            try {
                if (open != null) {
                    open.close(); //file is opened ===>close
                }
            } catch (IOException e) {
                System.out.println("ERROR : UNABLE TO CLOSE FILE(method: existing_username)!!!");
                System.exit(2001);
            }
        }
        return false; //not matched
    }
//file format :- username_pass_fullname_ques_ans.

    public void registerUser() {  //registers the user==> saves the details
        DynamicDirLocator ob = new DynamicDirLocator();
        FileOutputStream open = null;
        String format = username + "_" + password + "_" + fullname + "_" + ques + "_" + ans + "."; //string to append
        try {
            open = new FileOutputStream(ob.getFullPath("user_details_file",1), true); //opening the file(appending)-FileNotFoundException
            for (int i = 0; i < format.length(); i++) {
                open.write(format.charAt(i));
            }
        }//try
        catch (FileNotFoundException e) {
            System.out.println("ERROR : FILE WITH USER DETAILS NOT FOUND(method:registerUser)!!! ");
            System.out.println("ERROR : USER NOT REGISTERED");
            System.exit(2001);
        } catch (IOException e) {
            System.out.println("ERROR : UNABLE TO WRITE TO THE FILE(method:registerUser)!!!");
            System.out.println("ERROR : USER NOT REGISTERED");
            System.exit(2001);
        } finally {
            try {
                if (open != null) {
                    open.close(); //file is opened ===>close
                }
            } catch (IOException e) {
                System.out.println("ERROR : UNABLE TO CLOSE FILE(method:registerUser)!!!");
                System.exit(2001);
            }
            
        }
    }
}
