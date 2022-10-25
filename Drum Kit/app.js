const express = require('express');
const ejs = require('ejs');
const app = express();

app.set('view engine', 'ejs');

app.use(express.urlencoded({extended: true}));
app.use(express.static(__dirname + "/public"));

app.get("/", (req, res)=>{
    res.render("index");
});

let port = process.env.PORT;
if (port == null || port == "") {
  port = 8000;
}
app.listen(port, function () {
  console.log("Server has started successfully at port 8000.");
});

