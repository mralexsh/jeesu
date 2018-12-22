const app = new Vue({
    store: SENSORS,
	methods: {
    	recalcSensorsNodes() {
    		const queueNode = app.$store.getters.snapshotState();
			console.log("----------");
    		console.log(queueNode);
    		const node = queueNode.pop();
			console.log("++++++++++");
			console.log(node);
			app.$store.dispatch("updateSnapshotState", queueNode);

			app.$store.dispatch("updateSensors", node);

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
		const nodes = [];
		nodes.push(JSON.parse(event.data));
		app.$store.dispatch("updateSnapshotState", nodes);
	};

	socket.onerror = function(error) {
	  alert("Error " + error.message);
	};


