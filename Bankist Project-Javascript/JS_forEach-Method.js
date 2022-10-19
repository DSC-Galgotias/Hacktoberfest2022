let movements = [200, 450, -200, 3000, -500, 100];

// Using for-of loop -->We can get out of loop through break
for (const [i, movement] of movements.entries()) {
  if (movement > 0) {
    console.log(`Movement ${i + 1}: You deposited ${movement}.`);
  } else {
    console.log(`Movement ${i + 1}: You withdrew ${movement}.`);
  }
}

// Using forEach method --> We can't get out of loop before completion-Don't break
console.log("----forEach----");
movements.forEach(function (move, i, arr) {
  if (move > 0) {
    console.log(`Movement ${i + 1}: You deposited ${move}.`);
  } else {
    console.log(`Movement ${i + 1}: You withdrew ${move}.`);
  }
});
