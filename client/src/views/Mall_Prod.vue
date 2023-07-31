<template>
<div>
<Header />
    <div id="product-details" class="main_content">
        <div class="container">
            <div class="row">
                <div class="breadcrumbandSearch">
                    <nav class="breadcrumbandSearch" style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="../restaurant/Homepage.html">主頁</a></li>
                            <li class="breadcrumb-item"><a href="/mall">找餐券</a></li>
                            <li id="breadcrumb-page" class="breadcrumb-item active" aria-current="page" v-html="breadcrumbPage"></li>
                        </ol>
                    </nav>
                <div class="search_area">
                    <input type="text" id="search_text" class="search_text" name="search"> <input type="hidden" name="action" value="getByCompositeQuery">
                    <input type="submit" id="search_button" class="search_button" value="">
                </div>
            </div>
            <div class="parent_container">
                <main class="main">
                    <div class="prodpicArea">
                        <img id="prodpic" :src="prodpic">
                    </div>
                    <div class="prod">
                        <div id="prodInfor" v-html="prodInfor"></div>
                        <div id="map"></div>
                    </div>
                    <div class="prod" id="prodText" v-html="prodText"></div>
                    <div class="prod" id="prodUserguide" v-html="prodUserguide"></div>
                    <div class="prod">
                        <span class="prodTitle">商品評價</span> <br />
                        <div class="star_area">
                            <span id="star_score" class="star_score" v-html="star_score"></span>
                            <span class="star_block">
                                <span class="star" data-star="1"><i class="fas fa-star"></i></span>
                                <span class="star" data-star="2"><i class="fas fa-star"></i></span>
                                <span class="star" data-star="3"><i class="fas fa-star"></i></span>
                                <span class="star" data-star="4"><i class="fas fa-star"></i></span>
                                <span class="star" data-star="5"><i class="fas fa-star"></i></span>
                            </span>
                            <div class="star_filter">
                                <button class="star_filter_all" onclick="filterComments('all')">全部</button>
                                <button class="star_filter_all" onclick="filterComments(5)">五星</button>
                                <button class="star_filter_all" onclick="filterComments(4)">四星</button>
                                <button class="star_filter_all" onclick="filterComments(3)">三星</button>
                                <button class="star_filter_all" onclick="filterComments(2)">二星</button>
                                <button class="star_filter_all" onclick="filterComments(1)">一星</button>
                            </div>
                    </div>
                    <br />
                    <div class="comment" id="comment" v-html="comment"></div>
                    </div>
                </main>
                <aside class="aside">
                    <div class="pay_area">費用詳情</div>
                    <hr />
                    <ul class="price_list">
                        <li>單人｜平日晚餐｜ <span id="price" v-html="price"></span></li>
                    </ul>
                    <br />
                    <input type="button" id="pay_immediately" class="pay_immediately" value="立即購買" />
                    <input type="button" id="add_cart" class="add_cart" value="加入購物車" />
                </aside>
            </div>
        </div>
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
        getProdDetail: {
          method: 'GET',
          url: '/prod/Prod/details',
          params: {
            id: this.$route.params.id
          }
        }
      },
      prod: [],
      id: '',
      content: '',
      breadcrumbPage: '',
      prodpic: '',
      prodInfor: '',
      prodText: '',
      prodUserguide: '',
      star_score: 0,
      price: '',
      comment: ''
    }
  },
  created () {
    this.callGetProdDetail()
  },
  methods: {
    async callGetProdDetail () {
      const prodId = this.$route.params.id
      const response = await axios.get(`/prod/Prod/details?id=${prodId}`)
      const prod = response.data.prod[0]
      const orderDetails = response.data.orderDetails
      const price = prod.prodPrice.toLocaleString()
      //   const star = Math.floor(prod.prodCommentScore)
      // 圖片
      var base64String = prod.prodPic
      var binaryString = atob(base64String)
      var len = binaryString.length
      var bytes = new Uint8Array(len)
      for (let i = 0; i < len; ++i) {
        bytes[i] = binaryString.charCodeAt(i)
      }
      var blob = new Blob([bytes], { type: 'image/jpeg' })
      var imageUrl = URL.createObjectURL(blob)
      this.prodpic = `${imageUrl}`
      //   麵包屑
      this.breadcrumbPage = `${prod.resName} | ${prod.prodName}`
      // 商品詳情
      this.prodInfor = `
      <span id="productName" class="prodTitle">${prod.resName} | ${prod.prodName}</span>
      <br />
      <div class="type">${prod.resType}</div>
      <div class="add">${prod.resAdd}</div>`
      // 餐券詳情
      this.prodText = `
      <span class="prodTitle">餐券詳情</span>
      <br />
      <ul>
      <li>${prod.prodText}</li>
      </ul>`
      // 如何使用
      this.prodUserguide = `
      <span class="prodTitle">如何使用</span>
      <br />
      <ul>
      <li>${prod.prodUserguide}</li>
      </ul>
      `
      // 商品星等
      this.star_score = `
      ${prod.prodCommentScore} <span style="font-size: 18px;">/ 5</span>
      `
      // 評論
      if (orderDetails.length === 0) {
        this.star_score = `0 <span style="font-size: 18px;">/ 5</span>`
        this.comment = `<h2 style="margin-top:30px; text-align: center;">尚未有任何評論。</h2>`
      } else {
        for (let orderDetail of orderDetails) {
          const star = Math.floor(orderDetail.prodCommentScore)
          let starHtml = ''
          for (let i = 1; i <= 5; i++) {
            if (i <= star) {
              starHtml += `<span class="star -on" data-star="${i}" style="padding-right:3px;"><i class="fas fa-star"></i></span>`
            } else {
              starHtml += `<span class="star" data-star="${i}" style="padding-right:3px;"><i class="fas fa-star"></i></span>`
            }
          }
          if (orderDetail.prodCommentScore !== 0) {
            this.comment += `
        <div class="acc_profiles">
          <div class="acc_photo"></div>
          <div class="nameandStar">
            <div class="acc_name">${orderDetail.accName}</div>
            <div class="dot3">
              <input type="submit" id="more_button" class="more" value="" data-order-detail-id="${orderDetail.orderDetailId}">
            </div>
            <br />
            <div class="commemt_star_block">
              ${starHtml}
            </div>
          </div>
        </div>
        <div class="comment_area">
        
          <div class="comment_text">
            ${orderDetail.prodCommentText}
          </div>
        </div>
        <hr class="comment_hr" style="border: 1.3px solid; margin-bottom:15px;" />
      `
          }
        }
      }
      // 價格
      this.price = ` NT $${price}`
      this.googleMap(prod.resAdd)
    },
    async googleMap (address) {
    }
  },
  formatTimestamp (timestampString) {
    var timestamp = new Date(timestampString)
    var year = timestamp.getFullYear()
    var month = timestamp.getMonth() + 1
    var day = timestamp.getDate()
    var hours = timestamp.getHours()
    var minutes = timestamp.getMinutes()
    if (minutes < 10) {
      minutes = '0' + minutes
    }
    var formattedTimestamp = year + '年' + month + '月' + day + '日 ' + hours + ':' + minutes
    return formattedTimestamp
  }
}

</script>

<style>
@import '../styles/main_content/mall_prod.css';
@import '../styles/all/all.css';
</style>
