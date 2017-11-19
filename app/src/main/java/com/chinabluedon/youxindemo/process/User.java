package com.chinabluedon.youxindemo.process;

import java.io.Serializable;

/**
 * @author 胡腾
 * @time 2017/11/12  20:13
 * @desc ${TODD}
 */
public class User implements Serializable {


    private static final long serialVersionUID = -4204794478197318202L;

    public String name;

    public int age;

    public boolean isMale;

    public User(String name, int age, boolean isMale) {
        this.name = name;
        this.age = age;
        this.isMale = isMale;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isMale=" + isMale +
                '}';
    }
}
