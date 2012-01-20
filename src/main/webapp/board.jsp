<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html>
<html>
 <head>
  <title>Board</title>
 </head>
 <body>

<h1>Wheel</h1>

<p><i><bean:write name="message" ignore="true" /></i></p>

<table>
 <tr>
  <th>Component</th>
  <th>Position</th>
 </tr>
 <tr>
  <td>Arm</td>
  <td><bean:write name="arm" property="position" /></td>
 </tr>
 <tr>
  <td>Grain</td>
  <td><bean:write name="grain" property="position" /></td>
 </tr>
 <tr>
  <td>Sheep</td>
  <td><bean:write name="sheep" property="position" /></td>
 </tr>
</table>

<html:form action="takeGrain.do">
	<html:submit>Take Grain</html:submit>
</html:form>
<html:form action="takeSheep.do">
	<html:submit>Take Sheep</html:submit>
</html:form>
<html:form action="pushArm.do">
	<html:submit>Push Arm</html:submit>
</html:form>

 </body>
</html>