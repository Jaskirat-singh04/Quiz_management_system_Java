
package registrationAndVerification;


public class DetailsNotMatchedExc extends UserException {    //0-username,1-password,2-recovery_ans

    public DetailsNotMatchedExc(int a) {
        super(a);
    }

    public String toString() {
        if (exc_type == 0) {
            return "ERROR : username does not exists !!!";
        } else if (exc_type == 1) {
            return "ERROR : wrong password !!!";
        } else {
            return "ERROR : incorrect answer!!!";
        }
    }
}
