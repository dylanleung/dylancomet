var wsApi = {
            connectedEndpoint:null,
            callbackAdded:false,
            incompleteMessage:"",
            subscribe:function () {
                function callback(response) {
                    if (response.transport != 'polling' && response.state == 'messageReceived') {
                        if (response.status == 200) {
                            var data = response.responseBody;
                            try {
                                chatApi.update(data);
                            } catch (err) {
                                console.log(err);
                            }
                        }
                    }
                }

                /* transport can be : long-polling, streaming or websocket */
                this.connectedEndpoint = $.atmosphere.subscribe('${pageContext.request.contextPath}/websockets',
                        !this.callbackAdded ? callback : null,
                        $.atmosphere.request = {transport:'websocket', logLevel:'none'});
                callbackAdded = true;
            },

            send:function (message) {
                console.log("Sending message");
                console.log(message);
                this.connectedEndpoint.push(JSON.stringify(message))
            },

            unsubscribe:function () {
                $.atmosphere.unsubscribe();
            }
        };

        var chatApi = {
            update:function (data) {
                var $chat = $("#chat");
                $("<li></li>").text(data).prependTo($chat);
            }
        };

        $(function () {
            wsApi.subscribe();
            var currentChannel = null;
            $("#join").click(function () {
                if (currentChannel !== null) {
                    wsApi.send({"type":"unsubscribe", "channel":currentChannel});
                }
                var channel = $("#channel").val();
                wsApi.send({"type":"subscribe", "channel":channel});
                currentChannel = channel;
            });

            $("#leave").click(function () {
                wsApi.send({"type":"unsubscribe", "channel":currentChannel});
                currentChannel = null;
            });

            $("#subscribe").click(function () {
                wsApi.subscribe();
            });

            $("#unsubscribe").click(function () {
                wsApi.unsubscribe();
            });

        });