package gouwuche.presenter;

import gouwuche.IView.IFragment03;
import gouwuche.model.CartModel;
import gouwuche.model.ICartModel;

/**
 * Created by dell on 2017/12/16.
 */

public class CartPresenter implements ICartModel{

    private final CartModel cartModel;
    private IFragment03 iFragment03;

    public CartPresenter(IFragment03 iFragment03) {
        this.iFragment03 = iFragment03;
        cartModel=new CartModel(this);
    }

    public void getCartData(String cartUrl) {
        cartModel.getCartData(cartUrl);
    }

    @Override
    public void getSuccessCartJson(String json) {
        //回调给view
        iFragment03.getSuccessCartData(json);
    }
}