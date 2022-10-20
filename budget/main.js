const budgetValue = document.querySelector(".budget__value");
const expenseValue = document.querySelector(".expense__value");
const balanceValue = document.querySelector(".balance__value");

const list = document.querySelector(".list");
const budgetBtn = document.querySelector(".budget__btn");
const expenseBtn = document.querySelector(".expense__btn");
const listIte = document.querySelector(".list__item");
budgetBtn.addEventListener("click", () => {
  let budgetInputValue = document.querySelector(".budget__input").value;
  if (budgetInputValue == "" || budgetInputValue < 0) {
    document.querySelector(".budget__amout").classList.add("show");
  } else {
    document.querySelector(".budget__amout").classList.remove("show");
    budgetValue.innerHTML = `${budgetInputValue} $`;
    balanceValue.innerHTML = `${budgetInputValue} $`;
  }
});
expenseBtn.addEventListener("click", () => {
  let expenseTitle = document.querySelector(".expense__name").value;
  let expenseAmout = document.querySelector(".expense__amout").value;
  if (expenseAmout == "" || expenseAmout < 0 || expenseTitle == "") {
    document.querySelector(".expense__info").classList.add("show");
  } else {
    document.querySelector(".expense__info").classList.remove("show");
    list.appendChild(createExpense(expenseTitle, expenseAmout));
    balanceValue.innerHTML = `${subExpense(expenseAmout)} $`;
    expenseValue.innerHTML = `${totalExpense(expenseAmout)} $`;
    document.querySelector(".expense__name").value = "";
    document.querySelector(".expense__amout").value = "";
  }
});
list.addEventListener("click", (e) => {
  if (e.target.classList.contains("list__icon--red")) {
      removeExpense(e.target,e.target.parentElement.parentElement.children[1].innerHTML);
    }
    if (e.target.classList.contains("list__icon--blue")) {
        editExpense(e.target.parentElement.parentElement.children[0].innerHTML,e.target.parentElement.parentElement.children[1].innerHTML)
        e.target.parentElement.parentElement.remove();
  }
});
function createExpense(title, amout) {
  let listItem = document.createElement("div");
  listItem.setAttribute("class", "list__item");
  let listItemTitle = document.createElement("p");
  listItemTitle.innerHTML = title;
  let listItemAmout = document.createElement("p");
  listItemAmout.innerHTML = amout;
  let iconDiv = document.createElement("div");
  iconDiv.setAttribute("class", "list__icon");
  let editIcon = document.createElement("i");
  editIcon.setAttribute("class", "far fa-edit list__icon--blue");
  let removeIcon = document.createElement("i");
  removeIcon.setAttribute("class", "fas fa-trash list__icon--red");
  iconDiv.appendChild(editIcon);
  iconDiv.appendChild(removeIcon);
  listItem.appendChild(listItemTitle);
  listItem.appendChild(listItemAmout);
  listItem.appendChild(iconDiv);
  return listItem;
}
function subExpense(amout) {
  let balanceValueSub = parseFloat(balanceValue.innerHTML);
  balanceValueSub -= amout;
  return balanceValueSub;
}
function totalExpense(a) {
  let expenseValueAdd = parseFloat(expenseValue.innerHTML);
  expenseValueAdd = +expenseValueAdd + +a;
  return expenseValueAdd;
}
function removeExpense(remove,amout) {
    remove.parentElement.parentElement.remove();
    balanceValue.innerHTML = `${addInBalance(amout)} $`;;
    expenseValue.innerHTML = `${subInExpense(amout)} $`;;
}
function editExpense(title,amout) {
    document.querySelector(".expense__name").value = title;
    document.querySelector(".expense__amout").value = amout;
    balanceValue.innerHTML = `${addInBalance(amout)} $`;;
    expenseValue.innerHTML = `${subInExpense(amout)} $`;;
}
function addInBalance(a) {
   let balanceValueAdd = parseFloat(balanceValue.innerHTML);
  balanceValueAdd  = +balanceValueAdd + +a;
  return balanceValueAdd;
}
function subInExpense(a) {
    let expenseValueSub = parseFloat(expenseValue.innerHTML);
  expenseValueSub -=a;
  return expenseValueSub;
}
