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
public class UsersDTO implements Serializable {//khi nào mình xài DTO: method search của mình là trả ra nhiều record, bản chất 1 record của mình map lên trên bộ nhớ chính là 1 DTO, DTO ko phải mapping chính xác tất cả mọi cột dưới table, DTO k cho allow null

    private String username;
    private String password;
    private String fullname;
    private boolean role;

    public UsersDTO() {
    }

    public UsersDTO(String username, String password, String fullname, boolean role) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
    }

    //RightClick > Reafactor > Encapsulate field > Select All để nó auto gen getter setter
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the role
     */
    public boolean isRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(boolean role) {
        this.role = role;
    }

}
