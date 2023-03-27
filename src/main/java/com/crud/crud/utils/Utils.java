package com.crud.crud.utils;

import java.util.List;
import java.util.Random;

public class Utils {

    public static String checkDbType(String givenType) {
        if (givenType == null || givenType.trim().isEmpty()) return Constants.DB_TYPE_MY_SQL;
        if (givenType.trim().equalsIgnoreCase(Constants.DB_TYPE_MY_SQL)) return Constants.DB_TYPE_MY_SQL;
        return Constants.DB_TYPE_MONGODB;
    }

    public static boolean checkSearchKey(String searchKey) {
        if (searchKey == null || searchKey.trim().isEmpty()) return false;
        if (searchKey.equalsIgnoreCase(Constants.SEARCH_KEY_NAME) ||
                searchKey.equalsIgnoreCase(Constants.SEARCH_KEY_INSTITUTE) ||
                searchKey.equalsIgnoreCase(Constants.SEARCH_KEY_ROLL)) return true;
        return false;
    }

    public static String getRandomInstitute(List<String> institutes){
        Random rand = new Random();
        return institutes.get(rand.nextInt((institutes.size()-1)-0) + 0);
    }
}
