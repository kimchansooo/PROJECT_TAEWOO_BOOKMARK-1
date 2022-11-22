<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="msg" value="${requestScope.msg}"/>
<c:set var="url" value="${requestScope.url}"/>

<c:if test="${msg != null && url != null}">
<<<<<<< HEAD
   <script>
      alert('${msg}');
      location.href='${url}';
   </script>
=======
	<script>
		alert('${msg}');
		location.href='${url}';
	</script>
>>>>>>> 7de35155ad86794ad4f0f68b0057d2237db3b306
</c:if>