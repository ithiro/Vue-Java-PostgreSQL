<template>
  <q-page class='column items-center'>
    <h4>Cadastrar novo aluno</h4>
    <form @submit.prevent='onSubmit'>
      <q-input v-model='name' label="Nome" required/>
      <br>
      <q-list class='column items-center'>
        <q-item><q-btn color='primary' type='submit' label="Cadastrar"/></q-item>
        <q-item><q-btn color='black' to='/' exact label="Voltar"/></q-item>
      </q-list>
    </form>
  </q-page>
</template>

<script>
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'RegisterStudent',
  data() {
    return {
      name: null
    }
  },
  methods: {
    onSubmit() {
      this.$api
      .post('/alunos/cadastrar?nome='+this.name)
      .then(response => {
        if (response.data) {this.$store.dispatch('general/setMessage', "Aluno cadastrado com sucesso!");}
        else {this.$store.dispatch('general/setMessage', "Falha no cadastro do aluno.");}
        this.$router.push('/')
      })
      .catch(error => {
        alert("A conexão com o backend falhou, tente novamente mais tarde ou contate o administrador.")
        console.log("API error: "+error)
      })
    }
  },
  created() {
    this.$store.dispatch('general/setMessage', "Selecione uma das opções abaixo:");
  }
})
</script>
