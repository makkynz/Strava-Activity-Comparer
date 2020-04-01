$(document).ready(function () {


    $('#btnGetActivities').on('click', function () {
        callbackUrl = `${window.location.origin}/strava/callback`
        scope = "scope=activity:read";
        window.location = `https://www.strava.com/oauth/authorize?client_id=45153&response_type=code&redirect_uri=${callbackUrl}&approval_prompt=force&${scope}`
    })
});