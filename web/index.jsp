<%--
  Created by IntelliJ IDEA.
  User: antho
  Date: 5/01/2020
  Time: 4:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans|Source+Code+Pro" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://developers.strava.com/strava-developers.css" rel="stylesheet">

    <title>Strava Activity Tracker</title>
</head>
<body style="margin: 20px;">
<h1>Strava Activity Tracker</h1>
<div class="section section-strava-api container">
    <div class="row cta-row">
        <div class="col-md-3">
            <button class="btnGetActivities btn btn-primary">Get Strava Activities</button>
        </div>


    </div>
    <div class="row cta-row">
        <div class="col-md-3 output">

        </div>


    </div>
</div>


<script src="main.js"></script>
</body>
</html>
