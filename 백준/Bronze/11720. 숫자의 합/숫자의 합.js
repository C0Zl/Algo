const numArr = require('fs').readFileSync(0, 'utf-8').trim().split('\n')[1].split("").map(Number);
console.log(numArr.reduce((acc, n) => acc + +n, 0));