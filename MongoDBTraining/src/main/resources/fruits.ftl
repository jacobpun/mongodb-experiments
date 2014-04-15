<html>
	<head>
		<title>Hello World from freemarker!!</title>
	</head>
	<body>
		<form action="/favfruit" method ="POST">
			
			<p>What is your favorite fruit?</p>
			<#list fruits as fruit>
				<input type="radio" name="fruit" value="${fruit}">${fruit}</input><br/>
			</#list>
			<input type="submit" value="submit"/>
		</form>
	</body>
</html>