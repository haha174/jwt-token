package com.wen.token.util;

public class TokenString
{
  private String token;

  public TokenString()
  {

  }

  public TokenString(String token)
  {
    this.token = token;
  }

  public String getToken()
  {
    return token;
  }
  public void setToken(String token)
  {
    this.token = token;
  }

  @Override public String toString()
  {
    return "TokenString{" + "token='" + token + '\'' + '}';
  }
}