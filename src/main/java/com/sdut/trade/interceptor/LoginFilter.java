/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.sdut.trade.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 类描述：登陆拦截器
 *
 * @author liuzixiang[422206217@qq.com]
 * @date 2018/3/9
 */
public class LoginFilter implements Filter {


    public static List<String> pattenURL = new ArrayList<String>();

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        // 登陆url
        String loginUrl = httpRequest.getContextPath() + "/login.html";
        String url = httpRequest.getRequestURI().toString();

        /*
         * 注：在pattenURL中的全部不拦截
         * url.indexOf(urlStr) > -1  表示urlStr在url中出现过，出现就不拦截
         * */
        for (String urlStr : pattenURL) {
            if(url.indexOf(urlStr) > -1){
                chain.doFilter(request, response);
                return;
            }
        }

        /*
         * 超时处理，ajax请求超时设置超时状态，页面请求超时则返回提示并重定向
         * session.getAttribute("")是获取到登录人的session信息
         * */
        if (session.getAttribute("") == null) {
            // 判断是否为ajax请求
            if (httpRequest.getHeader("x-requested-with") != null
                    && httpRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                httpResponse.addHeader("sessionstatus", "timeOut"); //返回超时标识
                httpResponse.addHeader("loginPath", loginUrl);// 返回url
                chain.doFilter(request, response);// 不可少，否则请求会出错
            } else {
                //alert('会话过期,请重新登录');
                String str = "<script language='javascript'>"
                        + "window.top.location.href='"
                        + loginUrl
                        + "';</script>";
                response.setContentType("text/html;charset=UTF-8");// 解决中文乱码
                try {
                    PrintWriter writer = response.getWriter();
                    writer.write(str);
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    /*
     * 在pattenURL中的全部不拦截，所以上面会使用：path.indexOf(urlStr) > -1
     * */
    @Override
    public void init(FilterConfig arg0) throws ServletException {
        pattenURL.add("login.html");//登录jsp
        pattenURL.add("login.do");//登录方法
        pattenURL.add("css");//css
        pattenURL.add("image");//image
        pattenURL.add("js");//js
        pattenURL.add("fonts");//fonts
        pattenURL.add("png");
    }
}
