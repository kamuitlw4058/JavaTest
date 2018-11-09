package com.kimi.java.test.class_loader;

import java.net.URL;

public class main {
    public static void main(String args[])
    {
        System.out.println("hello");
        URL url = main.class.getClassLoader().getResource("");
        System.out.println(url);

    }
}
