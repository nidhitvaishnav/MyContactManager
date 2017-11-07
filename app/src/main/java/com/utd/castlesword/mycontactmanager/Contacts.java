package com.utd.castlesword.mycontactmanager;

/**assignment of subject 6326 Human computer interaction
 * Designed by: Nidhi Vaishnav (ntv170030)**/

public class Contacts {
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String mailId;

    //Contact class with its getters and setters
    public Contacts(String inFirstName, String inLastName, String inContactNumber, String inMailId){
        firstName = inFirstName;
        lastName = inLastName;
        contactNumber = inContactNumber;
        mailId = inMailId;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setContactNumber(String contactNumber){
        this.contactNumber = contactNumber;
    }
    public String getContactNumber(){
        return contactNumber;
    }
    public void setMailId(String mailId){
        this.mailId = mailId;
    }
    public String getMailId(){
        return mailId;
    }
}
