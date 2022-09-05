const Web3 = require("web3");
const fs = require('fs');
const path = require('path');
const confPath = path.join(__dirname, './config');

const infoJson = JSON.parse(fs.readFileSync(path.join(confPath, './wallet-info.json'), 'utf-8'));
const contractJson = JSON.parse(fs.readFileSync(path.join(confPath, './CheckCheckABI.json'), 'utf-8'));
const contractAddress = infoJson.contractAddress;
const fromAddress = infoJson.fromAddress;
const privateKey = infoJson.privateKey;
const httpProvider = infoJson.alchemy.HTTPS; // alchemy https url


module.exports.safeMint = async function (user_address_list, token_uri) {
    console.log("txApp.js Start!!!");
    console.log("user_address_list: ", user_address_list);
    console.log("token_uri: ", token_uri);
    const web3 = new Web3(httpProvider);
    const _contract = await new web3.eth.Contract(contractJson, contractAddress);
    const data = _contract.methods.safeMint(
        user_address_list,
        token_uri
        );

    const transaction = {
        chainId: await web3.eth.getChainId(),
        to: contractAddress,
        gasLimit: 1000000,
        value: await web3.utils.toHex(web3.utils.toWei('0.01', 'ether')),
        from: fromAddress,
        data: data.encodeABI()
    };
    
    const signedTx = await web3.eth.accounts.signTransaction(transaction, privateKey);
    const receipt = await web3.eth.sendSignedTransaction(signedTx.rawTransaction);
    const transactionHash = receipt.transactionHash;

    console.log("Transaction Hash : ", transactionHash);
}