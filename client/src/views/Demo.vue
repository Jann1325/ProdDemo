<template>
<div class="q-pa-sm">
    <!-- 編輯彈出框  -->
    <!-- 輸入區塊 -->
    <div class="row q-col-gutter-xs">
        <div class="col-12 col-md-6">
         <q-input label="Product Name" filled clearable v-model='request.getAllProds.params.prodName'></q-input>
        </div>
        <div class="col-12 col-md-6">
         <q-input label="Restaurant Name" filled clearable v-model='request.getAllProds.params.resName'></q-input>
        </div>
        <div class="col-12 col-md-6">
         <q-input label="Price" filled clearable v-model='request.getAllProds.params.prodPrice'></q-input>
        </div>
        <div class="col-12 col-md-6">
         <q-input label="Comment Score" filled clearable v-model='request.getAllProds.params.prodCommentScore'></q-input>
        </div>
        <div class="col-12 col-md-6">
        <q-input label="Restaurant Type" filled clearable v-model='request.getAllProds.params.resType'></q-input>
         </div>
        <div class="col-12 col-md-6">
         <q-input label="Restaurant Address" filled clearable v-model='request.getAllProds.params.resAdd'></q-input>
        </div>
  </div>
     <q-separator spaced />
    <!-- 按鈕區塊 -->
     <div class="row q-col-gutter-xs">
       <div class="col-12 col-md-2">
          <q-btn label='SEARCH PRODS' class="full-width" @click='callGetAllProds' />
       </div>
     </div>
       <q-separator spaced />
    <div class="q-pa-md row items-start justify-center q-gutter-md" style="border:1px">
                <q-card v-for="(prod, index) in prods" :key="index" class="my-card">
                     <div>
                        <q-checkbox v-model="prod.selected" />
                     </div>
                    <q-img :src="prod.imageUrl" :style="{ width: '100%', height: '170px' }" />
                    <q-card-section>
                        <div class="row no-wrap items-center">
                            <div class="col text-h6 ellipsis">{{ prod.prodId }}・{{ prod.resName }}</div>
                        </div>
                        <q-rating :value="prod.prodCommentScore || 0" :max="5" size="28px" color="orange" @input="prod.prodCommentScore = $event" />
                    </q-card-section>
                    <q-card-section class="q-pt-none">
                        <div class="text-subtitle1">${{ prod.prodPrice }}</div>
                        <div class="text-caption text-grey ">{{ prod.resAdd }}</div>
                        <div class="text-caption text-grey">{{ prod.resType }}</div>
                        <div class="text-caption text-grey">{{ prod.prodText }}</div>
                    </q-card-section>
                    <q-separator />
                    <q-card-actions>
                        <!-- <q-btn flat round icon="event" /> -->
                        <q-btn class="" flat color="primary">Edit</q-btn>
                    </q-card-actions>
                </q-card>
    </div>
   <div class="q-pa-lg float-right">
    <div class="q-gutter-md">
      <q-pagination
        v-model="current"
       :per-page="pagination.rowsPerPage"
       :max="Math.ceil(pagination.rowsNumber / pagination.rowsPerPage)"
        direction-links
        flat
        color="grey"
        active-color="primary"
        @input="alterPagination"
      />
    </div>
   </div>
</div>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      request: {
        getAllProds: {
          method: 'GET',
          url: '/prod/Prod/getall',
          params: {
          }
        }
      },
      prods: [],
      stars: '',
      current: 1,
      pagination: {
        rowsNumber: 0,
        rowsPerPage: 8,
        page: 1
      },
      selected: []
    }
  },
  created () {
    this.callGetAllProds()
  },
  methods: {
    // 呼叫所有產品
    async callGetAllProds () {
      try {
        this.request.getAllProds.params.page = this.pagination.page - 1 // 因為後端頁數是從 0 開始的，所以需要減 1
        this.request.getAllProds.params.size = this.pagination.rowsPerPage
        let response = await axios(this.request.getAllProds)
        this.prods = response.data.content.map((prod) => {
        // 處理圖片格式
          const base64String = prod.prodPic
          const binaryString = atob(base64String)
          const len = binaryString.length
          const bytes = new Uint8Array(len)
          for (let i = 0; i < len; ++i) {
            bytes[i] = binaryString.charCodeAt(i)
          }
          const blob = new Blob([bytes], { type: 'image/jpeg' })
          const imageUrl = URL.createObjectURL(blob)
          return { ...prod, imageUrl }
        })
        // 回傳後更新 pagination 資料
        this.pagination.rowsNumber = response.data.totalElements
        this.pagination.rowsPerPage = response.data.size
        this.pagination.page = response.data.number + 1
      } catch (error) {
        console.error('無法獲取資料:', error)
      }
    },
    alterPagination (args) {
      this.pagination.page = args
      this.callGetAllProds()
    }
  }
}
</script>

<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 290px
  height: 450px
</style>
