;const SENSORS = new Vuex.Store({
	state: {
		snapshotState: [],
		nodes: [],
		alarms: []
	},
	actions: {
		updateSnapshotState:function (context, payload) {
			context.commit("UPDATE_SNAPSHOT_STATE", payload);
		},
		updateSensors:function (context, payload) {
			context.commit("UPDATE_NODES", payload);
		},
		updateAlarms:function (context, payload) {
			context.commit("UPDATE_ALARMS", payload);
		}
	},
	mutations: {
		UPDATE_SNAPSHOT_STATE: function(state, snapshotState) {
			state.snapshotState = snapshotState;
		},
		UPDATE_NODES: function(state, nodes) {
			if (Array.isArray(nodes)) {
				state.nodes = nodes;
			} else {
				state.nodes = [nodes];
			}

		},
		UPDATE_ALARMS: function(state, alarms) {
			if (Array.isArray(alarms)) {
				state.alarms = alarms;
			} else {
				state.alarms = [alarms];
			}

		}
	},
	getters: {
		alarms: function(state) {
			return function() {
				return state.alarms;
			}
		},
		nodes: function(state) {
			return function() {
				return state.nodes;
			}
		},
		snapshotState: function(state) {
			return function() {
				return state.snapshotState;
			}
		},
		stateTimestamp: function (state) {
			return function() {
				return state.snapshotState.timestamp;
			}
		}
	}
}); 