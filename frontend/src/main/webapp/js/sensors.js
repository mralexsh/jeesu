;const SENSORS = new Vuex.Store({
	state: {
		snapshotState: [],
		nodes: []
	},
	actions: {
		updateSnapshotState:function (context, payload) {
			context.commit("UPDATE_SNAPSHOT_STATE", payload);
		},
		updateSensors:function (context, payload) {
			context.commit("UPDATE_NODES", payload.state);
		}
	},
	mutations: {
		UPDATE_SNAPSHOT_STATE: function(state, snapshotState) {
			state.snapshotState = snapshotState
		},
		UPDATE_NODES: function(state, nodes) {
			state.nodes = nodes
		}
	},
	getters: {
		nodes: function(state) {
			return function() {
				return state.nodes;
			}
		},
		snapshotState: function(state) {
			return function() {
				return state.snapshotState;
			}
		}
	}
}); 