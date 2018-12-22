const app = new Vue({
    store: SENSORS,
	methods: {
    	recalcSensorsNodes() {
    		const queueNode = app.$store.getters.snapshotState();
			app.$store.dispatch("updateSensors", queueNode.state);
    	}
	}
    
}).$mount('#app');


const socket = new WebSocket("ws://localhost:8080/backend/sensors");
socket.onopen = function() {
		setInterval(function () {
			app.recalcSensorsNodes();
		}, 1000);
	};

	socket.onclose = function(event) {};

	socket.onmessage = function(event) {
		app.$store.dispatch("updateSnapshotState", JSON.parse(event.data));
	};

	socket.onerror = function(error) {
	  alert("Error " + error.message);
	};


