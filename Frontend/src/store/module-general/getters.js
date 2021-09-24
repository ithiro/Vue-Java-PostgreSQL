export function getId(state) {
    return state.id
}

export function getName(state) {
    return state.name
}

export function getAluno(state) {
    var aluno = []
    aluno.push(state.id)
    aluno.push(state.name)
    return aluno
}

export function getMessage(state) {
    return state.message
}