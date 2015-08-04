<%-- 
    Document   : TestProfileUpdate
    Created on : 3 Aug, 2015, 6:56:31 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Update</title>
    </head>
    <body>
    
	<form action="../rest/user/uploadProfileImage" method="post" enctype="multipart/form-data">
            <p>
            Name : <input type="text" name="userName" />
            </p>
	   <p>
            File name : <input type="file" name="profilePhoto" size="45" />
           </p>
           
           <input type="submit"/>
        </form>  
        
    </body>
</html>
