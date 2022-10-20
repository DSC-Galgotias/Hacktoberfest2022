const canvas = document.getElementById("canvas");
const context = canvas.getContext("2d");
context.scale(100, 100);

context.fillStyle = "#FFEEEE";
context.fillRect(0, 0, canvas.width, canvas.height);

let score = 0;
let matrix = [
	[0, 0, 0, 0],
	[0, 0, 0, 0],
	[0, 0, 0, 0],
	[0, 0, 0, 0],
];
const color = [
	"#0DC2FF",
	"#0DFF72",
	"#F538FF",
	"#FF8E0D",
	"#FFE138",
	"#FF0D72",
	"lightpink",
	"#FFF",
	"#FFDE7D",
	"#3EC1D3",
	"#FFB4B4",
];

function counter(val) {
	let count = 0;
	while (val / 2 !== 1) {
		val /= 2;
		count++;
	}
	return count;
}
//refactor for draw
function drawMatrix() {
	matrix.forEach((row, y) => {
		row.forEach((val, x) => {
			if (val !== 0) {
				const index = counter(val);
				context.fillStyle = color[index];
				context.fillRect(x + 0.1, y + 0.1, 0.9, 0.9);
				context.fillStyle = "grey";
				context.font = ".4px serif";
				context.fillText(matrix[y][x], x + 0.4, y + 0.7, 0.4);
			} else {
				context.fillStyle = "#C3E5AE";
				context.fillRect(x + 0.1, y + 0.1, 0.9, 0.9);
			}
		});
	});
}
function updateScore () {
	document.getElementById('score').innerText = score;
}
function randomTile() {
	let options = [];
	for (let i = 0; i < matrix.length; i++) {
		for (let j = 0; j < matrix.length; j++) {
			if (matrix[i][j] === 0) {
				options.push({ x: i, y: j });
			}
		}
	}
	if (options.length) {
		const newTile = Math.random() < 0.9 ? 2 : 4;
		const index = options[(Math.random() * options.length) | 0];
		matrix[index.x][index.y] = newTile;
	}
}
function merge() {
	for (let i = 0; i < 4; i++) {
		for (let j = 3; j >= 1; j--) {
			if (matrix[i][j] === matrix[i][j - 1]) {
				matrix[i][j] *= 2;
				score += matrix[i][j];
				matrix[i][j - 1] = 0;
				j--;
			}
		}
	}
	updateScore();
	swipe();
}
function swipe() {
	matrix.forEach((row, i) => {
		const arr = row.filter((val) => val);
		const missing = new Array(4 - arr.length).fill(0);
		matrix[i] = missing.concat(arr);
	});
}
function isChanged(temp) {
	for (let i = 0; i < 4; i++) {
		for (let j = 0; j < 4; j++) {
			if (temp[i][j] !== matrix[i][j]) {
				return true;
			}
		}
	}
	return false;
}
function flip() {
	matrix.forEach((row) => row.reverse());
}
function rotate() {
	for (let i = 0; i < 4; i++) {
		for (let j = 3; j > i; j--) {
			[matrix[i][j], matrix[j][i]] = [matrix[j][i], matrix[i][j]];
		}
	}
	matrix.reverse();
}

function checkGameOver() {
	for (let i = 0; i < 4; i++) {
		for (let j = 0; j < 4; j++) {
			if (i !== 3 && matrix[i][j] === matrix[i + 1][j]) {
				return false;
			}
			if (j !== 3 && matrix[i][j] === matrix[i][j + 1]) {
				return false;
			}
		}
	}
	return true;
}
function checkGameWon() {
	for (let i = 0; i < 4; i++) {
		for (let j = 0; j < 4; j++) {
			if (matrix[i][j] === 2048) {
				return true;
			}
		}
	}
	return false;
}
function reset() {
	score = 0;
	updateScore();
	matrix.forEach((row) => row.fill(0));
	randomTile();
	randomTile();
	drawMatrix();
}
function matrixIsFull() {
	for (let i = 0; i < 4; i++) {
		for (let j = 0; j < 4; j++) {
			if (matrix[i][j] === 0) {
				return false;
			}
		}
	}
	return true;
}
function handleKey() {
	let temp = [];
	matrix.forEach((row) => temp.push(row));
	swipe();
	merge();

	if (checkGameWon()) {
		alert("You Won");
		reset();
	}
	if (isChanged(temp)) {
		randomTile();
		if (matrixIsFull()) {
			drawMatrix();
			if (checkGameOver()) {
				alert("Game Over");
				reset();
			}
		}
	}
}

let bool = false;
function handleEvent(e) {
	if (e.type === "keydown" && !bool) {
		bool = true;
		if (e.keyCode === 39) {
			handleKey();
			drawMatrix();
		} else if (e.keyCode === 37) {
			flip();
			handleKey();
			flip();
			drawMatrix();
		} else if (e.keyCode === 40) {
			rotate();
			handleKey();
			for (let i = 0; i < 3; i++) {
				rotate();
			}
			drawMatrix();
		} else if (e.keyCode === 38) {
			for (let i = 0; i < 3; i++) {
				rotate();
			}
			handleKey();
			rotate();
			drawMatrix();
		}
	} else if (e.type === "keyup") {
		bool = false;
	}
}

["keydown", "keyup"].forEach((eventName) => {
	document.addEventListener(eventName, (event) => {
		handleEvent(event);
	});
});
reset();