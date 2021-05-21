<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User | index</title>
    <!-- <link href="/templates/index.css" rel="stylesheet"> -->
</head>

<style>
  
body {
  margin: 0;
  padding: 0;
  background-color: #24305e;
  font-family: sans-serif;
}

.title {
  font-size: 23px;
  color: #f76c6c;
  display: flex;
  align-items: center;
  justify-content: center;
  padding-top: 10px;
  padding-bottom: 30px;
  margin: 10px;
}

.title h1 {
  padding: 5px 10px;
  border: solid #ffffff;
  border-width: 5px 0px;
}

.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 382px;
  overflow: hidden;
  margin: auto;
  padding: 60px;
  background: #f76c6c;
  border-radius: 17px;
}

.container h1 {
  color: #24305e;
  /* -webkit-text-stroke-width: 1px; */
  /* -webkit-text-stroke-color: black; */
  /* text-decoration: underline; */

}

label {
  color: #ffffff;
  font-size: 17px;
}
#email, #password {
  width: 250px;
  height: 30px;
  border: 1px solid black;
  border-radius: 15px;
  padding-left: 8px;
  margin: 10px;
}

#email:focus, #password:focus {
  outline-width: 0;
}

.error-message {
  color: red;
  font-size: 15px;
  display: flex;
  justify-content: center;
  align-items: center;
}

#submit {
  width: 150px;
  height: 30px;
  border: 2px solid #24305e;
  border-radius: 19px;
  margin: 20px auto 15px 65px;
  color: #ffffff;
  outline: none;
  background-color: #24305e;
}

#submit:hover{
  cursor: pointer;
  transform: scale(1.1);
}

.links-box {
  display: block;
  flex-direction: row;
  /* width: 100%; */
  /* height: 40px; */
  /* justify-items: center; */
}

.links-box a {
  display: inline;
  width: 50%;
  color: #000;
  text-decoration: none;
}

.links-box a:hover {
  color: #9CF6F6;
}

 
#new {
  padding-left: 25px;
}

#forget {
  padding-right: 25px;
} 
h3{
	text-align:center;
	color:white;
}
</style>
<body>
<header>
    <div class="title">
        <h1>E-SHOPPING</h1>
    </div>
    <!-- <h1>Create Account</h1> -->
</header>
<main>
	<h3>${messege}</h3>
    <h3>${welcome}</h3>
    <div class="container">

        <div class="container-items">
            <h1> User Login </h1>
        </div>
        <div class="container-items">
            <form action="login" method="post">
                <div class="email">
                    <input type="text" name="username" id="email" placeholder="User ID">
                </div>

                <div class="container-items">
                    <input type="password" name="password" id="password" placeholder="Password">
                </div>
                <div class="error-message">
                    <p><u>${error}</u></p>
                </div>
                <div class="container-items">
                    <button type="submit" id="submit" name="Create Account">Login</button>
                </div>
            </form>
        </div>

        <div class="container-items">
            <div class="links-box">
                <a id="new" href="register"> New Registration?</a>
            </div>
        </div>
    </div>
</main>
</body>
</html>