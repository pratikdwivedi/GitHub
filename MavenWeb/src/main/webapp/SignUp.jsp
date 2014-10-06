<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pratik-Project</title>
</head>
<body>
	<script src="js/validate.js">
		
	</script>
	<script
		src="js/jquery-2.1.1.min.js">
		
	</script>
	<script>
	$(document).ready(function(){
		  $("#hide").click(function(){
		    $("#test").hide();
		  });
		  $("#show").click(function(){
		    $("#test").show();
		  });
		});
	</script>
	<p class="style1">Fill up all mentioned data for Sign Up...</p>
	<form name="form1" method="post" action="SignUpAction.jsp"
		onsubmit="return usernameCheck()">
		<table width="435" height="586" border="0">
			<tr>
				<th width="351" scope="col"><div align="left">
						User Name: <input type="text" name="username" id="username"
							maxlength="30" />
					</div></th>
			</tr>
			<tr>
				<td>Mobile No.: <input name="mobno" id="mobno"
					maxlength=10 /></td>
			</tr>
			<tr>
				<td>Email Id: <input type="text" name="email" id="email"
					maxlength="50" /></td>
			</tr>
			<tr>
				<td><input name="Submit" type="submit" id="submit"
					value="Register" /> <input name="reset" type="reset" id="reset"
					value="Reset" /></td>
			</tr>
		</table>
	</form>
	<p id="test">This is another paragraph.</p>
	<button id="hide">HIDE</button>
	<button id="show">Show</button>
</body>
</html>