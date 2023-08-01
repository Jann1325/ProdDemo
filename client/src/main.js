import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './quasar'
import axios from 'axios'
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
// import 'jquery/dist/jquery.min'
// import 'bootstrap/dist/css/bootstrap.css'
// import * as VueGoogleMaps from 'vue2-google-maps'
// import Map from './components/Map.vue'
Vue.prototype.$http = axios
Vue.config.productionTip = false

new Vue({
  router,
  store,
  Header,
  Footer,
  Map,
  render: h => h(App)
}).$mount('#app')

// Vue.use(VueGoogleMaps, {
//   load: {
//     key: 'AIzaSyC0v5UHF0QfIwz00Y-ax_2oS8EnH3zyuss',
//     libraries: 'places'
//   },
//   installComponents: true
// })
