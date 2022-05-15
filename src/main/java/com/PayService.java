package com;

import model.Pay;


//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Payment")
public class PayService {
	
Pay p = new Pay();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayments() {
		return p.readPayment();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("fullName")String fullName,
								@FormParam("NIC")String NIC,
								@FormParam("amount")String amount,
								@FormParam("date")String date,
								@FormParam("bankName")String bankName,
								@FormParam("debitCard")String debitCard,
								@FormParam("otpNumber")String otpNumber)
{
	String output = p.insertPayment(fullName,NIC,amount,date,bankName,debitCard,otpNumber);
	return output;
}
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String paymentData)
	{
		JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
		String payID = paymentObject.get("payID").getAsString();
		String fullName = paymentObject.get("fullName").getAsString();
		String NIC = paymentObject.get("NIC").getAsString();
		String amount = paymentObject.get("amount").getAsString();
		String date = paymentObject.get("date").getAsString();
		String bankName = paymentObject.get("bankName").getAsString();
		String debitCard = paymentObject.get("debitCard").getAsString();
		String otpNumber = paymentObject.get("otpNumber").getAsString();
		
		String output = p.updatePayment(payID,fullName,NIC,amount,date,bankName,debitCard,otpNumber);
		return output;
	}
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deletePayment(String paymentData)
	{
		//convert input string => xml document
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());
		//read the value from the element
		String payID = doc.select("payID").text();
		String output =p.deletePayment(payID);
		return output;
	}
}
