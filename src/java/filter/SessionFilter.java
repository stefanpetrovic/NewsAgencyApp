/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package filter;

import java.io.IOException;
import java.util.Enumeration;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.UrednikBean;
import domen.Urednik;

/**
 *
 * @author stefan
 */
public class SessionFilter implements javax.servlet.Filter{
    
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String loginURL = ((HttpServletRequest)request).getContextPath()+ "/login.jsf";
        UrednikBean urednik =(UrednikBean) ((HttpServletRequest) request).getSession().getAttribute("urednikBean");
        if (urednik == null || urednik.getUrednik() == null) {
           ((HttpServletResponse) response).sendRedirect(loginURL);
           return;
        }else {
            chain.doFilter(request, response);
            return;
        }
    }

    @Override
    public void destroy() {
        
    }
    
}
