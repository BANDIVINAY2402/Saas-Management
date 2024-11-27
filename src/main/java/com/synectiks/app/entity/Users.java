package com.synectiks.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userlogindetails") // MongoDB collection name
public class Users {

    @Id
    private String userid; // Use String for MongoDB's default ObjectId

    private String username;
    private String firstname;
    private String lastname;

//    @javax.validation.constraints.Email // Ensure a valid email format
    private String email;

    private String password; // Hash before storing
    private String otp; // Optional: For temporary one-time password (if needed)

    // Getters and Setters
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "Users [userid=" + userid + ", username=" + username + ", firstname=" + firstname + ", lastname="
                + lastname + ", email=" + email + ", password=" + password + ", otp=" + otp + "]";
    }
}
