// DATA  //////////////////////////
const account1 = {
  owner: "Jonas Schmedtmann",
  movements: [200, 450, -400, 3000, -650, -130, 70, 1300],
  interestRate: 1.2,
  pin: 1111,
  movementsDates: [
    "2019-11-18T21:31:17.178Z",
    "2019-12-23T07:42:02.383Z",
    "2020-01-28T09:15:04.904Z",
    "2020-04-01T10:17:24.185Z",
    "2020-05-08T14:11:59.604Z",
    "2020-07-26T17:01:17.194Z",
    "2020-07-28T23:36:17.929Z",
    "2020-08-01T10:51:36.790Z",
  ],
  locale: "pt-PT",
  currency: "EUR",
};
const account2 = {
  owner: "Jessica Davis",
  movements: [5000, 3400, -150, -790, -3210, -1000, 8500, -30],
  interestRate: 1.5,
  pin: 2222,
  movementsDates: [
    "2019-11-01T13:15:33.035Z",
    "2019-11-30T09:48:16.867Z",
    "2019-12-25T06:04:23.907Z",
    "2020-01-25T14:18:46.235Z",
    "2020-02-05T16:33:06.386Z",
    "2020-04-10T14:43:26.374Z",
    "2020-06-25T18:49:59.371Z",
    "2020-07-26T12:01:20.894Z",
  ],
  locale: "en-US",
  currency: "USD",
};

const account3 = {
  owner: "Steven Thomas Williams",
  movements: [200, -200, 340, -20, 50, 400, -460],
  interestRate: 0.7,
  pin: 3333,
  movementsDates: [
    "2020-01-25T14:18:46.235Z",
    "2020-02-05T16:33:06.386Z",
    "2020-04-10T14:43:26.374Z",
    "2020-06-25T18:49:59.371Z",
    "2019-11-18T21:31:17.178Z",
    "2020-07-26T12:01:20.894Z",
    "2019-12-23T07:42:02.383Z",
  ],
  locale: "de-CH",
  currency: "CHF",
};
const account4 = {
  owner: "Sarah Smith",
  movements: [430, 1000, 700, 50, 90],
  interestRate: 1,
  pin: 4444,
  movementsDates: [
    "2020-01-28T09:15:04.904Z",
    "2020-04-01T10:17:24.185Z",
    "2020-05-08T14:11:59.604Z",
    "2020-07-26T17:01:17.194Z",
    "2020-07-28T23:36:17.929Z",
  ],
  locale: "de-DE",
  currency: "EUR",
};
let accounts = [account1, account2, account3, account4];
// Elements /////////////////////
const labelWelcome = document.querySelector(".welcome");
const labelDate = document.querySelector(".date");
const labelBalance = document.querySelector(".balance__value");
const labelSumIn = document.querySelector(".summary__value--in");
const labelSumOut = document.querySelector(".summary__value--out");
const labelSumInterest = document.querySelector(".summary__value--interest");
const labelTimer = document.querySelector(".timer");

const containerApp = document.querySelector(".app");
const containerMovements = document.querySelector(".movements");

const btnLogin = document.querySelector(".login__btn");
const btnTransfer = document.querySelector(".form__btn--transfer");
const btnLoan = document.querySelector(".form__btn--loan");
const btnClose = document.querySelector(".form__btn--close");
const btnSort = document.querySelector(".btn--sort");

const inputLoginUsername = document.querySelector(".login__input--user");
const inputLoginPin = document.querySelector(".login__input--pin");
const inputTransferTo = document.querySelector(".form__input--to");
const inputTransferAmount = document.querySelector(".form__input--amount");
const inputLoanAmount = document.querySelector(".form__input--loan-amount");
const inputCloseUsername = document.querySelector(".form__input--user");
const inputClosePin = document.querySelector(".form__input--pin");

const formatMovementDate = function (anyDate, locale) {
  const calcDaysPassed = function (date1, date2) {
    return Math.round(Math.abs((date2 - date1) / (1000 * 60 * 60 * 24)));
  };

  const daysPassed = calcDaysPassed(new Date(), anyDate);

  if (daysPassed === 0) {
    return "Today";
  }
  if (daysPassed === 1) {
    return "Yesterday";
  }
  if (daysPassed <= 7) {
    return `${daysPassed} days ago`;
  }
  return new Intl.DateTimeFormat(locale).format(anyDate);
};

const formatCurr = function (value, locale, currency) {
  return new Intl.NumberFormat(locale, {
    style: "currency",
    currency: currency,
  }).format(value);
};

