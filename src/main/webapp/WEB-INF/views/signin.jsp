<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<jsp:text>
		<![CDATA[ <!DOCTYPE html> ]]>
	</jsp:text>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<title>Spring Social Google - Contacts</title>
<jsp:directive.include file="header.jspf" />
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="span14 columns offset2">
				<h1>Spring Social Weibo Example Application</h1>
				<h5>Sources: <a href="https://github.com/GabiAxel/spring-social-google/">https://github.com/GabiAxel/spring-social-google/</a></h5>
				<form action="signin/weibo" method="GET">
				    <button type="submit" class="btn btn-large btn-primary">Sign in with Weibo</button>
				    <input type="hidden" name="scope" value="friendships_groups_read,friendships_groups_write,statuses_to_me_read,follow_app_official_microblog"/>		    
				</form>
			</div>
		</div>
	</div>
</body>
</html>
</jsp:root>
