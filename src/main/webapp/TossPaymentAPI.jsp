<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제하기</title>
  <script src="https://js.tosspayments.com/v1/payment"></script>
</head>
<body style="background-color:blue">
<div id="test">
</div>
<button onclick="test()" id="testbtn">
	테스트
</button>
</body>
	 <script>
	 function test(){
    var clientKey = 'test_ck_D5GePWvyJnrK0W0k6q8gLzN97Eoq'
    var tossPayments = TossPayments(clientKey) // 클라이언트 키로 초기화하기
    tossPayments.requestPayment('카드', { // 결제 수단 파라미터
    	  // 결제 정보 파라미터
    	  amount: 15000,
    	  orderId: 'qKsrgjWenNZ0DkvkzyBtd',
    	  orderName: '토스 티셔츠 외 2건',
    	  customerName: '박토스',
    	  successUrl: 'http://localhost:8090/Team4_1031/index.html',
    	  failUrl: 'http://localhost:8080/fail',
    	  
    	})
	 }
    </script>
</html>