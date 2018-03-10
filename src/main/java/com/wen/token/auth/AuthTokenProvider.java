package com.wen.token.auth;

import com.wen.token.service.TokenService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Component
public class AuthTokenProvider implements AuthenticationProvider
{
  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  protected HttpServletRequest request;

  @Autowired
  TokenService tokenService;
  
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException
  {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if(auth!=null && auth.isAuthenticated())
    {
      return new UsernamePasswordAuthenticationToken(auth.getPrincipal(), null, new ArrayList<>());
    }
    String token = (String) authentication.getPrincipal();
    if (token != null)
    {
      if (!tokenService.checkToken(token))
      {
        throw new CredentialsExpiredException("Access Token is expired. Please login again.");
      }
    }
    else
    {
      throw new BadCredentialsException("Invalid token String.");
    }
    logger.debug("Authenticated successfully.");
    return new UsernamePasswordAuthenticationToken(token, null, new ArrayList<>());
  }
  
  @Override
  public boolean supports(Class<?> authentication)
  {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }

}
