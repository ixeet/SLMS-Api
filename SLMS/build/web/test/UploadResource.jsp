<%-- 
    Document   : UploadResource
    Created on : 8 Aug, 2015, 10:25 AM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resource Details</title>
    </head>
    <body>
    
	<form action="../rest/course/uploadResourceDetail" method="post" enctype="multipart/form-data">
            <p>
            Name : <input type="text" name="resourceName" />
            </p>
            <p>
            Author name : <input type="text" name="resourceAuthor" />
           </p>
           <!--  <p>
            Duration : <input type="text" name="metadata" />
           </p> -->
            <p>
            Desc : <input type="text" name="descTxt" />
           </p>
            <p>
            Created By : <input type="text" name="lastUserIdCd" />
           </p>
            <p>
            Resource URL :  <input type="file" name="resourceUrl" size="45" />
           </p>
           
            <p>
            URL : <input type="text" name="upLoadUrl" />
           </p>
            <p>
            Author Image : <input type="text" name="resourceImage"/>
           </p>
           
           <input type="submit" value="SAVE"/>
        </form>  
        
    </body>
</html>
