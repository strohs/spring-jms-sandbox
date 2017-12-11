package com.cliff.jms.domain;

import java.io.Serializable;

/**
 * User domain object that will be converted into a message and placed onto queues
 * User: Cliff
 */
public class User implements Serializable {

    private String firstName;

    private String lastName;

    private int age;

    public User( String firstName, String lastName, Integer age ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder( "User{" );
        sb.append( "firstName='" ).append( firstName ).append( '\'' );
        sb.append( ", lastName='" ).append( lastName ).append( '\'' );
        sb.append( ", age=" ).append( age );
        sb.append( '}' );
        return sb.toString();
    }
}
