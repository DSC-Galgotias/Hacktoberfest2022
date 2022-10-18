"use strict";
let arr = ["a", "b", "c", "d", "e", "f"];

// SLICE --> does not change the original array,only extract elements from it
console.log(arr.slice(0, -1));
console.log(arr.slice(0));
console.log(arr.slice(-1));
console.log(arr.slice(-2));
console.log(arr.slice(-3));
console.log(arr.slice(0));
console.log(arr.slice(1));
console.log(arr.slice(2));
arr = ["a", "b", "c", "d", "e", "f"];

// SPLICE  --> does change the original array
console.log(arr.splice(0, 1));
console.log(arr.splice(0, 2));
console.log(arr.splice(-1));
console.log(arr.splice(0));
console.log(arr);

// REVERSE  --> does change the orignal array
arr = ["a", "b", "c", "d", "e", "f"];
console.log(arr.reverse());
console.log(arr);

// CONCAT
arr = ["a", "b", "c", "d", "e", "f"];
let arr2 = ["g", "h", "i", "j"];
console.log(arr.concat(arr2));
console.log(arr2.concat(arr));
let letters = arr.concat(arr2);
console.log(letters);

// JOIN
let str = letters.join(" ");
console.log(str);
