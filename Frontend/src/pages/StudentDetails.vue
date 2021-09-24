<template>
  <q-page class="column items-center">
    <h4>{{ message }}</h4>
    <StudentGrading
      :name="name"
      :average="average"
      :highest="highest"
      :lowest="lowest"
      :tests="tests"
    />
    <br>
    <q-list class="column items-center">
       <q-item><q-btn color="primary" to="/listar/detalhes/prova" exact label="Cadastrar Prova para este aluno"/></q-item>
       <q-item><q-btn color="black" to="/listar" exact label="Voltar"/></q-item>
    </q-list>
  </q-page>
</template>

<script>
import StudentGrading from 'components/StudentGrading.vue'
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'StudentDetails',
  components: {
    StudentGrading
  },
  data() {
    return {
      id: Number,
      name: String,
      message: String,
      average: "0.00",
      highest: 0,
      lowest: 0,
      tests: 0
    }
  },
  created() {
    this.message = this.$store.getters['general/getMessage']

    let aluno = this.$store.getters['general/getAluno']
    this.id = aluno[0]
    this.name = aluno[1]
    if ((typeof this.id === 'number') && (typeof this.name === 'string')) {
      this.$api
      .get('/alunos/'+this.id+'/medianota')
      .then(response => {this.average = response.data.toFixed(2)})
      .catch(error => {
        console.log("API: "+error)
        this.$router.push("/listar")
      })

      this.$api
      .get('/alunos/'+this.id+'/maiornota')
      .then(response => {this.highest = response.data})
      .catch(error => {
        console.log("API: "+error)
        this.$router.push("/listar")
      })

      this.$api
      .get('/alunos/'+this.id+'/menornota')
      .then(response => {this.lowest = response.data
      })
      .catch(error => {
        console.log("API: "+error)
        this.$router.push("/listar")
      })

      this.$api
      .get('/alunos/'+this.id+'/numeroprovas')
      .then(response => {this.tests = response.data})
      .catch(error => {
        console.log("API: "+error)
        this.$router.push("/listar")
      })
    } else {this.$router.push("/")}
  }
})
</script>
