<template>
<div>
 <Header />
<div class="main_content">
            <div class="container">
                <div class="row">
                    <div class="breadcrumbandSearch">
                        <nav class="breadcrumbandSearch"
                            style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a
                                    href="../restaurant/Homepage.html">主頁</a></li>
                                <li class="breadcrumb-item active" aria-current="page">找餐券</li>
                            </ol>
                        </nav>
                        <div class="search_area">
                          <input type="text" v-model="search" class="search_text" name="search" @keyup="handleKeyUp">
                          <button @click="callGetBySearch" class="search_button"></button>
                        </div>
                    </div>
                </div>
                <div id="hotCategoryandSort" class="hotCategoryandSort">
                    <div class="hotCategoryArea">
                        <span class="hotCategoryTitle">熱門類別</span>
                        <button class="hotCategory" @click="callGetByCategory('西式')">西式</button>
                        <button class="hotCategory" @click="callGetByCategory('景觀餐廳')">景觀餐廳</button>
                        <button class="hotCategory" @click="callGetByCategory('中式')">中式</button>
                        <button class="hotCategory" @click="callGetByCategory('小吃')">小吃</button>
                        <button class="hotCategory" @click="callGetByCategory('早午餐')">早午餐</button>
                    </div>
                    <div class="sort">
                        <div class="sortBy">排序方式</div>
                        <select  id="sort-select" class="sorting" @change="handleSortChange">
                            <option value="" selected >請選擇排序方式</option>
                            <option value="star">評分最高</option>
                            <option value="pricefromhigh">價格由高到低</option>
                            <option value="pricefromlow">價格由低到高</option>
                        </select>
                    </div>
                </div>
                <div class="parent_container">

                    <aside class="aside">
                      <div class="category">分類</div>
                        <hr />
                        <div class="dropdown">
                          <button class="btn btn-secondary dropdown-toggle categoryDetail" @click="toggleDropdown(1)" data-bs-toggle="dropdown" aria-expanded="false">菜系</button>
                          <ul class="dropdown-menu" v-show="showDropdown1" aria-labelledby="dropdownMenuButton1">
                            <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('西式')">西式</a></li>
                            <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('韓式')">韓式</a></li>
                            <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('中式')">中式</a></li>
                            <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('港式')">港式</a></li>
                            <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('日式')">日式</a></li>
                          </ul>
                        </div>
                        <div class="dropdown">
                          <button class="btn btn-secondary dropdown-toggle categoryDetail"
                            type="button" id="dropdownMenuButton1" @click="toggleDropdown(2)"
                            aria-expanded="false">時段</button>
                          <ul class="dropdown-menu" v-show="showDropdown2" aria-labelledby="dropdownMenuButton1">
                            <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('早餐')">早餐</a></li>
                            <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('午餐')">午餐</a></li>
                            <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('早午餐')">早午餐</a></li>
                            <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('晚餐')">晚餐</a></li>
                            <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('宵夜')">宵夜</a></li>
                          </ul>
                        </div>
                        <div class="dropdown">
                            <button class="btn btn-secondary dropdown-toggle categoryDetail"
                                type="button" id="dropdownMenuButton1" @click="toggleDropdown(3)"
                                aria-expanded="false">西式</button>
                            <ul class="dropdown-menu" v-show="showDropdown3" aria-labelledby="dropdownMenuButton1">
                              <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('漢堡')">漢堡</a></li>
                              <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('披薩')">披薩</a></li>
                              <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('牛排')">牛排</a></li>
                              <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('沙拉')">沙拉</a></li>
                              <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('水餃')">水餃</a></li>
                              <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('餐酒館')">餐酒館</a></li>
                              <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('義大利麵')">義大利麵</a></li>
                            </ul>
                        </div>
                        <div class="dropdown">
                          <button class="btn btn-secondary dropdown-toggle categoryDetail"
                            type="button" id="dropdownMenuButton1" @click="toggleDropdown(4)"
                            aria-expanded="false">日式</button>
                          <ul class="dropdown-menu" v-show="showDropdown4" aria-labelledby="dropdownMenuButton1">
                           <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('壽司')">壽司</a></li>
                           <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('拉麵')">拉麵</a></li>
                           <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('鐵板燒')">鐵板燒</a></li>
                           <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('壽喜燒')">壽喜燒</a></li>
                           <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('咖哩')">咖哩</a></li>
                           <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('居酒屋')">居酒屋</a></li>
                           <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('定食')">定食</a></li>
                          </ul>
                        </div>
                        <div class="dropdown">
                          <button class="btn btn-secondary dropdown-toggle categoryDetail" type="button" id="dropdownMenuButton1" @click="toggleDropdown(5)"
                            aria-expanded="false">中式</button>
                          <ul class="dropdown-menu" v-show="showDropdown5" aria-labelledby="dropdownMenuButton1">
                            <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('火鍋')">火鍋</a></li>
                            <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('燒烤')">燒烤</a></li>
                            <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('合菜')">合菜</a></li>
                            <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('麵食')">麵食</a></li>
                            <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('滷味')">滷味</a></li>
                            <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('牛肉麵')">牛肉麵</a></li>
                            <li><a class="dropdown-item categoryDetail" href="#" @click="callGetByCategory('粥')">粥</a></li>
                          </ul>
                        </div>
                          <button class="categoryDetail"  @click="callGetByCategory('飲料')">飲料</button>
                          <button class="categoryDetail"  @click="callGetByCategory('火鍋')">小吃</button>
                          <button class="categoryDetail"  @click="callGetByCategory('海鮮')">海鮮</button>
                          <button class="categoryDetail"  @click="callGetByCategory('素食')">素食</button>
                          <button class="categoryDetail"  @click="callGetByCategory('便當')">便當</button>
                          <button class="categoryDetail"  @click="callGetByCategory('炸物')">炸物</button>
                          <button class="categoryDetail"  @click="callGetByCategory('健康餐')">健康餐</button>
                          <button class="categoryDetail"  @click="callGetByCategory('自助餐')">自助餐</button>
                          <button class="categoryDetail"  @click="callGetByCategory('吃到飽')">吃到飽</button>
                          <button class="categoryDetail"  @click="callGetByCategory('咖啡廳')">咖啡廳</button>
                          <button class="categoryDetail"  @click="callGetByCategory('素食')">素食</button>
                          <button class="categoryDetail"  @click="callGetByCategory('東南亞')">東南亞</button>
                          <button class="categoryDetail"  @click="callGetByCategory('越南料理')">越南料理</button>
                          <button class="categoryDetail"  @click="callGetByCategory('印度料理')">印度料理</button>
                          <button class="categoryDetail"  @click="callGetByCategory('韓式炸雞')">韓式炸雞</button>
                          <button class="categoryDetail"  @click="callGetByCategory('約會餐廳')">約會餐廳</button>
                          <button class="categoryDetail"  @click="callGetByCategory('家庭餐廳')">家庭餐廳</button>
                          <button class="categoryDetail"  @click="callGetByCategory('景觀餐廳')">景觀餐廳</button>
                          <button class="categoryDetail"  @click="callGetByCategory('高級餐廳')">高級餐廳</button>
                          <button class="categoryDetail"  @click="callGetByCategory('親子餐廳')">親子餐廳</button>
                          <button class="categoryDetail"  @click="callGetByCategory('寵物餐廳')">寵物餐廳</button>
                          <button class="categoryDetail"  @click="callGetByCategory('甜點')">甜點</button>
                          <button class="categoryDetail"  @click="callGetByCategory('冰品')">冰品</button>
                          <button class="categoryDetail"  @click="callGetByCategory('其他')">其他</button>
                    </aside>

                    <main class="main">
                        <div v-if="isLoading" id="loadingContainer" class="loading">
                          <div class="loading-container">
                            <div class="spinner"></div>
                            <h2>Loading...</h2>
                          </div>
                        </div>
                        <div v-else v-html="content" id="content"></div>
                    </main>
                </div>
            </div>
          <div class="page">
            <nav aria-label="Page navigation example">
              <ul class="pagination justify-content-end" id="pagination">
                <li class="page-item" :class="{ disabled: !pagination.hasPrev}">
                  <a class="page-link" href="#" @click="goToPage(pagination.page - 1)">上一頁</a>
                </li>
                <li class="page-item" v-for="page in pagination.totalPages" :key="page"
                  :class="{ active: page === pagination.page }">
                  <a class="page-link" href="#" @click="goToPage(page)">{{ page }}</a>
                </li>
                <li class="page-item" :class="{ disabled: !pagination.hasNext }">
                  <a class="page-link" href="#" @click="goToPage(pagination.page + 1)">下一頁</a>
                </li>
              </ul>
            </nav>
          </div>
        </div>
