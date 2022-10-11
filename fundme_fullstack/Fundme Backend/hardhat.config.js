require("dotenv").config();
require("@nomiclabs/hardhat-etherscan");
require("@nomiclabs/hardhat-waffle");
require("hardhat-gas-reporter");
require("solidity-coverage");
require("hardhat-deploy");

/**
 * @type import('hardhat/config').HardhatUserConfig
 */

const RINKEBY_RPC_URL=process.env.RINKEBY_RPC_URL
const PRIVATE_KEY=process.env.PRIVATE_KEY
const COINMARKETCAP_API_KEY=process.env.COINMARKETCAP_API_KEY
const ETHERSCAN_API_KEY=process.env.ETHERSCAN_API_KEY
module.exports = {
  solidity:{
    compilers:[
      {version:"0.8.8"},{version:"0.6.6"}
    ]
  },
  networks: {
    rinkeby : {
      url: process.env.RINKEBY_RPC_URL || "",
      accounts:
        process.env.PRIVATE_KEY !== undefined ? [process.env.PRIVATE_KEY] : [],
      chainId:4,
      blockConfirmations:6,
    },
  },
  gasReporter: {
    enabled: process.env.REPORT_GAS !== undefined,
    currency: "USD",
  },
  etherscan: {
    apiKey: process.env.ETHERSCAN_API_KEY,
  },
  namedAccounts:{
    deployer: {
      default: 0,
    },
    users:{
      default: 1,
    }
  }
};
