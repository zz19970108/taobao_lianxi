package fenlei.view;

import fenlei.bean.LeftBean;
import fenlei.bean.RightBean;
import fenlei.bean.Right_zi_Bean;

/**
 * Created by dell on 2017/12/15.
 */

public interface fenlei_view {
    void getData_left(LeftBean leftBean);
    void getData_right(RightBean rightBean);
    void getData_right_zi(Right_zi_Bean ziBean);
}