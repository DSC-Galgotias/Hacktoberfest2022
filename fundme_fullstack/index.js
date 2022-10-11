//important import

import { ethers } from "./ethers-5.6.esm.min.js";
import { abi, contractAddress } from "./constants.js";

//connection with events

document.getElementById("btnConnect").onclick = btnConnect;
document.getElementById("btnFund").onclick = fund;
document.getElementById("balanceBtn").onclick = getBalance;
document.getElementById("withdrawBtn").onclick = withdraw;


//Connect with metamask account function

async function btnConnect() {
  if (typeof window.ethereum !== "undefined") {
    await window.ethereum.request({ method: "eth_requestAccounts" });
    document.getElementById("btnConnect").innerHTML = "Connected";
    document.getElementById("btnConnect").style.backgroundColor = "red";
  } else {
    document.getElementById("btnConnect").innerHTML = "Please connect with MetaMask";
  }
}

//fund functions
async function fund() {
  const ethAmount = document.getElementById("ethAmount").value;
  if(ethAmount != ""){
      document.getElementById("toast-text").innerHTML =`Funding with ${ethAmount}....` ;
      if (typeof window.ethereum !== "undefined") {
        const provider = new ethers.providers.Web3Provider(window.ethereum);
        const signer = provider.getSigner();
        const contract = new ethers.Contract(contractAddress, abi, signer);
        try {
          const transactionsResponse = await contract.fund({
            value: ethers.utils.parseEther(ethAmount),
          });
          //listen about tx to be mined
          await listenForTransactionMine(transactionsResponse, provider);
          document.getElementById("toast-text").innerHTML ="Funded!"
        } catch (error) {
          console.log(error);
        }
      }
    }
    else {
      document.getElementById("alert-toast").style.backgroundColor = "red";
      document.getElementById("toast-text").innerHTML =`Please Enter the Amount!!` ;
    }
}


//listener for transaction being mined 

function listenForTransactionMine(transactionsResponse, provider) {
  console.log(`Mining ${transactionsResponse.hash}...`);
  return new Promise((resolve, reject) => {                                //promise to wait for completion of transaction
    provider.once(transactionsResponse.hash, (transactionsReceipt) => {
      setTimeout(()=>{
        document.getElementById("toast-text").innerHTML = `Completed with ${transactionsReceipt.confirmations} confirmations`;
      },5000);
      
      resolve();
    });
  });
}

//get balance stats

async function getBalance() {
  if (typeof window.ethereum !== "undefined") {
    const provider = new ethers.providers.Web3Provider(window.ethereum);
    const balance = await provider.getBalance(contractAddress);
    const stats= ethers.utils.formatEther(balance);
    document.getElementById("stats").innerHTML = stats;
  }
}
 
  // withdraw all transactions function
async function withdraw() {
        document.getElementById("toast-text").innerHTML = "Withdrawing...";
        if (typeof window.ethereum !== "undefined") {
          const provider = new ethers.providers.Web3Provider(window.ethereum)
          await provider.send('eth_requestAccounts', [])
          const signer = provider.getSigner()
          const contract = new ethers.Contract(contractAddress, abi, signer)
          try {
            const transactionResponse = await contract.withdraw();
            await listenForTransactionMine(transactionResponse, provider);
          } catch (error) {
            console.log(error)
          }
        }
      }
    
    
