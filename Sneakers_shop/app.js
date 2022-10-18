const wrap=document.querySelector(".sliderWrepper");
const menuItm=document.querySelectorAll(".menuItem");
const products = [
  {
    id: 1,
    title: "Superstar",
    price: 6999,
    colors: [
      {
        code: "maroon",
        img: "./images/superstar.png",
      },
      {
        code: "black",
        img: "./images/superstar2.png",
      },
    ],
  },
  {
    id: 2,
    title: "NMD",
    price: 4999,
    colors: [
      {
        code: "black",
        img: "./images/NMD.png",
      },
      {
        code: "olivedrab",
        img: "./images/nmd2.png",
      },
    ],
  },
  {
    id: 3,
    title: "Air Jordan",
    price: 8999,
    colors: [
      {
        code: "crimson",
        img: "./images/aj1.png",
      },
      {
        code: "skyblue",
        img: "./images/aj2.png",
      },
    ],
  },
  {
    id: 4,
    title: "Yeezy 350",
    price: 5999,
    colors: [
      {
        code: "coral",
        img: "./images/yeezy.png",
      },
      {
        code: "black",
        img: "./images/yeezy2.png", 
      },
    ],
  },
  
];
let choosenProduct=products[0];

const currentProductImg=document.querySelector(".productImg");
const currentProductTitle=document.querySelector(".productTitle");
const currentProductPrice=document.querySelector(".price");
const currentProductColors=document.querySelectorAll(".color");
const currentProductSize=document.querySelectorAll(".size");


menuItm.forEach((item,index)=>{
  item.addEventListener("click",()=>{
    //chenging the slide
           wrap.style.transform= `translateX(${-100*index}vw)`;
    //amnd the broduct below on click
    choosenProduct=products[index]
    //change details of product
    currentProductTitle.textContent=choosenProduct.title
    currentProductPrice.textContent="₹"+choosenProduct.price

    currentProductImg.src=choosenProduct.colors[0].img
    //changing the colors
    currentProductColors.forEach((color,index)=>{
      color.style.backgroundColor=choosenProduct.colors[index].code;
    });

  });
});
//change the images as colors
currentProductColors.forEach((color,index)=>{
  color.addEventListener("click",()=>{
    currentProductImg.src=choosenProduct.colors[index].img;
  });
});

currentProductSize.forEach((size,index)=>{
  size.addEventListener("click",()=>{
    currentProductSize.forEach((size)=>{
      size.style.backgroundColor="white";
      size.style.color="black";
    });
    size.style.backgroundColor="black";
    size.style.color="white";
  });
});
