<template>
<div>
    <div class="select-days">
        <label>
            <input type="checkbox"  v-model="selectDays" value="0">일요일
        </label>
        <label>
            <input type="checkbox"  v-model="selectDays" value="1">월요일
        </label>
        <label>
            <input type="checkbox"  v-model="selectDays" value="2">화요일
        </label>
        <label>
            <input type="checkbox"  v-model="selectDays" value="3">수요일
        </label>
        <label>
            <input type="checkbox"  v-model="selectDays" value="4">목요일
        </label>
        <label>
            <input type="checkbox"  v-model="selectDays" value="5">금요일
        </label>
        <label>
            <input type="checkbox"  v-model="selectDays" value="6">토요일
        </label>
    </div> 

    
    <div>
        <v-radio-group v-model="room" row>
                <v-radio v-for="(roomtype, index) in rooms" :key="index" :label="`${roomtype.roomName}`" :value="roomtype.roomNo"> 
                </v-radio>
        </v-radio-group>
    </div>
    
    <div>
        가격<input type="text"  v-model="price"/>
        객실 수<input tpye="text" v-model="availableRoom"/>
    </div>

     <v-btn @click="showDatePicker" fab><v-icon x-large>mdi-calendar-multiselect</v-icon></v-btn>
        <div> <input type="text" readonly v-model="planDate" style="width: 300px" placeholder="날짜를 선택하세요" required></div>
    <v-dialog max-width="400" v-model="pickDate" light>
        <v-date-picker v-model="pickerDate" :weekday-format="getDay"
        :month-format="getMonth" :header-date-format="getMonth"
        :title-date-format="getMonth"  range></v-date-picker>
    </v-dialog>
    
    <v-btn @click=checkDays>등록</v-btn>



</div>
</template>

<script>
import axios from 'axios';
export default {
    name : "TotalSetting",
    data () {
        return {
            //datepicker
            pickerDate : [],
            pickDate:false,
            //
            room: '',
            rooms:[],
            price: '',
            availableRoom:'',
            selectDays: [],
            selectDates: [],
            allDates:[],
            yearAndMonth:[],
            availableRooms: [],
            roomSetting:[],
            settingDates:[],
            settingPrice:[],
            settingAvailableRoom:[],
            checkDate: '',
            writer: "admin@gmail.com",
            roomNo: []
        }
    },
    props: {
        dates:{
            type:Array,
             required: true
        }
    },
    methods: {
        //datepicker
    showDatePicker(){
      this.pickDate = true
    },
    getDay(date) {
        const daysOfWeek = ['일', '월', '화', '수', '목', '금', '토'];
        let i = new Date(date).getDay(date);
        return daysOfWeek[i];
    },
    getMonth(date) {
        const monthName = [
            '1월',
            '2월',
            '3월',
            '4월',
            '5월',
            '6월',
            '7월',
            '8월',
            '9월',
            '10월',
            '11월',
            '12월',
        ];
    let i = new Date(date).getMonth(date);
    return monthName[i];
    },
    checkDays () {
        for(let i = 0; i < this.dates.length; i++){
            for(let j = 0; j < this.selectDays.length; j++){
                let date = this.dates[i]

                this.allDates = [...this.allDates,date[this.selectDays[j]]]
            }
        }
        console.log(this.allDates)

        //선택한 날짜 사이에 선택한 요일인 날 구하기
        const startDay = this.pickerDate[0].substring(8,10)
        const lastDay  = this.pickerDate[1].substring(8,10)
         
        for(let i = 0; i < this.allDates.length; i++){
            if(this.allDates[i] >= startDay && this.allDates[i] <= lastDay){
                this.selectDates = [...this.selectDates, this.allDates[i]]
            }
        }
        console.log(this.selectDates)
        
        const yearAndMonth  = this.pickerDate[0].substring(0,7)

         //날짜에 방,가격,이용가능 객실 갯수 넣기
        for(let i = 0; i < this.selectDates.length; i++){
            this.settingDates[i] = yearAndMonth + "-" + this.selectDates[i]
            this.roomSetting =  this.room //방종류
            this.settingPrice =  this.price  // 방 가격
            this.settingAvailableRoom =  this.availableRoom //방 이용가능 갯수
        }
  
        const {settingDates,
            roomSetting,
            settingPrice,
            settingAvailableRoom,
            } = this

            
           
         axios.post('http://localhost:7777/calendar', {settingDates,roomSetting,settingPrice,settingAvailableRoom})
            .then(() => {
                alert('등록 되었습니다.')
                this.$router.push({
                    name: 'HotelListPage'
                })
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
  created () {
    //writer로 
    const {writer} = this
    axios.post('http://localhost:7777/roomTypeList', {writer} )
    .then((res) =>{
        console.log(res.data)
       
       for(let i = 0; i < res.data.length; i++){
        this.rooms = [...this.rooms,
             { roomName : res.data[i].roomName,
                roomNo:  res.data[i].roomNo
             }
             ]
        }
       
    })


  }

}
</script>