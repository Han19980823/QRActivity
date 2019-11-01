package dianshangjinjie.bw.com.demo1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Button btn_saoma,btn_sheng;
    private EditText ed_text;
    private ImageView img_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initData();
        initView();


    }

    private void initView() {
        btn_saoma = findViewById(R.id.btn_saoma);
        btn_sheng = findViewById(R.id.btn_sheng);
        ed_text = findViewById(R.id.ed_text);
        img_view = findViewById(R.id.img_view);
        btn_saoma.setOnClickListener(this);
        btn_sheng.setOnClickListener(this);
    }


    private void initData() {
        /**
         * 打开闪光灯
         */
        CodeUtils.isLightEnable(true);

        /**
         * 关闭闪光灯
         */
        CodeUtils.isLightEnable(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_saoma:
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.btn_sheng:
                String textContent = ed_text.getText().toString();
                if (!TextUtils.isEmpty(textContent)) {
                    Bitmap  mBitmap = CodeUtils.createImage(textContent, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                    img_view.setImageBitmap(mBitmap);

                }else{
                    Toast.makeText(MainActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == 1) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
