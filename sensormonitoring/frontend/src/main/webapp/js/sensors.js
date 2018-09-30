;const SENSORS = new Vuex.Store({
	state: {
		nodes: []
	},
	actions: {
		updateSenors:function (context, payload) {
			context.commit("UPDATE_NODES", payload);
		}
	},
	mutations: {
		UPDATE_NODES: function(state, nodes) {
			state.nodes = nodes
		}
	},
	getters: {
		nodes: function(state) {
			return function() {
				return state.nodes;
			}
		}
	}
}); 