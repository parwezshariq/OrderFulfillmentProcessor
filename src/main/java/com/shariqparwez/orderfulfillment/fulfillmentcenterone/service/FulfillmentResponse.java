package com.shariqparwez.orderfulfillment.fulfillmentcenterone.service;

/**
 * Represents a fulfillment reponse from the fulfillment center one restful web
 * service.
 * 
 * @author Shariq Parwez
 *
 */
public class FulfillmentResponse {

   private int responseCode;
   private String response;

   public FulfillmentResponse() {

   }

   public FulfillmentResponse(int responseCode, String response) {
      super();
      this.responseCode = responseCode;
      this.response = response;
   }

   /**
    * @return the responseCode
    */
   public int getResponseCode() {
      return responseCode;
   }

   /**
    * @param responseCode
    *           the responseCode to set
    */
   public void setResponseCode(int responseCode) {
      this.responseCode = responseCode;
   }

   /**
    * @return the response
    */
   public String getResponse() {
      return response;
   }

   /**
    * @param response
    *           the response to set
    */
   public void setResponse(String response) {
      this.response = response;
   }

   /*
    * (non-Javadoc)
    * 
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("FulfillmentResponse [responseCode=");
      builder.append(responseCode);
      builder.append(", ");
      if (response != null) {
         builder.append("response=");
         builder.append(response);
      }
      builder.append("]");
      return builder.toString();
   }
}
