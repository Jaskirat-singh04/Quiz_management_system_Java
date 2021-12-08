/*
 *class to facilitate the login of user ----
        just instatiate this class to provide this facility.
        if user forgets the password,use the second constructor
  three getters are also provided for retreiving username,id and security question for further uses.
  one setter is provided to set the answer received from the user
 */
package registrationAndVerification;

import java.io.*;
import ui.DynamicDirLocator;


public class Login {

    private String username, password, fullname, ques, ans;
    private boolean forgetPass;
    public Login(String user, String pass) { //constructor for login
        forgetPass = false;
        username = user;
        password = pass;
        fullname = "";ques = "";ans = "";
    }
    public Login(String user){ //constructor when user forgets password
        forgetPass = true;
        username = user;
        password = "";fullname = "";ques = "";ans = "";
    }
    
    public boolean matchAndFetchUser() throws DetailsNotMatchedExc {
        String read_username = "",read_password = ""; //usernames,password stored from file
        boolean userfound = false,pass_match=false;   //if user found==>true,passwordmatch ==> true
        int count_ = 0; //counts no of underscores(reset after dot) --- controller for storing to strings from file
        FileInputStream open = null;
        try {
            DynamicDirLocator ob = new DynamicDirLocator();
            open = new FileInputStream(ob.getFullPath("user_details_file", 1)); //opening the file
            int i; //to store characters  
            do {
                i = open.read(); //reading characters (integral values)
               
                if (userfound == false) { //if username is not matched yet (for whole file)
                    
                    if ((char) i == '_') { 
                        count_++; //increment
                        
                        if (read_username.equals(username)==true) { //when found~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                            userfound = true;
                            
                            //next iteration in else
                        }
                        
                    } else if ((char) i == '.') {
                        count_ = 0; //reset  
                        read_username = read_username.replace(read_username,""); //emptying the string to store a new one
                    
                    } else if (count_ == 0) { //at beginngin of each line in file
                        read_username = read_username + (char) i;  //storing the username
                        
                    }
                    else { 
                        //do nothing
                    }
                }
                else{ //if username matched---after 1st underscore(at this stage , count_ = 1; (only for a single line)
                    //check password
                   
                    if((char)i == '_'){ //following underscores(after storing password)
                        count_++; //2,3,4
                        if((count_ == 2) && (read_password.equals(password)==true)) pass_match = true;  //password matched
                        if((count_ == 2) && (read_password.equals(password)==false)){
                            if(forgetPass == false) throw new DetailsNotMatchedExc(1);  //if user is try to login through invalid password
                        }  //password not matched
                        if(forgetPass == true) password = read_password;   //if forget password is used
                    }
                    else if(count_ == 1) read_password = read_password + (char)i;//starts with this condition
                    //can only be reached if password has been matched
                    else if(count_ == 2) fullname = fullname + (char)i;
                    else if(count_ == 3) ques = ques + (char)i;
                    else if( (count_ == 4) && ((char)i != '.') ) ans = ans + (char)i;
                    else{
                        return true;
                    } //terminate--------------------------------matched and fetched the details
                }
                
            } while (i != -1); //end of file
        }//try
        catch (FileNotFoundException e) {
            System.out.println("ERROR : FILE WITH USER DETAILS NOT FOUND(method: matchAndFetchUser)!!! ");
            System.exit(2001);
        } catch (IOException e) {
            System.out.println("ERROR : UNABLE TO READ THE FILE(method: matchAndFetchUser)!!!");
            System.exit(2001);
        } finally {
            try {
                if (open != null) {
                    open.close(); //file is opened ===>close
                }
            } catch (IOException e) {
                System.out.println("ERROR : UNABLE TO CLOSE FILE(method: matchAndFetchUser)!!!");
                System.exit(2001);
            }
        }
        if(userfound == false) throw new DetailsNotMatchedExc(0);
        return false;
    }

    
    
    //getters~~~~~~~~~~
    public String getUsername(){
        return username;
    }
    public String getFullname(){
        return fullname;
    }
    public String getQuestion(){
        return ques;
    }
    public String getPassword(){
        return password;
    }
    public String getAnswer(){
        return ans;
    }
    
}
