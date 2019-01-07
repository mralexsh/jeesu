const app = new Vue({
    store: SENSORS,
	methods: {
		refreshState: function() {
    		const s = app.$store.getters.snapshotState();
			app.$store.dispatch("updateSensors", s.root);
			app.$store.dispatch("updateAlarms", this.extractAlarms(s.root));
    	},
		extractAlarms: function(nodes) {
			const res = [];
			nodes.forEach(function (node) {
				node.sensors.forEach(function (sensor) {
					if (Array.isArray(sensor.alarms)) {
						sensor.alarms.forEach(function (alarm) {
							res.push(alarm);
						})
					}
				});
			})
			return res;
		}
	}
    
}).$mount('#app');


const socket = new WebSocket("ws://localhost:8080/backend/sensors");
socket.onopen = function() {
		setInterval(function () {
			app.refreshState();
		}, 1000);
	};

	socket.onclose = function(event) {};

	socket.onmessage = function(event) {
		app.$store.dispatch("updateSnapshotState", JSON.parse(event.data));
	};

	socket.onerror = function(error) {
	  alert("Error " + error.message);
	};


