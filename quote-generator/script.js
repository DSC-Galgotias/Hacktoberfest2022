// selecting items
const quoteContainer = document.getElementById("quote-container");
const quoteText = document.getElementById("quote");
const authorText = document.getElementById("author");
const twitterBtn = document.getElementById("twitter");
const newQuotebtn = document.getElementById("new-quote");
const loader = document.getElementById("loader");

let apiQuotes = [];
// show loding
function loading() {
  loader.hidden = false;
  quoteContainer.hidden = true;
}
// hide loading
function complete() {
  loader.hidden = true;
  quoteContainer.hidden = false;
}

// git quotes from api
function newQuote() {
  loading();
  const quote = apiQuotes[Math.floor(Math.random() * apiQuotes.length)];
  authorText.textContent = quote.author ? quote.author : "unknown";
  quoteText.textContent = quote.text;
  complete();
  if (quoteText.text.length > 120) quoteText.classList.add("long-quote");
  else quoteText.classList.remove("long-quote");
  // set quote hide loader
  quoteText.textContent = quote.text;
}

async function getQuotes() {
  // loading();
  const apiUrl = "https://type.fit/api/quotes";
  try {
    const response = await fetch(apiUrl);
    apiQuotes = await response.json();
    newQuote();
  } catch (error) {}
}
// tweet quote
function tweetQuote() {
  const twitterUrl = `https://twitter.com/intent/tweet?text=
  ${quoteText.textContent}-${authorText.textContent}`;
  window.open(twitterUrl, "_blank");
}
// event listeners
newQuotebtn.addEventListener("click", newQuote);
twitterBtn.addEventListener("click", tweetQuote);
// onload
getQuotes();
