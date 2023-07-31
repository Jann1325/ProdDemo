<template>
    <header>
<!-- 設定導覽列標籤在桌機以上size展開 -->
        <nav class="navbar navbar-expand-lg" id="navbar">
            <div class="container">
               <a class="navbar-brand" href="../restaurant/Homepage.html">
                    <img src="../assets/header/logo2.png" class="logo2" />
                    <h4>C H O O E A T ！</h4>
                </a>
                <div class="function_icons order-lg-last">
                    <p class="mb-0 d-flex">
                        <a href="mall_shopping_cart.html" class="d-flex align-items-center justify-content-center mr-1 icon">
                            <img style="width: 40px; height: 40px; margin-right: 5px;" src="../assets/header/carts.png" />
                        </a>
                        <a href="../account/login.html" class="accountIcon d-flex align-items-center justify-content-center mr-1 icon">
                            <img style="width: 40px; height: 40px; margin-right: 5px;" src="../assets/header/profile.png" />
                        </a>
                        <span style="display: inline-flex; align-items: center; vertical-align: middle; margin-left: 10px;" id="sname"></span>
                    </p>
                </div>
                <!-- 這個button是漢堡選單的menu按鈕 -->
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="fa fa-bars"></span> Menu
                </button>
                <!-- 以下為header各分區的標題連結 -->
                <div class="collapse navbar-collapse" id="ftco-nav">
                    <ul class="navbar-nav d-flex align-items-center justify-content-center" style="gap: 1rem">
                        <li class="nav-item"><a href="../restaurant/Homepage.html" class="nav-link" style="margin-left: 100px;">首頁</a></li>
                        <li class="nav-item"><a href="../restaurant/Homepage.html" class="nav-link">找餐廳</a></li>
                        <li class="nav-item"><a href="/mall" class="nav-link">找餐券</a></li>
                        <li class="nav-item"><a href="../activity/activity.html" class="nav-link">揪團</a></li>
                        <li class="nav-item"><a href="aboutUs.html" class="nav-link">關於我們</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
</template>

<script>
export default {
  // Vue組件選項可以放在這裡
}
</script>

<style>
@import '../styles/header/header.css';
@import '../styles/all/all.css';
</style>
