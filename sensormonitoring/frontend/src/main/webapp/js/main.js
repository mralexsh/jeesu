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



app.$store.dispatch("updateSenors", testNodes);
