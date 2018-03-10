package com.wen.token.web;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Aspect @Component
public class ControllerInterceptor
{
  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Pointcut("execution(* com.wen.token.web..*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
  public void controllerMethodPointcut()
  {
  }

  /**
   * implement of Interceptor
   *
   * @param pjp
   * @return BaseResponse
   */
  @Around("controllerMethodPointcut()")
  public Object Interceptor(ProceedingJoinPoint pjp) throws Exception
  {
    long beginTime = System.currentTimeMillis();
    MethodSignature signature = (MethodSignature) pjp.getSignature();
    Method method = signature.getMethod();
    String methodName = method.getName();

    Set<Object> allParams = new LinkedHashSet<>();
    Object result = null;
    Object[] args = pjp.getArgs();
    //has request
    boolean hasRequest = false;
    if(!"putAll".equals(methodName))
    {
      for (Object arg : args)
      {
        //logger.debug("arg: {}", arg);
        if (arg instanceof Map<?, ?>)
        {
          Map<String, Object> map = (Map<String, Object>) arg;
          allParams.add(map);
        }
        else if (arg instanceof HttpServletRequest)
        {
          HttpServletRequest request = (HttpServletRequest) arg;
          Map<String, String[]> paramMap = request.getParameterMap();
          if (paramMap != null && paramMap.size() > 0)
          {
            //remove password,token
            if(paramMap.containsKey("username") && paramMap.containsKey("password"))
            {
              allParams.add(paramMap.get("username"));
            }
            else if(paramMap.containsKey("token"))
            {
              allParams.add("token:***********");
            }
            else
            {
              allParams.add(paramMap);
            }
            hasRequest = true;
          }
        }
        else if (arg instanceof HttpServletResponse || arg instanceof Exception)
        {
          //do nothing...
        }
        else
        {
          //if no HttpServletRequest , print parameter
          if(!hasRequest)
          {
            allParams.add(arg);
          }
        }
      }
    }

    if(!"putAll".equals(methodName))
    {
      logger.info("start request: {} with params:{}", methodName, allParams);
    }
    else
    {
      logger.info("start request: {}", methodName);
    }


    try
    {
      if (result == null)
      {
        result = pjp.proceed();
      }
    }
    catch (Throwable e)
    {
      logger.error("exception: {}", e);
      throw new Exception(e);
      //result = new BaseResponse(IConstants.RESPONSE_STATUS_CODE_FAILED, e.getMessage());
    }

    long costMs = System.currentTimeMillis() - beginTime;
    logger.info("{} request end, cost : {} ms", methodName, costMs);

    return result;
  }
}
