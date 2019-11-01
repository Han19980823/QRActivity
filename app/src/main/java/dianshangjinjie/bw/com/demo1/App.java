package dianshangjinjie.bw.com.demo1;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * 作者：Han98
 * 创建时间：2019/11/1
 * 描述：TODO
 * 最近修改：2019/11/1 18:52 modify by liujc
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initZxing();
    }

    private void initZxing() {
        ZXingLibrary.initDisplayOpinion(this);
    }
}