const displayMovements = function (account) {
  containerMovements.innerHTML = "";

  account.movements.forEach(function (mov, i) {
    let type = mov > 0 ? "DEPOSIT" : "WITHDRAWAL";

    const date = new Date(account.movementsDates[i]);
    const displayDate = formatMovementDate(date, account.locale);

    const formattedMovement = formatCurr(mov, account.locale, account.currency);
    const html = `
    <div class="movements__row">
          <div class="movements__type movements__type--deposit">${
            i + 1
          } ${type}</div>
          <div class="movements__date">${displayDate}</div>
          <div class="movements__value">${formattedMovement}</div>
        </div>`;

    containerMovements.insertAdjacentHTML("afterbegin", html);
  });
};

const balanceCalcPrint = function (acco) {
  acco.balance = acco.movements.reduce(function (acc, mov) {
    return acc + mov;
  }, 0);

  labelBalance.textContent = formatCurr(
    acco.balance,
    acco.locale,
    acco.currency
  );
};

const summaryCalcPrint = function (acco) {
  const totalDeposits = acco.movements
    .filter(function (mov) {
      if (mov > 0) {
        return mov;
      }
    })
    .reduce(function (acc, mov) {
      return acc + mov;
    }, 0);
  labelSumIn.textContent = formatCurr(
    totalDeposits,
    acco.locale,
    acco.currency
  );
  const totalWithdraws = acco.movements
    .filter(function (mov) {
      if (mov < 0) {
        return mov;
      }
    })
    .reduce(function (acc, mov) {
      return acc + mov;
    }, 0);
  labelSumOut.textContent = formatCurr(
    totalWithdraws,
    acco.locale,
    acco.currency
  );
  const interest = acco.movements
    .filter(function (mov) {
      if (mov > 0) {
        return mov;
      }
    })
    .map(function (mov, i) {
      return (mov * acco.interestRate) / 100;
    })
    .filter(function (int) {
      if (int >= 1) {
        return int;
      }
    })
    .reduce(function (acc, int, i) {
      return acc + int;
    }, 0);
  labelSumInterest.textContent = formatCurr(
    interest,
    acco.locale,
    acco.currency
  );
};

const updateUI = function (acco) {
  displayMovements(acco);
  balanceCalcPrint(acco);
  summaryCalcPrint(acco);
};
/*
// Maximum
const calcMax = function (movements) {
  const max = movements.reduce(function (acc, mov) {
    if (mov > acc) {
      return mov;
    } else {
      return acc;
    }
  }, movements[0]);
  console.log(max);
};
calcMax(account1.movements);

//////////// The Map Method ////////////////
let movements = [1, 2, 3, 4, 5, 6];
const eurToUSD = 1.1;
const movementsUSD = movements.map(function (mov) {
  return mov * eurToUSD;
});
console.log(movements);
console.log(movementsUSD);

const movementsIndex = movements.map(function (mov, i) {
  return mov + i;
});
console.log(movementsIndex);

////////////// The Filter Method ////////////////////
movements = [-1, -2, 3, 8, 5, 6, -6];
const deposits = movements.filter(function (mov) {
  if (mov > 0) {
    return mov;
  }
});
const withdraws = movements.filter(function (mov) {
  if (mov < 0) {
    return mov;
  }
});
console.log(deposits);
console.log(withdraws);

//////////////////// The Reduce Method //////////////

movements = [1, -5, 9, 8, 10, -5, -2, 10, 20, -40];
const balance = movements.reduce(function (acc, curr, i, arr) {
  return acc + curr;
}, 0);
console.log(balance);

////////////// Chaining - Mixing up Map,Filter,Reduce methods////////////////////////
movements = [1, -5, 10, 56, -20, 31];
const totalDepositsUSD = movements
  .filter(function (mov) {
    if (mov > 0) {
      return mov;
    }
  })
  .map(function (mov, i, arr) {
    return 1.1 * mov;
  })
  .reduce(function (acc, mov, i) {
    return acc + mov;
  }, 0);
console.log(totalDepositsUSD);

/////////////// The Find Method ///////////////////////
accounts = [account1, account2, account3, account4];
const account = accounts.find(function (acc) {
  if (acc.owner === "Jessica Davis") {
    return acc;
  }
});
console.log(account);

//////////////   The Some Method //////////////////
movements = [1, 2, -5, 6, 84, 10, 50, 60, 75, 95, -50, -20, -14, -100];
console.log(
  movements.some(function (mov) {
    if (mov < -99.99999) {
      return mov;
    }
  })
);

///////////// The Every Method /////////////////////
movements = [1, 2, -5, 6, 84, 10, 50, 60, 75, 95, -50, -20, -14, -100];
console.log(
  movements.every(function (mov) {
    if (mov < 10) {
      return mov;
    }
  })
);
////////////// The Flat Method ///////////////////////
let accounts2 = [account1, account2, account3, account4];
const accountMovements = accounts.map(function (acc) {
  return acc.movements;
  const allMovements = accountMovements.flat();
  console.log(allMovements);
  const totalMovements = allMovements.reduce(function (accu, mov) {
    return mov + accu;
  });
}, 0);

///////////////// The Sorting Method /////////////////////
// on Strings
let arr = ["Ayush", "Rakesh", "Dalwadi", "Yash", "Dhruv"];
console.log(arr.sort());
// on Numbers
arr = [-1, 5, 9, 10, -20, -10, 50]; //Ascending Order
console.log(
  arr.sort(function (a, b) {
    if (a > b) {
      return 1;
    }
    if (a < b) {
      return -1;
    }
  })
);
///DEscending Order
console.log(
  arr.sort(function (a, b) {
    if (a > b) {
      return -1;
    }
    if (a < b) {
      return 1;
    }
  })
);

//////////////// Array.from Method --> to create array /////////////////////////

const arr2 = Array.from({ length: 5 }, function (cur, i) {
  return i + 1;
});
console.log(arr2);

// To create the above array
const arr3 = Array.from({ length: 7 }, function () {
  return 2;
});
console.log(arr3);

////////////////// Practice Questions ///////////////////////

const bankDepositSum = accounts ////Finding sum of all deposits of all accounts;
  .map(function (acc) {
    return acc.movements;
  })
  .flat(1)
  .filter(function (cur) {
    if (cur > 0) {
      return cur;
    }
  })
  .reduce(function (accum, cur) {
    return accum + cur;
  }, 0);
console.log(bankDepositSum);

/////////////////////////////////////////////////////////
*/

