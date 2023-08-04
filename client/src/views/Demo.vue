<template>
<div class="q-pa-sm">
    <!-- 編輯彈出框  -->
    <q-dialog v-model="editDialog" persistent>
         <q-card style="width: 700px; max-width: 80vw;">
            <q-card-section>
              <span class="text-h6">Edit Prod Information</span>
           </q-card-section>
            <!-- 編輯框的輸入欄位 -->
            <q-card-section>
                <q-input v-model="request.saveProd.data.prodId" label="Prod ID" type="number" disable v-show="request.saveProd.data.prodId" />
                <q-input v-model="request.saveProd.data.restaurantId" label="Restaurant ID" type="number" clearable />
                <q-input v-model="request.saveProd.data.prodName" label="Prod Name" clearable />
                <q-input v-model="request.saveProd.data.prodText" label="Prod Text" clearable />
                <q-input v-model="request.saveProd.data.prodUserguide" label="Prod Userguide" clearable />
                <q-input v-model="request.saveProd.data.prodPrice"  label="Prod Price" type="number" clearable />
                <q-input v-model="request.saveProd.data.prodQty" label="Prod Qty" type="number" clearable />
                <q-input v-model="request.saveProd.data.prodState" label="Prod State" type="number" clearable />
                <div style="margin: 10px;"></div>
                <!-- <q-uploader v-model="request.saveProd.data.prodPic" label="Prod Pic" accept=".jpg, image/*" @input="convertImageToBase64" url="prod/Prod/saveprod"  /> -->
                <div class="col-12 col-md-6">
                  <div style="margin: 10px;"></div>
                  <q-card style="width: 50%;">
                   <q-card-section class="text-subtitle2">
                      <div @click="choosepicture" style="cursor: pointer">
                        <span style="cursor: pointer">Prod Pic</span>
                        <input hidden ref="fileInput" type="file" @change="onSelectFile" />
                      </div>
                      <div class="text-caption" style="cursor: pointer">(Click to Choose Picture)</div>
                      <q-img v-if="imageData" :src="imageData" style="cursor: pointer;width: 290px;  height: 170px" @click="choosepicture" />
                    </q-card-section>
                  </q-card>
                </div>
            </q-card-section>
            <!-- 編輯框的功能按鈕 -->
            <q-card-actions align="right">
                <q-btn flat label="Cancel" color="red" v-close-popup @click="newProd={}" />
                <q-btn flat label="Confirm" color="primary" v-close-popup @click="callSaveProd" />
            </q-card-actions>
        </q-card>
   </q-dialog>
    <!-- 搜尋輸入區塊 -->
    <div class="row q-col-gutter-xs">
        <div class="col-12 col-md-6">
         <q-input label="Product Name" filled clearable v-model='request.getProds.params.prodName'></q-input>
        </div>
        <div class="col-12 col-md-6">
         <q-input label="Restaurant Name" filled clearable v-model='request.getProds.params.resName'></q-input>
        </div>
        <div class="col-12 col-md-6">
         <q-input label="Price From" filled clearable type="number" v-model='request.getProds.params.prodPriceFrom'></q-input>
        </div>
        <div class="col-12 col-md-6">
         <q-input label="Price To" filled clearable type="number" v-model='request.getProds.params.prodPriceTo'></q-input>
        </div>
        <div class="col-12 col-md-6">
         <q-input label="Comment Score From" filled clearable type="number" v-model='request.getProds.params.prodCommentScoreFrom'></q-input>
        </div>
         <div class="col-12 col-md-6">
         <q-input label="Comment Score To" filled clearable type="number" v-model='request.getProds.params.prodCommentScoreTo'></q-input>
        </div>
        <div class="col-12 col-md-6">
        <q-input label="Restaurant Type" filled clearable v-model='request.getProds.params.resType'></q-input>
         </div>
        <div class="col-12 col-md-6">
         <q-input label="Restaurant Address" filled clearable v-model='request.getProds.params.resAdd'></q-input>
        </div>
  </div>
     <q-separator spaced />
    <!-- 按鈕區塊 -->
     <div class="row q-col-gutter-xs">
       <div class="col-12 col-md-2">
          <q-btn label='SEARCH PRODS' class="full-width" @click='callGetProds' />
       </div>
       <div class="col-12 col-md-2">
         <q-btn label='SAVE PRODS' class="full-width" @click='editDialog=true'/>
       </div>
       <div class="col-12 col-md-2 offset-md-4">
         <q-btn label='UPDATE PRODS' class="full-width" @click='updateOpenDialog' v-show="selected.length!=0"/>
       </div>
       <div class="col-12 col-md-2">
         <q-btn label='DELETE PRODS' class="full-width" @click='callDeleteProd' v-show="selected.length!=0"/>
       </div>
     </div>
       <q-separator spaced />
    <div class="q-pa-md row items-start justify-center q-gutter-md" style="border:1px">
      <q-card v-for="(prod, index) in prods" :key="index" class="my-card">
        <div>
          <q-checkbox v-model="prod.selected" @input="handleCheckboxClick(prod)" />
        </div>
        <q-img :src="prod.prodPicBase64" :style="{ width: '100%', height: '170px' }" />
        <q-card-section>
          <div class="row no-wrap items-center">
            <div class="col text-h6 ellipsis">{{ prod.prodId }}・{{ prod.prodName }}</div>
          </div>
          <q-rating :value="prod.prodCommentScore || 0" :max="5" size="28px" color="orange" @input="prod.prodCommentScore = $event" />
        </q-card-section>
        <q-card-section class="q-pt-none">
          <div class="text-subtitle1">餐廳：{{ prod.resName }}</div>
          <div class="text-subtitle1">價格：${{ prod.prodPrice }}</div>
          <div class="text-caption text-grey ">地址：{{ prod.resAdd }}</div>
          <div class="text-caption text-grey">餐廳分類：{{ prod.resType }}</div>
          <div class="text-caption text-grey">餐券描述：{{ prod.prodText }}</div>
        </q-card-section>
      </q-card>
    </div>
   <div class="q-pa-lg float-right">
    <div class="q-gutter-md">
       <span>共有 {{pagination.rowsNumber}} 資料</span>
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
        },
        getProds: {
          method: 'GET',
          url: '/prod/Prod/getprod',
          params: {
          }
        },
        saveProd: {
          method: 'POST',
          url: '/prod/Prod/saveprod',
          data: {
            prodPic: null
          }
        },
        deleteProd: {
          method: 'POST',
          url: '/prod/Prod/deleteprod',
          data: {}
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
      selected: [],
      editDialog: false,
      imageData: null
    }
  },
  created () {
    this.callGetProds()
  },
  methods: {
    // 呼叫所有產品
    async callGetAllProds () {
      try {
        this.request.getAllProds.params.page = this.pagination.page - 1
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
          const prodPicBase64 = URL.createObjectURL(blob)
          return { ...prod, prodPicBase64, selected: false }
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
      this.callGetProds()
    },
    // 搜尋商品
    async callGetProds () {
      this.current = 1
      try {
        this.request.getProds.params.page = this.pagination.page - 1
        this.request.getProds.params.size = this.pagination.rowsPerPage
        let response = await axios(this.request.getProds)
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
          const prodPicBase64 = URL.createObjectURL(blob)
          return { ...prod, prodPicBase64, selected: false }
        })
        // 回傳後更新 pagination 資料
        this.pagination.rowsNumber = response.data.totalElements
        this.pagination.rowsPerPage = response.data.size
        this.pagination.page = response.data.number + 1
      } catch (error) {
        console.error('無法獲取資料:', error)
      }
    },

    // 新增或修改商品資料
    async callSaveProd () {
      try {
        this.request.saveProd.data.prodPicBase64 = this.prodPicBase64
        let response = await axios(this.request.saveProd)
        alert(`Employee ID: ${response.data.prodId}\nName: ${response.data.prodName}\nhas been saved successfully!`)
        // 新增或修改成功後重新載入商品列表
        this.callGetAllProds()
      } catch (error) {
        alert('Save Prod Faild')
        console.log(error)
      }
      this.request.saveProd.data = {}
    // },
    // // 處理新增或修改商品時的圖片存取
    // convertImageToBase64 (files) {
    //   console.log('有觸發')
    //   if (files.length > 0) {
    //     const imageFile = files[0]
    //     console.log(imageFile)
    //     let reader = new FileReader()
    //     reader.onload = (e) => {
    //       this.request.saveProd.data.prodPicBase64 = e.target.result.split(',')[1]
    //     }
    //     reader.readAsDataURL(imageFile)
    //   }
    },
    // 刪除商品
    async callDeleteProd () {
      this.request.deleteProd.data.prodId = this.selected[0].prodId
      try {
        await axios(this.request.deleteProd)
        alert(`Prod ID: ${this.request.deleteProd.data.prodId}\nhas been deleted.`)
        // 刪除成功後重新載入商品列表
        this.callGetAllProds()
      } catch (error) {
        alert('Delete Failed')
        console.log(error)
      }
    },
    // 勾選商品判斷綁定ID
    handleCheckboxClick (prod) {
      if (prod.selected) {
        this.selected.push(prod)
      } else {
        const index = this.selected.findIndex((item) => item.prodId === prod.prodId)
        if (index !== -1) {
          this.selected.splice(index, 1)
        }
      }
    },
    // 修改商品跳彈窗
    updateOpenDialog () {
      this.editDialog = true
      this.request.saveProd.data = Object.assign({}, this.selected[0])
      // 如果已有圖片，顯示圖片檔案
      if (this.request.saveProd.data.prodPicBase64) {
        this.imageData = this.request.saveProd.data.prodPicBase64
      } else {
        this.imageData = null
      }
    },
    onSelectFile () {
      const file = event.target.files[0]
      if (file) {
        const reader = new FileReader()
        reader.onload = e => {
          this.imageData = e.target.result
        }
        reader.readAsDataURL(file)
      }
    },
    choosepicture () {
      this.$refs.fileInput.click()
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
