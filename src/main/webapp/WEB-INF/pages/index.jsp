<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title></title>
</head>
<body>

<h4>One list:</h4>

<ol>
    <li>Red</li>
    <li>Green</li>
    <li>Blue
        <ol><li><strong>one</strong></li>
            <li><em>two</em></li>
        </ol>
    </li>
</ol>

<h4>Another list:</h4>

<ul>
    <li>One
        <ul>
            <li>e</li>
            <li>b</li>
        </ul>
    </li>
    <li>Two</li>
    <li>Three</li>
</ul>

</body>
</html>