import Web3 from "web3";
import dBlogs from './truffle/build/contracts/DPost.json'


let selectedAccount;
let isInitialized = false
let dBlogContract;

export const init = async () => {
  let provider = window.ethereum;

  if (typeof provider !== 'undefined') {

    provider.request({ method: 'eth_requestAccounts' }).then(accounts => {
      selectedAccount = accounts[0]
      console.log('selected account is' + selectedAccount);
    }).catch(err => {
      console.log(err);
      return;
    });

    window.ethereum.on('accountsChanged', function (accounts) {
      selectedAccount = accounts[0]
      console.log("Selected account is changed to " + selectedAccount);
    });
  }
  const web3 = new Web3(provider || 'http://localhost:7545');

  const networkId = await web3.eth.net.getId();

  dBlogContract = new web3.eth.Contract(dBlogs.abi, dBlogs.networks[networkId].address);

  isInitialized = true
};

export const createPost = async (props) => {

  if (!isInitialized) {
    await init()
  }

  console.log(await dBlogContract.methods.createPost(props).send({ from: selectedAccount }))

}

export const getAllPosts = async () => {
  if (!isInitialized) {
    await init()
  }
  
  const response = await dBlogContract.methods.getAllPost().call();
  if(response){
    return response
  }
 
}

export const getCount = async () => {
  if (!isInitialized) {
    await init()
  }
  
  const response = await dBlogContract.methods.getCount().call();
  if(response){
    return response
  }
 
}

export const likePost = async ({index,addr}) => {
  if (!isInitialized) {
    await init()
  }
  
    console.log(await dBlogContract.methods.likePost(index,addr).send({from: selectedAccount})); 
 
}
