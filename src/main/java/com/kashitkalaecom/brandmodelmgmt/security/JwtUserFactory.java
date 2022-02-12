package com.kashitkalaecom.brandmodelmgmt.security;


public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(JwtTokenRequest user) {
        return new JwtUser(user);
    }
    
}
