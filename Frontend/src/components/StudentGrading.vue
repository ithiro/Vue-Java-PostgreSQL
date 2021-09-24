<template>
  <q-list bordered padding>
    <q-item>
      <q-item-section>Aluno</q-item-section>
      <q-item-section side>{{ name }}</q-item-section>
    </q-item>
    <q-separator/>
    <q-item>
      <q-item-section>Média</q-item-section>
      <q-item-section side>{{ average }}</q-item-section>
    </q-item>
    <q-item>
      <q-item-section>Maior nota</q-item-section>
      <q-item-section side>{{ highest }}</q-item-section>
    </q-item>
    <q-item>
      <q-item-section>Menor nota</q-item-section>
      <q-item-section side>{{ lowest }}</q-item-section>
    </q-item>
    <q-separator/>
    <q-item>
      <q-item-section>Provas realizadas</q-item-section>
      <q-item-section side>{{ tests }}</q-item-section>
    </q-item>
  </q-list>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'StudentGrading',
  props: {
    name: {
      type: String,
      default: ""
    },
    average: {
      type: String,
      default: "0.00"
    },
    highest: {
      type: Number,
      default: 0
    },
    lowest: {
      type: Number,
      default: 0
    },
    tests: {
      type: Number,
      default: 0
    },
  }
})
</script>
