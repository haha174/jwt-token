package com.wen.token.util;

public class BaseResponse
{
  /**
   * status code default 200
   */
  private String statusCode = IConstants.RESPONSE_STATUS_CODE_SUCCESS;
  /**
   * status message default success
   */
  private String statusMsg = IConstants.RESPONSE_STATUS_CODE_SUCCESS_MSG;

  public BaseResponse()
  {
    statusCode = IConstants.RESPONSE_STATUS_CODE_SUCCESS;
    statusMsg = IConstants.RESPONSE_STATUS_CODE_SUCCESS_MSG;
  }

  public BaseResponse(String statusCode, String statusMsg)
  {
    super();
    this.statusCode = statusCode;
    this.statusMsg = statusMsg;
  }

  public String getStatusCode()
  {
    return statusCode;
  }

  public void setStatusCode(String statusCode)
  {
    this.statusCode = statusCode;
  }

  public String getStatusMsg()
  {
    return statusMsg;
  }

  public void setStatusMsg(String statusMsg)
  {
    this.statusMsg = statusMsg;
  }

}
