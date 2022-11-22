package kr.or.kosa.service.payment;

import java.io.IOException;
import java.net.URI;
//import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;

public class PaymentService implements Action {
//결제 api?? -> 결제 성공/실패 팝업으로
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
//		HttpServletRequest request = HttpServletRequest.newBuilder()
//			    .uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
//			    .header("Authorization", "Basic dGVzdF9za196WExrS0V5cE5BcldtbzUwblgzbG1lYXhZRzVSOg==")
//			    .header("Content-Type", "application/json")
//			    .method("POST", HttpServletRequest.BodyPublishers.ofString("{\"paymentKey\":\"ovokXHUy6edshVMvA2B7E\",\"amount\":15000,\"orderId\":\"l4r-wMvhCF9v2pJigVJcZ\"}"))
//			    .build();
//			HttpServletResponse<String> response = HttpClient.newHttpClient().send(request, HttpServletResponse.BodyHandlers.ofString());
//			try {
//				response = HttpClient.newHttpClient().send(request, HttpServletResponse.BodyHandlers.ofString());
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			System.out.println(response.body());
//		
		try {
			
		} catch (Exception e) {
			
		} 
		//팝업 보여주고 다른 페이지로
		request.setAttribute("msg","");
		request.setAttribute("url", "");
		return forward;
	}

}
