package com.maneaandrei.sda.foodshop.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 *  Daca faci login sa stii daca esti user si trb redirectare la o pagina diferita pt Manager sau User
 */

public class RoleRedirectAuthenticationSuccessHandler implements AuthenticationSuccessHandler
{
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private final Map<String, String> roleTargetUrlMap = new HashMap<>();

    public RoleRedirectAuthenticationSuccessHandler(Map<String, String> roleTargetUrlMap)
    {
        this.roleTargetUrlMap.putAll(roleTargetUrlMap);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException
    {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    protected void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException
    {

        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted())
        {
            //"Response has already been committed. Unable to redirect to targetUrl;
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(final Authentication authentication)
    {
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities)
        {
            String authorityName = grantedAuthority.getAuthority();
            if (roleTargetUrlMap.containsKey(authorityName))
            {
                return roleTargetUrlMap.get(authorityName);
            }
        }

        throw new IllegalStateException();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request)
    {
        HttpSession session = request.getSession(false);
        if (session == null)
        {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
