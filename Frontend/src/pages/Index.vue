<template>
  <q-page class="column items-center">
    <h4>{{ message }}</h4>
    <q-list class="column items-center">
      <q-item><q-btn color="primary" to="/cadastrar" exact label="Cadastrar Aluno"/></q-item>
      <q-item><q-btn color="primary" to="/listar" exact label="Listar Alunos"/></q-item>
      <q-item><q-btn color="primary" to="/maiornota" exact label="Ver maior Nota"/></q-item>
    </q-list>
  </q-page>
</template>

<script>
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'PageIndex',
  data () {
    return {
      message: String
    }
  },
  created() {
    this.message = this.$store.getters['general/getMessage']
  }
})
</script>