//
// Converting to Number
let fun;
fun = +"23";
console.log(fun);

// Checking a value is a Number or not
console.log(Number.isFinite(20));
console.log(Number.isFinite("20"));
console.log(Number.isFinite(200 / 0));
console.log(Number.isFinite(+"20"));
console.log(Number.isFinite("20dd"));

// Checking a value is Integer or not
console.log(Number.isInteger(+"20"));
console.log(Number.isInteger(20));
console.log(Number.isInteger(20 / 0));
console.log(Number.isInteger("20gg"));

// Math and Rounding
console.log(Math.max(1, 2, 3, 4, 5, 6, 7));
console.log(Math.max(1, 2, 3, "4", 5, 6, 7));
console.log(Math.min(1, 2, 3, 4, 5, 6, 7));
console.log(Math.min(1, 2, 3, "4", 5, 6, "7"));
console.log(8 ** 2);
console.log(8 ** (1 / 3));

// PI Find area of circle of given radius
console.log(Math.PI * Number.parseInt("2.99hh") ** 2);

//Random Number Genratiing
console.log(Math.random()); //Between 0 and 1
const randomNum = function (min, max) {
  //Between MAX and MIN
  const Num = Math.trunc(Math.random() * (max - min)) + min;
  console.log(Num);
};
randomNum(10, 20);

// RoudingOff to integers
console.log(Math.round(23.333));
console.log(Math.round(23.933));
console.log(Math.ceil(23.11));
console.log(Math.ceil(23.91));
console.log(Math.floor(23.158));
console.log(Math.floor(23.958));

// Remainder Operation
console.log(5 % 2);
console.log(6 % 2);
console.log(5 / 2);

// Number Seperators
console.log(2_22_300_000);
console.log(1_500);
const PI = 3.1_415;
/////// PI=3.14_15; Correct
/////// PI=3.141_5;  Correct
/////// PI=3.__14;  ERROR
console.log(PI);

// BigInt Method --> numbers
console.log(123456789n);
console.log(233n, typeof 233n);
console.log(BigInt(23));
console.log(23n * 2n);
console.log(23n + 2n);
console.log(23n - 2n);
////console.log(1000 + 2000n); //Cannot mix BigInt and other data types

// Creating Dates
let future = new Date(2037, 10, 19, 15, 23, 0);
console.log(future);
console.log(future.getFullYear());
console.log(future.getMonth());
console.log(future.getDate());
console.log(future.getHours());
console.log(future.getMinutes());
console.log(future.getSeconds());
console.log(future.toISOString());
console.log(Date.now());
future.setFullYear(2047);
future.setMonth(4);
future.setDate(30);
future.setHours(14);
future.setMinutes(23);
future.setSeconds(56);
console.log(future);
console.log(future.toISOString());

// Operations with Dates
future = new Date(2037, 3, 14);
console.log(Number(future));

// Internalisation Date and Time
const obj1 = {
  day: "numeric",
  month: "long",
  year: "numeric",
  weekday: "long",
  hour: "numeric",
  minute: "numeric",
  second: "numeric",
};
const now = new Date();
console.log(new Intl.DateTimeFormat("en-US", obj1).format(now));

