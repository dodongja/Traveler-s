<template>
  <div align="center" style="display: inline-block" class="wrap">
    <v-container>
      <table style="width: 1000px">
        <tr>
          <td align="left" colspan="2">
            <h2 class="pageTit">나의 예약 내역</h2>
          </td>
        </tr>

        <tr>
          <td align="left">
            <span class="page-count">RESERVED</span>
          </td>
          <td>
            <v-row>
              <v-spacer></v-spacer>
              <span>환불 및 위약금 규정</span>
              <payment-dialog/>
              &ensp;
            </v-row>
          </td>
        </tr>

        <tr>
          <td colspan="2">
            <v-row>
              <v-col>
                <my-bookig-list-reserved
                  :bookingLists="reserves"
                  @requestRefund="requestRefund"
                ></my-bookig-list-reserved>
              </v-col>
            </v-row>
          </td>
        </tr>
      </table>
    </v-container>
  </div>
</template>

<script>
//import MyPageLeftMenu from "@/components/mypage/MyPageLeftMenu.vue";
import axios from "axios"
import { mapActions, mapState } from "vuex";
import MyBookigListReserved from "@/components/mypage/MyBookigListReserved.vue";
import PaymentDialog from '@/components/mypage/PaymentDialog.vue';

export default {
  name: "MyPageBookingReserved",
  components: {
    //MyPageLeftMenu,

    MyBookigListReserved,
    PaymentDialog,
  },
  data() {
    return {
      status: "",
    };
  },

  computed: {
    ...mapState(["user", "reserves"]),
  },
  methods: {
    ...mapActions(["getStatusType", ""]),
    ...mapActions(["setUser", "getReserves"]),

    getStatusList(event) {
      this.status = event.target.value;

      console.log(this.status);
    },
    requestRefund(id){
      axios.post(`http://localhost:7777/payment/requestRefund/${id}`)
      .then((res) => {
        if (res){
          alert("예약 취소 성공")
          this.$router.push("/")
        } else {
          alert("예약 취소 실패")
          this.$router.push("/")
        }
      }
      )
    }
  },
  mounted() {
    this.setUser();
    this.getReserves();
  },
};
</script>

<style>
/*table, th, td{
    border-collapse:collapse;
    border: 1px solid black;
}*/

div {
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
.wrap {
  height: 100%;
}

h2 {
  padding: 50px 0px 30px 0px;
}
.pageTit {
  padding: 50px 0px 30px 0px;
  font-family: "NanumSquareRound";
  font-size: 2rem;
  line-height: 1.25;
  letter-spacing: -0.01em;
  color: #202020;
  font-weight: 900;
}
.pageTit:after {
  content: "";
  display: inline-block;
  width: 4px;
  height: 4px;
  margin-bottom: 24px;
  border-radius: 50%;
  background-color: #e63668;
}
.page-count {
  margin-left: 15px;
  border: 1px;
  border-bottom: 5px solid #e63668;
  border-radius: 5px;
  padding: 5px 10px;
  outline: none;
  color: #18225c;
  -webkit-text-stroke: 0.7px #18225c;
}
.btn {
  position: sticky;
  left: 90%;
  text-decoration: none;
}
.reservation-select {
  margin-top: 10px;
  margin-left: 30px;
  width: 200px;
  height: 30px;
  border-radius: 2pt;
  box-shadow: 0 0 0 1pt grey;
  outline: none;
  transition: 0.1s;
}
.reservationNameLabel {
  font-size: 14px;
  margin-left: 50px;
}
</style>
