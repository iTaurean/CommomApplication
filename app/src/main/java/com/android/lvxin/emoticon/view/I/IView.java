package com.android.lvxin.emoticon.view.I;

import com.android.lvxin.emoticon.bean.EmoticonBean;

/**
* @ClassName: IView 
* @Description: TODO
* @author lvxin
* @date May 26, 2015 10:19:56 AM
 */
public interface IView {
    void onItemClick(EmoticonBean bean);
    
    void onItemDisplay(EmoticonBean bean);
    
    void onPageChangeTo(int position);
}
