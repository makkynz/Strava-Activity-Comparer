<%--
  Created by IntelliJ IDEA.
  User: antho
  Date: 5/01/2020
  Time: 4:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>Hello, world!</title>
    <style>

        .card-text b{
            font-weight: 200;
        }
        .athletes, .activities{
            max-width: 1000px;
        }

        .activities{
            margin-top:20px;
        }
        .alert-danger{
            display: none;
        }

    </style>
</head>
<body style="margin:20px">
<h1>BF Run Club Lock Down Challenge</h1>
<p>
    Strava activity since Covid 19 lock down in NZ (26/03/2020)
    <img src="api_logo_pwrdBy_strava_horiz_light.svg" style="width:110px;"/>


</p>
<div class="alert alert-danger" role="alert">
    Sorry, we can't get your activities unless you authorise to connect to Strava.
</div>
<p>
<button class="btn btn-primary btnGetActivities" style="background-color: #fc5c19;
    border-color: #fc5c19;">Get My Activities</button></p>
<div class="athletes card-deck"></div>
<div class="activities">


</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.1/moment.min.js"></script>
<script src="main.js"></script>
</body>
</html>



