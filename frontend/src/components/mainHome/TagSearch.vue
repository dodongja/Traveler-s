<template>
    <v-container style="width:100%">
        <v-col>
        <h3 class="titDep2">태그스토리</h3>
        </v-col>

        <!-- img -->
        <v-row style="width:100%" justify="center">
            <v-col cols="12" xs="12" sm="12" md="6" lg="4" xl="3" justify="center">
                <figure id="storyImg">
                    <a href="#"><img src="@/assets/tagSearchImg/tagImg1.png" width="368" height="436" /></a>
                    <div class="infoTxt">
                        <strong class="tit">읽어도 좋고 책멍도 좋다</strong>
                        <p class="txt">
                            <span>#카페투어</span>
                            <span>#북카페</span>
                            <span>#책방투어</span>
                            <span>#태그여행</span>
                        </p>
                    </div>
                </figure>
            </v-col>
            <v-col jcols="12" xs="12" sm="12" md="6" lg="4" xl="3">
                <figure id="storyImg">
                    <a href="#"><img src="@/assets/tagSearchImg/tagImg2.png" width="368" height="436" /></a>
                    <div class="infoTxt">
                        <strong class="tit">스토리가 있는 여행<br>[우리들의 블루스]</strong>
                        <p class="txt">
                            <span>#카페투어</span>
                            <span>#뷰맛집</span>
                            <span>#힐링</span>
                            <span>#태그여행</span>
                        </p>
                    </div>
                </figure>
            </v-col>
            <v-col cols="12" xs="12" sm="12" md="6" lg="4" xl="3">
                <figure id="storyImg">
                    <a href="#"><img src="@/assets/tagSearchImg/tagImg3.png" width="368" height="436" /></a>
                    <div class="infoTxt">
                        <strong class="tit">pit a PET 나와 함께하개</strong>
                        <p class="txt">
                            <span>#반려동물동반</span>
                            <span>#오션뷰</span>
                            <span>#루프탑</span>
                            <span>#타르트맛집</span>
                        </p>
                    </div>
                </figure>
            </v-col>
        </v-row>

        <!-- #info -->
        <v-col justify="center">
            <v-btn v-for="(info, idx) in hotelInfo" :key="idx" @click="search(info)" color="#f8f8f8" variant="plain"
                depressed class="button1 b-color rot-1">#{{ info }}
            </v-btn>
        </v-col>
    </v-container>
</template>

<script>

export default {
    name: 'TagSearch',
    components: {
        },
    props: {
        mHotels: {
            type: Array
        }
    },
    data () {
        return {
            hotelInfo:[
                '오션뷰',
                '테라스',
                '바베큐그릴',
                '반려동물',
                '온천',
                '수영장',
                '전기차충전',
                '공항근처',
                '골프장',
                '무료주차'
            ],
            swiperOption: {
                loop: true,
                slidesPerView: 3,
                spaceBetween: 20,
                grabCursor: true,
                pagination: {
                    el: '.swiper-pagination',
                    dynamicBullets: true
                },
            }
        }
    },
    methods : {
        search(info) {
            const word = info;
            console.log(word)
            this.$router.push({name: 'TagSearchPage',
                                    params: { word } })
                                  
        }
    },

}
</script>
<style scoped>

    @font-face {
    font-family: 'NanumSquareRound';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/NanumSquareRound.woff') format('woff');
}

body, p, h1, h2, h3, h4, h5, h6, ul, ol, li, dl, dt, dd, table, th, td, form, fieldset, legend, input, textarea, button, select, div, figure, article, header, nav, footer, section, input, textarea, button, span, strong, em {
    margin: 0;
    padding: 0;
    color: #606060;
    font-family: Pretendard,-apple-system,BlinkMacSystemFont,Open Sans,Helvetica Neue,sans-serif;
    font-weight: 400;
    line-height: 1.5;
    letter-spacing: -0.01em;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    word-break: keep-all;
    word-wrap: break-word;
    white-space: normal;
}

.button1 {
    display: inline-block;
    position: relative;
    border-radius: 20px;
    text-decoration: none;
    margin: 0.7em;
    font-size: 1.0em;
    font-weight: 500;
    font-family: Pretendard,-apple-system,BlinkMacSystemFont,Open Sans,Helvetica Neue,sans-serif;;
    color: #404040;
}
.justify-center {
    margin: 1px;
    padding: 20px;
    width: 700px;
}
.v-divider {
    margin: 1px;
    padding: 5px;
}

.section{
    margin: 2%;
}
#TagName {
    justify-content: center;
}

    .rentcarMainContests .titDep2 {
    margin-top: 104px;
    text-align: center;
}

    .titDep2 {
    margin-bottom: 32px;
    font-size: 1.6rem;
    line-height: 1.3;
    font-family: NanumSquareRound;
    font-weight: 900;
}

    [class^=titDep] {
    position: relative;
    color: #202020;
    line-height: 1;
}

    img {
    border-radius: 15px;
    width:368px;
    height:436px;
}




    /*.tagImg:hover img {
  transform: scale(1.1);
}*/

    figure {
    position: relative;
    width: 100%;
    display: block;
    height: 436px;
}

    a:after {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-image: -webkit-gradient(linear, left top, left bottom, color-stop(48%, rgba(0,0,0,0)), to(#000));
    background-image: linear-gradient(to bottom, rgba(0,0,0,0) 48%, #000);
    opacity: .8;
    border-radius: 16px;
}

    .infoTxt {
    position: absolute;
    bottom: 36px;
    left: 0;
    z-index: 1;
    padding: 0 32px;
}

    .infoTxt .tit {
    display: block;
    margin-bottom: 11px;
    color: #fff;
    font-weight: 700;
    font-size: 1.2rem;
    line-height: 1.4;
    text-align: left;
}

    .infoTxt .txt {
    display: block;
    color: #F8F8F8;
    opacity: .7;
}

    .infoTxt .txt>span {
    margin-right: 5px;
    color: #F8F8F8;
}
#storyImg {
    width:368px;
    height:436px;
    margin: 15px 0;
}

</style>
