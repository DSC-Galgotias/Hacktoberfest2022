const weightInput=document.querySelector('.Weight')
const heightInput=document.querySelector('.Height')
// const calculate=()=>{
    const Weight=Number(weightInput.value)
    console.log(Weight)
    const Height=Number(heightInput.value)
    const Result=Number((Weight*Weight) /Height)
     console.log(`${Result}`)
     document.querySelector('.result').value=`${Result}`
// }
// calculate()