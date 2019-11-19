<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

/* Style the header */
main {
	background-color: #34a4eb;
	text-align: center;
	font-size: 20px;
	color: white;
	height: 550;
}

footer {
	background-color: #34a4eb;
	padding: 30px;
	text-align: center;
	color: white;
}

h2 {
	font-size: 35px;
	padding: 30px;
}

button {
	margin-top: 50px;
	width: 200px;
	height: 50px;
	font-size: 15px;
}

a:hover {
	background-color: yellow;
}

button:hover {
	background-color: #afc3f0;
	cursor: pointer;
}
</style>
<body>
	<main>
		<h2>Welcome to URL Shortening Service!</h2>
		<p>
			Result: <a href="<%=request.getParameter("result")%>"><%=request.getParameter("result")%></a>
		</p>
		<button onclick="window.location='/urlshortsvc'">Return to
			Home Page</button>
	</main>
	<footer>
		<p>Copyright @Rajan</p>
	</footer>
</body>
</html>
