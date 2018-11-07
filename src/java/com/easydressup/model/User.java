package com.easydressup.model;

import java.io.Serializable;

/**
 * User model class
 *
 * @author
 */
public class User implements Serializable{

    private int userId;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String role;

    /**
     * Get user id
     *
     * @return User id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Set user id
     *
     * @param userId - User id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Set user name
     *
     * @return User name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set user name
     *
     * @param userName - User name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get the password
     *
     * @return user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password
     *
     * @param password - User password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the first name
     *
     * @return First name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the first name
     *
     * @param firstName - First name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the last name
     *
     * @return Last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the last name
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the role
     *
     * @return User role
     */
    public String getRole() {
        return role;
    }

    /**
     * Set the role
     *
     * @param role - Role
     */
    public void setRole(String role) {
        this.role = role;
    }

}
