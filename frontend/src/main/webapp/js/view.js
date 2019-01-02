Vue.component('a-sensor', {
    props: ["sensor"],
	template: '<div class="a-sensor">' +
	'<p>{{sensor.imei}}</p>' +
	'<div class="analog-body">' +
	'<div :class="[bodyPercentage, bColor]"  :style="{width: sensor.value + \'%\'}">' +
	'</div>' +
	'</div>' +
    '</div>',
	computed: {
    	bodyPercentage: function() {
    		return 	"analog-body-percentage";
		},
    	bColor: function() {
    		const alarms = this.$props.sensor.alarms;
    		if (Array.isArray(alarms)) {
				const activeAlarms = alarms.filter(function (a) {
					return	a.alarmActivityState === "ON";
				});
    			if (activeAlarms.length !== 0) {
					switch (activeAlarms[0].alarmStatus) {
						case "INFO":
							return "info-back";
						case "WARNING":
							return "warn-back";
						case "ERROR":
							return "err-back";
					}
				} else {
					return "info-back";
				}
			}
		}
	}
});

Vue.component('d-sensor', {
    props: ["sensor"],
	template: '<div class="d-sensor">' +
	'<p>{{sensor.imei}}</p>' +
	'<div class="discreete-body discreete-set info-back" v-if="sensor.value!=0"></div>' +
	'<div class="discreete-body discreete-unset" v-if="sensor.value==0"></div>' +
    '</div>'
});


Vue.component('sensor-node', {
    props: ["node", "timestamp"],
	template: '<div class="sensor-node"><p> {{ node.node }} &nbsp; {{stateTime}}</p>' +
	'<ul>' + 
    '<li v-for="sensor in node.sensors">' +
    '<d-sensor :sensor=sensor :timestamp=timestamp v-if="sensor.type==0"/>' +
    '<a-sensor :sensor=sensor :timestamp=timestamp v-if="sensor.type==1"/>' +
    '</li>' +
    '</ul>' +	
	'</div>',
	computed: {
    	stateTime: function () {
			//return new Date(this.$props.timestamp * 1000).toISOString().slice(-13, -5);
			return new Date(this.$props.timestamp).toISOString();
		}
	}
});



Vue.component('sensor-nodes', {
    template: '<ul>' + 
    '<li v-for="node in nodes">' +
    '<sensor-node :node=node :timestamp=stateTimestamp></sensor-node>' +
    '</li>' +
    '</ul>',
    
    
    computed: {
    	nodes: function() {
    		return this.$store.getters.nodes();
    	},
		stateTimestamp: function() {
			return this.$store.getters.stateTimestamp();
		}
    }
});
