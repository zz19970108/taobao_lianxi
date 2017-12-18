package bwie.com.taobao_lianxi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import register.bean.Bean;
import register.presenter.RegPresenter;
import register.view.RegView;

public class RegisterActivity extends AppCompatActivity implements RegView{

    private EditText phone;
    private EditText et_pwd;
    private ImageView iv_reg;
    private ImageView iv_back;
    private RegPresenter regPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        phone = (EditText) findViewById(R.id.et_phone);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        iv_reg = (ImageView) findViewById(R.id.iv_reg);
        iv_back = (ImageView) findViewById(R.id.iv_back);

        regPresenter=new RegPresenter(this);

        iv_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regPresenter.setRegView(new Bean(phone.getText().toString(),et_pwd.getText().toString()));
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void RegSuccess() {
        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void RegFailed() {
        Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_LONG).show();
    }
}
