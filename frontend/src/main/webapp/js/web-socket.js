const socket = new WebSocket("ws://localhost:8080/backend/sensors");
socket.onopen = function() {};

socket.onclose = function(event) {};

socket.onmessage = function(event) {
    sensorsSnapshotState.snapshot = JSON.parse(event.data);
};

socket.onerror = function(error) {
    alert("Error " + error.message);
};
