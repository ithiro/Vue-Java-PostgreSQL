<template>
  <q-page class="column items-center">
    <h4>Cadastrar nova prova</h4>
    <p><b>Aluno {{ name }}</b></p>
    <form @submit.prevent="onSubmit">
      <q-input v-model.number="grade" type="number" step="0.01" min="0" max="100" label="Nota" hint="0 ~ 100"/>
      <br>
      <q-list class="column items-center">
        <q-item><q-btn color="primary" type="submit" label="Cadastrar"/></q-item>
        <q-item><q-btn color="black" to="/listar/detalhes" exact label="Voltar"/></q-item>
      </q-list>
    </form>
  </q-page>
</template>

<script>
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'RegisterTest',
  data() {
    return {
      id: 0,
      name: "",
      grade: null
    }
  },
  methods: {
    onSubmit() {
      this.$api
      .post('/provas/cadastrar?idAluno='+this.id+'&nota='+this.grade)
      .then(response => {
        if (response.data) {this.$store.dispatch('general/setMessage', "Prova cadastrada com sucesso!");}
        else {this.$store.dispatch('general/setMessage', "Falha no cadastro da prova.");}
        this.$router.push("/listar/detalhes")
      })
      .catch(error => {
        alert("A conexão com o backend falhou, tente novamente mais tarde ou contate o administrador.")
        console.log("API error: "+error)
      })
    }
  },
  created() {
    let aluno = this.$store.getters['general/getAluno']
    this.id = aluno[0]
    this.name = aluno[1]
    if ((typeof this.id !== 'number') || (typeof this.name !== 'string')) {this.$router.push("/")}
  }
})
</script>
