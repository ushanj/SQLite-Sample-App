package com.example.tute5_new.Database;

import android.provider.BaseColumns;

public class UsersMasters {

    public UsersMasters() {
    }

    public static class Users implements BaseColumns {
        public static  final String  TABLE_NAME="users";
        public static  final String  COLUMN_NAME_USERNAME="username";
        public static  final String  COLUMN_NAME_PASSWORD="password";
    }

}
