package registrationAndVerification;

/* types of exceptions-
	- username/password not under specified conditions.
	- invalid username,password,recovery_ans
	- username already exists
 */
public class UserException extends Exception {

    int exc_type;   //type of exception -- to print required message.

    UserException(int a) {
        exc_type = a;
    }

    public String toString() {
        return "ERROR : INVALID USERNAME/PASSWORD!!!";
    }
}

class InvalidWordLimitExc extends UserException {           //0-username,1-pass(4-6),3-fullname

    InvalidWordLimitExc(int a) {
        super(a);
    }

    public String toString() {
        if (exc_type == 0) {
            return "ERROR : maximum permissible characters in username is 20 !!!";
        } else if (exc_type == 1) {
            return "ERROR : password must have 4-6 characters !!!";
        } else {
            return "ERROR : maximum permissible characters in fullname is 20 !!!";
        }
    }
}

class InvalidCharExc extends UserException {           //0-username,1-pass,2-fullname

    InvalidCharExc(int a) {
        super(a);
    }

    public String toString() {
        if (exc_type == 0) {
            return "ERROR: only alphabetical characters and @ are allowed in username !!!";
        } else if (exc_type == 1) {
            return "ERROR: only aplha-numerical characters allowed in password!!!";
        } else {
            return "ERROR: only alphabetical characters are allowed in fullname !!!";
        }
    }
}

class UsernameExistsExc extends UserException {  //if user name already exists

    UsernameExistsExc(int a) {
        super(a);
    }

    public String toString() {
        return "ERROR : username already exists!!!";
    }
}

class UnderScore_Exc extends UserException {  //if user name already exists

    UnderScore_Exc(int a) {
        super(a);
    }

    public String toString() {
        if (exc_type == 0) {
            return "ERROR : QUESTION MUST NOT HAVE UNDERSCORES('_')!!!";
        } else {
            return "ERROR : ANSWER MUST NOT HAVE UNDERSCORES('_')!!!";
        }
    }
}
