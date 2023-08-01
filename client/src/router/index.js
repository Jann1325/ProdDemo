import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

// const routes = [
//   {
//     path: '/',
//     name: 'Home',
//     component: Home
//   },
//   {
//     path: '/about',
//     name: 'About',
//     // route level code-splitting
//     // this generates a separate chunk (about.[hash].js) for this route
//     // which is lazy-loaded when the route is visited.
//     component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
//   },
//   {
//     path: '/mall',
//     name: 'mall',
//     component: () => import('../views/Mall.vue')
//   },
//   {
//     path: '/prod',
//     name: 'prod',
//     component: () => import('../views/Mall_Prod.vue')
//   }
// ]

// const router = new VueRouter({
//   routes
// })

export default new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/about',
      name: 'About',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
    },
    {
      path: '/mall',
      name: 'mall',
      component: () => import('../views/Mall.vue')
    },
    {
      path: '/prod/:id/',
      name: 'prod',
      component: () => import('../views/Mall_Prod.vue')
    },
    {
      path: '/demo',
      name: 'demo',
      component: () => import('../views/Demo.vue')
    }
  ]
})
