package com.hiya.ee.cas;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "HiyaAuthenticationServelt", description = "注解", urlPatterns =
{ "/authentication" })
public class HiyaAuthenticationServelt extends HttpServlet
{
    private static final long serialVersionUID = 143434L;

    /**
     * key=value&
     * 
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        Token result = new Token("ILLEGAL_USER", "", "", new Date());

        String ticket = req.getParameter("TICKET");// 10
        if (!StringUtils.isEmpty(ticket))
        {
            List<Token> ticketList = AuthenticationCache.getAuthenticationCache().stream()
                    .filter(e -> e.getTicket().equals(ticket) && new Date().compareTo(e.getValidityDate()) > 0)
                    .collect(Collectors.toList());
            if (null != ticketList && ticketList.size() > 0)
            {
                result = ticketList.get(0);
            }
        } 
        else
        {
            String username = req.getParameter("username");// 10
            String password = req.getParameter("password");// 20
            if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password))
            {
                if (AuthenticationCache.getAuthenticationDatabase().containsKey(username)
                        && password.equals(AuthenticationCache.getAuthenticationDatabase().get(username)))
                {
                    String ticketDesc = "HIYA_" + UUID.randomUUID().toString().replaceAll("-", "");
                    Token tt = new Token(ticketDesc, username, password, new Date(System.currentTimeMillis() + 1000 * 60 * 5));
                    AuthenticationCache.getAuthenticationCache().add(tt);
                    result = tt;
                }
            }
        }
        resp.getWriter().println(result);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        super.doDelete(req, resp);
    }
}