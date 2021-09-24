<template>
  <q-page class="column items-center">
    <h4>Maior Nota</h4>
    <div class="certificate">
      <h6>
        A maior nota é
        <h4>{{ grade }}</h4>
        do aluno
        <h4>{{ name }}</h4>
      </h6>
    </div>
    <br><br><br>
    <q-btn color="black" to="/" exact label="Voltar"/>
  </q-page>
</template>

<script>
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'HighestGrade',
  data() {
    return {
      name: "???",
      grade: 0,
    }
  },
  created() {
    this.$store.dispatch('general/setMessage', "Selecione uma das opções abaixo:");
    this.$api
    .get('/alunos/maiornota')
    .then(response => {
      this.name = response.data[0]
      this.grade = response.data[1]
    })
    .catch(error => {
      alert("A conexão com o backend falhou, tente novamente mais tarde ou contate o administrador.")
      console.log("API error: "+error)
    })
  }
})
</script>
