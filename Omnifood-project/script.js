// Make mobile navigation work

const btnNavEl = document.querySelector(".nav-btn");
const headerEl = document.querySelector(".header");

btnNavEl.addEventListener("click", function () {
  headerEl.classList.toggle("nav-open");
});

//Make Navigation Sticky
