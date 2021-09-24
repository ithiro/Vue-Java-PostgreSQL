export function setAluno({ commit }, {id, name}) {
    commit('SET_ID', id)
    commit('SET_NAME', name)
}

export function setMessage({ commit }, message) {
    commit('SET_MESSAGE', message)
}