////Internationalisation Number
const num = 23652.222;
const obj2 = {};
console.log(new Intl.NumberFormat("en-US", obj2).format(num));

//// setTimeout
const ingredients = ["olives", "spinach"];
const timer = setTimeout(
  function (ing1, ing2) {
    console.log(`Pizza with ${ing1} and ${ing2}`);
  },
  3000,
  "spinach",
  "olives"
);
console.log("Ayush");
if (ingredients.includes("spinach")) {
  clearTimeout(timer);
}

//// setInterval
// const now3 = new Date();
// setInterval(function () {
//   console.log(now);
// }, 5000);
///////////////////////////////////////////////////////////////

const createUsername = (accs) => {
  accs.forEach(function (acc) {
    acc.username = acc.owner
      .toLowerCase()
      .split(" ")
      .map(function (nameParts) {
        return nameParts[0];
      })
      .join("");
  });
};
createUsername(accounts); //console.log(accounts);

const startLogoutTimer = function () {
  const tick = function () {
    let min = `${Math.trunc(time / 60)}`.padStart(2, 0);
    let sec = time % 60;
    labelTimer.textContent = `${min}:${sec}`;

    if (time === 0) {
      clearInterval(logOutTimer);
      labelWelcome.textContent = "Log in to get started";
      containerApp.style.opacity = 0;
    }
    time--;
  };
  let time = 300;
  tick();
  const logOutTimer = setInterval(tick, 1000);
};

// Event Handlers
let currentAccount;

btnLogin.addEventListener("click", function (e) {
  e.preventDefault();
  console.log("LOGIN");

  currentAccount = accounts.find(function (acc) {
    if (acc.username === inputLoginUsername.value) {
      return acc;
    }
  });
  console.log(currentAccount);
  if (Number(inputLoginPin.value) === currentAccount.pin) {
    labelWelcome.textContent = `Welcome ${currentAccount.owner.split(" ")[0]}`;
    containerApp.style.opacity = 100;

    const now = new Date();
    const options = {
      date: "numeric",
      day: "numeric",
      // weekday: "long",
      year: "numeric",
      month: "numeric",
      hour: "numeric",
      min: "2-digit",
      sec: "numeric",
    };
    labelDate.textContent = new Intl.DateTimeFormat(
      currentAccount.locale,
      options
    ).format(now);
    startLogoutTimer();
    inputLoginUsername.value = inputLoginPin.value = "";
    inputLoginPin.blur();
    updateUI(currentAccount);
  }
});

btnTransfer.addEventListener("click", function (e) {
  e.preventDefault();

  const amount = Number(inputTransferAmount.value);
  const receiverAcc = accounts.find(function (acc) {
    if (acc.username === inputTransferTo.value) {
      return acc;
    }
  });

  if (
    amount > 0 &&
    amount <= currentAccount.balance &&
    receiverAcc.username !== currentAccount.username
  ) {
    currentAccount.movements.push(-amount);
    receiverAcc.movements.push(amount);

    currentAccount.movementsDates.push(new Date().toISOString());
    receiverAcc.movementsDates.push(new Date().toISOString());
    updateUI(currentAccount);
  }
  inputTransferAmount.value = inputTransferTo.value = "";
});

btnLoan.addEventListener("click", function (e) {
  e.preventDefault();

  const amount = Math.floor(inputLoanAmount.value);
  if (
    amount > 0 &&
    currentAccount.movements.some(function (mov) {
      if (mov >= 0.1 * amount) {
        return true;
      }
    })
  ) {
    setTimeout(function () {
      currentAccount.movements.push(amount);
      currentAccount.movementsDates.push(new Date().toISOString());
      updateUI(currentAccount);
    }, 2500);
  }
  inputLoanAmount.value = "";
});

btnClose.addEventListener("click", function (e) {
  e.preventDefault();
  // console.log("Delete");
  if (
    currentAccount.username === inputCloseUsername.value &&
    currentAccount.pin === Number(inputClosePin.value)
  ) {
    const index = accounts.findIndex(function (acc) {
      if (acc.username === currentAccount.username) {
        return acc;
      }
    });
    accounts.splice(index, index + 1);
    console.log(accounts);
    inputCloseUsername.value = inputClosePin.value = "";
    containerApp.style.opacity = 0;
    labelWelcome.textContent = "Log in to get started";
  }
});
labelBalance.addEventListener("click", function () {
  const movs = [...document.querySelectorAll(".movements__row")];
  movs.forEach(function (mov, i) {
    // Use of Remainder Operation
    if (i % 2 === 0) {
      //Every 2nd value
      mov.style.backgroundColor = "orangered";
    }
    if (i % 3 === 0) {
      //Every 3rd value
      mov.style.backgroundColor = "blue";
    }
  });
});
