package com.cliff.jms;

import com.cliff.jms.domain.User;

/**
 * User: Cliff
 */
public class DummyConfig {

    private String strVal;

    private boolean bVal;

    private User user;

    public String getStrVal() {
        return strVal;
    }

    public void setStrVal( String strVal ) {
        this.strVal = strVal;
    }

    public boolean isbVal() {
        return bVal;
    }

    public void setbVal( boolean bVal ) {
        this.bVal = bVal;
    }

    public User getUser() {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }
}
