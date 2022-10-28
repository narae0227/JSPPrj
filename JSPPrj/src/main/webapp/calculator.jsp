
<!-- 코드블럭 : jsp에서 자바코드를 출력할수 있도록 씌우는 것 -->
<% 
	int x = 3;
	int y = 4;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
input{
	width:50px;
	height:50px;
}

.output{
	height:50px;
	background: #e9e9e9;
	font-size : 24px;
	font-weight : bold;
	text-align : right;
	padding : 0px 5px;
}

</style>
</head>
<body>
	<div>
		<form action="calc3" method="post">
			<table>
				<tr>
					<td class="output" colspan="4">${exp}</td>
				</tr>
				<tr>
					<td><input type="submit" value="CE" name="operator"/></td>
					<td><input type="submit" value="C" name="operator"/></td>
					<td><input type="submit" value="BS" name="operator"/></td>
					<td><input type="submit" value="/" name="operator"/></td>
				</tr>
				<tr>
					<td><input type="submit" value="7" name="val"/></td>
					<td><input type="submit" value="8" name="val"/></td>
					<td><input type="submit" value="9" name="val"/></td>
					<td><input type="submit" value="*" name="operator"/></td>
				</tr>
				<tr>
					<td><input type="submit" value="4" name="val"/></td>
					<td><input type="submit" value="5" name="val"/></td>
					<td><input type="submit" value="6" name="val"/></td>
					<td><input type="submit" value="-" name="operator"/></td>
				</tr>
				<tr>
					<td><input type="submit" value="1" name="val"/></td>
					<td><input type="submit" value="2" name="val"/></td>
					<td><input type="submit" value="3" name="val"/></td>
					<td><input type="submit" value="+" name="operator"/></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="0" name="val"/></td>
					<td><input type="submit" value="." name="dot"/></td>
					<td><input type="submit" value="=" name="operator"/></td>
				</tr>
			</table>
			
		</form>
	</div>
</body>
</html>