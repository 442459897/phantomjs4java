<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>


</head>
<body><%=request.getAttribute("img_path") %>
	<form action="Url2PictureServlet">
		<table>
			<tr>
				<td>URL1：</td><td><input type="text" name="url1" width="200px"/></td>
			</tr>
			<tr>
				<td>URL2：</td><td><input type="text" name="url2" width="200px"/></td>
			</tr>
			<tr>
				<td>URL3：</td><td><input type="text" name="url3" width="200px"/></td>
			</tr>
			<tr>
				<td>URL4：</td><td><input type="text" name="url4" width="200px"/></td>
			</tr>
			<tr>
				<td></td><td><input type="submit"/></td>
			</tr>
		</table>
	</form>
</body>
</html>