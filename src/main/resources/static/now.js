


        function updateBrowserTime() {
            setInterval(() => {
                let now = new Date().toLocaleTimeString();
                $("#browserTime").text(`Browser Time: ${now}`);
            }, 1000);
        }

       function updateServerUptime() {
    $.ajax({
        type: "GET",
        url: "/server-uptime",
        success: function (response) {
            let serverStartTime = response.serverStartTime; // Get server start time in milliseconds

            function refreshServerTime() {
                let currentTimeMillis = serverStartTime + (Date.now() - serverStartTime);
                let serverTime = new Date(currentTimeMillis).toLocaleTimeString();
                $("#serverUptime").text(`Server Time: ${serverTime}`);
            }

            refreshServerTime();
            setInterval(refreshServerTime, 1000);
        }


        $("#predefinedCommands").change(function () {
            executeCommand($(this).val());
        });

        $("#commandForm").submit(function (event) {
            event.preventDefault();
            executeCommand($("#commandInput").val().trim());
        });

        $("#commandInput").keydown(function (event) {
            if (event.key === "Enter") {
                event.preventDefault();
                executeCommand($("#commandInput").val().trim());
            }
        });

        updateBrowserTime();
        updateServerUptime();
    });
    }



