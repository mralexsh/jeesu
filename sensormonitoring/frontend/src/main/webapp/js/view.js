Vue.component('a-sensor', {
    props: ["sensor"],
	template: '<div class="a-sensor">' +
	'<p> ANALOG SENSOR {{sensor.imei}}</p>' +
	'<div class="analog-body">' +
	'<div class="analog-body-percentage" v-bind:style="{width: sensor.value + \'%\'}">' +	
	'</div>' +
	'</div>' +
    '</div>'
});

Vue.component('d-sensor', {
    props: ["sensor"],
	template: '<div class="d-sensor">' +
	'<p> DISCREETE SENSOR {{sensor.imei}}</p>' +
	'<div class="discreete-body discreete-set" v-if="sensor.value!=0"></div>' +
	'<div class="discreete-body discreete-unset" v-if="sensor.value==0"></div>' +
    '</div>'
});


Vue.component('sensor-node', {
    props: ["node"],
	template: '<div class="sensor-node"><p> SUPER {{ node.node }} </p>' +
	'<ul>' + 
    '<li v-for="sensor in node.sensors">' +
    '<d-sensor :sensor=sensor v-if="sensor.type==0"/>' +
    '<a-sensor :sensor=sensor v-if="sensor.type==1"/>' +
    '</li>' +
    '</ul>' +	
	'</div>'
});



Vue.component('sensor-nodes', {
    template: '<ul>' + 
    '<li v-for="node in nodes">' +
    '<sensor-node :node=node></sensor-node>' +
    '</li>' +
    '</ul>',
    
    
    computed: {
    	nodes: function() {
    		return this.$store.getters.nodes();
    	}
    }
});
