package com.tms.taskManagementSystem.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class IsAuthorized {

    public static String getAuthenticatedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                return principal.toString();
            }
        }
        return null;
    }

    public static boolean isAuthorized(String username) {
        String authenticatedUsername = getAuthenticatedUsername();
        return authenticatedUsername != null && authenticatedUsername.equals(username);
    }
}
