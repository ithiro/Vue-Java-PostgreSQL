<template>
  <q-page class="column items-center">
    <h4>Alunos</h4>
    <q-list v-for="aluno in alunos" :key="aluno.id" class="column items-center">
      <q-item>
        <q-btn
          :label="aluno.name"
          @click="goToDetails(aluno.id, aluno.name)"
          exact
        />
      </q-item>
    </q-list>
    <br><br>
    <q-btn color="black" to="/" exact label="Voltar"/>
    <br><br>
  </q-page>
</template>

<script>
import { defineComponent } from 'vue';
import { mapActions } from 'vuex';

export default defineComponent({
  name: 'ListStudents',
  methods: {
    goToDetails(id, name) {
      this.$store.dispatch('general/setMessage', "Detalhes do Aluno");
      this.$store.dispatch('general/setAluno', {id, name})
      this.$router.push("/listar/detalhes")
    }
  },
  data() {
    return {
      alunos: []
    }
  },
  created() {
    this.$store.dispatch('general/setMessage', "Selecione uma das opções abaixo:");
    this.$api
    .get('/alunos')
    .then(response => {this.alunos = response.data})
    .catch(error => {
      alert("A conexão com o backend falhou, tente novamente mais tarde ou contate o administrador.")
      console.log("API error: "+error)
    })
  }
})
</script>
