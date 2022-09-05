package com.boomlabs.checkapp.web3j;

import org.junit.jupiter.api.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.http.HttpService;

import java.util.concurrent.ExecutionException;

public class BlockNumber {

    final String MY_ETHEREUM_NODE_URL = "https://soft-greatest-shard.ethereum-goerli.discover.quiknode.pro/e44f222532feb6587cc538dcc84df02ec6e7ff56/";

    @Test
    public void getBlockNumber() throws ExecutionException, InterruptedException {
        Web3j web3 = Web3j.build(new HttpService(MY_ETHEREUM_NODE_URL));
        EthBlockNumber result  = web3.ethBlockNumber().sendAsync().get();
        System.out.println(" The Block Number is: " + result.getBlockNumber());
    }
}
