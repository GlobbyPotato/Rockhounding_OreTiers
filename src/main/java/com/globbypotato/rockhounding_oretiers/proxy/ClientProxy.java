package com.globbypotato.rockhounding_oretiers.proxy;

import com.globbypotato.rockhounding_oretiers.ModContents;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenders() {
  		ModContents.registerRenders();
    }
}
