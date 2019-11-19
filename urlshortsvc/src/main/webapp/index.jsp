<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

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

input {
	margin-top: 30px;
	font-size: 15px;
}

button {
	margin-top: 30px;
	width: 100px;
	height: 25px;
	font-size: 15px;
}

button:hover {
	background-color: #afc3f0;
	cursor: pointer;
}
</style>
<body>
	<main>
		<h2>Welcome to URL Shortening Service!</h2>

		<form action="/urlshortsvc/rest/shortenurl">
			<p>Enter link to shorten:</p>
			<input type="text" name="url" autofocus placeholder="www.intuit.com"
				size="100" required="required"
				pattern="^(http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/)?[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$">
			<button>Submit</button>
		</form>

	</main>
	<footer>
		<p>Copyright @Rajan</p>
	</footer>
</body>
</html>
