
const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', name: "Index", component: () => import('pages/Index.vue') },
      { path: 'cadastrar', name: "Cadastrar", component: () => import('pages/RegisterStudent.vue') },
      { path: 'listar', name: "Listar", component: () => import('pages/ListStudents.vue') },
      { path: 'maiornota', name: "MaiorNota", component: () => import('pages/HighestGrade.vue') },
    ]
  },
  {
    path: '/listar',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: 'detalhes', name: "Detalhes", component: () => import('pages/StudentDetails.vue') },
    ]
  },
  ,
  {
    path: '/listar/detalhes',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: 'prova', name: "Prova", component: () => import('pages/RegisterTest.vue') },
    ]
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/Error404.vue')
  }
]

export default routes
