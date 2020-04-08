function viewActivities() {
    let tableTemplate ="<table class=\"table\">\n" +
        "        <thead>\n" +
        "        <tr>\n" +
        "            <th scope=\"col\">Date</th>\n" +
        "            <th scope=\"col\">Name</th>\n" +
        "            <th scope=\"col\">Type</th>\n" +
        "            <th scope=\"col\">Distance</th>\n" +
        "            <th scope=\"col\">Pace</th>\n" +
        "\n" +
        "        </tr>\n" +
        "        </thead>\n" +
        "        <tbody>\n" +
        "        [rows]\n" +
        "        </tbody>\n" +
        "    </table>"
    let rowTemplate =
        "        <tr>\n" +
        "            <td scope=\"row\">[date]</td>\n" +
        "            <td><a href='https://www.strava.com/activities/[activityId]'>[name]</a></td>\n" +
        "            <td>[type]</td>\n" +
        "            <td>[distance] km</td>\n" +
        "            <td>[pace]</td>\n" +
        "        </tr>\n"

    let athlete = dataJson[$(this).data("index")];
    let rowHtml = "";
    for (let j = athlete["activities"].length-1; j >= 0; j--) {
        let activity = athlete["activities"][j];
        let activityId = activity["activityId"]
        let distance = (activity["distance"] / 1000).toFixed(2);
        let name = activity["name"]
        let activityDate =moment(activity["startDate"]).format('MMM Do ddd h:mma');
        let pace = speedToPace(activity["averageSpeed"]);
        let type = activity["type"]


        rowHtml += rowTemplate
            .replace("[date]", activityDate)
            .replace("[type]", activity["type"])
            .replace("[distance]", distance)
            .replace("[pace]", pace)
            .replace("[activityId]", activityId)
            .replace("[name]",name)


    }

    let tblHtml = tableTemplate.replace("[rows]", rowHtml)

    $('.activities').html(tblHtml);

    $([document.documentElement, document.body]).animate({
        scrollTop: $(".activities").offset().top
    }, 500);


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

let dataJson = null;

$(document).ready(function () {

    $('.alert-danger').toggle(location.search.indexOf("result=noauth") > -1);
    $('.btnGetActivities').on('click', function () {
        callbackUrl = window.location.origin+"/strava/callback"
        scope = "scope=activity:read";
        window.location = "https://www.strava.com/oauth/authorize" +
            "?client_id="+stravaClientId +
            "&response_type=code" +
            "&redirect_uri="+callbackUrl+"" +
            "&approval_prompt=force&"+scope
    })

    function renderStats(json){
        dataJson = json;
        let athleteTemplate = "<div class=\"card\" style=\"width: 13rem;max-width: 13em\">\n" +
            "    <img class=\"card-img-top\" src=\"[profilePic]\" alt=\"Card image cap\">\n" +
            "    <div class=\"card-body\">\n" +
            "        <h5 class=\"card-title\">[name]</h5>\n" +
            "        <p class=\"card-text\"><b>Total km:</b> [total]</p>\n" +
            "        <p class=\"card-text\"><b>Last Activity:</b> <br>[lastActivityData]</p>\n" +
            "        <a href=\"#\" class=\"btn btn-primary btnActivities\" data-index=\"[index]" +
            "\">View Activities</a>\n" +
            "    </div>\n" +
            "</div>"
        for (let i = 0; i < json.length; i++) {

            let athlete = json[i];


            let totalKms = 0;
            let lastActivityData = "";
            for (let j = athlete["activities"].length-1; j >= 0; j--) {
                let activity = athlete["activities"][j];
                let distance = (activity["distance"] / 1000).toFixed(2);
                totalKms =  totalKms + parseFloat(distance);
                let activityDate =moment(activity["startDate"]).format('MMM Do ddd h:mma');
                let pace = speedToPace(activity["averageSpeed"]);
                let type = activity["type"]

                if(j == athlete["activities"].length-1){
                    lastActivityData = activityDate + " | " +type+" | "+ distance +" km"
                }


            }
            let  html = athleteTemplate
                .replace("[profilePic]", athlete["profilePic"])
                .replace("[name]", athlete["name"])
                .replace("[total]", totalKms)
                .replace("[lastActivityData]", lastActivityData)
                .replace("[index]", i)

            $('.athletes').append(html)

        }
    }




    $.ajax({
        dataType: "json",
        url: "/api/activities",

        success: function( data ) {
            renderStats(JSON.parse(data));


            $('.btnActivities').on('click', viewActivities)
            console.log(  JSON.parse(data));
        }
    });
});