package com.liaojacky.springbootmenu.dto;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class CreateOrderRequest {


    @NotEmpty
    private List<BuyItem> buyItemList; // 將前端傳回的JSON參數轉換成class,巢狀JSON

    public List<BuyItem> getBuyItemList() {
        return buyItemList;
    }

    public void setBuyItemList(List<BuyItem> buyItemList) {
        this.buyItemList = buyItemList;
    }
}
