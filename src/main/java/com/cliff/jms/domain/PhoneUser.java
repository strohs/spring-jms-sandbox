package com.cliff.jms.domain;

import java.io.Serializable;

/**
 * Another type of user that's not currently being used
 * User: Cliff
 */
public class PhoneUser implements Serializable {

    private String firstName;

    private String lastName;

    private int age;

    private String phoneNumber;

    public PhoneUser() {
    }

    public PhoneUser( String firstName, String lastName, int age, String phoneNumber ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge( int age ) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber( String phoneNumber ) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder( "PhoneUser{" );
        sb.append( "firstName='" ).append( firstName ).append( '\'' );
        sb.append( ", lastName='" ).append( lastName ).append( '\'' );
        sb.append( ", age=" ).append( age );
        sb.append( ", phoneNumber='" ).append( phoneNumber ).append( '\'' );
        sb.append( '}' );
        return sb.toString();
    }
}
