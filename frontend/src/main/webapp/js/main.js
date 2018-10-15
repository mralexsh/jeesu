const app = new Vue({
    store: SENSORS,
    
}).$mount('#app');


const testNodes = [
	{ 
		node: "node1",
		sensors: [
			{
				imei: "test sensor1",
				value: "0",
				type: 0
			},
			{
				imei: "test sensor2",
				value: "2",
				type: 0
			},
			{
				imei: "test sensor3",
				value: "2",
				type: 1,
				percentage: 25
			}
			
		]
	},
	{ 
		node: "node2",
		sensors: [
			{
				imei: "test sensor1",
				value: "2",
				type: 1,
				percentage: 40
			},
			{
				imei: "test sensor2",
				value: "2",
				type: 0
			},
			{
				imei: "test sensor3",
				value: "2",
				type: 1,
				percentage: 80
			}
			
		]
			
	},
	{ 
		node: "node3",
		sensors: [
			{
				imei: "test sensor1",
				value: "2",
				type: 1,
				percentage: 10
			},
			{
				imei: "test sensor2",
				value: "2",
				type: 1,
				percentage: 99
			},
			{
				imei: "test sensor3",
				value: "2",
				type: 1,
				percentage: 9
			}
		]
	}
]
const socket = new WebSocket("ws://localhost:8080/backend/sensors");
	socket.onopen = function() {};

	socket.onclose = function(event) {};

	socket.onmessage = function(event) {
		const testNodes = [];
		testNodes.push(JSON.parse(event.data));
		app.$store.dispatch("updateSenors", testNodes);
	};

	socket.onerror = function(error) {
	  alert("Error " + error.message);
	};


