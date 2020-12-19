package com.aasshh.user.utils;

public class Server {
    static final String ROOT_URL = "http://ec2-18-216-7-14.us-east-2.compute.amazonaws.com:1111/";
    public static final String LOGIN = ROOT_URL + "customer/login";
    public static final String SIGN_UP_GET_STARTED = ROOT_URL + "customer/getStarted";
    public static final String SIGN_UP = ROOT_URL + "customer/signup";
    public static final String VERIFY_OTP = ROOT_URL + "customer/verifyOtp";
    public static final String FORGET_PSW = ROOT_URL + "customer/forgotPassword";


    //Product related
    public static final String GET_PROD_CATEGORY = ROOT_URL + "product/getCategories";

}
