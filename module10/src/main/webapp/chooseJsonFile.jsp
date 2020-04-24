<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Choose json file</title>
</head>
<body>

<form action="/customTags/choose" method="post">
    <input type="hidden" name="fileName" value="jsonFile1.json">
    <input type="submit" value="Json1">
</form>

<form action="/customTags/choose" method="post">
    <input type="hidden" name="fileName" value="jsonFile2.json">
    <input type="submit" value="Json2">
</form>

<form action="/customTags/choose" method="post">
    <input type="hidden" name="fileName" value="jsonFile3.json">
    <input type="submit" value="Json3">
</form>

<form action="/customTags/choose" method="post">
    <input type="hidden" name="fileName" value="jsonFile4.json">
    <input type="submit" value="Json4">
</form>

</body>
</html>