$(document).ready(function () {
    $('.btnGetActivities').on('click', function () {
        callbackUrl = `${window.location.origin}/strava/callback`
        scope = "scope=activity:read";
        window.location = `https://www.strava.com/oauth/authorize?client_id=45153&response_type=code&redirect_uri=${callbackUrl}&approval_prompt=force&${scope}`
    })

    function renderStats(json){
        for (var i = 0; i < json.length; i++) {
            let athlete = json[i];
            let html = "<table border='1'><tr><th>"+athlete["name"]+"</th></tr>";

            for (let j = 0; j < athlete["activities"].length; j++) {
                let activity = athlete["activities"][j];
                html +="<tr><td>"
                    +new Date(activity["startDate"]).toLocaleString("en-NZ")
                    +" | " +activity["type"]
                    +" | "+(activity["distance"] / 1000).toFixed(2) + " km"
                    + " | " + speedToPace(activity["averageSpeed"]) + " m/km"
                    + "</td></tr>";

            }
            html +="</table>"
            $('.output').append(html)

        }
    }

    function speedToPace(speed){
        return fancyTimeFormat(1000/speed)
    }

    function fancyTimeFormat(time)
    {
        // Hours, minutes and seconds
        var hrs = ~~(time / 3600);
        var mins = ~~((time % 3600) / 60);
        var secs = ~~time % 60;

        // Output like "1:01" or "4:03:59" or "123:03:59"
        var ret = "";

        if (hrs > 0) {
            ret += "" + hrs + ":" + (mins < 10 ? "0" : "");
        }

        ret += "" + mins + ":" + (secs < 10 ? "0" : "");
        ret += "" + secs;
        return ret;
    }


    $.ajax({
        dataType: "json",
        url: "/api/activities",

        success: function( data ) {
            renderStats(JSON.parse(data));
            console.log(  JSON.parse(data));
        }
    });
});