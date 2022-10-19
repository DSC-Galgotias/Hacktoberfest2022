string=''
const buttons=document.querySelectorAll('.button')
buttons.forEach(button=>{
    button.onclick=()=>{
        if(button.innerHTML== '='){
            string=eval(string)
            document.querySelector("input").value=string
        }
        else if(button.innerHTML== 'c'){
            string=''
            document.querySelector("input").value=string
        }
        else{
            string=string + button.innerHTML
            document.querySelector("input").value=string
        }
    }
})