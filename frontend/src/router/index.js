import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

import store from '@/store'

import TotalSetting from '../components/ceo/TotalSetting.vue'
import HotelReadPage from '@/views/hotelDetail/HotelReadPage.vue'




Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/member/MemberLogin.vue')
  },
  {
    path: '/signup',
    name: 'SignUp',
    component: () => import('@/views/member/MemberSignUp.vue')
  },
  {
    path: '/oauth2/redirect',
    name: 'OAuth2Redirect',
    component: () => import('../views/member/OAuth2Redirect.vue')
  },
  {
    path: '/HotelListPage',
    name: 'HotelListPage',
    component: () => import('../views/hotel/HotelListPage.vue')
  },
  {
    path: '/hotelRegister',
    name: 'HotelRegisterPage',
    component: () => import('../views/hotel/HotelRegisterPage.vue')
  },
  {
    path: '/mypage',
    name: 'MyPage',
    component: () => import('../views/mypage/MyPage.vue')
  },
  {
    path: '/roomRegister',
    name: 'RoomRegisterPage',
    component: () => import('../views/hotel/RoomRegisterPage.vue')
  },
  {

    path: '/calendar',
    name: 'HotelCalendar',
    component: () => import('../components/ceo/HotelCalendar.vue')
  },
  {
    path: '/total',
    name: 'TotalSetting',
    components: {
      default: TotalSetting
    },
    props: {
      default: true
    }
  },
  {
    path: '/hotelReadPage/:hotelNo',
    name: 'HotelReadPage',
    components: {
      default: HotelReadPage
    },
    props: {
      default: true
    }
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


router.beforeEach(function(to,from,next){
  store.dispatch("validate_login")
  next()
})

export default router