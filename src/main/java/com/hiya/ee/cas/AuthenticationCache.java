package com.hiya.ee.cas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthenticationCache 
{
       private static List<Token>  authenticationCache = new ArrayList <>();
       private static Map<String,String>  authenticationDatabase = new HashMap<>();

       static
       {
           authenticationDatabase.put("wade", "1001!");
           authenticationDatabase.put("james", "1002!");
           authenticationDatabase.put("mcgrady", "1003!");
           authenticationDatabase.put("allen", "1004!");
       }

    public static List<Token> getAuthenticationCache()
    {
        return authenticationCache;
    }

    public static void setAuthenticationCache(List<Token> authenticationCache)
    {
        AuthenticationCache.authenticationCache = authenticationCache;
    }

    public static Map<String, String> getAuthenticationDatabase()
    {
        return authenticationDatabase;
    }

    public static void setAuthenticationDatabase(Map<String, String> authenticationDatabase)
    {
        AuthenticationCache.authenticationDatabase = authenticationDatabase;
    }
}