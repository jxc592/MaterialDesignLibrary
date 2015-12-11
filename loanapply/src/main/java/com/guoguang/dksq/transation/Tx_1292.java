package com.guoguang.dksq.transation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

/**
 * Created by tk on 2015/11/11.
 */
public class Tx_1292 extends BaseTx{
    Gson gson ;

    public Tx_1292() {
        super();
        gson = new GsonBuilder().create();
    }

    @Override
    public String getBody() {
        // TODO Auto-generated method stub
        HashMap<String, String> map = new HashMap<>();
        map.put("TransCode", "1292");
        return gson.toJson(map);
    }
}
