<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Vector" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Order Details</title>
  	<style>
       body{
      box-sizing: border-box;
      font-family: 'Poppins', sans-serif;
      background-color:black;
    }
    #main-container{
      display: flex;
      justify-content: center;
      align-items: center;
      border: 2px solid black;
      height:500px;
      padding: 10px;
    }
    #sub-main{
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      border: 2px solid black;
      width: 40%;
      height: 480px;
      margin: 10px;
    }
    .sub{
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      border: 2px solid black;
      height: 480px;
      width: 30%;
      margin: 10px;
      border: 2px solid white;
      border-radius: 15px;
    }
    table{
      border-collapse: collapse;
    }

    td, th{
      padding: 15px;
      text-align: center;
      font-size: large;
    }

    th{
      background-color: cyan;
    }
    td{
      font-size: large;
    }
    tr:nth-child(even){
      background-color: white;
    }
    tr:nth-child(odd){
      background-color:rgba(110, 119, 119, 0.788);
    }
    h2{
      background-color: darkslategrey;
      padding: 8px;
      color: floralwhite;
      border-radius: 12px;
      border: 2px solid white;
    }
    h1{
    	margin-left: 20px;
    	padding: 15px 20px; 
    	color: white;
    }
    h2{
    	width: auto;
    	
    }
    hr{
    	height:2px;
    	border-width:0;
    	color:white;
    	background-color:white;
    }
    .sub-sub-main{
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      border: 2px solid black;
      margin:25px;
      height: 200px;
      padding: 8px;
      border: 2px solid white;
      border-radius: 15px;
    }
  </style>
</head>
<body>
	<h1>All the details of your orders!</h1>
	<hr>
	 <div id = "main-container">
	   <div class="sub">
	   	<h2>Bulk Orders</h2>
	     <table border ="1" width="400" align="center">
	     	<tr>
		     	<th>Item Name</th>
		     	<th>Quantity</th>
		    </tr>
		    <%Vector<Vector<String>> bulkItem = (Vector<Vector<String>>)request.getAttribute("bulkOrders");
	        for(Vector v: bulkItem){%>
            <tr>
              <td><%=v.get(0)%></td>
              <td><%=v.get(1)%></td>
            </tr>
	       	<%}%>
	     </table>
	   </div>
	   <div class="sub">
	     	<h2>Not Bulk Orders</h2>
	       	<table border ="1" width="400" align="center">
	     	<tr>
		     	<th>Item Name</th>
		     	<th>Quantity</th>
		    </tr>
		    <%Vector<Vector<String>> NotBulkItem = (Vector<Vector<String>>)request.getAttribute("NotbulkOrders");
	        for(Vector m: NotBulkItem){%>
	        <tr>
		        <td><%=m.get(0)%></td>
		       	<td><%=m.get(1)%></td>
		      </tr>
	       	<%}%>
	     </table>
	   </div>
	   <div id="sub-main">
	     <div class="sub-sub-main">
	     <h2>Total Amount</h2>
	      <table border ="1" width="400" align="center">
	     	<tr>
		     	<th>Total</th>
		    </tr> 
		    <tr>
		      <td>${total}</td>
		    </tr>
		  </table>
	     </div>
	     <div class="sub-sub-main">
	       <h2>Total Amount after Discount</h2>
	      <table border ="1" width="400" align="center">
	     	<tr>
		     	<th>Total</th>
		    </tr> 
		    <tr>
		      <td>${discountedPrice}</td>
		    </tr>
		  </table>
	     </div>
	   </div>
	 </div>
</body>
</html>