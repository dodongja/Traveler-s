<template>
     <member-sign-up    @checkPhoneNumber="checkPhoneNumber"
                        @checkNumber="checkNumber"
                        @signUp="signUp"
                        :certification="certification"/>
</template>


<script>

import MemberSignUp from '@/components/member/MemberSignUpForm.vue'
import axios from 'axios'


export default {
        name: "SignUp",
        components: {
            MemberSignUp
        },
        data (){
            return{
                numStr:'',
                certification: false
            }
        },
        methods: {
            checkPhoneNumber(payload) {
                const mobile = payload
                console.log(mobile)
                axios.post('http://localhost:7777/check-number', {mobile})
                    .then(res => {
                        if(res.data == "이미 인증된 전화번호입니다"){
                            alert(res.data)
                        }else{
                           this.numStr = res.data
                           console.log(this.numStr)
                        }
                        })
                    
                        .catch(() => {
                        alert('오류발생')
                            })
            },
            checkNumber(payload) {
                const submitNum = payload
                if(this.numStr == submitNum){
                    alert("인증성공")
                    this.certification = true

                }else{
                    alert("다시 입력 하세요")
                }
            },
            signUp(payload){
                const { email, password, name, mobile, role } = payload
                axios.post('http://localhost:7777/user/register', {email, password, name, mobile, role})
                    .then(res => {
                            if(res.data != "가입되었습니다"){
                                alert(res.data)
                            }else{
                           alert('회원가입이 완료 되었습니다.')
                           this.$router.push({ name: 'home' })
                           }
                        })
                    
                        .catch(() => {
                        alert('오류발생')
                            })
            },
                
            
        }

}

</script>
