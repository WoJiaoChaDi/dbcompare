package com.chadi.factory;

/**
 * @ClassName SimpleDataSource
 * @Description TODO
 * @Author XuDong
 * @Date 2021/1/18 16:04
 * @Version 1.0
 **/
public class SimpleDataSource {

    String username;
    String password;
    String url;
    String driver;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}
