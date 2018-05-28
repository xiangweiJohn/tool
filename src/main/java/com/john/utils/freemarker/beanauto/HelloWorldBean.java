package com.john.utils.freemarker.beanauto;

public class HelloWorldBean {

    //属性
    private String attribute_a;

    private Integer attribute_b;

    private Object attribute_c;

    //和前端传来的String字符串产生联系
    private String HelloWorld;

    //set,get方法
    public void setAttribute_a(String attribute_a) {

        this.attribute_a = attribute_a;
    }

    public void setAttribute_b(Integer attribute_b) {

        this.attribute_b = attribute_b;
    }

    public void setAttribute_c(Object attribute_c) {

        this.attribute_c = attribute_c;
    }

    public void setHelloWorld(String HelloWorld) {

        this.HelloWorld = HelloWorld;
    }

    public String getAttribute_a() {

        return attribute_a;
    }

    public Integer getAttribute_b() {

         return attribute_b;
    }

    public Object getAttribute_c() {

        return attribute_c;
    }

    public String getHelloWorld() {

        return HelloWorld;
    }

    @Override
    public String toString() {
        return "HelloWorldBean [" +
        "attribute_a='" + attribute_a + '\'' +
        ", attribute_b='" + attribute_b + '\'' +
        ", attribute_c='" + attribute_c + '\'' +
        ", HelloWorld=" + HelloWorld +
        ']';
    }


}