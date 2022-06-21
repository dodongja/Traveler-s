<template>
<div class="section">
    <div class="container">
      <h2 class="subtitle has-text-centered">
        <button class="button is-small is-primary is-outlined mr-5"
        @click="calendarData(-1)">&lt;</button>
        {{ year }}년 {{ month }}월
        <button class="button is-small is-primary is-outlined ml-5"
        @click="calendarData(1)">&gt;</button>
      </h2>
    </div>
    <div>
      <table class="table has-text-centered is-fullwidth">
        <thead>
          <th v-for="(day,index) in days" :key="index">{{ day }}</th>
        </thead>
        
        <tbody>
          <tr v-for="(date, idx) in dates" :key="idx" >
            <td v-for="(day, secondIdx) in date"
                :key="secondIdx"
                :class="{'primary': day === today && month === currentMonth && year === currentYear}">
                  {{ day }}
            <div> 
              <div style="overflow-y:scroll; height:100%; width:100%">
              <div v-for="(room, id) in  availableRoomList" :key="id" >  
                <div v-if="room.useDate.substring(8,10) == day && day >= today"  > 
                 <div>{{room.roomName}} ({{room.numberOfRoom}}) </div>
                 <div>  {{room.price}} 원  </div>     
                </div>
              </div> 
              </div>
              <v-btn v-if="day != '' && day >= today && month == currentMonth"> 등록/수정</v-btn>
              <v-btn v-else-if="day != '' &&  month > currentMonth"> 등록/수정 </v-btn>
              
            </div>

           
            </td>
          </tr>
           
        </tbody>
      </table>

    </div>
  
   <router-link :to="{ name:  'TotalSetting' , params: { dates: this.dates } }">
    일괄 설정
   </router-link>

    
    
  </div>
</template>

<script>
import axios from 'axios';

export default {
    name : "HotelCalendar",
    data () {
        return{
            //달력
            days: [
            '일요일',
            '월요일',
            '화요일',
            '수요일',
            '목요일',
            '금요일',
            '토요일',
            ], 
            dates: [],
            currentYear: 0,
            currentMonth: 0,
            year: 0,
            month: 0,
            lastMonthStart: 0,
            nextMonthStart: 0,
            today: 0,
            //리스트
            availableRoomList:[],
            rooms:[],
            writer:  "admin@gmail.com"
    }
},
    
created() { // 데이터에 접근이 가능한 첫 번째 라이프 사이클
    //달력
    const date = new Date();
    this.currentYear = date.getFullYear(); // 이하 현재 년, 월 가지고 있기
    this.currentMonth = date.getMonth() + 1;
    this.year = this.currentYear;
    this.month = this.currentMonth;
    this.today = date.getDate(); // 오늘 날짜
    this.calendarData();
    console.log(this.availableRoomList)
   
    //writer로 
   
    
  },
methods: {
        //달력
    calendarData(arg) { // 인자를 추가
        if (arg < 0) { // -1이 들어오면 지난 달 달력으로 이동
            this.month -= 1;
        } else if (arg === 1) { // 1이 들어오면 다음 달 달력으로 이동
            this.month += 1;
        }
        if (this.month === 0) { // 작년 12월
            this.year -= 1;
            this.month = 12;
        } else if (this.month > 12) { // 내년 1월
            this.year += 1;
            this.month = 1;
        }
       
       const [
            monthFirstDay,
            monthLastDate,
            lastMonthLastDate,
        ] 
        = this.getFirstDayLastDate(this.year, this.month);

        this.dates = this.getMonthOfDays(monthFirstDay,monthLastDate,lastMonthLastDate,);

        
     // 10,11,12월일경우 넣어야함
        this.checkDate = this.year + "-0" + this.month
        console.log(this.checkDate)
       this.checkList(this.checkDate)
      
    },
    
    getFirstDayLastDate(year, month) {
        const firstDay = new Date(year, month - 1, 1).getDay(); // 이번 달 시작 요일
        const lastDate = new Date(year, month, 0).getDate(); // 이번 달 마지막 날짜
        let lastYear = year;
        let lastMonth = month - 1;
        if (month === 1) {
            lastMonth = 12;
            lastYear -= 1;
        }
        const prevLastDate = new Date(lastYear, lastMonth, 0).getDate(); // 지난 달 마지막 날짜
        
        return [firstDay, lastDate, prevLastDate];
    },
    getMonthOfDays(monthFirstDay,monthLastDate,prevMonthLastDate,) {
        
        let day = 1;
        let prevDay = (prevMonthLastDate - monthFirstDay) + 1;
        const dates = [];
        let weekOfDays = [];

        while (day <= monthLastDate) {
            if (day === 1) {
            // 1일이 어느 요일인지에 따라 테이블에 그리기 위한 지난 셀의 날짜들을 구할 필요가 있다
            for (let j = 0; j < monthFirstDay; j += 1) {
                if (j === 0) this.lastMonthStart = prevDay; // 지난 달에서 제일 작은 날짜
                weekOfDays.push('');
                prevDay += 1;
            }
            }
            weekOfDays.push(day);
            
            if (weekOfDays.length === 7) {
            // 일주일 채우면
            dates.push(weekOfDays);
            weekOfDays = []; // 초기화
            }

            day += 1;
        }

        const len = weekOfDays.length;
        
        if (len > 0 && len < 7) {
            for (let k = 1; k <= 7 - len; k += 1) {
            //if(k==2 || k ==1){
                weekOfDays.push('');
           // }
            }
        }
        
        
        if (weekOfDays.length > 0) dates.push(weekOfDays); // 남은 날짜 추가
        this.nextMonthStart = weekOfDays[0]; // 이번 달 마지막 주에서 제일 작은 날짜
        
        if(dates.length < 5){
            this.weekofDays = ['','','','','','','']
            dates.push(this.weekofDays)
        }
        console.log(dates)
        
        return dates;
    },
    //checkDate인 날짜만 가져옴
    checkList (checkDate) {
      const {writer} = this
      axios.post('http://localhost:7777/roomList',{checkDate, writer})
          .then((res) => {
              
                  console.log(res.data)
                this.availableRoomList = res.data

              
          })
          .catch(() => {
              alert('오류가 발생하였습니다.')
          })
    },   
  
  },
  computed: {
    planDate () {
      return this.pickerDate.join(' ~ ')
    },
  },
}
</script>

<style scoped>
table {
    position: center;
    bottom: 50px;
    left: 140px;
    border-collapse: collapse;
}
td {
    border: 1px solid black;
    width: 200px;
    height: 200px;
    text-align: center;
}
.room-setting{
    margin:50px;
}



</style>