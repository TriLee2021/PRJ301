/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trilm.users;

import java.io.Serializable;

/**
 *
 * @author minht
 */
public class UsersCreateError implements Serializable{
    //các field ko đc là public 
    private String usernameLengthError;
    private String passwordLengthError;
    private String fullnameLengthError;
    private String confirmLengthError;
    private String usernameIsExistedError;

    public UsersCreateError() {
    }

    /**
     * @return the usernameLengthError
     */
    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    /**
     * @param usernameLengthError the usernameLengthError to set
     */
    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    /**
     * @return the passwordLengthError
     */
    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    /**
     * @param passwordLengthError the passwordLengthError to set
     */
    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    /**
     * @return the fullnameLengthError
     */
    public String getFullnameLengthError() {
        return fullnameLengthError;
    }

    /**
     * @param fullnameLengthError the fullnameLengthError to set
     */
    public void setFullnameLengthError(String fullnameLengthError) {
        this.fullnameLengthError = fullnameLengthError;
    }

    /**
     * @return the confirmLengthError
     */
    public String getConfirmLengthError() {
        return confirmLengthError;
    }

    /**
     * @param confirmLengthError the confirmLengthError to set
     */
    public void setConfirmLengthError(String confirmLengthError) {
        this.confirmLengthError = confirmLengthError;
    }

    /**
     * @return the usernameIsExistedError
     */
    public String getUsernameIsExistedError() {
        return usernameIsExistedError;
    }

    /**
     * @param usernameIsExistedError the usernameIsExistedError to set
     */
    public void setUsernameIsExistedError(String usernameIsExistedError) {
        this.usernameIsExistedError = usernameIsExistedError;
    }
    
    

    //RightClick > Reafactor > Encapsulate field > Select All để nó auto gen getter setter
    /**
     * @return the usernameLengthError
     */
    
    
    
}