<Footer />
</div>
</template>

<script>
import axios from 'axios'
import Header from '@/components/Header.vue'
import Footer from '@/components/Footer.vue'
export default {
  components: {
    Header,
    Footer
  },
  data () {
    return {
      request: {
        getAllProds: {
          method: 'GET',
          url: '/prod/Prod/getall',
          params: {
            size: 8
          }
        },
        getByCategory: {
          method: 'GET',
          url: '/prod/Prod/getByCategory',
          params: {
            size: 8,
            category: ''
          }
        },
        getBySearch: {
          method: 'GET',
          url: '/prod/Prod/getByCompositeQuery',
          params: {
            size: 8,
            search: ''
          }
        },
        getSortBy: {
          method: 'GET',
          url: '/prod/Prod/sortBy',
          params: {
            size: 8,
            sortParam: ''
          }
        }
      },
      prods: [],
      content: '',
      isLoading: true,
      pagination: {
        page: 1,
        totalPages: 1
      },
      currentPage: 1,
      pageSize: 8,
      showDropdown1: false,
      showDropdown2: false,
      showDropdown3: false,
      showDropdown4: false,
      showDropdown5: false,
      search: ''
    }
  },
  created () {
    this.callGetAllProds()
  },
  methods: {
    // 呼叫所有產品
    async callGetAllProds () {
      try {
        this.isLoading = true
        this.request.getAllProds.params.page = this.currentPage - 1
        const response = await axios(this.request.getAllProds)
        this.prods = response.data.content
        this.pagination.totalPages = response.data.totalPages
        this.pagination.hasNext = !response.data.last
        this.pagination.hasPrev = !response.data.first
        // 呼叫產生產品的方法
        this.generateProductCards()
      } catch (error) {
        console.error('Error fetching products:', error)
      } finally {
        this.isLoading = false
      }
    },
    // 顯示產品
    generateProductCards () {
      this.content = ''
      const startIndex = (this.pagination.page - 1) * this.pageSize
      const endIndex = Math.min(startIndex + this.pageSize, this.prods.length)
      for (let i = startIndex; i < endIndex; i++) {
        const prod = this.prods[i]
        const price = prod.prodPrice.toLocaleString()
        const star = Math.floor(prod.prodCommentScore)
        let starHtml = ''
        for (let i = 1; i <= 5; i++) {
          starHtml += `<span class="star ${i <= star ? '-on' : ''}" data-star="${i}" style="font-size: 20px;"><i class="fas fa-star"></i></span>`
        }
        // 解析 byte[] 字串
        // const uint8Array = new Uint8Array(prod.prodPic)
        // let blob = new Blob([uint8Array], { type: 'image/*' })
        // let imageUrl = URL.createObjectURL(blob)
        // 解析 Base64 字串並轉換成 Uint8Array
        var base64String = prod.prodPic
        var binaryString = atob(base64String)
        var len = binaryString.length
        var bytes = new Uint8Array(len)
        for (let i = 0; i < len; ++i) {
          bytes[i] = binaryString.charCodeAt(i)
        }
        var blob = new Blob([bytes], { type: 'image/jpeg' })
        var imageUrl = URL.createObjectURL(blob)
        this.content += `
        <a href="/prod/${prod.prodId}" value="${prod.prodId}" name="id" data-product-id="${prod.prodId}" class="prodLink">
          <div href="" class="prod">
            <img src="${imageUrl}" />
            <span class="prodTitle">${prod.resName} | ${prod.prodName}</span>
            <div class="type" style="font-size: 18px;">${prod.resType}</div>
            <div class="add" style="font-size: 18px; margin-top: 5px; margin-bottom: 20px;">${prod.resAdd}</div>
            <span style="font-size: 20px;">NT$${price}</span>
            <span class="star_block" style="font-size: 20px;">${starHtml}</span>
          </div>
        </a>`
      }
    },
    // 分頁功能
    goToPage (page) {
      if (page !== this.currentPage && page >= 1 && page <= this.pagination.totalPages) {
        this.currentPage = page
        this.callGetAllProds()
      }
    },
    // 餐廳分類搜尋功能
    async callGetByCategory (category) {
      try {
        this.isLoading = true
        this.request.getByCategory.params.page = this.currentPage - 1
        this.request.getByCategory.params.category = category
        const response = await axios(this.request.getByCategory)
        if (!response.data || response.data.content.length === 0) {
          this.content = `<h2 style="margin-top:30px; text-align: center;">未找到任何餐廳。</h2>`
          return
        }
        this.prods = response.data.content
        this.pagination.totalPages = response.data.totalPages
        this.pagination.hasNext = !response.data.last
        this.pagination.hasPrev = !response.data.first
        // 呼叫產生產品的方法
        this.generateProductCards()
      } catch (error) {
        console.error(error)
      } finally {
        this.isLoading = false
        this.showDropdown = false
        window.scrollTo({
          top: 0,
          behavior: 'smooth'
        })
      }
    },
    // 餐廳分類選單展開
    toggleDropdown (menuIndex) {
      this['showDropdown' + menuIndex] = !this['showDropdown' + menuIndex]
    },
    // 文字搜尋功能
    async callGetBySearch (search) {
      try {
        const userInput = this.search
        this.isLoading = true
        this.request.getBySearch.params.page = this.currentPage - 1
        this.request.getBySearch.params.search = userInput
        const response = await axios(this.request.getBySearch)
        if (!response.data || response.data.content.length === 0) {
          this.content = `<h2 style="margin-top:30px; text-align: center;">未找到任何餐廳。</h2>`
          return
        }
        this.prods = response.data.content
        this.pagination.totalPages = response.data.totalPages
        this.pagination.hasNext = !response.data.last
        this.pagination.hasPrev = !response.data.first
        // 呼叫產生產品的方法
        this.generateProductCards()
      } catch (error) {
        console.error('Error fetching products:', error)
      } finally {
        this.isLoading = false
      }
    },
    // 搜尋框 Enter 事件綁定
    handleKeyUp (event) {
      if (event.keyCode === 13) {
        this.callGetBySearch(this.search)
      }
    },
    // 選擇排序方式事件綁定
    handleSortChange (event) {
      const selectedValue = event.target.value
      if (selectedValue) {
        this.callGetSortBy(selectedValue)
      }
    },
    // 商品排序功能
    async callGetSortBy (sortParam) {
      try {
        this.isLoading = true
        this.request.getSortBy.params.page = this.currentPage - 1
        this.request.getSortBy.params.sortParam = sortParam
        const response = await axios(this.request.getSortBy)
        if (!response.data || response.data.content.length === 0) {
          this.content = `<h2 style="margin-top:30px; text-align: center;">未找到任何餐廳。</h2>`
          return
        }
        this.prods = response.data.content
        this.pagination.totalPages = response.data.totalPages
        this.pagination.hasNext = !response.data.last
        this.pagination.hasPrev = !response.data.first
        // 呼叫產生產品的方法
        this.generateProductCards()
      } catch (error) {
        console.error('Error fetching products:', error)
      } finally {
        this.isLoading = false
      }
    }
  }
}
</script>

<style>
@import '../styles/main_content/mall.css';
@import '../styles/all/all.css';
</style>
